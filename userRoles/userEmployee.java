package userRoles;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import abstractStockFactory.abstractWarehouseFactory;
import abstractStockFactory.electronicalStock;
import abstractStockFactory.electronicalWarehouse;
import abstractStockFactory.stationaryStock;
import abstractStockFactory.stationaryWarehouse;
import systemDatabase.userDatabase;
import systemDatabase.warehouseLocation;

public class userEmployee extends abstractUser {

    private String stockType;
    public Scanner scanner = new Scanner(System.in);

    public userEmployee(String name, String password, String role, String stockType) {
        super(name, password, role);
        this.stockType = stockType;
    }

    @Override
    public void showActions() {
        while (true) {

            String action = "";
            System.out.println("Welcome Back Employee: " + super.getName());
            System.out.println("Enter Your Action");
            System.out.println("(1) Add New Stock");
            System.out.println("(2) Edit Stock");
            System.out.println("(3) Exit");
            System.out.print("Your Action:");
            action = scanner.nextLine();

            if (action.equals("3")) {
                break;
            } else if (action.equals("1")) {
                createStock();
                continue;
            } else if (action.equals("2")) {
                editStock(super.getName());
                continue;
            } else {
                System.out.println("Invalid Action");
            }
        }
    }

    public void createStock() {
        while (true) {
            warehouseLocation locationList = new warehouseLocation();
            // name, description, quantity, supplierName, employeeName, location
            String name = "";
            String description = "";
            String quantity = "";
            String supplierName = "";
            String employeeName = "";
            String location = "";
            String stocktype = "";
            System.out.println("Please Make Sure All Information Is Correctly Inputted " + super.getName() + ":");
            System.out.println("New Stock Creation");
            System.out.print("Stock Name:");
            name = scanner.nextLine();
            System.out.print("Stock Description:");
            description = scanner.nextLine();
            System.out.print("Stock Quantity:");
            quantity = scanner.nextLine();

            System.out.println("Type of Stock:");
            if (stockType.equals("Stationary")) {
                System.out.println("(1) Brush Set");
                System.out.println("(2) Crayon Set");
            } else if (stockType.equals("Electronical")) {
                System.out.println("(1) Microchip");
                System.out.println("(2) Motherboard");
            }
            System.out.print("Stock In Creation:");
            stocktype = scanner.nextLine();

            System.out.print("Stock Supplier Name:");
            supplierName = scanner.nextLine();
            locationList.showWarehouseLocations();
            System.out.print("WareHouse:");
            location = scanner.nextLine();

            if (name == "" || description == "" || quantity == "" || supplierName == "" || location == ""
                    || stocktype == "") {
                System.out.println("Invalid Information!");
                break;
            } else {
                try {
                    int numericQuantity = Integer.parseInt(quantity);
                    int numericLocation = Integer.parseInt(location);
                    int numericStocktype = Integer.parseInt(stocktype);

                    if (numericLocation > locationList.listSize() || numericLocation <= 0 || numericQuantity <= 0
                            || numericStocktype <= 0) {
                        System.out.println("Invalid Numeric Values");
                        break;
                    }

                    employeeName = super.getName();
                    location = locationList.getWarehosueLocation(numericLocation - 1);

                    if (stockType.equals("Stationary")) {
                        System.out.println("Generating Stock...");
                        abstractWarehouseFactory<stationaryStock> awf = new stationaryWarehouse(name, description,
                                numericQuantity, supplierName, employeeName, location);
                        stationaryStock ss = awf.create(numericStocktype);
                        if (ss != null) {
                            ss.generateStock();
                            System.out.println("New Stationary Stock Generated");
                        } else {
                            throw new Exception();
                        }
                    } else if (stockType.equals("Electronical")) {
                        System.out.println("Generating Stock...");
                        abstractWarehouseFactory<electronicalStock> awf = new electronicalWarehouse(name, description,
                                numericQuantity, supplierName, employeeName, location);
                        electronicalStock ss = awf.create(numericStocktype);
                        if (ss != null) {
                            ss.generateStock();
                            System.out.println("New Electronical Stock Generated");
                        } else {
                            throw new Exception();
                        }

                    }
                    break;
                } catch (Exception e) {
                    System.err.println("Invalid Inputs");
                    break;
                }

            }
        }
    }

