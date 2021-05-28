public class Manufacturer {

    private String name;

    private Address address;

    public Manufacturer(String manufacturerName, Address manufacturerAddress) {

        this.name = manufacturerName;
        this.address = manufacturerAddress;
    }

    public String getName() {

        return this.name;
    }

    public Address getAddress() {

        return this.address;
    }
}


