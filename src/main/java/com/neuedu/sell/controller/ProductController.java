package com.neuedu.sell.controller;

import com.neuedu.sell.VO.ProductCategoryVO;
import com.neuedu.sell.VO.ProductInfoVO;
import com.neuedu.sell.VO.ResultVO;
import com.neuedu.sell.entity.ProductCategory;
import com.neuedu.sell.entity.ProductInfo;
import com.neuedu.sell.service.ProductCategoryService;
import com.neuedu.sell.service.ProductInfoService;
import com.neuedu.sell.service.impl.ProductInfoServiceImpl;
import com.neuedu.sell.utils.ResultVOUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/buyer/product")
public class ProductController {

    @Autowired
    private ProductInfoService productInfoService;

    @Autowired
    private ProductCategoryService productCategoryService;

    @RequestMapping("/list")
    public ResultVO list() {

        //1.查询所有上架商品
        List<ProductInfo> productInfoList = productInfoService.findUpAll();
        //根据商品列表构建一个商品类别编号的集合
        List<Integer> categoryTypelist = new ArrayList<>();
        for (ProductInfo info : productInfoList) {
            categoryTypelist.add(info.getCategoryType());
        }
        //3.根据商品查询对应的类别
        List<ProductCategory> productCategoryList = productCategoryService.findByCategoryTypeIn(categoryTypelist);

        //4.数据拼装
        //1.将原数据集合转化成VO集合
        List<ProductCategoryVO> productCategoryVOList = new ArrayList<>();
        for (ProductCategory productCategory : productCategoryList) {
            //构建VO对象
            ProductCategoryVO productCategoryVO = new ProductCategoryVO();
            //将原对象的数据赋值到VO对象中
            BeanUtils.copyProperties(productCategory, productCategoryVO);

            List<ProductInfoVO> productInfoVOList = new ArrayList<>();
            //将商品元数据转化成商品VO集合
            for (ProductInfo info : productInfoList) {
                if (info.getCategoryType().equals(productCategory.getCategoryType())) {
                    ProductInfoVO productInfoVO = new ProductInfoVO();
                    BeanUtils.copyProperties(info, productInfoVO);
                    productInfoVOList.add(productInfoVO);
                }
            }
            //将转化好的商品VO集合设置到类别中
            productCategoryVO.setProductInfoVOList(productInfoVOList);
            //将VO对象放到集合中
            productCategoryVOList.add(productCategoryVO);
        }


        return ResultVOUtils.success(productCategoryVOList);

    }

}
