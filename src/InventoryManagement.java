//Inventory management system designed to keep track of products both sold and added.

//James Vega
//COP 3337
//Panther ID: 6293129


import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class InventoryManagement
{
    private static Database database = new Database();

    private static void addProduct() {
        String productName = GetData.getWord("Enter product name: ").trim();
        if (productName.isEmpty()) {
            JOptionPane.showMessageDialog(null, "A product name is required.");
            return;
        }
        if (database.searchProduct(productName) != null) {
            JOptionPane.showMessageDialog(null, "There is a product with that name already.");
            return;
        }
        int productQuantity = GetData.getInt("How many quantity are available for sale?");
        if (productQuantity < 0)
        {
            JOptionPane.showMessageDialog(null, "A negative quantity does not make any sense.");
            return;
        }
        double productUnitPrice = GetData.getDouble("How much does a unit of product cost?");
        if (productUnitPrice < 0.0D)
        {
            JOptionPane.showMessageDialog(null, "A negative price does not make any sense.");
            return;
        }
        String manufacturerName = GetData.getWord("Enter the product's manufacturer name: ").trim();
        if (manufacturerName.isEmpty())
        {
            JOptionPane.showMessageDialog(null, "A manufacturer name is required.");
            return;
        }
        Manufacturer manufacturer = database.searchManufacturer(manufacturerName);
        if (manufacturer == null) {
            JOptionPane.showMessageDialog(null, "Unfortunately we cannot find the manufacturer in the database, you probably have to add it first.");
            return;
        }
        database.addProduct(new Product(manufacturer, productName, productQuantity, productUnitPrice));
        JOptionPane.showMessageDialog(null, "New product added to the database.");
    }

    private static void removeProduct()
    {
        String productName = GetData.getWord("Enter product name: ").trim();
        if (productName.isEmpty()) {
            JOptionPane.showMessageDialog(null, "A product name is required.");
            return;
        }
        if (!database.removeProduct(productName))
        {
            JOptionPane.showMessageDialog(null, "The product does not exist in the database.");
            return;
        }
        JOptionPane.showMessageDialog(null, "The product has been removed from the inventory.");
    }

    private static void addManufacturer()
    {
        String manufacturerName = GetData.getWord("Enter manufacturer name: ").trim();
        if (manufacturerName.isEmpty())
        {
            JOptionPane.showMessageDialog(null, "A manufacturer name is required.");
            return;
        }
        String address = GetData.getWord("Enter manufacturer address (Format: street, city, state, zip): ").trim();
        if (address.isEmpty()) {
            JOptionPane.showMessageDialog(null, "An address is required.");
            return;
        }
        String[] addressFields = address.split(",");
        if (addressFields.length != 4)
        {
            JOptionPane.showMessageDialog(null, "The address format is invalid, please make sure there is a street, city, state, and zip separated by a comma.");
            return;
        }
        Address manufacturerAddress = new Address(addressFields[0], addressFields[1], addressFields[2], addressFields[3]);
        database.addManufacturer(new Manufacturer(manufacturerName, manufacturerAddress));
        JOptionPane.showMessageDialog(null, "Manufacturer added to the database.");
    }

    private static void restockProduct()
    {
        String productName = GetData.getWord("Enter product name: ").trim();
        if (productName.isEmpty())
        {
            JOptionPane.showMessageDialog(null, "A product name is required.");
            return;
        }
        Product product = database.searchProduct(productName);
        if (product == null)
        {
            JOptionPane.showMessageDialog(null, "The product does not exist in the database.");
            return;
        }
        int quantity = GetData.getInt("How many units should be added to the product?");
        if (quantity < 0)
        {
            JOptionPane.showMessageDialog(null, "A negative quantity does not make any sense.");
            return;
        }
        product.addQuantity(quantity);
        JOptionPane.showMessageDialog(null, "Product has been restocked with the given quantity.");
    }

    private static void sellProduct()
    {
        String productName = GetData.getWord("Enter product name: ").trim();
        if (productName.isEmpty())
        {
            JOptionPane.showMessageDialog(null, "A product name is required.");
            return;
        }
        Product product = database.searchProduct(productName);
        if (product == null)
        {
            JOptionPane.showMessageDialog(null, "The product does not exist in the database.");
            return;
        }
        int quantity = GetData.getInt("How many units of the product will be sold?");
        if (quantity < 0) {
            JOptionPane.showMessageDialog(null, "A negative quantity does not make any sense.");
            return;
        }
        if (!product.removeQuantity(quantity))
        {
            JOptionPane.showMessageDialog(null, "The product cannot meet the demanded quantity.");
            return;
        }
        double totalCost = product.getUnitPrice() * quantity;
        JOptionPane.showMessageDialog(null, "Sold " + quantity + " units of the product, total cost is $" + String.format("%.2f", new Object[] { Double.valueOf(totalCost) }));
    }

    private static void searchProduct()
    {
        String productName = GetData.getWord("Enter product name: ").trim();
        if (productName.isEmpty()) {
            JOptionPane.showMessageDialog(null, "A product name is required.");
            return;
        }
        Product product = database.searchProduct(productName);
        if (product == null) {
            JOptionPane.showMessageDialog(null, "The product does not exist in the database.");
            return;
        }
        JOptionPane.showMessageDialog(null, "Name: " + product.getName() + "\nUnits Left: " + product
                .getQuantity() + "\nUnit Price: $" +
                String.format("%.2f", new Object[] { Double.valueOf(product.getUnitPrice()) }));
    }

    private static void listProductInventory()
    {
        JTextArea textArea = new JTextArea();
        textArea.setFont(new Font("monospaced", 0, 12));
        textArea.append(String.format("%-20s%-15s%-10s%-20s%-5s", new Object[] { "Product", "Quantity", "Price", "Manufacturer", "State" }) + "\n");
        for (Product product : database.getProducts()) {
            textArea.append(String.format("%-20s%-15s%-10s%-20s%-5s", new Object[] { product
                    .getName(), product
                    .getQuantity() + "", product
                    .getUnitPrice() + "", product
                    .getManufacturer().getName(), product
                    .getManufacturer().getAddress().getState() + "\n" }));
        }
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(500, 500));
        JOptionPane.showMessageDialog(null, scrollPane);
    }

    private static void listRemovedProducts()
    {
        JTextArea textArea = new JTextArea();
        textArea.setFont(new Font("monospaced", 0, 12));
        textArea.append(String.format("%-20s%-20s", new Object[] { "Product", "Manufacturer" }) + "\n");
        for (Product product : database.getProducts()) {
            textArea.append(String.format("%-20s%-20s", new Object[] { product
                    .getName(), product
                    .getManufacturer().getName() + "\n" }));
        }
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(500, 500));
        JOptionPane.showMessageDialog(null, scrollPane);
    }

    public static void main(String[] args)
    {
        int option = -1;
        while (option != 0)
        {
            option = GetData.getInt("ABC Enterprise Main Menu:\n1. Add product\n2. Remove product\n3. Add manufacturer\n4. Restock product\n5. Sell product\n6. Search product\n7. List product inventory\n8. List removed products\n0. Exit\nEnter the number of your choice: ");
            switch (option)
            {
                case 1:
                    addProduct();
                    continue;
                case 2:
                    removeProduct();
                    continue;
                case 3:
                    addManufacturer();
                    continue;
                case 4:
                    restockProduct();
                    continue;
                case 5:
                    sellProduct();
                    continue;
                case 6:
                    searchProduct();
                    continue;
                case 7:
                    listProductInventory();
                    continue;
                case 8:
                    listRemovedProducts();
                    continue;
                case 0:
                    continue;
            }
            JOptionPane.showMessageDialog(null, "Invalid menu.");
        }
    }
}
