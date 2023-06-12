package com.inventoryms.ims.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inventoryms.ims.models.AccountsPayable;

@Repository
public interface AccountsPayableRepository extends JpaRepository<AccountsPayable, Long> {

}
