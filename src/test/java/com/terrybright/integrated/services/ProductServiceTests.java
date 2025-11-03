import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.terrybright.integrated.services.model.Product;
import com.terrybright.integrated.services.repository.ProductRepository;
import com.terrybright.integrated.services.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class ProductServiceTests {

    @InjectMocks
    private ProductService productService;

    @Mock
    private ProductRepository productRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindAll() {
        Product product1 = new Product(1L, "Rice", "High quality rice", 2000.0, 50);
        Product product2 = new Product(2L, "Yam", "Fresh yam tubers", 1500.0, 30);
        when(productRepository.findAll()).thenReturn(Arrays.asList(product1, product2));

        List<Product> products = productService.findAll();

        assertEquals(2, products.size());
        assertEquals("Rice", products.get(0).getName());
        assertEquals("Yam", products.get(1).getName());
    }

    @Test
    public void testFindById() {
        Product product = new Product(1L, "Rice", "High quality rice", 2000.0, 50);
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        Product foundProduct = productService.findById(1L).orElse(null);

        assertNotNull(foundProduct);
        assertEquals("Rice", foundProduct.getName());
    }

    @Test
    public void testSave() {
        Product product = new Product(null, "Noodles", "Instant noodles", 500.0, 100);
        when(productRepository.save(any(Product.class))).thenReturn(new Product(3L, "Noodles", "Instant noodles", 500.0, 100));

        Product savedProduct = productService.save(product);

        assertNotNull(savedProduct.getId());
        assertEquals("Noodles", savedProduct.getName());
    }

    @Test
    public void testUpdate() {
        Product existingProduct = new Product(1L, "Rice", "High quality rice", 2000.0, 50);
        when(productRepository.findById(1L)).thenReturn(Optional.of(existingProduct));
        when(productRepository.save(any(Product.class))).thenReturn(existingProduct);

        existingProduct.setPrice(1800.0);
        Product updatedProduct = productService.update(existingProduct);

        assertEquals(1800.0, updatedProduct.getPrice());
    }
}