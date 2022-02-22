package com.microservicesSptingBoot.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microservicesSptingBoot.model.Country;

public interface CountryDao extends JpaRepository<Country,Integer> {

}
