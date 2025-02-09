public class User {
    protected int userID;
    protected String name;
    protected String email;

    public User(int userID, String name, String email) {
        this.userID = userID;
        this.name = name;
        this.email = email;
    }

    public String getDetails() {
        return "User ID: " + userID + ", Name: " + name + ", Email: " + email;
    }

    public int getUserID() {
        return userID;
    }
}
