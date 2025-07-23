package com.tvm.productcontroller;

import com.tvm.ProductDto.productdto;
import com.tvm.Productservice.ProductService;
import com.tvm.productEntity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductController
{
    @Autowired
    private ProductService productService;


    @PostMapping("/add")
    public ResponseEntity<Product> create(@RequestBody productdto dto) {
        return new ResponseEntity<>(productService.create(dto), HttpStatus.CREATED);
    }

    @GetMapping("/get/{id}")
    public  ResponseEntity<Product>getbyId(@PathVariable long id)
    {
        return ResponseEntity.ok(productService.getById(id));
    }

    @GetMapping("/search")
    public ResponseEntity<List<Product>> search(
            @RequestParam (required = true) String name,
            @RequestParam (required = true)String category,
            @RequestParam (required = true) Double Price)
    {
        return  ResponseEntity.ok(productService.search(name,category,Price));
    }


    @PutMapping("/{id}")
    public ResponseEntity<Product> updateproduct(@PathVariable long id, @RequestBody productdto dto)
    {
        return ResponseEntity.ok(productService.update(id, dto));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String>delete(@PathVariable Long id)
    {
        productService.delete(id);
//        return ResponseEntity.noContent().build();
        return  ResponseEntity.ok("delete id sucess");
    }


    @PatchMapping("/{id}/stock")
    public  ResponseEntity<Product>updatestock(@PathVariable long id ,@RequestParam int quantity)
    {

        Product updatedProduct = productService.updatestocks(id, quantity);
        return ResponseEntity.status(HttpStatus.CREATED).body(updatedProduct);
    }

}
