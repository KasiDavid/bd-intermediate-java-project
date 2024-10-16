package com.amazon.ata.deliveringonourpromise.dao;

/**
 * DAO interface to abstract calls.
 * @param <I> input...not sure
 * @param <O> output...I think (Come back to review, after learning about interfaces)
 */
public interface ReadOnlyDao<I, O> {

    /**
     * Get object method to be implemented.
     * @param orderId Order Id.
     * @return Object abstracted object.
     */
    O get(I orderId);
}
