# 🛒 Online Shop REST API

This REST API is developed to serve as the backend of an online shop. ADMIN users are allowed to manage the products. Registered users can create orders and review products by leaving a comment and scoring. Unauthenticated users can only browse through the products.
The API uses the HTTP protocol to allow users to interact with the system through a series of defined URLs. To design these solutions, the following technologies were used:

- Java 17 :coffee:
- Spring Boot 3.2.1 :leaves: 
- Maven for dependency management :elephant:
- MySQL database engine :dolphin: and data persistence with JPA and Hibernate.

The main dependencies used are:

- Spring Data JPA (Persist SQL databases using Java Persistence API using Spring Data and Hibernate.)
- Spring Web (Build web applications, including RESTful, using Spring MVC. Use Apache Tomcat as the default built-in container.)
- Spring Security (JWT Authentication)

## :space_invader: Design patterns:

### DTO (Data Transfer Object):
This pattern is used to transfer data between different layers of the application and to improve the security of the application by controlling what data is transferred.

## :computer: Entity Relationship Diagram:

![MER](https://github.com/Alejo-Rojas-R/shop-v3-api/blob/main/diagrams/entity-relationship-diagram.png)

---

## :computer: Endpoints:
**NOTE:** Each endpoint request will be authorized based on the user role. Authenticated users that have the ***ADMIN*** role will be able to perform all of the HTTP methods (POST, GET, PUT, and DELETE). 
Authenticated users that have the ***USER*** role will be able to perform some POST and GET actions. Users that are not authenticated will be able to perform some GET actions. Each endpoint will specify which roles have permission to perform requests to it.

## 🛍️ Create Product Endpoint 

### POST: /products

Creates a new product in the database with the information provided in the body of the application.

Input parameters:

- name: product name (string)
- price: product price (number)
- discount: product discount (number)
- description: product description (string)
- stock: quantity of this product available in stock (number)
- categoryId: id of the category to which the product belongs (number)
- imageUrl: thumbnail image URL for this product (string)

Request example:

```(http://localhost:8082/products)```

```java
{
    "name": String,
    "price": Float,
    "discount": Float,
    "description": String,
    "stock": Integer,
    "categoryId": Long,
    "imageUrl": String
}
```

The API will return the newly created product in a JSON format:
```json
{
    "name": "Blueberries, 18 oz Container",
    "price": 6.84,
    "discount": 10.0,
    "description": "Make decadent meals with sweet and light Fresh Blueberries.",
    "stock": 5,
    "categoryId": 1,
    "imageUrl": "https://i5.walmartimages.com/seo/Fresh-Blueberries-18-oz-Container_66ee489f-0ddc-43b4-99ce-07c0a7bf886e_1.b805f72f3cafb54c77a036a6ab6f4ed4.jpeg"
}
```

---

## 🛍️ Update Product Endpoint (ADMIN users)

### PUT: /products/{productId}

Updates a new product with the information provided in the body of the application.

Input parameters:

- productId: ID of the product that will be updated (number)
- name: product name (string)
- price: product price (number)
- discount: product discount (number)
- description: product description (string)
- stock: quantity of this product available in stock (number)
- categoryId: id of the category to which the product belongs (number)
- imageUrl: thumbnail image URL for this product (string)

**NOTE:** Only the fields that are going to be updated are needed.

Request example:

```(http://localhost:8082/products/1)```

```java
{
    "name": String,
    "price": Float,
    "discount": Float,
    "description": String,
    "stock": Integer,
    "categoryId": Long,
    "imageUrl": String
}
```

The API will return the updated product in a JSON format:
```json
{
    "name": "Blueberries, 18 oz Container",
    "price": 6.84,
    "discount": 10.0,
    "description": "Make decadent meals with sweet and light Fresh Blueberries.",
    "stock": 5,
    "categoryId": 1,
    "imageUrl": "https://i5.walmartimages.com/seo/Fresh-Blueberries-18-oz-Container_66ee489f-0ddc-43b4-99ce-07c0a7bf886e_1.b805f72f3cafb54c77a036a6ab6f4ed4.jpeg"
}
```

---

## 🛍️ Read Many Products Endpoint (All users)

### GET: /products/category/{categoryId}?page={page}&size={size})

Lists all the products that belong to a specific category and filters by page.

Input parameters:

- categoryId: id of the category we want to list products (number)
- page: page of the results we want to search in (number)
- size: limit of how many products are retrieved on this page (number)

Request example:

```(http://localhost:8082/products/category/1?page=0&size=3)```

The API will return the found products in a JSON format:
```json
[
    {
        "id": 1,
        "name": "Blueberries, 18 oz Container",
        "price": 6.84,
        "discount": 10.0,
        "description": "Create decadent meals with sweet and light Fresh Blueberries. Enjoy them for breakfast, lunch, dinner, or dessert. Use them to make a lemon and blueberry galette, bake them into delicious blueberry muffins, cook up a sweet and savory pizza topped with blueberries and bacon, or reduce them for a sauce to use on grilled chicken or cheesecake. They contain essential vitamins and nutrients like, vitamin C, vitamin K, antioxidants, and manganese making them perfect for a healthy diet. Prior to serving simply gently wash them with cool water and enjoy the fresh taste. Refrigerate the berries to keep them fresh and ready for use. Pick up a Fresh Blueberry container today and savor the delectable flavor.",
        "stock": 0,
        "imageUrl": "https://i5.walmartimages.com/seo/Fresh-Blueberries-18-oz-Container_66ee489f-0ddc-43b4-99ce-07c0a7bf886e_1.b805f72f3cafb54c77a036a6ab6f4ed4.jpeg",
        "category": {
            "id": 1,
            "name": "Fruits"
        },
        "review": [
            {
                "score": 4,
                "description": "delicious",
                "date": "2024-01-09T19:23:06.000+00:00"
            },
            {
                "score": 5,
                "description": "Very good quality",
                "date": "2024-01-09T22:41:59.000+00:00"
            }
        ]
    },
    {
        "id": 2,
        "name": "Strawberries, 2 lb Container",
        "price": 7.93,
        "discount": 10.0,
        "description": "The sweet, juicy flavor of Fresh Strawberries makes them a refreshing and delicious treat. Enjoy them for breakfast, lunch, dinner, or dessert. Use them as topping for pancakes, bake them in mouthwatering bread, mix them with cucumbers for a light and flavorful salad, or puree them for strawberry shortcake. They contain essential vitamins and nutrients like vitamin C, fiber, potassium, vitamin B, and magnesium making them perfect for a healthy diet. Before serving simply gently wash them, remove the leafy caps, and enjoy the fresh taste. Refrigerate the berries in the original Clam Shell to keep them fresh and ready for use. Pick up Fresh Strawberries today and savor the delectable flavor.",
        "stock": 0,
        "imageUrl": "https://i5.walmartimages.com/seo/Fresh-Strawberries-2-lb-Container_dd2bcd97-25af-4a91-9258-989853e16b2f_1.36dd4f1579a25d423741d9970de3ddac.jpeg",
        "category": {
            "id": 1,
            "name": "Fruits"
        },
        "review": []
    }
]
```

---

## 🛍️ Read Single Product Endpoint (All users)

### GET: /products/{productId}

Read a product by its ID to retrieve all its details.

Input parameters:

- productId: id of the product we want to see the details of (number)

Request example:

```(http://localhost:8082/products/1)```

The API will return the details of the product in a JSON format:
```json
{
    "id": 1,
    "name": "Blueberries, 18 oz Container",
    "price": 6.84,
    "discount": 10.0,
    "description": "Create decadent meals with sweet and light Fresh Blueberries. Enjoy them for breakfast, lunch, dinner, or dessert. Use them to make a lemon and blueberry galette, bake them into delicious blueberry muffins, cook up a sweet and savory pizza topped with blueberries and bacon, or reduce them for a sauce to use on grilled chicken or cheesecake. They contain essential vitamins and nutrients like, vitamin C, vitamin K, antioxidants, and manganese making them perfect for a healthy diet. Prior to serving simply gently wash them with cool water and enjoy the fresh taste. Refrigerate the berries to keep them fresh and ready for use. Pick up a Fresh Blueberry container today and savor the delectable flavor.",
    "stock": 0,
    "imageUrl": "https://i5.walmartimages.com/seo/Fresh-Blueberries-18-oz-Container_66ee489f-0ddc-43b4-99ce-07c0a7bf886e_1.b805f72f3cafb54c77a036a6ab6f4ed4.jpeg",
    "category": {
        "id": 1,
        "name": "Fruits"
    },
    "review": [
        {
            "score": 4,
            "description": "delicious",
            "date": "2024-01-09T19:23:06.000+00:00"
        },
        {
            "score": 5,
            "description": "Very good quality",
            "date": "2024-01-09T22:41:59.000+00:00"
        }
    ]
}
```

---

## 🛍️ Delete Product Endpoint (ADMIN users)

### DELETE: /products/{productId}

Delete a product by its ID.

Input parameters:

- productId: ID of the product we want to delete (number)

Request example:

```(http://localhost:8082/products/1)```

The API will return a 200 status code if the product has been deleted:

---

## :octocat: CI-Continuous Integration:

GitHub Actions is used for continuous integration. GitHub Actions is an automation tool that allows you to run automated workflows in response to specific events, such as the creation of a merge request. This tool automates test execution, application packaging, and deployment to different environments, all within the same workflow. This saves time and reduces errors by ensuring that the application is built correctly with every change made to the code. GitHub Actions allows you to customize configurations to meet the specific needs of the project.

## :bullettrain_side: CD-Continuous Deployment:

AWS is used for continuous deployment. The application hosting platform provides continuous deployment functionality that allows any code changes to be automatically deployed to a production environment. Using Github as a source, Railway triggers an automatic build and deployment to the production environment on every change to the repository without the need for manual intervention.
