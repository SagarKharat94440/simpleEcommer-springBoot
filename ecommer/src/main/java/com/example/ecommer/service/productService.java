package com.example.ecommer.service;

import com.example.ecommer.model.product;
import com.example.ecommer.repository.productRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class productService {

    @Autowired
    private productRepo repo;

    public List<product> getAllProduct() {
        return repo.findAll();
    }

    public product getProductById(int id) {
        return repo.findById(id).get();
    }

    public product addProduct(product product1, MultipartFile imageFile) throws IOException {
        product1.setImageName(imageFile.getOriginalFilename());
        product1.setImageType(imageFile.getContentType());
        product1.setImageDate(imageFile.getBytes());
        return repo.save(product1);

    }

    public product updateProduct(int id, product product, MultipartFile imageFile) throws IOException {
        product.setImageDate(imageFile.getBytes());
        product.setImageName(imageFile.getOriginalFilename());
        product.setImageType(imageFile.getContentType());
        return  repo.save(product);
    }

    public void deleteProduct(int id) {
        repo.deleteById(id);
    }

    public  List<product> serchProduct(String keyword){
       return repo.searchProducts(keyword);
    }
}
