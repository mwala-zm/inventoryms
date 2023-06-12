package com.inventoryms.ims.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inventoryms.ims.models.OrderProcessing;

@Repository
public interface OrderProcessingRepository extends JpaRepository<OrderProcessing, Long> {

}
