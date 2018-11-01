package com.neuedu.sell.repository;

import com.neuedu.sell.entity.OrderMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface OrderMasterRepositoty extends JpaRepository<OrderMaster,String> {

    Page<OrderMaster> findByBuyerOpenid(String openId, Pageable pageable);
}
