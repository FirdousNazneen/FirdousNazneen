package com.sathya3.springmvc.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sathya3.springmvc.service.ProductService;
import com.sathya3.springmvc.service.entity.ProductEntity;
import com.sathya3.springmvc.service.model.ProductModel;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.RequestBody;


@Controller
public class ProductController {
@Autowired
ProductService  ProductService;

//form
	@GetMapping("/productform")
	public String getProductForm()
	{
		return "product";
	}
	
	
	@PostMapping("/SaveProduct")
	public String saveProduct(ProductModel ProductModel)
	{
		ProductService.saveProductDetails(ProductModel);
		return "success";
		
	}

//form with value
//@GetMapping("/productform")
 //public String getproductForm(Model model)
//{
	//ProductModel  ProductModel=new ProductModel();
			//ProductModel .setMadeIn("India");
	//ProductModel .setQuantity(2);
	//ProductModel .setDiscountRate(10.5);
	//model.addAttribute("ProductModel", ProductModel);
	//return "add-product";
//}



	@PostMapping("/saveProduct")
	public String saveProduct(@Valid ProductModel ProductModel,BindingResult  bindingResult  ,Model model) {
		HashMap<String, String>validationErrors=new HashMap<String, String>();
		if(bindingResult.hasErrors())
		{
			for(FieldError fieldError: bindingResult.getFieldErrors())
				
				validationErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
				model.addAttribute("validationErrors", validationErrors);
		
		
	}
	  return  "add-product";
	
	}
	
//get all products
	
	@GetMapping("/getallproducts")
	public String getProductForm(Model model)
	{
		List<ProductEntity>products=ProductService.getAllProducts();
		model.addAttribute( "Products",products);
		return "productlist";
	
	}
	
//search by id
	@GetMapping("/getsearchform")
	public String getsearchForm()
	{
		return "Search-Product";
	}
	
	@PostMapping("/searchbyid")
	public String searchById(@RequestParam Long id,Model model)
	{
		ProductEntity product=ProductService.searchById(id);
		model.addAttribute("product",product);
		return "Search-Product";
	}
	
//delete operation   
	@GetMapping("/delete/{id}")
	public String deleteProductById(@PathVariable("id") Long id) {
		ProductService.deleteProductById(id);
		return "redirect:/getallproducts";
	}
	
	@GetMapping("/edit/{id}")
	public String showEditForm(@PathVariable ("id")Long id,Model model) {

		ProductModel product= ProductService.showEditForm(id);
		model.addAttribute("product",product);
		model.addAttribute("id",id);
	    return "edit-product";
	}
	 
	@PostMapping("/editbyid/{id}")
	public String editById(@PathVariable("id") Long id, ProductModel productModel ) {
		ProductService.editById(id, productModel);	
		return "redirect:/getallproducts";
	}

}
	
	



