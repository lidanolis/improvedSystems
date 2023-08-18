package userRoles;

public abstract class abstractUser {
    private String name;
    private String password;
    private String role;

    public abstractUser(String name, String password, String role) {
        this.name = name;
        this.password = password;
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public abstract void showActions();
}
