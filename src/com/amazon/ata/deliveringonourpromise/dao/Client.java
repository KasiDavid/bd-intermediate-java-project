package com.amazon.ata.deliveringonourpromise.dao;

import com.amazon.ata.deliveringonourpromise.types.Promise;

public interface Client {
    Promise getDeliveryPromiseByOrderItemId(String customerOrderItemId);
}
