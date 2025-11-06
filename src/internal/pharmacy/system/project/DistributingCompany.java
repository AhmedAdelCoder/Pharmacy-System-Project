
package internal.pharmacy.system.project;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DistributingCompany {

    private final String companyID;
    private String name;
    private String email;
    private String location;
    private final List<Product> suppliedProducts;

//------------------------------- Constructors ---------------------------------
    public DistributingCompany(String companyID, String name, String email, String location) {
        if (companyID == null || name == null) {
            throw new IllegalArgumentException("Company ID and name required.");
        }
        this.companyID = companyID.trim();
        this.name = name.trim();
        this.email = email != null ? email.trim() : "";
        this.location = location != null ? location.trim() : "";
        this.suppliedProducts = new ArrayList<>();
    }

//--------------------- setter and getter --------------------------------------
    public String getCompanyID() {
        return companyID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name != null && !name.isBlank()) {
            this.name = name.trim();
        }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (email != null) {
            this.email = email.trim();
        }
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        if (location != null) {
            this.location = location.trim();
        }
    }

    public List<Product> getSuppliedProducts() {
        return new ArrayList<>(suppliedProducts);
    }

//-------------------------------- methods -------------------------------------
    
    public boolean addProduct(Product product) {
        if (product == null) {
            return false;
        }
        for (Product p : suppliedProducts) {
            if (p.getProductID() == product.getProductID()) {
                return false;
            }
        }
        suppliedProducts.add(product);
        return true;
    }

    // Remove product by productID
    public boolean removeProductById(int productID) {
        Optional<Product> toRemove = suppliedProducts.stream()
                .filter(p -> p.getProductID() == productID)
                .findFirst();
        if (toRemove.isPresent()) {
            suppliedProducts.remove(toRemove.get());
            return true;
        }
        return false;
    }

    // Find a product in supplier catalog
    public Product findProductById(int productID) {
        return suppliedProducts.stream()
                .filter(p -> p.getProductID() == productID)
                .findFirst()
                .orElse(null);
    }

 
    public boolean supplyStock(Inventory inventory, int productID, int quantity) {
        if (inventory == null || quantity <= 0) {
            return false;
        }
        Product supplied = findProductById(productID);
        if (supplied == null) {
            return false;
        }

        Product inInventory = inventory.searchById(productID);
        if (inInventory != null) {
            int newQty = inInventory.getStockQuantity() + quantity;
            inInventory.setStockQuantity(newQty);
            return true;
        } else {
            inventory.addDrugs(supplied);

            Product added = inventory.searchById(productID);
            if (added != null) {
                added.setStockQuantity(added.getStockQuantity() + quantity);
                return true;
            }
            return false;
        }
    }

    @Override
    public String toString() {
        return "Company{"
                + "companyID='" + companyID + '\''
                + ", name='" + name + '\''
                + ", email='" + email + '\''
                + ", location='" + location + '\''
                + ", suppliedProductsCount=" + suppliedProducts.size()
                + '}';
    }
}
