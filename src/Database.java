import java.util.ArrayList;

public class Database
{
    private ArrayList<Manufacturer> manufacturers = new ArrayList<>();

    private ArrayList<Product> inventory = new ArrayList<>();

    private ArrayList<Product> removedProducts = new ArrayList<>();

    public Product searchProduct(String name)
    {
        for (Product product : this.inventory)
        {
            if (product.getName().equalsIgnoreCase(name))
                return product;
        }
        return null;
    }

    public boolean removeProduct(String name)
    {
        Product product = searchProduct(name);
        if (product == null)
            return false;
        this.inventory.remove(product);
        this.removedProducts.add(product);
        return true;
    }

    public boolean addProduct(Product product)
    {
        if (searchProduct(product.getName()) != null)
            return false;
        this.inventory.add(product);
        return true;
    }

    public Product[] getProducts()
    {
        Product[] result = new Product[this.inventory.size()];
        for (int i = 0; i < this.inventory.size(); i++)
            result[i] = this.inventory.get(i);
        return result;
    }

    public Product[] getRemovedProducts()
    {
        Product[] result = new Product[this.removedProducts.size()];
        for (int i = 0; i < this.removedProducts.size(); i++)
            result[i] = this.removedProducts.get(i);
        return result;
    }

    public Manufacturer searchManufacturer(String name)
    {
        for (Manufacturer manufacturer : this.manufacturers)
        {
            if (manufacturer.getName().equalsIgnoreCase(name))
                return manufacturer;
        }
        return null;
    }

    public boolean addManufacturer(Manufacturer manufacturer)
    {
        if (searchManufacturer(manufacturer.getName()) != null)
            return false;
        this.manufacturers.add(manufacturer);
        return true;
    }

    public Manufacturer[] getManufacturers()
    {
        Manufacturer[] result = new Manufacturer[this.manufacturers.size()];
        for (int i = 0; i < this.manufacturers.size(); i++)
            result[i] = this.manufacturers.get(i);
        return result;
    }
}


