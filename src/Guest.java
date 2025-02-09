import java.sql.Date;

public class Guest extends User {
    public Guest(int userID, String name, String email) {
        super(userID, name, email);
    }

    // Отмена бронирования
    public void cancelBooking(int bookingID) {
        System.out.println("Отмена бронирования с ID: " + bookingID);
        // Логика для отмены бронирования
    }

    // Изменение бронирования
    public void modifyBooking(int bookingID, Date newStartDate, Date newEndDate, int newRoomNumber) {
        System.out.println("Изменение бронирования с ID: " + bookingID);
        System.out.println("Новый номер комнаты: " + newRoomNumber);
        System.out.println("Новая дата заезда: " + newStartDate);
        System.out.println("Новая дата выезда: " + newEndDate);
        // Логика для изменения бронирования в базе данных
    }
}