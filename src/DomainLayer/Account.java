package DomainLayer;

public class Account {

    private int accountId;
    private String name;
    private String street;
    private int housenumber;
    private String housenumberadd;
    private String postalcode;
    private String city;

    public Account(int accountId, String name, String street, int housenumber, String housenumberadd, String postalcode, String city) {
        setAccountId(accountId);
        setName(name);
        setStreet(street);
        setHousenumber(housenumber);
        setHousenumberadd(housenumberadd);
        setPostalcode(postalcode);
        setCity(city);
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getHousenumber() {
        return housenumber;
    }

    public void setHousenumber(int housenumber) {
        this.housenumber = housenumber;
    }

    public String getHousenumberadd() {
        return housenumberadd;
    }

    public void setHousenumberadd(String housenumberadd) {
        this.housenumberadd = housenumberadd;
    }

    public String getPostalcode() {
        return postalcode;
    }

    public void setPostalcode(String postalcode) {
        this.postalcode = postalcode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "Account{" +
                "name='" + name + '\'' +
                '}';
    }
}
