package com.javalopers.tiendafacil.backend.service.interfaces;
import com.javalopers.tiendafacil.backend.dto.ProductDTO;

import java.util.List;

public interface ProductService {

    ProductDTO saveProduct (ProductDTO productRequest);

    List<ProductDTO> getAllProducts ();

    ProductDTO getProduct (Integer id);

    ProductDTO updateProduct (Integer id, ProductDTO productRequest);

    void deleteProduct (Integer id);
}
