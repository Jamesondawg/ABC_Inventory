public class Address
{
    private String street;

    private String city;

    private String state;

    private String zip;

    public Address(String addressStreet, String addressCity, String addressState, String addressZip) {
        this.street = addressStreet;
        this.city = addressCity;
        this.state = addressState;
        this.zip = addressState;
    }

    public String getStreet() {
        return this.street;
    }

    public String getCity() {
        return this.city;
    }

    public String getState() {
        return this.state;
    }

    public String getZip() {
        return this.zip;
    }
}

