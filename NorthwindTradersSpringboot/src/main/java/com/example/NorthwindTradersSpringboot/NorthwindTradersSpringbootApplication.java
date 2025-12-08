package com.example.NorthwindTradersSpringboot;

import com.example.NorthwindTradersSpringboot.dao.ProductDao;
import com.example.NorthwindTradersSpringboot.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;


@SpringBootApplication
public class NorthwindTradersSpringbootApplication implements CommandLineRunner {

    @Autowired
    private ProductDao productDao;   // Dependency Injection

    public static void main(String[] args) {
        SpringApplication.run(NorthwindTradersSpringbootApplication.class, args);
    }

    @Override
    public void run(String... args) {

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== PRODUCT ADMIN MENU ===");
            System.out.println("1. List Products");
            System.out.println("2. Add Product");
            System.out.println("3. Exit");

            System.out.print("Enter choice: ");
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    productDao.getAll().forEach(System.out::println);
                    break;

                case 2:
                    System.out.print("Enter product ID: ");
                    int id = Integer.parseInt(scanner.nextLine());

                    System.out.print("Enter product name: ");
                    String name = scanner.nextLine();

                    System.out.print("Enter category: ");
                    String category = scanner.nextLine();

                    System.out.print("Enter price: ");
                    double price = Double.parseDouble(scanner.nextLine());

                    Product product = new Product(id, name, category, price);
                    productDao.add(product);

                    System.out.println("Product added!");
                    break;

                case 3:
                    System.out.println("Goodbye!");
                    return;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}


