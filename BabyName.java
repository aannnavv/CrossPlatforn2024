package lr3;

public class BabyName {
    private String name;
    private String gender;
    private int count;
    private int rating;

    public BabyName(String name, String gender, int count, int rating) {
        this.name = name;
        this.gender = gender;
        this.count = count;
        this.rating = rating;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public int getCount() {
        return count;
    }

    public int getRating() {
        return rating;
    }
}
