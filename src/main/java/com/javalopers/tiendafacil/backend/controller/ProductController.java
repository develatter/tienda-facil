package com.javalopers.tiendafacil.backend.controller;

import com.javalopers.tiendafacil.backend.dto.ProductDTO;
import com.javalopers.tiendafacil.backend.service.interfaces.ProductService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/products")
@AllArgsConstructor
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDTO saveProduct (@Valid @RequestBody ProductDTO productRequest){

        return productService.saveProduct(productRequest);
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<ProductDTO> getAllProducts (){

        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProductDTO getProductById (@PathVariable Integer id){

        return productService.getProduct(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProductDTO updateProduct (@Valid @PathVariable Integer  id, @RequestBody ProductDTO productRequest){

        return productService.updateProduct(id,productRequest);

    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct (@PathVariable (value = "id") Integer id){

        productService.deleteProduct(id);
    }
}
