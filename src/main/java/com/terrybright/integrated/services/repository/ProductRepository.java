// ...existing code...
package com.terrybright.integrated.services.service;

import com.terrybright.integrated.services.model.Product;
import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<Product> findAll();
    Optional<Product> findById(Long id);
    Product create(Product product);
    Product update(Long id, Product product);
    void delete(Long id);
}package com.terrybright.integrated.services.repository;

import com.terrybright.integrated.services.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}