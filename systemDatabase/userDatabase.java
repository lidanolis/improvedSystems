package systemDatabase;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class userDatabase {
    private ArrayList<String> userList = new ArrayList<String>();
    protected String fileName = "user.txt"; // Replace with your file's name or path

    public userDatabase() {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                userList.add(line);
            }
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }
    }

    public Boolean validateUsername(String username) {
        for (String user : userList) {
            String[] name = user.split(";");
            if (name[0].equals(username)) {
                return true;
            }
        }
        return false;
    }

    public String getStockTypePermission(String username) {
        for (String user : userList) {
            String[] data = user.split(";");
            if (data[0].equals(username)) {
                return data[3];
            }
        }
        return "Invalid";
    }

    public Boolean validatePassword(String username, String password) {
        for (String user : userList) {
            String[] name = user.split(";");
            if (name[0].equals(username)) {
                if (name[1].equals(password)) {
                    return true;
                }
            }
        }
        return false;
    }

    public Boolean validateRole(String username, String password, String role) {
        for (String user : userList) {
            String[] name = user.split(";");
            if (name[0].equals(username)) {
                if (name[1].equals(password)) {
                    if (name[2].equals(role)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

}
