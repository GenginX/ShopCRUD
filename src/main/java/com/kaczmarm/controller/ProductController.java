package com.kaczmarm.controller;

import com.kaczmarm.domain.Product;
import com.kaczmarm.dto.CreateProductDto;
import com.kaczmarm.service.ProductService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/product")
public class ProductController {

    private ProductService service;

    @Autowired
    public ProductController(ProductService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Product> creatProduct(
            @RequestBody @Valid CreateProductDto dto){
        Product product = service.save(dto);
        return new ResponseEntity<>(product, HttpStatus.CREATED);
    };

    @GetMapping
    public ResponseEntity<List<Product>> getAllProduct(){
        List<Product> all = service.getAll();
        return new ResponseEntity<>(all, HttpStatus.FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProductById(@PathVariable ("id") Long id){
        String result = service.removeById(id);
        return new ResponseEntity<>(result, HttpStatus.ACCEPTED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable ("id") Long id){
        Product productById = service.getProductById(id);
        return new ResponseEntity<>(productById, HttpStatus.FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@RequestBody CreateProductDto dto, @PathVariable("id") Long id){
        Product product = service.updateProduct(id, dto);
        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Product>> getProducts(@RequestParam String name){
            List<Product> byName = service.findByName(name);
            return new ResponseEntity<>(byName, HttpStatus.FOUND);
    }

}
