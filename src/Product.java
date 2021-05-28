public class Product {
    private final Manufacturer manufacturer;

    private final String name;

    private int quantity;

    private double unitPrice;

    public Product (Manufacturer productManufacturer, String productName, int productQuantity, double productUnitPrice) {

        this.manufacturer = productManufacturer;
        this.name = productName;
        this.quantity = productQuantity;
        this.unitPrice = productUnitPrice;
    }

    public Manufacturer getManufacturer() {

        return this.manufacturer;
    }

    public String getName() {

        return this.name;
    }

    public int getQuantity() {

        return this.quantity;
    }

    public double getUnitPrice() {

        return this.unitPrice;
    }

    public boolean addQuantity (int productQuantity) {

        if (productQuantity < 0)
            return false;

        this.quantity += productQuantity;
            return true;
    }

    public boolean removeQuantity (int productQuantity) {

        if (productQuantity < 0 || this.quantity - productQuantity < 0)
            return false;

        this.quantity -= productQuantity;
            return true;
    }

    public void setUnitPrice(double productUnitPrice) {

        this.unitPrice = productUnitPrice;
    }
}

