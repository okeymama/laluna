package com.universe.laluna.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.universe.laluna.entity.Customer;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, Long>{

}
