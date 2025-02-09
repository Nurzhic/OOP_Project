import java.sql.Date;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

public class HotelBookingSystem {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DatabaseHandler.connect();

        // Инициализация списка пользователей
        List<User> users = new ArrayList<>(); // Список всех пользователей

        System.out.println("Введите ID пользователя:");
        int userID = scanner.nextInt();
        scanner.nextLine(); // Поглощаем лишний символ новой строки

        // Ввод имени и email для авторизации
        System.out.println("Введите ваше имя:");
        String name = scanner.nextLine();

        System.out.println("Введите ваш email:");
        String email = scanner.nextLine();

        // Получаем пользователя из базы данных
        User user = DatabaseHandler.getUser(name, email);

        if (user == null) {
            System.out.println("❌ Неверные данные. Попробуйте снова.");
            DatabaseHandler.close();
            return;  // Выход из программы, если данные неверные
        }

        System.out.println("Вход выполнен успешно!");
        System.out.println(user.getDetails());  // Вывод информации о пользователе

        // Если это администратор, даем доступ к администраторскому функционалу
        Admin admin = null;
        if (user instanceof Admin) {
            admin = (Admin) user;
        }

        while (true) {
            System.out.println("\n===== HOTEL BOOKING SYSTEM =====");
            System.out.println("1. Просмотр всех номеров");
            System.out.println("2. Забронировать номер");
            System.out.println("3. Просмотреть бронирования");

            // Если это гость, показываем дополнительные функции
            if (user instanceof Guest) {
                System.out.println("4. Отменить бронирование");
                System.out.println("5. Изменить бронирование");
            }

            // Если это администратор, показываем управление пользователями
            if (user instanceof Admin) {
                System.out.println("4. Добавить пользователя");
                System.out.println("5. Удалить пользователя");
                System.out.println("6. Просмотреть всех пользователей");
                System.out.println("7. Редактировать данные пользователя");
            }

            if (user instanceof Admin){
                System.out.println("8. Выйти");
            } else {
                System.out.println("6. Выйти");
            }
            System.out.print("Выберите действие: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    RoomManager.getRooms();
                    break;
                case 2:
                    System.out.print("Введите номер комнаты для бронирования: ");
                    int roomNumber = scanner.nextInt();
                    System.out.print("Введите дату заезда (ГГГГ-ММ-ДД): ");
                    String start = scanner.next();
                    System.out.print("Введите дату выезда (ГГГГ-ММ-ДД): ");
                    String end = scanner.next();
                    BookingManager.createBooking(user.getUserID(), roomNumber, Date.valueOf(start), Date.valueOf(end));

                    if (user instanceof Guest){
                        // После бронирования, предложим оплатить
                        System.out.print("Введите сумму для оплаты: ");
                        double amount = scanner.nextDouble();
                        System.out.println("Вы выбрали оплату на сумму: " + amount);

                        System.out.println("Выберите способ оплаты:");
                        System.out.println("1. Кредитная карта");
                        System.out.println("2. PayPal");
                        int paymentChoice = scanner.nextInt();

                        // Обработка оплаты
                        PaymentManager.processPayment(userID, amount, paymentChoice);
                    }
                    break;
                case 3:
                    // Просмотр бронирований
                    if (user instanceof Guest) {
                        // Гость может увидеть только свои бронирования
                        BookingManager.getBookingsForUser(user.getUserID());
                    } else if (user instanceof Admin) {
                        // Администратор может увидеть все бронирования
                        BookingManager.getBookings();
                    }
                    break;
                case 4:
                    // Для гостей
                    if (user instanceof Guest) {
                        System.out.print("Введите ID бронирования для отмены: ");
                        int bookingID = scanner.nextInt();
                        ((Guest) user).cancelBooking(bookingID);
                    }
                    // Для администраторов
                    if (user instanceof Admin) {
                        System.out.println("Добавление пользователя:");
                        System.out.print("Введите ID нового пользователя: ");
                        int newUserID = scanner.nextInt();
                        scanner.nextLine(); // Поглощаем лишний символ новой строки
                        System.out.print("Введите имя нового пользователя: ");
                        String newName = scanner.nextLine();
                        System.out.print("Введите email нового пользователя: ");
                        String newEmail = scanner.nextLine();
                        System.out.print("Введите роль нового пользователя (admin/guest): ");
                        String newRole = scanner.nextLine();
                        admin.addUser(users, newUserID, newName, newEmail, newRole);
                    }
                    break;
                case 5:
                    // Для гостей
                    if (user instanceof Guest) {
                        System.out.print("Введите ID бронирования для изменения: ");
                        int bookingIDToModify = scanner.nextInt();
                        System.out.print("Введите новую дату заезда (ГГГГ-ММ-ДД): ");
                        String newStart = scanner.next();
                        System.out.print("Введите новую дату выезда (ГГГГ-ММ-ДД): ");
                        String newEnd = scanner.next();
                        System.out.print("Введите новый номер комнаты: ");
                        int newRoomNumber = scanner.nextInt();
                        ((Guest) user).modifyBooking(bookingIDToModify, Date.valueOf(newStart), Date.valueOf(newEnd), newRoomNumber);
                    }
                    // Для администраторов
                    if (user instanceof Admin) {
                        System.out.println("Удаление пользователя:");
                        System.out.print("Введите ID пользователя для удаления: ");
                        int deleteUserID = scanner.nextInt();
                        admin.deleteUser(users, deleteUserID);
                    }
                    break;
                case 6:
                    // Для администраторов
                    if (admin != null) {
                        System.out.println("Просмотр всех пользователей:");
                        admin.viewUsers(users);
                    } else {
                        System.out.println("Выход из программы...");
                        DatabaseHandler.close();
                        return;
                    }
                    break;
                case 7:
                    // Редактирование данных пользователя (для администратора)
                    if (admin != null) {
                        System.out.println("Редактирование данных пользователя:");
                        System.out.print("Введите ID пользователя для редактирования: ");
                        int editUserID = scanner.nextInt();
                        scanner.nextLine(); // Поглощаем лишний символ новой строки
                        System.out.print("Введите новое имя: ");
                        String newName = scanner.nextLine();
                        System.out.print("Введите новый email: ");
                        String newEmail = scanner.nextLine();
                        admin.editUser(users, editUserID, newName, newEmail);
                    }
                    break;
                case 8:
                    System.out.println("Выход из программы...");
                    DatabaseHandler.close();
                    return;
                default:
                    System.out.println("Неверный ввод. Попробуйте снова.");
            }
        }
    }
}
