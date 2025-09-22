package com.example.ecommer.controller;

import com.example.ecommer.model.product;
import com.example.ecommer.service.productService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api")
public class productController {

    @Autowired
    productService service;

    @RequestMapping("/")
     public  String greet(){
        return "Welcome!!";
    }

    @RequestMapping("/products")
    public ResponseEntity<List<product>> getProduct(){
        return new ResponseEntity<>(service.getAllProduct(), HttpStatus.OK);
    }

    @RequestMapping("/product/{id}")
    public  ResponseEntity<product >getById(@PathVariable int id){
        return  new ResponseEntity<>( service.getProductById(id),HttpStatus.OK);
    }

    @PostMapping("/product")
    public  ResponseEntity<?> addProduct(@RequestPart product product, @RequestPart MultipartFile imageFile){
        try {
            product product1= service.addProduct(product, imageFile );
            return new ResponseEntity<>(product1, HttpStatus.OK);
        }catch (Exception e){
            return  new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/product/{productId}/image")
    public  ResponseEntity<byte[]> getProductById(@PathVariable int productId){

        product product2= service.getProductById(productId);
        byte[] imageFile=product2.getImageDate();

        return ResponseEntity.ok()
                .body(imageFile);
    }

    @PutMapping("product/{id}")
    public  ResponseEntity<String> updateProduct(@PathVariable int id,@RequestPart product product, @RequestPart MultipartFile imageFile) throws IOException {
        product product1= service.updateProduct(id, product,imageFile);
        if(product1!=null)
            return new ResponseEntity<>("Updated",HttpStatus.OK);
        else
            return new ResponseEntity<>("Failed to Update", HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @DeleteMapping("product/{id}")
    public  ResponseEntity<String> updateProduct(@PathVariable int id){
        product product2= service.getProductById(id);
        if (product2!=null){
             service.deleteProduct(id);
             return new ResponseEntity<>("product deleted", HttpStatus.OK);}
        else
            return new ResponseEntity<>("product not found", HttpStatus.BAD_REQUEST);
    }

    @GetMapping("products/search")
    public  ResponseEntity<List> searchProduct(@RequestParam String keyword){
        return new ResponseEntity<>(service.serchProduct(keyword), HttpStatus.OK);
    }
}
