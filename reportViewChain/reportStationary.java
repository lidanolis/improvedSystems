package reportViewChain;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import systemDatabase.userDatabase;

public class reportStationary extends abstractReportHandler {
    private ArrayList<String> stockList = new ArrayList<String>();
    protected String fileName = "electronicalStock.txt";

    @Override
    public void handle(String name) {
        userDatabase validation = new userDatabase();
        String stockType = validation.getStockTypePermission(name);

        if (stockType.equals("Stationary")) {
            try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    stockList.add(line);
                }
            } catch (IOException e) {
                System.err.println("Error reading the file: " + e.getMessage());
            }

            System.out.println("\nStationary Report");
            for (String aStock : stockList) {
                String[] stk = aStock.split(";");
                System.out.println("Stock Name:" + stk[0]);
                System.out.println("Stock Type:" + stk[1]);
                System.out.println("Stock Description:" + stk[2]);
                System.out.println("Stock Quantity:" + stk[3]);
                System.out.println("Stock Supplier:" + stk[4]);
                System.out.println("Restocker:" + stk[5]);
                System.out.println("Stock Warehouse Location:" + stk[6]);
                System.out.println("\n");
            }

        } else {
            System.out.println("\nInvalid Acquitance");
        }

    }

}
