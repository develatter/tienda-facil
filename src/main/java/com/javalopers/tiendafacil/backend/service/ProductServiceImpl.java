package com.javalopers.tiendafacil.backend.service;

import com.javalopers.tiendafacil.backend.dto.ProductDTO;
import com.javalopers.tiendafacil.backend.exception.ProductNotFoundException;
import com.javalopers.tiendafacil.backend.model.Product;
import com.javalopers.tiendafacil.backend.repository.ProductRepository;
import com.javalopers.tiendafacil.backend.service.interfaces.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public ProductDTO saveProduct(ProductDTO productRequest) {

        Product newProduct = new Product();
        newProduct.setProductName(productRequest.getProductName());

        if(productRepository.existsByProductDescription(productRequest.getProductDescription())){
            throw new IllegalArgumentException
                    ("Product with description " + productRequest.getProductDescription() + " already exists in our System.");
        }

        newProduct.setProductDescription(productRequest.getProductDescription());
        newProduct.setUnitPrice(productRequest.getUnitPrice());
        newProduct.setCurrentStock(productRequest.getCurrentStock());


        productRepository.save(newProduct);

        ProductDTO productResponse = new ProductDTO();
        productResponse.setProductId(newProduct.getProductId());
        productResponse.setProductName(newProduct.getProductName());
        productResponse.setProductDescription(newProduct.getProductDescription());
        productResponse.setUnitPrice(newProduct.getUnitPrice());
        productResponse.setCurrentStock(newProduct.getCurrentStock());

        return productResponse;
    }

    @Override
    public List<ProductDTO> getAllProducts() {

        List <Product> productList = productRepository.findAll();

        List<ProductDTO> productDTOList = new ArrayList<>();

        for(Product product : productList){

            ProductDTO productDTO = new ProductDTO();
            productDTO.setProductId(product.getProductId());
            productDTO.setProductName(product.getProductName());
            productDTO.setProductDescription(product.getProductDescription());
            productDTO.setUnitPrice(product.getUnitPrice());
            productDTO.setCurrentStock(product.getCurrentStock());

            productDTOList.add(productDTO);

        }

        return productDTOList;
    }

    @Override
    public ProductDTO getProduct (Integer id) {

        Product existingProduct = productRepository.findById(id).
                orElseThrow(()-> new ProductNotFoundException("Este Producto no existe en nuestro sistema"));

        ProductDTO productDTO = new ProductDTO();
        productDTO.setProductId(existingProduct.getProductId());
        productDTO.setProductName(existingProduct.getProductName());
        productDTO.setProductDescription(existingProduct.getProductDescription());
        productDTO.setUnitPrice(existingProduct.getUnitPrice());
        productDTO.setCurrentStock(existingProduct.getCurrentStock());

        return productDTO;
    }

    @Override
    public ProductDTO updateProduct(Integer id, ProductDTO productRequest) {

        Product existingProduct = productRepository.findById(id).
                orElseThrow(()-> new ProductNotFoundException("Este Producto no existe en nuestro sistema"));

        existingProduct.setProductName(productRequest.getProductName());

        // Corroborando única Descripción
        if (!existingProduct.getProductDescription().equals(productRequest.getProductDescription())
                && productRepository.existsByProductDescription(productRequest.getProductDescription())) {
            throw new IllegalArgumentException("Este producto ya existe en nuestro sistema");
        }

        existingProduct.setProductDescription(productRequest.getProductDescription());
        existingProduct.setUnitPrice(productRequest.getUnitPrice());
        existingProduct.setCurrentStock(productRequest.getCurrentStock());

        productRepository.save(existingProduct);

        ProductDTO productResponse = new ProductDTO();
        productResponse.setProductId(existingProduct.getProductId());
        productResponse.setProductName(existingProduct.getProductName());
        productResponse.setProductDescription(existingProduct.getProductDescription());
        productResponse.setUnitPrice(existingProduct.getUnitPrice());
        productResponse.setCurrentStock(existingProduct.getCurrentStock());

        return productResponse;

    }


    @Override
    public void deleteProduct (Integer id) {

        productRepository.findById(id).
                orElseThrow(()-> new ProductNotFoundException("Este Producto no existe en nuestro sistema"));

        productRepository.deleteById(id);
    }
}
