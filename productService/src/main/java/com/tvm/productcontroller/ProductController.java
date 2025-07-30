package com.tvm.productcontroller;

import com.tvm.ProductDto.productdto;
import com.tvm.Productservice.ProductService;
import com.tvm.productEntity.Product;
import com.tvm.productservicefeign.Vendorclient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pro")
public class ProductController
{
    @Autowired
    private ProductService productService;

   @Autowired
   private Vendorclient vendorclient;

@PostMapping("/addproduct/{Vendorid}")
public ResponseEntity<Product> create(@RequestBody productdto dto, @PathVariable long Vendorid) {

    return new ResponseEntity<>(productService.create(dto,Vendorid), HttpStatus.CREATED);
}



    @GetMapping("/get/{id}/{Vendorid}")
    public  ResponseEntity<Product>getbyId(@PathVariable long id, @PathVariable long Vendorid)
    {
        return ResponseEntity.ok(productService.getById(id,Vendorid));
    }

    @GetMapping("/search")
    public ResponseEntity<List<Product>> search(
            @RequestParam (required = true) String name,
            @RequestParam (required = true)String category,
            @RequestParam (required = true) Double Price)
    {
        return  ResponseEntity.ok(productService.search(name,category,Price));
    }


    @PutMapping("/{id}/{vendorid}")
    public ResponseEntity<Product> updateproduct(@PathVariable long id, @RequestBody productdto dto,@PathVariable  long vendorid)
    {
        return ResponseEntity.ok(productService.update(id, dto,vendorid));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String>delete(@PathVariable Long id)
    {
        productService.delete(id);

        return  ResponseEntity.ok("delete id success");
    }


    @PatchMapping("/{id}/stock")
    public  ResponseEntity<Product>updatestock(@PathVariable long id ,@RequestParam int quantity)
    {

        Product updatedProduct = productService.updatestocks(id, quantity);
        return ResponseEntity.status(HttpStatus.CREATED).body(updatedProduct);
    }


    //feign client
    @GetMapping("/getproduct/{id}")
    public ResponseEntity<productdto>getallproduct(@PathVariable long id)
    {
        return  ResponseEntity.ok(productService.getAllproducts(id));
    }

}
