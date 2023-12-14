import javafx.scene.transform.Scale;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

class Product {
    private String name;
    private double price;
    private int quantity;
//    private Product product;

    public Product(String name,double price, int quantity){
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }
    public String getName(){
        return name;
    }
    public double getPrice(){
        return price;
    }
    public int getQuantity(){
        return quantity;
    }
    public void setPrice(double price){
        this.price = price;
    }
    public void setQuantity(int quantity){
        this.quantity = quantity;
    }

    public void sell(int quantitySold) {
        if(quantitySold <= this.quantity) {
            this.quantity -= quantitySold;
        }
    }
}

class Sale {
    private String productName;
    private int quantitySold;
    private double totalAmount;

    public Sale(String productName, int quantitySold,double totalAmount) {
        this.productName = productName;
        this.quantitySold = quantitySold;
        this.totalAmount = totalAmount;
    }

    public String getProductName(){
        return productName;
    }
    public int getQuantitySold(){
        return quantitySold;
    }
    public double getTotalAmount(){
        return totalAmount;
    }
}

 class Inventory{
    private List<Product> products;
    private List<Sale> sales;

    public Inventory(){
        products = new ArrayList<>();
        sales = new ArrayList<>();
    }

    public void addProduct(Product product){
        products.add(product);
        System.out.println("Added product : " + product.getName());
    }

    public void updateProduct(String productName, double newPrice , int newQuatity){
        for (Product product : products) {
            if(product.getName().equals(productName)) {
                product.setPrice(newPrice);
                product.setQuantity(newQuatity);
                System.out.println("Update Product : " + product.getName());
                return;
            }
        }
        System.out.println("Product not found : " + productName);
    }

    public void removeProduct(String productName) {
        for (Iterator<Product> iterator = products.iterator();iterator.hasNext();) {
            Product product = iterator.next();
            if(product.getName().equals(productName)) {
                iterator.remove();
                System.out.println("Removed product : " + productName);
                return;
            }
        }
        System.out.println("Product not found : " + productName);
    }

    public void trackStock(){
        System.out.println("Stock levels : ");
        for(Product product : products) {
            System.out.println(product.getName() + ": " + product.getQuantity());
            if(product.getQuantity() <10){
                System.out.println("***Low stock alert*** : " + product.getName());
            }
        }
    }
    public void generateReport() {
        System.out.println("Inventory Report");
        System.out.println("Name\tPrice\tQuantity\tValue");
        double totalValue = 0;

        for (Product product : products) {
            System.out.println(
                    product.getName() + " \t " + product.getPrice() + " \t " + product.getQuantity()
                            + " \t " + (product.getPrice() * product.getQuantity())
            );
            totalValue += product.getPrice() * product.getQuantity();

        if (product.getQuantity() < 10) {
            System.out.println("***Low stock alert*** : " + product.getName());
        }
    }

        System.out.println("Total Value : " + totalValue);
    }

    public void recordSale(String productName, int quantitySold) {
        for (Product product : products) {
            if(product.getName().equals(productName)) {
                if(quantitySold <= product.getQuantity()) {
                    double totalAmount = quantitySold * product.getPrice();
                    Sale sale = new Sale(productName,quantitySold,totalAmount);
                    sales.add(sale);
                    product.sell(quantitySold);
                    System.out.println("Sold " + quantitySold + " units of " + productName);
                }else {
                    System.out.println("Not enough stock of : " + productName);
                }
                return;
            }
        }
        System.out.println("Product not found : " + productName);
    }

    public void notifyLowStock() {
        System.out.println("Low stock alert : ");
        for (Product product : products) {
            if(product.getQuantity() < 10) {
                System.out.println(product.getName() + ": " + product.getQuantity());
            }
        }
    }

    public void generateSalesReport() {
        System.out.println("Sales Reports : ");
        System.out.println("Product\tQuantity\tTotal Amount ");
        for (Sale sale : sales) {
            System.out.println(
                    sale.getProductName() + " \t " + sale.getQuantitySold() + " \t " + sale.getTotalAmount()
            );
        }
    }
 }

public class InventoryManagementApp {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Inventory inventory = new Inventory();

