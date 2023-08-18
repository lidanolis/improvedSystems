package roleValidationChain;

import systemDatabase.userDatabase;
import userRoles.abstractUser;
import userRoles.userAdmin;

public class roleAdmin extends abstractRoleHandler {

    @Override
    public abstractUser handle(String username, String password, String role) {
        userDatabase validation = new userDatabase();
        if (validation.validateRole(username, password, role) && role.equals("Admin")) {
            return new userAdmin(username, password, role);
        } else {
            return handleNext(username, password, role);
        }
    }

}
