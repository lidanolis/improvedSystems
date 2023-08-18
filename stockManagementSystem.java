import java.util.Scanner;

import roleValidationChain.abstractRoleHandler;
import roleValidationChain.roleAdmin;
import roleValidationChain.roleChain;
import roleValidationChain.roleEmployee;
import roleValidationChain.rolePassword;
import roleValidationChain.roleUsername;
import userRoles.abstractUser;

class stockManagementSystem {
    public static void main(String[] args) {

        abstractRoleHandler handler = new roleUsername();
        handler.setNextRoleHandler(
                new rolePassword().setNextRoleHandler(new roleEmployee().setNextRoleHandler(new roleAdmin())));

        roleChain chain = new roleChain(handler);
        abstractUser user = null;
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String action = "";
            System.out.println("Welcome");
            System.out.println("(1) Login");
            System.out.println("(2) Exit");
            System.out.print("Your Action:");
            action = scanner.nextLine();

            if (action.equals("1")) {
                while (true) {

                    String name = "";
                    String password = "";
                    String role = "";

                    System.out.print("Enter your username: ");
                    name = scanner.nextLine();

                    System.out.print("Enter your password: ");
                    password = scanner.nextLine();

                    System.out.println("Role:");
                    System.out.println("(1) Employee");
                    System.out.println("(2) Admin");
                    System.out.print("Enter your role: ");
                    role = scanner.nextLine();

                    if (role.equals("1")) {
                        role = "Employee";
                    } else if (role.equals("2")) {
                        role = "Admin";
                    } else {
                        System.out.println("Invalid Input");
                        continue;
                    }

                    user = chain.logIn(name, password, role);
                    if (user != null) {
                        break;
                    }

                }
                user.showActions();
            } else if (action.equals("2")) {
                break;
            } else {
                System.out.println("Invalid Input");
            }

        }
        scanner.close();
    }
}