package com.example.OrderManagement1.service.impl;

import com.example.OrderManagement1.entity.Product;
import com.example.OrderManagement1.exception.ResourceNotFoundException;
import com.example.OrderManagement1.payload.ProductDto;
import com.example.OrderManagement1.repository.ProductRepository;
import com.example.OrderManagement1.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<ProductDto> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public ProductDto getProductById(int productId) {
        Product product = getProduct(productId);
        return convertToDto(product);
    }

    @Override
    public List<ProductDto> getByPriceLessThan(double price) {
        List<Product> products = productRepository.findByPriceLessThan(price);
        return products.stream()
                .map(product -> modelMapper.map(product, ProductDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductDto> getByVatGreaterThan(double vat) {
        List<Product> products = productRepository.findByVatGreaterThan(vat);
        return products.stream()
                .map(product -> modelMapper.map(product, ProductDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductDto> getByOrderByPriceDesc() {
        List<Product> products = productRepository.findByOrderByPriceDesc();
        return products.stream()
                .map(product -> modelMapper.map(product, ProductDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductDto> getByVatBetween(double minVat, double maxVat) {
        List<Product> products = productRepository.findByVatBetween(minVat, maxVat);
        return products.stream()
                .map(product -> modelMapper.map(product, ProductDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public ProductDto createProduct(ProductDto productDto) {
        Product product = convertToEntity(productDto);
        Product savedProduct = productRepository.save(product);
        return convertToDto(savedProduct);
    }

    @Override
    public ProductDto updateProduct(int productId, ProductDto productDto) {
        Product existingProduct = getProduct(productId);
        existingProduct.setSlug(productDto.getSlug());
        existingProduct.setName(productDto.getName());
        existingProduct.setReference(productDto.getReference());
        existingProduct.setPrice(productDto.getPrice());
        existingProduct.setVat(productDto.getVat());
        existingProduct.setStockable(productDto.isStockable());
        Product updatedProduct = productRepository.save(existingProduct);
        return convertToDto(updatedProduct);
    }

    @Override
    public void deleteProduct(int productId) {
        Product product = getProduct(productId);
        productRepository.delete(product);
    }

    @Override
    public List<ProductDto> getProductsBySlug(String slug) {
        List<Product> products = productRepository.findBySlug(slug);
        return products.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductDto> getProductsByName(String name) {
        List<Product> products = productRepository.findByNameContainingIgnoreCase(name);
        return products.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductDto> getProductsByReference(String reference) {
        List<Product> products = productRepository.findByReference(reference);
        return products.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductDto> getProductsByPriceRange(double minPrice, double maxPrice) {
        List<Product> products = productRepository.findByPriceBetween(minPrice, maxPrice);
        return products.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductDto> getProductsByVat(double vat) {
        List<Product> products = productRepository.findByVat(vat);
        return products.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductDto> getProductsInStock() {
        List<Product> products = productRepository.findByStockableTrue(true);
        return products.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private Product getProduct(int productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "id", productId));
    }

    private ProductDto convertToDto(Product product) {
        return modelMapper.map(product, ProductDto.class);
    }

    private Product convertToEntity(ProductDto productDto) {
        return modelMapper.map(productDto, Product.class);
    }
}
