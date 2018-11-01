package com.neuedu.sell.service;

import com.neuedu.sell.dto.CartDTO;
import com.neuedu.sell.entity.OrderDetail;
import com.neuedu.sell.entity.ProductInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductInfoService {

    /**
     * 查询所有在架上坪
     * @return
     */
    List<ProductInfo> findUpAll();

    /**
     * 查询所有商品，包含分页
     * @param pageable
     * @return
     */
    Page<ProductInfo> findAll(Pageable pageable);

    /**
     * 根据id查询
     * @param productInfoId
     * @return
     */
    ProductInfo findOne(String productInfoId);

    /**
     * 增加或修改
     */

    ProductInfo save(ProductInfo productInfo);


    /**
     * 扣库存
     */
    void decreaseStock(List<CartDTO> cartDTOList);

}
