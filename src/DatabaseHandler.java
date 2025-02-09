import java.sql.*;

import java.sql.*;

public class DatabaseHandler {
    private static Connection connection;
    private static final String URL = "jdbc:mysql://localhost:3306/hotel_booking_system";
    private static final String USER = "root"; // Укажи свой логин MySQL
    private static final String PASSWORD = "Div_light20021"; // Укажи свой пароль MySQL

    public static void connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Загружаем MySQL-драйвер
            connection = DriverManager.getConnection(
                    URL, USER, PASSWORD
            );
            System.out.println("✅ Подключено к базе данных!");
        } catch (ClassNotFoundException e) {
            System.out.println("❌ Драйвер MySQL не найден! Добавь `mysql-connector-java.jar` в зависимости проекта.");
        } catch (SQLException e) {
            System.out.println("❌ Ошибка подключения к базе данных: " + e.getMessage());
        }
    }


    public static Connection getConnection() {
        if (connection == null) {
            System.out.println("❌ Ошибка: соединение с базой данных отсутствует!");
        }
        return connection;
    }

    public static void close() {
        try {
            if (connection != null) {
                connection.close();
                System.out.println("✅ Соединение с базой данных закрыто.");
            }
        } catch (SQLException e) {
            System.out.println("❌ Ошибка при закрытии соединения.");
        }
    }

    // Метод для поиска пользователя по имени и email
    public static User getUser(String name, String email) {
        String query = "SELECT * FROM users WHERE name = ? AND email = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, name);
            stmt.setString(2, email);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int userID = rs.getInt("id");
                String role = rs.getString("role");
                if (role.equalsIgnoreCase("admin")) {
                    return new Admin(userID, name, email);
                } else if (role.equalsIgnoreCase("guest")) {
                    return new Guest(userID, name, email);
                }
            }
        } catch (SQLException e) {
            System.out.println("❌ Ошибка при поиске пользователя: " + e.getMessage());
        }
        return null;  // Пользователь не найден
    }
}

