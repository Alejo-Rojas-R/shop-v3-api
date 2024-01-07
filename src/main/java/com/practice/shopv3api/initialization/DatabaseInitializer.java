package com.practice.shopv3api.initialization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

@Component
public class DatabaseInitializer implements ApplicationRunner {

    @Autowired
    private EntityManager entityManager;

    @Override
    @Transactional
    public void run(ApplicationArguments args) {
        // Check if the category table is empty
        Query categoryQuery = entityManager.createQuery("SELECT COUNT(c) FROM Category c");
        long categoryCount = (long) categoryQuery.getSingleResult();

        // Check if the product table is empty
        Query productQuery = entityManager.createQuery("SELECT COUNT(p) FROM Product p");
        long productCount = (long) productQuery.getSingleResult();

        // Check if the user table is empty
        Query userQuery = entityManager.createQuery("SELECT COUNT(u) FROM User u");
        long userCount = (long) userQuery.getSingleResult();

        // Insert data only if the tables are empty
        if (categoryCount == 0 && productCount == 0 && userCount == 0) {
            executeInsertScripts();
        }
    }

    private void executeInsertScripts() {
        // Insert data into the category table
        Query insertCategoryQuery = entityManager.createNativeQuery("INSERT INTO category (id, name) VALUES " +
                "(1,'Fruits'), (2,'Vegetables'), (3,'Dairy'), (4,'Meat'), " +
                "(5,'Snacks'), (6,'Bread'), (7,'Beverages'), (8,'Pasta, Rice & Cereal'), " +
                "(9,'Personal Care'), (10,'Pet Care'), (11,'Cleaning Supplies')");
        insertCategoryQuery.executeUpdate();

        // Insert data into the product table
        Query insertProductQuery = entityManager.createNativeQuery("INSERT INTO product (id, description, stock, image_url, name, price, discount, category_id) VALUES " +
                "(1,'Create decadent meals with sweet and light Fresh Blueberries...', 0, 'https://i5.walmartimages.com/seo/Fresh-Blueberries-18-oz-Container_66ee489f-0ddc-43b4-99ce-07c0a7bf886e_1.b805f72f3cafb54c77a036a6ab6f4ed4.jpeg', 'Blueberries, 18 oz Container', 6.84, 10, 1), " +
                "(2,'The sweet, juicy flavor of Fresh Strawberries make them a refreshing and delicious treat...', 0, 'https://i5.walmartimages.com/seo/Fresh-Strawberries-2-lb-Container_dd2bcd97-25af-4a91-9258-989853e16b2f_1.36dd4f1579a25d423741d9970de3ddac.jpeg', 'Strawberries, 2 lb Container', 7.93, 10, 1), " +
                "(3,'Stock up on several of these juicy Lemons to enjoy with each day while meal planning...', 0, 'https://i5.walmartimages.com/seo/Fresh-Lemon-Each_f025c57c-13e1-4a1a-ac81-0695aaf2473d.7f3c9f067735c2730223c9147a646f7c.jpeg', 'Lemon', 0.58, 10, 1), " +
                "(4,'Enjoy the sweet, refreshing taste of a Seedless Watermelon. Seedless Watermelon is great for breakfast, lunch, dessert, or when you want a snack...', 0, 'https://i5.walmartimages.com/seo/Fresh-Seedless-Watermelon-Each_e2ec527d-fe7b-4309-9373-186de34557cf.1c562d1a69a2a8f4cb7b5de8f125fc76.jpeg', 'Seedless Watermelon', 7.97, 10, 1), " +
                "(5,'Enjoy a burst of tropical flavor with this Fresh Pineapple. This pineapple can be a satisfying afternoon snack, or you can use it in a variety of recipes...', 0.3, 'https://i5.walmartimages.com/seo/Fresh-Pineapple-Each_1d2b3723-b31f-481d-ae30-c82fcbb59e20.d2e4de8d8b987f98a6e9da93a7e8c752.jpeg', 'Pineapple', 2.04, 10, 1), " +
                "(6,'Treat yourself to the refreshing flavor of a fresh Cantaloupe. Enjoy this tasty melon on its own as a healthy snack or incorporate it into a variety of delicious recipes...', 0, 'https://i5.walmartimages.com/seo/Fresh-Cantaloupe-Each_fb4c18a5-9367-4770-b99f-7518c72db482.5609c32e87a3110b734aad048bf9fe35.jpeg', 'Cantaloupe', 2.98, 10, 1), " +
                "(7,'Treat your family to the wildly juicy, sweet and crisp taste of Envy Apples. With a classic apple cider flavor, these apples have a firm texture with flesh that is slow to brown...', 0, 'https://i5.walmartimages.com/seo/Fresh-Envy-Apples-Each_32451a10-0563-426a-9a16-a8865b2c3774_3.b3be01fcc4c956f51fe3890589897d31.jpeg', 'Apple', 1.18, 10, 1)");
        insertProductQuery.executeUpdate();

        // Insert data into the user table
        Query insertUserQuery = entityManager.createNativeQuery("INSERT INTO user (id, address, email, name, last_name, password, phone, role) VALUES " +
                "(1, 'Calle 97 #80', 'rojas.pop.prod@gmail.com', 'Admin', 'Admin', '$2a$08$MaAfL5L9z7YMskGxnkhdK.Bq.OkWbpRt3MMcJ/bKvEffyXSjuy3ju', 318346, 'ADMIN')");
        insertUserQuery.executeUpdate();
    }
}