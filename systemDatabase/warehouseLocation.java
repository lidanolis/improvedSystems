package systemDatabase;

import java.util.ArrayList;
import java.util.List;

public class warehouseLocation {

    private List<String> locations = new ArrayList<>();

    public int listSize() {
        return locations.size();
    }

    public warehouseLocation() {
        locations.add("123 Main St, CityA, StateA ZIP12345");
        locations.add("456 Elm St, CityB, StateB ZIP67890");
        locations.add("789 Oak St, CityC, StateC ZIP54321");
        locations.add("101 Maple St, CityD, StateD ZIP98765");
        locations.add("202 Pine St, CityE, StateE ZIP43210");
    }

    public void showWarehouseLocations() {
        int i = 1;
        for (String location : locations) {
            System.out.println("(" + i + ") " + location);
            i++;
        }
    }

    public String getWarehosueLocation(int number) {
        return locations.get(number);
    }

}
