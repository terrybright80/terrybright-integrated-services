package com.terrybright.integrated.services.service;

import com.terrybright.integrated.services.model.Product;
import com.terrybright.integrated.services.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public Product create(Product product) {
        product.setId(null); // Ensure new product creation
        return productRepository.save(product);
    }

    @Override
    public Product update(Long id, Product product) {
        return productRepository.findById(id)
            .map(existing -> {
                existing.setName(product.getName());
                existing.setDescription(product.getDescription());
                existing.setPrice(product.getPrice());
                existing.setQuantity(product.getQuantity());
                existing.setUnit(product.getUnit());
                return productRepository.save(existing);
            })
            .orElseThrow(() -> new IllegalArgumentException("Product not found: " + id));
    }

    @Override
    public void delete(Long id) {
        productRepository.deleteById(id);
    }
}