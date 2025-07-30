package com.tvm.Exceptionhandler;

public class VendorIdIsNotFound extends  RuntimeException
{
    public  VendorIdIsNotFound (String message)
    {
        super(message);
    }
}
