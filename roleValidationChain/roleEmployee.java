package roleValidationChain;

import systemDatabase.userDatabase;
import userRoles.abstractUser;
import userRoles.userEmployee;

public class roleEmployee extends abstractRoleHandler {

    @Override
    public abstractUser handle(String username, String password, String role) {
        userDatabase validation = new userDatabase();
        if (validation.validateRole(username, password, role) && role.equals("Employee")) {
            String stockType = validation.getStockTypePermission(username);
            return new userEmployee(username, password, role, stockType);
        } else {
            return handleNext(username, password, role);
        }
    }

}
