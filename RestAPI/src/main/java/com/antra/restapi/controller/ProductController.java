package com.antra.restapi.controller;

import com.antra.restapi.entity.Product;
import com.antra.restapi.exception.ProductException;
import com.antra.restapi.service.ProductService;
import jakarta.validation.Valid;
import org.apache.logging.log4j.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductController {

    private ProductService service;
    @Autowired
    public ProductController(ProductService service) {
        this.service = service;
    }

    /**
     * create a product
     * @param product
     * @return
     */
    @RequestMapping(value = "/products", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<Product> addProduct(@RequestBody @Valid Product product){
        return new ResponseEntity<>(service.saveProduct(product), HttpStatus.CREATED);
    }


//    @RequestMapping(value = "/addProducts", method = RequestMethod.POST, consumes = "application/json")
//    public List<Product> addProducts(@RequestBody List<Product> products){
//        return service.saveProducts(products);
//    }

    /**
     * get all products
     * @return
     */
    @RequestMapping(value = "/products",  method = RequestMethod.GET)
    public ResponseEntity<List<Product>> findAllProducts(){
        return ResponseEntity.ok(service.getProducts());
    }

    /**
     * get products by id
     * @param id
     * @return
     */
    @RequestMapping(value = "/products/{id}", method = RequestMethod.GET)
    public ResponseEntity<Product> findProductById(@PathVariable int id){
       Product product = service.getProductById(id);
       if(product==null){
           throw new ProductException(messages.getMessage("USER_NOT_FOUND"));
       }
    }
//    @RequestMapping(value = "/products/{name}", method = RequestMethod.GET)
//    public Product findProductByName(@PathVariable String name){
//        return service.getProductByName(name);
//    }

    /**
     * update products by id
     * @param id
     * @param product
     * @return
     */
    @RequestMapping(value = "/products/{id}", method = RequestMethod.PUT, consumes = "application/json")
    public Product updateProduct(@PathVariable int id, @RequestBody Product product){
        return service.updateProduct(id, product);
    }

    /**
     * delete products by id
     * @param id
     * @return
     */
    @RequestMapping(value = "/products/{id}", method = RequestMethod.DELETE)
    public String deleteProduct(@PathVariable int id){
        return service.deleteProduct(id);

    }
}
