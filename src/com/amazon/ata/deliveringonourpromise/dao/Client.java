package com.amazon.ata.deliveringonourpromise.dao;

import com.amazon.ata.deliveringonourpromise.types.Promise;

/**
 * Client interface to abstract client promises.
 */
public interface Client {
    /**
     * Get object method to be implemented.
     * @param customerOrderItemId Order item Id.
     * @return Object abstracted object.
     */
    Promise getDeliveryPromiseByOrderItemId(String customerOrderItemId);
}
