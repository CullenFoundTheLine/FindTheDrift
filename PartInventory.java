import java.util.*;

public class PartInventory {
    private Map<String, List<Part>> partsByCategory = new HashMap<>();

    public void addPart(Part part) {
        partsByCategory.putIfAbsent(part.getCategory(), new ArrayList<>());
        partsByCategory.get(part.getCategory()).add(part);
    }

    public List<Part> getPartsByCategory(String category) {
        return partsByCategory.getOrDefault(category, new ArrayList<>());
    }

    public void showInventory() {
        for (String category : partsByCategory.keySet()) {
            System.out.println("Category: " + category);
            for (Part part : partsByCategory.get(category)) {
                System.out.println("- " + part.getName() + " (" + part.getSimBoost() + ")");
            }
        }
    }
}



