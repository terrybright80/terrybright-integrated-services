import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.terrybright.integrated.services.model.Order;
import com.terrybright.integrated.services.repository.OrderRepository;
import com.terrybright.integrated.services.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class OrderServiceTests {

    @InjectMocks
    private OrderService orderService;

    @Mock
    private OrderRepository orderRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindAll() {
        Order order1 = new Order(1L, 1L, 2, "John Doe", null);
        Order order2 = new Order(2L, 2L, 1, "Jane Doe", null);
        when(orderRepository.findAll()).thenReturn(Arrays.asList(order1, order2));

        List<Order> orders = orderService.findAll();

        assertEquals(2, orders.size());
        verify(orderRepository, times(1)).findAll();
    }

    @Test
    public void testFindById() {
        Order order = new Order(1L, 1L, 2, "John Doe", null);
        when(orderRepository.findById(1L)).thenReturn(Optional.of(order));

        Optional<Order> foundOrder = orderService.findById(1L);

        assertTrue(foundOrder.isPresent());
        assertEquals("John Doe", foundOrder.get().getCustomerName());
        verify(orderRepository, times(1)).findById(1L);
    }

    @Test
    public void testSave() {
        Order order = new Order(null, 1L, 2, "John Doe", null);
        when(orderRepository.save(any(Order.class))).thenReturn(new Order(1L, 1L, 2, "John Doe", null));

        Order savedOrder = orderService.save(order);

        assertNotNull(savedOrder.getId());
        verify(orderRepository, times(1)).save(order);
    }

    @Test
    public void testUpdate() {
        Order existingOrder = new Order(1L, 1L, 2, "John Doe", null);
        when(orderRepository.findById(1L)).thenReturn(Optional.of(existingOrder));
        when(orderRepository.save(any(Order.class))).thenReturn(existingOrder);

        Order updatedOrder = orderService.update(1L, existingOrder);

        assertEquals("John Doe", updatedOrder.getCustomerName());
        verify(orderRepository, times(1)).findById(1L);
        verify(orderRepository, times(1)).save(existingOrder);
    }
}