package com.kaczmarm.service;

import com.kaczmarm.domain.Product;
import com.kaczmarm.dto.CreateProductDto;
import com.kaczmarm.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProductService {


    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product save(CreateProductDto dto){
        Product product = createProductFromDto(dto);

        productRepository.save(product);
      return product;
    }

    public List<Product> getAll(){
        List<Product> all = productRepository.getAll();
        return all;
    }

    public String removeById(Long id){
        if (checkIfIdExists(id)) {
            productRepository.deleteProduct(id);
            return "Product with id: " + id + " removed successfully";
        }
        return "We couldn't find product with ID: " + id;
    }

    public Product getProductById(Long id){
       return productRepository.getProductById(id);
    }

    private boolean checkIfIdExists(Long id){
       return productRepository.getAll()
                .stream()
                .map(e->e.getId())
               .anyMatch(e -> e.equals(id));
    }

    private Product createProductFromDto(CreateProductDto dto){

        return Product.builder()
                .category(dto.getCategory())
                .description(dto.getDescription())
                .name(dto.getName())
                .price(dto.getPrice())
                .quantity(dto.getQuantity())
                .creationTime(LocalDateTime.now())
                .build();
    }

    public Product updateProduct(Long id, CreateProductDto dto){
        Product product = createProductFromDto(dto);
        return productRepository.updateProduct(product, id);
    }

    public List<Product> findByName(String name){
        List<Product> allByName = productRepository.getAllByName(name);
        return allByName;
    }


}
