package roleValidationChain;

import userRoles.abstractUser;

public abstract class abstractRoleHandler {

    private abstractRoleHandler nextHandler;

    public abstractRoleHandler setNextRoleHandler(abstractRoleHandler nextHandler) {
        this.nextHandler = nextHandler;
        return this;
    }

    public abstract userRoles.abstractUser handle(String username, String password, String role);

    protected abstractUser handleNext(String username, String pasword, String role) {
        if (nextHandler == null) {
            System.out.println("Invalid Role");
            return null;
        }
        return nextHandler.handle(username, pasword, role);
    }
}
