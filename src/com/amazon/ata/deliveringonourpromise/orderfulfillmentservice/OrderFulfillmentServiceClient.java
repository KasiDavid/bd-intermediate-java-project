package com.amazon.ata.deliveringonourpromise.orderfulfillmentservice;

import com.amazon.ata.deliveringonourpromise.types.Promise;
import com.amazon.ata.orderfulfillmentservice.OrderFulfillmentService;
import com.amazon.ata.orderfulfillmentservice.OrderPromise;

/**
 * Client for accessing the OrderFulfillmentService to retrieve Promises.
 */
public class OrderFulfillmentServiceClient {
    private OrderFulfillmentService ofService;

    /**
     * Create new client that calls OFS with the given service object.
     *
     * @param ofService The OrderFulfillmentService that this client will call.
     */
    public OrderFulfillmentServiceClient(OrderFulfillmentService ofService) {
        this.ofService = ofService;
    }

    /**
     * Fetches the Promise for the given order item ID.
     *
     * @param customerOrderItemId String representing the order item ID to fetch the order for.
     * @return the Promise for the given order item ID.
     */
    public Promise getOrderFulfillmentPromiseByOrderItemId(String customerOrderItemId) {
        OrderPromise ofPromise = ofService.getOrderPromise(customerOrderItemId);

        if (null == ofPromise) {
            return null;
        }

        return Promise.builder()
                .withPromiseLatestArrivalDate(ofPromise.getPromiseLatestArrivalDate())
                .withCustomerOrderItemId(ofPromise.getCustomerOrderItemId())
                .withPromiseLatestShipDate(ofPromise.getPromiseLatestShipDate())
                .withPromiseEffectiveDate(ofPromise.getPromiseEffectiveDate())
                .withIsActive(ofPromise.isActive())
                .withPromiseProvidedBy(ofPromise.getPromiseProvidedBy())
                .withAsin(ofPromise.getAsin())
                .build();
    }
}