    public void editStock(String name) {
        ArrayList<String> stockList = new ArrayList<String>();
        ArrayList<String> filterStockList = new ArrayList<String>();
        ArrayList<Integer> filterStockListNumber = new ArrayList<Integer>();
        userDatabase validation = new userDatabase();
        String stockType = validation.getStockTypePermission(name);
        String filePath;

        if (stockType.equals("Electronical")) {
            filePath = "electronicalStock.txt";
        } else if (stockType.equals("Stationary")) {
            filePath = "stationaryStock.txt";
        } else {
            filePath = "Invalid";
        }

        if (!(filePath.equals("Invalid"))) {

            try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    stockList.add(line);

                    String[] addFilter = line.split(";");
                    if (addFilter[5].equals(name)) {
                        filterStockList.add(line);
                        filterStockListNumber.add(stockList.size() - 1);
                    }
                }
            } catch (IOException e) {
                System.err.println("Error reading the file: " + e.getMessage());
            }

            if (filterStockList.isEmpty()) {
                System.out.println("\nNo Personally Added Stock");
            } else {
                for (int i = 0; i < filterStockList.size(); i++) {
                    String[] astk = filterStockList.get(i).split(";");
                    System.out.println("\n(" + (i + 1) + ") Stock:");
                    System.out.println("Stock Name:" + astk[0]);
                    System.out.println("Stock Type:" + astk[1]);
                    System.out.println("Stock Description:" + astk[2]);
                    System.out.println("Stock Quantity:" + astk[3]);
                    System.out.println("Stock Supplier:" + astk[4]);
                    System.out.println("Restocker:" + astk[5]);
                    System.out.println("Stock Warehouse Location:" + astk[6]);
                    System.out.println("\n");
                }

                while (true) {
                    String selection;

                    System.out.print("\nInput 0 to Exit");
                    System.out.print("Stock To Edit:");
                    selection = scanner.nextLine();

                    if (selection == "") {
                        System.out.println("Invalid Information!");
                        break;
                    } else {
                        try {
                            int numericSelection = Integer.parseInt(selection);

                            if (numericSelection == 0) {
                                break;
                            }
                            if (numericSelection > filterStockList.size() || numericSelection < 0) {
                                System.out.println("Invalid Numeric Values");
                                break;
                            }

                            String[] editStock = filterStockList.get(numericSelection - 1).split(";");
                            while (true) {
                                String action = "";
                                System.out.println("(1) Stock Name:" + editStock[0]);
                                System.out.println("(2) Stock Type:" + editStock[1]);
                                System.out.println("(3) Stock Description:" + editStock[2]);
                                System.out.println("(4) Stock Quantity:" + editStock[3]);
                                System.out.println("(5) Stock Supplier:" + editStock[4]);
                                System.out.println("(6) Restocker:" + editStock[5]);
                                System.out.println("(7) Stock Warehouse Location:" + editStock[6]);
                                System.out.println("(8) Cancel Action");
                                System.out.println("(9) Confirm Modification");
                                System.out.println("\n");
                                System.out.print("Modification Section:");
                                action = scanner.nextLine();

                                try {
                                    int numericAction = Integer.parseInt(action);

                                    if (numericAction > 9 || numericAction <= 0) {
                                        System.out.println("Invalid Numeric Values");
                                        continue;
                                    }

                                    if (numericAction == 8) {
                                        break;
                                    } else if (numericAction == 9) {
                                        String combined = editStock[0] + ";" + editStock[1] + ";" + editStock[2]
                                                + ";" + editStock[3] + ";" + editStock[4] + ";" + editStock[5]
                                                + ";" + editStock[6];
                                        filterStockList.set(numericSelection - 1, combined);

                                        int j = 0;
                                        for (int i : filterStockListNumber) {
                                            stockList.set(i, filterStockList.get(j));
                                            j++;
                                        }

                                        try {
                                            String data = "";
                                            for (String newstk : stockList) {
                                                data = data + newstk + "\n";
                                            }

                                            BufferedWriter writer = new BufferedWriter(
                                                    new FileWriter(filePath));

                                            writer.write(data);
                                            writer.close();

                                            System.out.println("Stock Has Been Updated");
                                            break;
                                        } catch (IOException e) {
                                            System.err.println("An error occurred: " + e.getMessage());
                                            break;
                                        }
                                    }

                                    String modifyString = "";
                                    System.out.print("Modify To:");
                                    modifyString = scanner.nextLine();

                                    if (!(modifyString.equals(""))) {
                                        editStock[numericAction - 1] = modifyString;
                                    } else {
                                        System.out.print("New Value Cannot Be Empty!\n");
                                        continue;
                                    }

                                } catch (Exception e) {
                                    System.err.println("Invalid Inputs");
                                    continue;
                                }
                            }
                        } catch (Exception e) {
                            System.err.println("Invalid Inputs");
                            break;
                        }

                    }
                }
            }

        }

    }

}
