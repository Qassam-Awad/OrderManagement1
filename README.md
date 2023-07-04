# OrderManagement1

### Prerequisites
- Docker

### Setting Up the Development Environment
1. Pull the MySQL Docker image:
   ```
   docker pull mysql
   ```

2. Create a Docker network for the Spring Boot application and MySQL container:
   ```
   docker network create springboot-mysql-net
   ```

3. Run the MySQL container:
   ```
   docker run --name mysqldb --network springboot-mysql-net -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=order_management -d mysql
   ```

4. Build the project (skipping tests):
   ```
   mvn clean package -DskipTests
   ```

5. Build the Docker image for the Spring Boot application:
   ```
   docker build -t springboot-restful-webservices .
   ```

6. Run the Spring Boot application in a Docker container:
   ```
   docker run --network springboot-mysql-net --name springboot-mysql-container -p 8080:8080 springboot-restful-webservices
   ```

7. The application should now be accessible at [http://localhost:8080](http://localhost:8080).

### Cleaning Up
1. Stop and remove the running Docker containers:
   ```
   docker stop mysqldb springboot-mysql-container
   docker rm mysqldb springboot-mysql-container
   ```

2. Remove the Docker network:
   ```
   docker network rm springboot-mysql-net
   ```
```

## API Endpoints

### Customers
- **GET** /api/customers - Get all customers
- **GET** /api/customers/{customerId} - Get customer by ID
- **POST** /api/customers - Create a new customer
- **PUT** /api/customers/{customerId} - Update customer by ID
- **DELETE** /api/customers/{customerId} - Delete customer by ID
- **GET** /api/customers/birthdate/before/{date} - Get customers with birthdate before a specific date
- **GET** /api/customers/firstname/{firstName} - Get customers by first name
- **GET** /api/customers/lastname/{lastName} - Get customers by last name
- **GET** /api/customers/birthdate/{birthdate} - Get customers by birthdate
- **GET** /api/customers/firstname/{firstName}/lastname/{lastName} - Get customers by first name and last name
- **GET** /api/customers/birthdate/range?startDate={startDate}&endDate={endDate} - Get customers with birthdate in a date range

### Orders
- **GET** /api/orders - Get all orders
- **GET** /api/orders/{orderId} - Get order by ID
- **POST** /api/orders - Create a new order
- **PUT** /api/orders/{orderId} - Update order by ID
- **DELETE** /api/orders/{orderId} - Delete order by ID
- **GET** /api/orders/customer/{customerId} - Get orders by customer ID
- **GET** /api/orders/orderdate/{orderDate} - Get orders by order date
- **GET** /api/orders/orderdate/range?startDate={startDate}&endDate={endDate} - Get orders with order date in a date range
- **GET** /api/orders/customername?firstName={firstName}&lastName={lastName} - Get orders by customer first name and last name
- **GET** /api/orders/customer/{customerId}/orderdate/range?startDate={startDate}&endDate={endDate} - Get orders by customer ID and order date in a date range
- **GET** /api/orders/pricelessthan/{price} - Get orders with price less than a specific value
- **GET** /api/orders/orderbypricedesc - Get orders ordered by price in descending order

### Product Orders
- **GET** /api/productorders - Get all product orders
- **GET** /api/productorders/{productId}/{orderId} - Get product order by product ID and order ID
- **POST** /api/productorders - Create a new product order
- **PUT** /api/productorders/{productId}/{orderId} - Update product order by product ID and order ID
- **DELETE** /api/productorders/{productId}/{orderId} - Delete product order by product ID and order ID
- **GET** /api/productorders/product/{productId}/order/{orderId} - Get product orders by product ID and order ID

### Products
- **GET** /api/products - Get all products
- **GET** /api/products/{productId} - Get product by ID
- **POST** /api/products - Create a new product
- **PUT** /api/products/{productId} - Update product by ID
- **DELETE** /api/products/{productId} - Delete product by ID
- **GET** /api/products/slug/{slug} - Get products by slug
- **GET** /api/products/name/{name} - Get products by name
- **GET** /api/products/reference/{reference} - Get products by reference
- **GET** /api/products/pricerange?minPrice={minPrice}&maxPrice={maxPrice} - Get products with price in a range
- **GET** /api/products/vat/{vat} - Get products by VAT
- **GET** /api/products/instock - Get products that are in stock
- **GET** /api/products/pricelessthan/{price} - Get products with price less than a specific value
- **GET** /api/products/vatgreaterthan/{vat} - Get products with VAT greater than a specific value
- **GET** /api/products/orderbypricedesc - Get products ordered by price in descending order
- **GET** /api/products/vatbetween?minVat={minVat}&maxVat={maxVat} - Get products with VAT in a range

### Stocks
- **GET** /api/stocks - Get all stocks
- **GET** /api/stocks/{stockId} - Get stock by ID
- **POST** /api/stocks - Create a new stock
- **PUT** /api/stocks/{stockId} - Update stock by ID
- **DELETE** /api/stocks/{stockId} - Delete stock by ID
- **GET** /api/stocks/product/{productId} - Get stocks by product ID
- **GET** /api/stocks/quality/{quality} - Get stocks by quality
- **GET** /api/stocks/updatedaterange?startDate={startDate}&endDate={endDate} - Get stocks with update date in a date range
- **GET** /api/stocks/product/{productId}/quality/{quality} - Get stocks by product ID and quality
- **GET** /api/stocks/quantitygreaterthan/{quantity} - Get stocks with quantity greater than a specific value
- **GET** /api/stocks/updateatbetween?startDate={startDate}&endDate={endDate} - Get stocks with update date in a date range
- **GET** /api/stocks/product/{productId}/quantitygreaterthan/{quantity} - Get stocks by product ID and quantity greater than a specific value
- **GET** /api/stocks/product/{productId}/updateatbetween?startDate={startDate}&endDate={endDate} - Get stocks by product ID and update date in a date range

### Roles
- **GET** /api/roles/{roleName} - Get role by name

### Users
- **GET** /api/users - Get all users
- **GET** /api/users/{userId} - Get user by ID
- **POST** /api/users - Create a new user
- **PUT** /api/users/{userId} - Update user by ID
- **DELETE** /api/users/{userId} - Delete user by ID
# Order Management System - Authentication Controller

This controller handles user authentication and token management.

## Endpoints

### Register

Registers a new user.

- **URL:** `/api/v1/auth/register`
- **Method:** `POST`
- **Request Body:**
  - `username` (string): The username of the user.
  - `password` (string): The password of the user.
  - `email` (string): The email address of the user.

#### Example

```http
POST /api/v1/auth/register
Content-Type: application/json

{
  "username": "john_doe",
  "password": "password123",
  "email": "john.doe@example.com"
}
POST /api/v1/auth/authenticate
Content-Type: application/json

{
  "username": "john_doe",
  "password": "password123"
}

### Error Handling
- **GET** /api/error - Simulate an error response

### Swagger UI
- **GET** /swagger-ui.html - Swagger UI documentation

