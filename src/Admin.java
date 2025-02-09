import java.util.List;

public class Admin extends User {
    public Admin(int userID, String name, String email) {
        super(userID, name, email);
    }

    // Метод для управления номерами
    public void manageRooms() {
        System.out.println("Администратор управляет номерами отеля.");
    }

    // Метод для добавления нового пользователя
    public void addUser(List<User> users, int userID, String name, String email, String role) {
        if (role.equalsIgnoreCase("admin")) {
            users.add(new Admin(userID, name, email));
            System.out.println("Администратор " + name + " добавлен.");
        } else if (role.equalsIgnoreCase("guest")) {
            users.add(new Guest(userID, name, email));
            System.out.println("Гость " + name + " добавлен.");
        } else {
            System.out.println("Неверная роль. Попробуйте снова.");
        }
    }

    // Метод для удаления пользователя
    public void deleteUser(List<User> users, int userID) {
        users.removeIf(user -> user.userID == userID);
        System.out.println("Пользователь с ID " + userID + " удален.");
    }

    // Метод для просмотра всех пользователей
    public void viewUsers(List<User> users) {
        System.out.println("Список всех пользователей:");
        for (User user : users) {
            System.out.println(user.getDetails());
        }
    }

    // Метод для редактирования данных пользователя
    public void editUser(List<User> users, int userID, String newName, String newEmail) {
        for (User user : users) {
            if (user.userID == userID) {
                user.name = newName;
                user.email = newEmail;
                System.out.println("Данные пользователя с ID " + userID + " обновлены.");
                return;
            }
        }
        System.out.println("Пользователь с ID " + userID + " не найден.");
    }
}