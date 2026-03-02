package activity7.models;

public class Contact {
    private String name;
    private String phoneNumber;
    private String email;

    public Contact(){ }

    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return this.name;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public String getEmail() {
        return this.email;
    }

    
    public String toCsvString() {
        String finalStr = getName() + "," + getPhoneNumber() + "," + getEmail();
        return finalStr;
    }

    @Override
    public String toString() {
    return "Name: " + name + "| Phone: " + phoneNumber + "| Email: " + email;
    }
}
