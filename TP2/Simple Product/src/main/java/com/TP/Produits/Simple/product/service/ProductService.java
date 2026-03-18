package com.TP.Produits.Simple.product.service;

import com.TP.Produits.Simple.product.dto.ProductDTO;
import com.TP.Produits.Simple.product.dto.ProductRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
@Service
@Transactional
public interface ProductService {
    // Récupère tous les produits
    List<ProductDTO> getAllProducts();
    // Récupère un produit par son ID
    ProductDTO getProductById(Long id) throws Exception;
    // Crée un nouveau produit
    ProductDTO createProduct(ProductRequest request);
    // Met à jour un produit existant
    ProductDTO updateProduct(Long id, ProductRequest request) throws Exception;
    // Supprime un produit
    void deleteProduct(Long id) throws Exception;

    boolean verifyProductExists(Long id);
}

