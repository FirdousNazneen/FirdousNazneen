package com.sathya3.springmvc.service.model;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
 
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductModel {
	
	 @NotBlank(message="name cannot be blank")
	private String name;
	 
	 @NotBlank(message="brandcannot be blank")
	private String brand;
	 
	 @NotBlank(message=" madeIn cannot be blank")
	private String madeIn;
	 
	 @NotBlank(message="price cannot be blank")
	private double price;
	 
	 @NotBlank(message="price cannot be blank")
	private double Quantity;
	 
	 @NotBlank(message="discountRate cannot be blank")
	private double discountRate;
	
}
