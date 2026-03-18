package com.TP.Produits.Simple.product.service;

import com.TP.Produits.Simple.product.dto.ProductDTO;
import com.TP.Produits.Simple.product.dto.ProductRequest;
import com.TP.Produits.Simple.product.mapper.ProductMapper;
import com.TP.Produits.Simple.product.model.Product;
import com.TP.Produits.Simple.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository repository;
    private final ProductMapper mapper;
    @Override
    public List<ProductDTO> getAllProducts() {
        return mapper.toDTOList(repository.findAll());
    }
    @Override
    public ProductDTO getProductById(Long id) throws Exception {
        Product product = repository.findById(id)
                .orElseThrow(() -> new Exception(
                        "Product not found with id: " + id));
        return mapper.toDTO(product);
    }
    @Override
    public ProductDTO createProduct(ProductRequest request) {
        Product product = mapper.toEntity(request);
        Product saved = repository.save(product);
        return mapper.toDTO(saved);
    }
    @Override
    public ProductDTO updateProduct(Long id, ProductRequest request) throws Exception {
        Product product = repository.findById(id)
                .orElseThrow(() -> new Exception(
                        "Product not found with id: " + id));
        mapper.updateEntity(request, product);
        Product updated = repository.save(product);
        return mapper.toDTO(updated);
    }
    @Override
    public void deleteProduct(Long id) throws Exception {
        if (!repository.existsById(id)) {
            throw new Exception("Product not found with id: " + id);
        }
        repository.deleteById(id);
    }

    @Override
    public boolean verifyProductExists(Long id) {
        return repository.existsById(id); // CORRECT: referencing the injected instance
    }
}

