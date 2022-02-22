package com.microservicesSptingBoot.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.microservicesSptingBoot.controller.AddResponse;
import com.microservicesSptingBoot.dao.CountryDao;
import com.microservicesSptingBoot.model.Country;
@Component
@Service
public class CountryService {
	

	@Autowired 
	CountryDao countryRep;

     
     
     public List<Country> getCountries() {
    	 List<Country> countries=countryRep.findAll();
    	return countries;
     }
     
     public Country getCountryById(int id) {
     List<Country>	countries=countryRep.findAll();
     Country country=null;
   //   for(Country con:countries)
   //   {
   // 	  if(con.getId()==id)
   // 		  country=con;
   //   }
     
     country = countries.stream()
    		 .filter(countryId->(Integer.toString(id)).equals(Integer.toString(countryId.getId())))
    		  .findAny()
    		  .orElse(null);
     
     
      return country;
      
      
     }
     
     public Country getCountryByName(String countryName) {
       List<Country> countries=countryRep.findAll();
       Country country=null;
      // for(Country con:countries) {
    //	   if (con.getCountryName().equalsIgnoreCase(countryName)){
    //		   country=con;
    //	   }
     //  }
       
       country=countries.stream()
    		   .filter(countryNamefilter->countryName.equals(countryNamefilter.getCountryName()))
    		   .findAny()
    		   .orElse(null);
    		   
       
       
       return country;
       
     }
     
     
   public Country addNewCountry( Country country) {
   country.setId(getMaxId());
   countryRep.save(country);
   return country;
     }
   //util method to get max id
   public  int getMaxId() {
	  return countryRep.findAll().size()+1;
	   
   }
   
   public Country updateCountry( Country country) {
	   countryRep.save(country);
	   return country;
     }
   
   public void deleteCountry( Country  country) {
	   countryRep.delete(country);
     }
   
     
     
     
}
