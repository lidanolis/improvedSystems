package abstractStockFactory;

public class stationaryWarehouse implements abstractWarehouseFactory<stationaryStock> {
    private String name;
    private String description;
    private int quantity;
    private String supplierName;
    private String employeeName;
    private String location;

    public stationaryWarehouse(String name, String description, int quantity, String supplierName, String employeeName,
            String location) {
        this.name = name;
        this.description = description;
        this.quantity = quantity;
        this.supplierName = supplierName;
        this.employeeName = employeeName;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public stationaryStock create(int stockType) {
        if (stockType == 1) {
            return new brushSet(name, description, quantity, supplierName, employeeName, location);
        } else if (stockType == 2) {
            return new crayonSet(name, description, quantity, supplierName, employeeName, location);
        }

        return null;
    }

}
