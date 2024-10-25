package com.amazon.ata.deliveringonourpromise.types;

import com.amazon.ata.ordermanipulationauthority.OrderCondition;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;

public class OrderTest {
    @Test
    public void orderClass_orderItemListIsChanged_returnOriginalItemList() {
        //GIVEN
        List<OrderItem> customerOrderItemList = new ArrayList<>();
        customerOrderItemList.add(OrderItem.builder().withOrderId("original").build());
        ZonedDateTime zonedDateTime = ZonedDateTime.now().minusDays(1);
        OrderCondition orderCondition = OrderCondition.PENDING;
        //an order (build a new order object)
        Order order = Order.builder()
                .withOrderId("12345")
                .withShipOption("Freight")
                .withMarketplaceId("XD00001")
                .withCustomerId("Kasi")
                .withCondition(orderCondition)
                .withCustomerOrderItemList(customerOrderItemList)
                .withOrderDate(zonedDateTime)
                .build();
        List<OrderItem> originalOrderItems = order.getCustomerOrderItemList();

        //WHEN
        //A direct change is made to field variables
        OrderItem orderItem = OrderItem.builder()
                .withOrderId("changed")
                .build();
        List<OrderItem> changedOrderItem = order.getCustomerOrderItemList();
        changedOrderItem.add(0,orderItem);


        //THEN
        //if change occurs then test fails
        Assertions.assertNotEquals(originalOrderItems,changedOrderItem,"Expected order item list to remain unchanged");
    }

    @Test
    public void orderClass_zonedDateTimeIsChanged_returnOriginalOrderDate() {
        //GIVEN
        List<OrderItem> customerOrderItemList = new ArrayList<>();
        ZonedDateTime zonedDateTime = ZonedDateTime.now().minusDays(1);
        OrderCondition orderCondition = OrderCondition.PENDING;
        //an order (build a new order object)
        Order order = Order.builder()
                .withOrderId("12345")
                .withShipOption("Freight")
                .withMarketplaceId("XD00001")
                .withCustomerId("Kasi")
                .withCondition(orderCondition)
                .withCustomerOrderItemList(customerOrderItemList)
                .withOrderDate(zonedDateTime)
                .build();

        //WHEN
        //A direct change is made to field variables
        ZonedDateTime zonedDateTime1 = order.getOrderDate();
        zonedDateTime1 = ZonedDateTime.now().minusDays(3);

        //THEN
        //if change occurs then test fails
        Assertions.assertNotEquals(zonedDateTime1,order.getOrderDate(),"Expected date time to remain unchanged");
    }
}
