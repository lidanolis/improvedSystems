package abstractStockFactory;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class crayonSet implements stationaryStock {

    private static final String STOCKTYPE = "CrayonSet";

    private String name;
    private String description;
    private int quantity;
    private String supplierName;
    private String employeeName;
    private String location;

    public crayonSet(String name, String description, int quantity, String supplierName, String employeeName,
            String location) {
        this.name = name;
        this.description = description;
        this.quantity = quantity;
        this.supplierName = supplierName;
        this.employeeName = employeeName;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    @Override
    public void getStationaryStock() {
        System.out.println("Stock Name:" + name);
        System.out.println("Stock Type:" + STOCKTYPE);
        System.out.println("Stock Description:" + description);
        System.out.println("Stock Quantity:" + quantity);
        System.out.println("Stock Supplier:" + supplierName);
        System.out.println("Restocker:" + employeeName);
        System.out.println("Stock Warehouse Location:" + location);
    }

    @Override
    public void generateStock() {
        String filePath = stationaryStock.STORAGE;
        String data = name + ";" + STOCKTYPE + ";" + description + ";" + quantity + ";" + supplierName + ";"
                + employeeName + ";"
                + location;

        try {
            // Create a File instance for the specified file path
            File file = new File(filePath);

            // Check if the file exists
            if (file.exists()) {
                // If the file exists, append the data
                FileWriter writer = new FileWriter(file, true); // true for append mode
                writer.write(data + "\n"); // Add a new line for the appended data
                writer.close();
            } else {
                // If the file doesn't exist, create and write data
                FileWriter writer = new FileWriter(file);
                writer.write(data);
                writer.close();
            }
        } catch (IOException e) {
            System.err.println("An error occurred: " + e.getMessage());
        }
    }

}
