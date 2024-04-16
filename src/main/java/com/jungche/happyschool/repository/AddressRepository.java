package com.jungche.happyschool.repository;

import com.jungche.happyschool.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Integer> {
}
