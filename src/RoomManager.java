import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class RoomManager {
    public static void getRooms() {
        Connection conn = DatabaseHandler.getConnection();
        if (conn == null) {
            System.out.println("❌ Ошибка: нет соединения с базой данных.");
            return;
        }

        String query = "SELECT * FROM rooms";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id") +
                        ", Номер: " + rs.getInt("room_number") +
                        ", Тип: " + rs.getString("type") +
                        ", Цена: " + rs.getDouble("price") +
                        ", Забронировано: " + (rs.getBoolean("is_booked") ? "Да" : "Нет"));
            }
        } catch (Exception e) {
            System.out.println("❌ Ошибка при получении списка номеров: " + e.getMessage());
        }
    }
}
