import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
class Author implements Serializable {
    private String firstName;
    private String lastName;

    public Author(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public String toString() {
        return    firstName   + " " + lastName + '\n';
    }
}
