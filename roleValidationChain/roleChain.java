package roleValidationChain;

import userRoles.abstractUser;

public class roleChain {
    private abstractRoleHandler handler;

    public roleChain(abstractRoleHandler handler) {
        this.handler = handler;
    }

    public abstractUser logIn(String name, String password, String role) {
        abstractUser user = handler.handle(name, password, role);
        if (user == null) {
            return null;
        }
        return user;
    }

}
