package com.tvm.Productservice;

import com.tvm.ProductDto.productdto;
import com.tvm.productEntity.Product;

import java.util.List;

public interface ProductService
{
    Product create(productdto dto, Long Vendorid);

    List<Product> search(String name, String category, Double price);

    Product update(long id, productdto dto, long vendorid);

    void delete(Long id);

    Product updatestocks(long id, int quantity);

    Product getById(long id, Long Vendorid);

    productdto getAllproducts(long id);
}
