package com.tvm.Productservice;

import com.tvm.ProductDto.productdto;
import com.tvm.productEntity.Product;
import com.tvm.productEntity.Vendor;
import com.tvm.productrepository.ProductRepository;
import com.tvm.productrepository.VendorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceimpl implements  ProductService
{
    @Autowired
    private ProductRepository productrepo;
    @Autowired
    private VendorRepository vendorrepo;

    @Override
    public Product create(productdto dto) {
        Product product = new Product();
        product.setName(dto.getName());
        product.setCategory(dto.getCategory());
        product.setPrice(dto.getPrice());
        product.setStock(dto.getStock());

        Vendor vendor = vendorrepo.findById(dto.getVendorId())
                .orElseGet(() -> {
                    Vendor newVendor = new Vendor();
                    newVendor.setId(dto.getVendorId());
                    newVendor.setName(dto.getVendorname());
                    return vendorrepo.save(newVendor);  // 💾 Save vendor if not found
                });

        product.setVendor(vendor);

        return productrepo.save(product);  // 💾 Save product with the vendor
    }


    @Override
    public Product getById(long id) {
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
    public Product update(long id, productdto dto) {
        Product product = productrepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
        product.setName(dto.getName());
        product.setCategory(dto.getCategory());
        product.setPrice(dto.getPrice());
        product.setStock(dto.getStock());

        Vendor vendor = vendorrepo.findById(dto.getVendorId())
                .orElseThrow(() -> new RuntimeException("Vendor not found with id: " + dto.getVendorId()));
        product.setVendor(vendor);

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

}
