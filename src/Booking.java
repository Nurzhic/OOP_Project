import java.sql.Date;

public class Booking {
    private int bookingID;
    private Guest guest;
    private Room room;
    private Date startDate;
    private Date endDate;
    private String specialRequest;

    public Booking(Guest guest, Room room, Date startDate, Date endDate) {
        this.guest = guest;
        this.room = room;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    // Конструктор с дополнительным параметром specialRequest
    public Booking(Guest guest, Room room, Date startDate, Date endDate, String specialRequest) {
        this.guest = guest;
        this.room = room;
        this.startDate = startDate;
        this.endDate = endDate;
        this.specialRequest = specialRequest;
    }
}
