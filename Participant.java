import java.io.Serializable;

public class Participant implements Serializable {
    private String name;
    private String email;
    private String organization;
    private int age;
    private String country;

    public Participant(String name, String email, String organization, int age, String country) {
        this.name = name;
        this.email = email;
        this.organization = organization;
        this.age = age;
        this.country = country;
    }

    public Participant(String name, String email, String organization) {
        this.name = name;
        this.email = email;
        this.organization = organization;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }



}
