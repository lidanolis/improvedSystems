package roleValidationChain;

import systemDatabase.userDatabase;
import userRoles.abstractUser;

public class rolePassword extends abstractRoleHandler {

    @Override
    public abstractUser handle(String username, String password, String role) {
        userDatabase validation = new userDatabase();
        if (!(validation.validatePassword(username, password))) {
            System.out.println("Invalid Password");
            return null;
        } else {
            return handleNext(username, password, role);
        }
    }

}
