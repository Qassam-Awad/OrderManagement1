/**
 * The `ProductController` class handles HTTP requests related to product management.
 * It provides endpoints for retrieving, creating, updating, and deleting products.
 */
package com.example.OrderManagement1.controller;

import com.example.OrderManagement1.payload.ProductDto;
import com.example.OrderManagement1.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@Api(tags = "Product Management")
public class ProductController {

    private final ProductService productService;

    /**
     * Constructs a new `ProductController` with the specified `ProductService`.
     *
     * @param productService the ProductService used to perform product-related operations.
     */
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    /**
     * Retrieves all products.
     *
     * @return ResponseEntity containing a list of `ProductDto` objects and an HTTP status code.
     */
    @GetMapping
    @ApiOperation("Retrieve all products")
    public ResponseEntity<List<ProductDto>> getAllProducts() {
        List<ProductDto> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    /**
     * Retrieves a product by its ID.
     *
     * @param productId the ID of the product to retrieve.
     * @return ResponseEntity containing the `ProductDto` object and an HTTP status code.
     */
    @GetMapping("/{productId}")
    @ApiOperation("Retrieve a product by its ID")
    public ResponseEntity<ProductDto> getProductById(@PathVariable int productId) {
        ProductDto product = productService.getProductById(productId);
        return ResponseEntity.ok(product);
    }

    /**
     * Creates a new product.
     *
     * @param productDto the `ProductDto` object containing the product details.
     * @return ResponseEntity containing the created `ProductDto` object and an HTTP status code.
     */
    @PostMapping
    @ApiOperation("Create a new product")
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto productDto) {
        ProductDto createdProduct = productService.createProduct(productDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
    }

    /**
     * Updates a product with the specified ID.
     *
     * @param productId  the ID of the product to update.
     * @param productDto the `ProductDto` object containing the updated product details.
     * @return ResponseEntity containing the updated `ProductDto` object and an HTTP status code.
     */
    @PutMapping("/{productId}")
    @ApiOperation("Update a product with the specified ID")
    public ResponseEntity<ProductDto> updateProduct(
            @PathVariable int productId,
            @RequestBody ProductDto productDto) {
        ProductDto updatedProduct = productService.updateProduct(productId, productDto);
        return ResponseEntity.ok(updatedProduct);
    }

    /**
     * Deletes a product with the specified ID.
     *
     * @param productId the ID of the product to delete.
     * @return ResponseEntity with no content and an HTTP status code.
     */
    @DeleteMapping("/{productId}")
    @ApiOperation("Delete a product with the specified ID")
    public ResponseEntity<Void> deleteProduct(@PathVariable int productId) {
        productService.deleteProduct(productId);
        return ResponseEntity.noContent().build();
    }

    /**
     * Retrieves products by the specified slug.
     *
     * @param slug the slug used to filter products.
     * @return ResponseEntity containing a list of `ProductDto` objects and an HTTP status code.
     */
    @GetMapping("/slug/{slug}")
    @ApiOperation("Retrieve products by the specified slug")
    public ResponseEntity<List<ProductDto>> getProductsBySlug(@PathVariable String slug) {
        List<ProductDto> products = productService.getProductsBySlug(slug);
        return ResponseEntity.ok(products);
    }

    /**
     * Retrieves products by the specified name.
     *
     * @param name the name used to filter products.
     * @return ResponseEntity containing a list of `ProductDto` objects and an HTTP status code.
     */
    @GetMapping("/name/{name}")
    @ApiOperation("Retrieve products by the specified name")
    public ResponseEntity<List<ProductDto>> getProductsByName(@PathVariable String name) {
        List<ProductDto> products

                = productService.getProductsByName(name);
        return ResponseEntity.ok(products);
    }

    /**
     * Retrieves products by the specified reference.
     *
     * @param reference the reference used to filter products.
     * @return ResponseEntity containing a list of `ProductDto` objects and an HTTP status code.
     */
    @GetMapping("/reference/{reference}")
    @ApiOperation("Retrieve products by the specified reference")
    public ResponseEntity<List<ProductDto>> getProductsByReference(@PathVariable String reference) {
        List<ProductDto> products = productService.getProductsByReference(reference);
        return ResponseEntity.ok(products);
    }

    /**
     * Retrieves products within the specified price range.
     *
     * @param minPrice the minimum price of products.
     * @param maxPrice the maximum price of products.
     * @return ResponseEntity containing a list of `ProductDto` objects and an HTTP status code.
     */
    @GetMapping("/price")
    @ApiOperation("Retrieve products within the specified price range")
    public ResponseEntity<List<ProductDto>> getProductsByPriceRange(
            @RequestParam double minPrice,
            @RequestParam double maxPrice) {
        List<ProductDto> products = productService.getProductsByPriceRange(minPrice, maxPrice);
        return ResponseEntity.ok(products);
    }

    /**
     * Retrieves products with the specified VAT (Value Added Tax).
     *
     * @param vat the VAT used to filter products.
     * @return ResponseEntity containing a list of `ProductDto` objects and an HTTP status code.
     */
    @GetMapping("/vat/{vat}")
    @ApiOperation("Retrieve products with the specified VAT (Value Added Tax)")
    public ResponseEntity<List<ProductDto>> getProductsByVat(@PathVariable double vat) {
        List<ProductDto> products = productService.getProductsByVat(vat);
        return ResponseEntity.ok(products);
    }

    /**
     * Retrieves products that are currently in stock.
     *
     * @return ResponseEntity containing a list of `ProductDto` objects and an HTTP status code.
     */
    @GetMapping("/in-stock")
    @ApiOperation("Retrieve products that are currently in stock")
    public ResponseEntity<List<ProductDto>> getProductsInStock() {
        List<ProductDto> products = productService.getProductsInStock();
        return ResponseEntity.ok(products);
    }

}