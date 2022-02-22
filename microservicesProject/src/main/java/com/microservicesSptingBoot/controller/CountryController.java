package com.microservicesSptingBoot.controller;



import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.microservicesSptingBoot.model.Country;
import com.microservicesSptingBoot.services.CountryService;

@RestController

public class CountryController {
	
	
	@Autowired 
	CountryService countryService;
	
	@GetMapping("/getCountries")
	 public ResponseEntity<List<Country>> getCountries() {
		
		try {
			List <Country> countries=countryService.getCountries();
			return new ResponseEntity<List<Country>>(countries,HttpStatus.FOUND);
			}
		catch(Exception e){
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);	
			}
		 }

	
	
	@GetMapping("/getCountries/{id}")
	 public ResponseEntity<Country> getCountryById(@PathVariable(value="id")int id) {
		try {
		Country country=countryService.getCountryById(id);
		return new ResponseEntity<Country>(country,HttpStatus.OK);
		}catch(Exception e){
			return new ResponseEntity<Country>(HttpStatus.NOT_FOUND);	
		}
	 }
	@GetMapping("/getCountries/countryname")
	 public ResponseEntity<Country> getCountryByName(@RequestParam(value="name")String countryName) {
		try {
			Country country=countryService.getCountryByName(countryName);
			return new ResponseEntity<Country>(country,HttpStatus.OK);
			}catch(Exception e){
				return new ResponseEntity<Country>(HttpStatus.NOT_FOUND);	
			}
	 }
	
	@PostMapping("/addcountry")
	 public  ResponseEntity<Country> addCountry(@RequestBody Country country) {
		
			try {
				 country= countryService.addNewCountry(country);
				
				return new ResponseEntity<Country>(country,HttpStatus.CREATED);
				}catch(NoSuchElementException e){
					return new ResponseEntity<Country>(HttpStatus.CONFLICT);	
				}
		 }
	
	@PutMapping("/updatecountry/{id}")
	 public ResponseEntity<Country> updateCountry(@PathVariable(value="id")int id,@RequestBody Country country
			 ) {
		try {
		       Country existsCountry=countryService.getCountryById(id); 
		       existsCountry.setCountryName(country.getCountryName());
		       existsCountry.setCountryCapital(country.getCountryCapital());
		       Country updated_country=countryService.updateCountry(existsCountry);
		       return new ResponseEntity<Country>(HttpStatus.OK);
		}
		catch(Exception e){
			  return new ResponseEntity<Country>(HttpStatus.CONFLICT);
		}
		
	 }
	

	@DeleteMapping(path="/deletecountry/{id}")
	 public ResponseEntity<Country> deleteCountry(@PathVariable(value="id")int id) {
		Country country=null;
		try {
			country =countryService.getCountryById(id); 
			countryService.deleteCountry(country);
			return new ResponseEntity<Country>(country, HttpStatus.OK);
			}
			catch(NoSuchElementException e){
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
	
			
		 }
		
		
	
	
	
}
