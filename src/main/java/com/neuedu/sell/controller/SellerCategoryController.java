package com.neuedu.sell.controller;

import com.neuedu.sell.entity.ProductCategory;
import com.neuedu.sell.entity.ProductInfo;
import com.neuedu.sell.enums.ProductStatusEnum;
import com.neuedu.sell.enums.ResultEnum;
import com.neuedu.sell.exception.SellException;
import com.neuedu.sell.form.CategoryForm;
import com.neuedu.sell.form.ProductForm;
import com.neuedu.sell.service.ProductCategoryService;
import com.neuedu.sell.service.ProductInfoService;
import com.neuedu.sell.utils.KeyUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Calendar;
import java.util.List;

@Controller
@RequestMapping("/seller/category")
public class SellerCategoryController {


    @Autowired
    private ProductCategoryService productCategoryService;


    @GetMapping("/list")
    public ModelAndView list(){
        List<ProductCategory> productCategoryList = productCategoryService.findAll();
        ModelAndView model = new ModelAndView("category/list");
        model.addObject("productCategoryList", productCategoryList);
        return model;
    }



    @GetMapping("/index")
    public ModelAndView index(@RequestParam(value = "categoryId", required = false)Integer categoryId){
        ModelAndView model = new ModelAndView("category/index");
        //不为空则为修改
        if (!StringUtils.isEmpty(categoryId)){
            ProductCategory productCategory = productCategoryService.findOne(categoryId);
            model.addObject("productCategory", productCategory);
        }
        return model;
    }


    @PostMapping("/save")
    public ModelAndView save(CategoryForm categoryForm){
        //判断是新增还是修改
        if (StringUtils.isEmpty(categoryForm.getCategoryId())){
            //新增
            ProductCategory productCategory = new ProductCategory();
            BeanUtils.copyProperties(categoryForm, productCategory);
            productCategoryService.save(productCategory);
        }else {
            //修改
            ProductCategory productCategory = productCategoryService.findOne(categoryForm.getCategoryId());
            BeanUtils.copyProperties(categoryForm, productCategory);
            productCategoryService.save(productCategory);
        }



        ModelAndView model = new ModelAndView("redirect:list");

        return model;
    }
}
