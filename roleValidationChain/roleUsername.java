package roleValidationChain;

import systemDatabase.userDatabase;
import userRoles.abstractUser;

public class roleUsername extends abstractRoleHandler {

    @Override
    public abstractUser handle(String username, String password, String role) {
        userDatabase validation = new userDatabase();
        if (!(validation.validateUsername(username))) {
            System.out.println("Invalid Username");
            return null;
        } else {
            return handleNext(username, password, role);
        }
    }

}
