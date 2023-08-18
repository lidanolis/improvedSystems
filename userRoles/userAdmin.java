package userRoles;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import reportViewChain.abstractReportHandler;
import reportViewChain.reportChain;
import reportViewChain.reportElectronical;
import reportViewChain.reportStationary;
import systemDatabase.userDatabase;

public class userAdmin extends abstractUser {

    public Scanner scanner = new Scanner(System.in);
    private List<String> roles = new ArrayList<>();
    private List<String> acquintances = new ArrayList<>();

    public userAdmin(String name, String password, String role) {
        super(name, password, role);

        roles.add("Employee");
        roles.add("Admin");
        acquintances.add("Stationary");
        acquintances.add("Electronical");
    }

    @Override
    public void showActions() {
        while (true) {

            String action = "";
            System.out.println("Welcome Back Employee: " + super.getName());
            System.out.println("(1) View Report");
            System.out.println("(2) Register New User");
            System.out.println("(3) Exit");
            System.out.print("Enter Your Action:");
            action = scanner.nextLine();

            if (action.equals("1")) {
                viewReport(super.getName());
                continue;
            } else if (action.equals("2")) {
                createStock();
                continue;
            } else if (action.equals("3")) {
                break;
            } else {
                System.out.println("Invalid Action");
            }
        }
    }

    public void createStock() {
        userDatabase usd = new userDatabase();
        while (true) {
            String name = "";
            String password = "";
            String role = "";
            String acquintance = "";

            System.out.println("Please Make Sure All Information Is Correctly Inputted " + super.getName() + ":");
            System.out.println("New User Creation");
            System.out.print("Username:");
            name = scanner.nextLine();
            System.out.print("Password:");
            password = scanner.nextLine();

            System.out.println("Role:");
            int i = 1;
            for (String arole : roles) {
                System.out.println("(" + i + ") " + arole);
                i++;
            }
            System.out.print("Role Selection:");
            role = scanner.nextLine();

            System.out.println("Warehouse Acquinted:");
            i = 1;
            for (String aacquintance : acquintances) {
                System.out.println("(" + i + ") " + aacquintance);
                i++;
            }
            System.out.print("Acquitance Selection:");
            acquintance = scanner.nextLine();

            if (name == "" || password == "" || role == "" || acquintance == "") {
                System.out.println("Invalid Information!");
                break;
            } else {
                try {

                    int numericRole = Integer.parseInt(role);
                    int numericAcquintance = Integer.parseInt(acquintance);

                    if (numericRole > roles.size() || numericRole <= 0 || numericAcquintance > acquintances.size()
                            || numericAcquintance <= 0) {
                        System.out.println("Invalid Numeric Values");
                        break;
                    }

                    if (usd.validateUsername(name)) {
                        System.out.println("Username Already Exist, Please Pick Another One");
                    } else {

                        try {
                            String data = name + ";" + password + ";" + roles.get(numericRole - 1) + ";"
                                    + acquintances.get(numericAcquintance - 1);
                            File file = new File("user.txt");
                            FileWriter fw = new FileWriter(file, true);
                            fw.write(data + "\n");
                            fw.close();
                            System.out.println("Success");
                        } catch (Exception e) {
                            System.out.println("Failed");
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

    public void viewReport(String name) {
        abstractReportHandler handler = new reportElectronical();
        handler.setNextReportHandler(new reportStationary());
        reportChain rChain = new reportChain(handler);
        rChain.viewReport(name);

    }

}