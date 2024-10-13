package com.amazon.ata.deliveringonourpromise.dao;

import com.amazon.ata.deliveringonourpromise.ordermanipulationauthority.OrderManipulationAuthorityClient;
import com.amazon.ata.ordermanipulationauthority.OrderManipulationAuthority;
import com.amazon.ata.ordermanipulationauthority.OrderResult;
import com.amazon.ata.ordermanipulationauthority.OrderResultItem;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.testng.Assert;

import java.util.Arrays;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class OrderDaoTest {

    private OrderDao dao;
    private OrderManipulationAuthorityClient client;
    private OrderManipulationAuthority mockOrderManipulationAuthority;
    private String orderId;
    private String orderItemId;
    private OrderResult orderResult;
    private OrderResultItem orderResultItem;

    @BeforeEach
    private void createClient() {
        mockOrderManipulationAuthority = mock(OrderManipulationAuthority.class);
        client = new OrderManipulationAuthorityClient(mockOrderManipulationAuthority);
        orderId = "111-7497023-2960775";
        orderItemId = "54321";

        orderResultItem = OrderResultItem.builder()
                .withCustomerOrderItemId(orderItemId)
                .withOrderId(orderId).build();
        orderResult = OrderResult.builder()
                .withOrderId(orderId)
                .withCustomerOrderItemList(Arrays.asList(orderResultItem))
                .build();

        when(mockOrderManipulationAuthority.getCustomerOrderByOrderId(orderId)).thenReturn(orderResult);
    }


    @Test
    public void get_forKnownOrderId_returnsOrder() {
        //Given An order ID that we know exists
        String orderID = "111-7497023-2960775";

        dao = new OrderDao(client);

        //When We call `get()` with that order ID

        // Then The result is not null
        Assertions.assertNotNull(dao.get(orderID), "Expected known order ID to not return null");
    }

    @Test
    public void get_forInvalidOrderId_returnsNull() {
        //Given An order ID that we know is wrong
        String orderID = "111-7497023-2960774";

        dao = new OrderDao(client);

        //When We call `get()` with that order ID

        // Then The result is null
        Assertions.assertNull(dao.get(orderID), "Expected invalid order ID to return null");
    }

    @Test
    public void get_forNullOrderId_returnsNull() {
        //Given An order ID that we know is null
        String orderID = null;

        dao = new OrderDao(client);

        //When We call `get()` with that order ID

        // Then The result is null
        Assertions.assertNull(dao.get(orderID), "Expected null order ID to return null");
    }
}
