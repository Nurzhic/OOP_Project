import java.sql.*;

public class BookingManager {

    public static void createBooking(int userID, int roomNumber, Date startDate, Date endDate) {
        String query = "INSERT INTO bookings (user_id, room_id, start_date, end_date) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = DatabaseHandler.getConnection().prepareStatement(query)) {
            stmt.setInt(1, userID);
            stmt.setInt(2, roomNumber);
            stmt.setDate(3, startDate);
            stmt.setDate(4, endDate);
            stmt.executeUpdate();
            System.out.println("Бронирование успешно создано.");
        } catch (Exception e) {
            System.out.println("Ошибка при создании бронирования.");
        }
    }

    public static void getBookingsForUser(int userID) {
        String query = "SELECT * FROM bookings WHERE user_id = ?";
        try (PreparedStatement stmt = DatabaseHandler.getConnection().prepareStatement(query)) {
            stmt.setInt(1, userID);
            ResultSet rs = stmt.executeQuery();

            if (!rs.isBeforeFirst()) {
                System.out.println("У вас нет бронирований.");
            } else {
                while (rs.next()) {
                    System.out.println("ID бронирования: " + rs.getInt("id") +
                            ", Номер комнаты: " + rs.getInt("room_id") +
                            ", Дата заезда: " + rs.getDate("start_date") +
                            ", Дата выезда: " + rs.getDate("end_date"));
                }
            }
        } catch (SQLException e) {
            System.out.println("❌ Ошибка при получении списка бронирований.");
        }
    }

    public static void getBookings() {
        String query = "SELECT * FROM bookings";
        try (Statement stmt = DatabaseHandler.getConnection().createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                System.out.println("ID бронирования: " + rs.getInt("id") +
                        ", ID гостя: " + rs.getInt("user_id") +
                        ", Номер комнаты: " + rs.getInt("room_id") +
                        ", Дата заезда: " + rs.getDate("start_date") +
                        ", Дата выезда: " + rs.getDate("end_date"));
            }
        } catch (Exception e) {
            System.out.println("Ошибка при получении списка бронирований.");
        }
    }

    public static void deleteBooking(int bookingID) {
        String query = "DELETE FROM bookings WHERE id = ?";
        try (PreparedStatement stmt = DatabaseHandler.getConnection().prepareStatement(query)) {
            stmt.setInt(1, bookingID);
            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Бронирование удалено.");
            } else {
                System.out.println("Бронирование не найдено.");
            }
        } catch (Exception e) {
            System.out.println("Ошибка при удалении бронирования.");
        }
    }
}
