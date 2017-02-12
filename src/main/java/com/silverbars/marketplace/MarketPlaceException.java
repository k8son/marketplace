package com.silverbars.marketplace;

/**
 * Base exception class for all exceptions.
 */
public class MarketPlaceException extends RuntimeException{
    /**
     * Constructs a MarketPlaceException with message and root cause.
     * @param msg
     * @param rootCause
     */
    public MarketPlaceException(String msg, Throwable rootCause){
        super(msg, rootCause);
    }

    /**
     * Constructs a MarketPlaceException with just a message.
     * @param msg
     */
    public MarketPlaceException(String msg){
        this(msg, null);
    }

}
