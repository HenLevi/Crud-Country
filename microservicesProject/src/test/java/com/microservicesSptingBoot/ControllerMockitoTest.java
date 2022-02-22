package com.microservicesSptingBoot;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.microservicesSptingBoot.controller.CountryController;
import com.microservicesSptingBoot.model.Country;
import com.microservicesSptingBoot.services.CountryService;

@TestMethodOrder(OrderAnnotation.class)
@SpringBootTest(classes= {ControllerMockitoTest.class})
public class ControllerMockitoTest {
	@Mock
	 CountryService countryservice;

	
    @InjectMocks
    CountryController countrycontroller;
    
    List <Country> countries;
    Country country;
    
    @Test
    @Order(1)
    public void test_getCountries() {
    	countries=new ArrayList<Country>();
    	countries.add(new Country(1,"Israel","Jerusalem"));
    	countries.add(new Country(2,"UK","London"));
    	
    	when(countryservice.getCountries()).thenReturn(countries);
    	ResponseEntity<List<Country>> res=countrycontroller.getCountries();
    	assertEquals(HttpStatus.FOUND,res.getStatusCode());
    	
    }
    
    @Test
    @Order(2)
    public void test_getCountryById() {
    	int countryId=1;
    	country= new Country(1,"Israel","Jerusalem");
    	when(countryservice.getCountryById(countryId)).thenReturn(country);
    	ResponseEntity<Country> res=countrycontroller.getCountryById(countryId);
    	assertEquals(HttpStatus.OK,res.getStatusCode());
    	assertEquals(countryId,res.getBody().getId());

    }
   
    @Test
    @Order(3)
    public void test_getCountryByName() {
    	country= new Country(2,"UK","London");
    	String countryName="UK";
    	when(countryservice.getCountryByName(countryName)).thenReturn(country);
    	ResponseEntity<Country> res=countrycontroller.getCountryByName(countryName);
    	assertEquals(HttpStatus.OK,res.getStatusCode());
    	assertEquals(countryName,res.getBody().getCountryName());
    }
    
    @Test
    @Order(4)
    public void test_addcountry() {
    	country= new Country(3,"Germany","Berlin");
    	when(countryservice.addNewCountry(country)).thenReturn(country);
    	ResponseEntity<Country> res=countrycontroller.addCountry(country);
    	assertEquals(HttpStatus.CREATED,res.getStatusCode());
    	assertEquals(country,res.getBody());
    }
    
    
    @Test
    @Order(5)
    public void test_updateCountry() {
    	country= new Country(2,"Japan","Tokyo");
    	int countryId=2;  	
    	when(countryservice.getCountryById(countryId)).thenReturn(country);   	
    	ResponseEntity<Country> res=countrycontroller.updateCountry(countryId,country);
    	assertEquals(HttpStatus.OK,res.getStatusCode());
    }
    
    @Test
    @Order(6)
    public void test_deleteCountry() {
    	country= new Country(3,"Japan","Tokyo");
    	int countryId=3;
    	when(countryservice.getCountryById(countryId)).thenReturn(country);
    	ResponseEntity<Country> res=countrycontroller.deleteCountry(countryId);
    	assertEquals(HttpStatus.OK,res.getStatusCode());

    }
    
    
}
