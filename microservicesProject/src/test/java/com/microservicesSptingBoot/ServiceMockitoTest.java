package com.microservicesSptingBoot;



import java.util.ArrayList;
import java.util.List;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.springframework.boot.test.context.SpringBootTest;
import com.microservicesSptingBoot.dao.CountryDao;
import com.microservicesSptingBoot.model.Country;
import com.microservicesSptingBoot.services.CountryService;
import static org.junit.Assert.assertEquals;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;


@TestMethodOrder(OrderAnnotation.class)
@SpringBootTest(classes= {ServiceMockitoTest.class})

public class ServiceMockitoTest {
	
	@Mock
	CountryDao countryRep;

	
    @InjectMocks
    CountryService Countryservice;
    
    public List<Country> countries;
    @Test
    @Order(1)
    public void test_getCountries() {
   
    	List<Country> countries=new ArrayList<Country>();
    	countries.add(new Country(1,"Israel","Jerusalem"));
    	countries.add(new Country(2,"UK","London"));
    	when(countryRep.findAll()).thenReturn(countries);//Mocking
    	
    	assertEquals(2,Countryservice.getCountries().size());
    	
    }
    
    @Test
    @Order(2)
    public void test_getCountryById() {
    	
    	int countryId=1;
    	List<Country> countries=new ArrayList<Country>();
    	countries.add(new Country(1,"Israel","Jerusalem"));
    	countries.add(new Country(1,"UK","London"));
    	when(countryRep.findAll()).thenReturn(countries);//Mocking 	
    	assertEquals(countryId,Countryservice.getCountryById(countryId).getId());
    	
    }
    
    @Test
    @Order(3)
    public void test_getCountryByName() {
    	
    	String countryName="Israel";
    	List<Country> countries=new ArrayList<Country>();
    	countries.add(new Country(1,"Israel","Jerusalem"));
    	countries.add(new Country(1,"UK","London"));
    	when(countryRep.findAll()).thenReturn(countries);//Mocking 	
    	assertEquals(countryName,Countryservice.getCountryByName(countryName).getCountryName());
    	
    }
    
    @Test
    @Order(4)
    public void test_addNewCountry() {
    	
    Country country = new Country(3,"Germany","Berlin")	;
	when(countryRep.save(country)).thenReturn(country);
	assertEquals(country,Countryservice.addNewCountry(country));
 	
    }
    @Test
    @Order(5)
    public void test_updateCountry() {
    	
    Country country = new Country(3,"Germany","Berlin")	;
	when(countryRep.save(country)).thenReturn(country);
	assertEquals(country,Countryservice.updateCountry(country));
    }
    
    @Test
    @Order(6)
    public void test_deleteCountry() {
    	
    Country country = new Country(3,"Germany","Berlin")	;
	Countryservice.deleteCountry(country);
	verify(countryRep,times(1)).delete(country);
    }
      
    
}