        while(true){
            System.out.println("Menu: ");
            System.out.println("1. Product Management ");
            System.out.println("2. Stock Tracking ");
            System.out.println("3. Sales Record ");
            System.out.println("4. Report Generator");
            System.out.println("5. Exit ");

            System.out.print("Enter your choice (1-5): ");
            int choice = sc.nextInt();
            sc.nextLine(); // consume for next line character

            switch (choice) {
                case 1:
                    //Sub-menu of Product management
                    while (true) {
                        System.out.println("Product Management: ");
                        System.out.println("1. Add Product ");
                        System.out.println("2. Update Product ");
                        System.out.println("3. Remove Product ");
                        System.out.println("4. Sale product ");
                        System.out.println("5. Back to Main Menu ");

                        System.out.print("Enter your choice (1-5): ");
                        int pMchoice = sc.nextInt();
                        sc.nextLine(); // consume for next line character

                        switch (pMchoice) {
                            case 1:
                                System.out.print("Enter Product name : ");
                                String name = sc.nextLine();
                                System.out.print("Enter product price : ");
                                double price = sc.nextDouble();
                                System.out.print("Enter initial quantity : ");
                                int quantity = sc.nextInt();
                                sc.nextLine(); // Consume the newline character
                                Product newProduct = new Product(name, price, quantity);
                                inventory.addProduct(newProduct);
                                break;

                            case 2:
                                System.out.print("Enter Product name to update : ");
                                String updatename = sc.nextLine();
                                System.out.print("Enter new product price : ");
                                double updateprice = sc.nextDouble();
                                System.out.print("Enter new quantity : ");
                                int updatequantity = sc.nextInt();
                                sc.nextLine(); // Consume the newline character
                                inventory.updateProduct(updatename, updateprice, updatequantity);
                                break;

                            case 3:
                                System.out.println("Enter product name to remove : ");
                                String removeName = sc.nextLine();
                                inventory.removeProduct(removeName);
                                break;

                            case 4:
                                System.out.println("Enter product name to record sale : ");
                                String saleProductName = sc.nextLine();
                                System.out.print("Enter quantity sold : ");
                                int saleQuantity = sc.nextInt();
                                sc.nextLine();
                                inventory.recordSale(saleProductName, saleQuantity);
                                break;

                            case 5:
                                //Back to the main menu
                                break;

                            default:
                                System.out.println("Invalid choice. Please enter a numer between 1 and 5. ");
                        }

                        if (pMchoice == 5) {
                            // Exit the product management sub-menu
                            break;
                        }
                    }
                    break;

                case 2:
                    while(true){
                        System.out.println("1. Notify Low Stock ");
                        System.out.println("2. Track Stock ");
                        System.out.println("3. Back to Main Menu ");

                        System.out.print("Enter your choice (1-3): ");
                        int stockTrackingchoice = sc.nextInt();
                        sc.nextLine(); // Consume the newline character

                        switch (stockTrackingchoice) {
                            case 1:
                             inventory.notifyLowStock();
                            break;

                            case 2:
                              inventory.trackStock();
                            break;

                             case 3:
                               //Back to the main menu
                             break;

                             default:
                                 System.out.println("Invalid choice. Please enter a number between 1 and 3. ");

                        }
                    if (stockTrackingchoice == 3) {
                        // Exit the product management sub-menu
                        break;
                      }
                    }
                    break;

                case 3:
                    while (true){
                        System.out.println("1. Sale Record ");
                        System.out.println("2. Track Stock ");
                        System.out.println("3. Back to Main Menu ");

                        System.out.println("Enter your choice (1-3) : ");
                        int saleRecchoice = sc.nextInt();
                        sc.nextLine();

                        switch(saleRecchoice) {
                            case 1:
                                inventory.generateSalesReport();
                                break;

                            case 2:
                                inventory.trackStock();
                                break;

                            case 3:
                                //Back to the main menu
                                break;

                            default:
                                System.out.println("Invalid choice. Please enter a number between 1 and 3. ");
                        }
                        if (saleRecchoice == 3) {
                            // Exit the product management sub-menu
                             break;
                            }
                    }
                    break;

                case 4:
                    inventory.generateReport();;
                    break;

                case 5:
                    System.out.println("Exiting the Inventory Management System : ");
                    System.exit(0);

                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 5. ");
            }
        }
    }
}
