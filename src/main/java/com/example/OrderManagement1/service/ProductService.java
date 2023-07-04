package com.example.OrderManagement1.service;

import com.example.OrderManagement1.payload.ProductDto;

import java.util.List;

/**
 * The ProductService interface provides methods for managing products.
 */
public interface ProductService {

    /**
     * Retrieves a list of all products.
     *
     * @return A list of ProductDto objects representing all products.
     */
    List<ProductDto> getAllProducts();

    /**
     * Retrieves a product by its ID.
     *
     * @param productId The ID of the product to retrieve.
     * @return The ProductDto object representing the product.
     */
    ProductDto getProductById(int productId);

    /**
     * Creates a new product.
     *
     * @param productDto The ProductDto object representing the product to create.
     * @return The ProductDto object representing the created product.
     */
    ProductDto createProduct(ProductDto productDto);

    /**
     * Updates an existing product.
     *
     * @param productId  The ID of the product to update.
     * @param productDto The ProductDto object representing the updated product data.
     * @return The ProductDto object representing the updated product.
     */
    ProductDto updateProduct(int productId, ProductDto productDto);

    /**
     * Deletes a product.
     *
     * @param productId The ID of the product to delete.
     */
    void deleteProduct(int productId);

    /**
     * Retrieves a list of products with the specified slug.
     *
     * @param slug The slug of the products to retrieve.
     * @return A list of ProductDto objects representing the products.
     */
    List<ProductDto> getProductsBySlug(String slug);

    /**
     * Retrieves a list of products with the specified name.
     *
     * @param name The name of the products to retrieve.
     * @return A list of ProductDto objects representing the products.
     */
    List<ProductDto> getProductsByName(String name);

    /**
     * Retrieves a list of products with the specified reference.
     *
     * @param reference The reference of the products to retrieve.
     * @return A list of ProductDto objects representing the products.
     */
    List<ProductDto> getProductsByReference(String reference);

    /**
     * Retrieves a list of products within the specified price range.
     *
     * @param minPrice The minimum price of the products to retrieve.
     * @param maxPrice The maximum price of the products to retrieve.
     * @return A list of ProductDto objects representing the products.
     */
    List<ProductDto> getProductsByPriceRange(double minPrice, double maxPrice);

    /**
     * Retrieves a list of products with the specified VAT.
     *
     * @param vat The VAT of the products to retrieve.
     * @return A list of ProductDto objects representing the products.
     */
    List<ProductDto> getProductsByVat(double vat);

    /**
     * Retrieves a list of products that are currently in stock.
     *
     * @return A list of ProductDto objects representing the products in stock.
     */
    List<ProductDto> getProductsInStock();

    /**
     * Retrieves a list of products with a price less than the specified value.
     *
     * @param price The maximum price of the products to retrieve.
     * @return A list of ProductDto objects representing the products.
     */
    List<ProductDto> getByPriceLessThan(double price);

    /**
     * Retrieves a list of products with a VAT greater than the specified value.
     *
     * @param vat The minimum VAT of the products to retrieve.
     * @return A list of ProductDto objects representing the products.
     */
    List<ProductDto> getByVatGreaterThan(double vat);

    /**
     * Retrieves a list of products ordered by price in descending order.
     *
     * @return A list of ProductDto objects representing the products.
     */
    List<ProductDto> getByOrderByPriceDesc();

    /**
     * Retrieves a list of products with a VAT between the specified minimum and maximum values.
     *
     * @param minVat The minimum VAT of the products to retrieve.
     * @param maxVat The maximum VAT of the products to retrieve.
     * @return A list of ProductDto objects representing the products.
     */
    List<ProductDto> getByVatBetween(double minVat, double maxVat);
}
