package com.tvm.Productservice;

import com.tvm.Exceptionhandler.VendorIdIsNotFound;
import com.tvm.ProductDto.Vendorclientdto;
import com.tvm.ProductDto.productdto;
import com.tvm.productEntity.Product;
import com.tvm.productrepository.ProductRepository;
import com.tvm.productservicefeign.Vendorclient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceimpl implements  ProductService
{
    @Autowired
    private ProductRepository productrepo;

@Autowired
private Vendorclient vendorclient;
    @Override
    public Product create(productdto dto, Long Vendoid) {
        Vendorclientdto client= vendorclient.isApproved(Vendoid);
//        Vendorclientdto vendor = vendorClient.isApproved(productDto.getVendorid());
        if (!client.isApproval()) {
            throw new VendorIdIsNotFound("Vendor is not approved. Cannot add product.");
        }
        Product product = new Product();
        Vendorclientdto c=new Vendorclientdto();
        product.setName(dto.getName());
        product.setCategory(dto.getCategory());
        product.setPrice(dto.getPrice());
        product.setStock(dto.getStock());
        product.setVendorid(dto.getVendorid());


        return productrepo.save(product);
    }


    @Override
    public Product getById(long id, Long Vendorid) {
        Vendorclientdto client= vendorclient.isApproved(Vendorid);

        if (!client.isApproval()) {
            throw new VendorIdIsNotFound("Vendor is not approved. Cannot add product.");
        }
        return productrepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
    }



    @Override
    public List<Product> search(String name, String category, Double price) {
        if (name != null) return productrepo.findByNameContainingIgnoreCase(name);
        if (category != null) return productrepo.findByCategory(category);
        if (price != null) return productrepo.findByPriceLessThanEqual(price);
        return productrepo.findAll();
    }

    @Override
    public Product update(long id, productdto dto, long vendorid) {
        Vendorclientdto client= vendorclient.isApproved(vendorid);
//        Vendorclientdto vendor = vendorClient.isApproved(productDto.getVendorid());
        if (!client.isApproval()) {
            throw new VendorIdIsNotFound("Vendor is not approved. Cannot add product.");
        }
        Product product = productrepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
        product.setName(dto.getName());
        product.setCategory(dto.getCategory());
        product.setPrice(dto.getPrice());
        product.setStock(dto.getStock());
        return productrepo.save(product);
    }

    @Override
    public void delete(Long id) {
        if (!productrepo.existsById(id)) {
            throw new RuntimeException("Product not found with id: " + id);
        }
        productrepo.deleteById(id);
    }

    @Override
    public Product updatestocks(long id, int quantity) {
        Product product = productrepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
        product.setStock(quantity);
        productrepo.save(product);
        return product;
    }
    @Override
    public productdto getAllproducts(long id)
    {
        Optional<Product>pt=productrepo.findById(id);

        if(pt.isEmpty())
        {
            throw  new ResponseStatusException(HttpStatus.NOT_FOUND,"Product Not Found :"+id);
        }
        Product product=pt.get();
        productdto dto=new productdto();
        dto.setName(product.getName());
        dto.setCategory(product.getCategory());
        dto.setPrice(product.getPrice());
        dto.setStock(product.getStock());

        return dto;
    }

}
