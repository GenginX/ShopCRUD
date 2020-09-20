package com.kaczmarm.repository;

import com.kaczmarm.domain.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Repository
public class ProductRepository {

    private List<Product> productList = new ArrayList<>();

    private AtomicLong idCounter = new AtomicLong(0);

    public Product save(Product product){

        product.setId(idCounter.getAndIncrement());

        productList.add(product);

        return product;
    }

    public List<Product> getAll(){
        return productList;
    }

    public void deleteProduct(Long id){
        productList.stream()
                .filter(p -> p.getId().equals(id))
                .findAny()
                .ifPresent(e -> productList.remove(e));
    }

    public Product getProductById(Long id){
        Product product = productList
                .stream()
                .filter(e -> e.getId().equals(id))
                .findAny()
                .orElseGet(null);

        return product;

    }
    public Product updateProduct(Product product, Long id){
        Product productById = getProductById(id);

        productById.setDescription(product.getDescription());
        productById.setPrice(product.getPrice());
        productById.setQuantity(product.getQuantity());

        return productById;
    }

    public List<Product> getAllByName(String name){
        List<Product> collect = productList
                .stream()
                .filter(e -> e.getName().toLowerCase().contains(name.toLowerCase()))
                .collect(Collectors.toList());
        return collect;
    }

}
