// ...existing code...
package com.terrybright.integrated.services.service;

import com.terrybright.integrated.services.model.Product;
import com.terrybright.integrated.services.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository repo;

    public ProductServiceImpl(ProductRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<Product> findAll() {
        return repo.findAll();
    }

    @Override
    public Optional<Product> findById(Long id) {
        return repo.findById(id);
    }

    @Override
    public Product create(Product product) {
        product.setId(null);
        return repo.save(product);
    }

    @Override
    public Product update(Long id, Product product) {
        return repo.findById(id).map(existing -> {
            existing.setName(product.getName());
            existing.setDescription(product.getDescription());
            existing.setPrice(product.getPrice());
            existing.setQuantity(product.getQuantity());
            existing.setUnit(product.getUnit());
            return repo.save(existing);
        }).orElseThrow(() -> new IllegalArgumentException("Product not found: " + id));
    }

    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }
}package com.terrybright.integrated.services.service;

import com.terrybright.integrated.services.model.Product;
import com.terrybright.integrated.services.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    public Product create(Product product) {
        return productRepository.save(product);
    }

    public Product update(Long id, Product productDetails) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id " + id));
        product.setName(productDetails.getName());
        product.setDescription(productDetails.getDescription());
        product.setPrice(productDetails.getPrice());
        product.setQuantity(productDetails.getQuantity());
        return productRepository.save(product);
    }

    public void delete(Long id) {
        productRepository.deleteById(id);
    }
}