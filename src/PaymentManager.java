public class PaymentManager {
    public static void processPayment(int userID, double amount, int paymentChoice) {
        if (paymentChoice == 1) {
            // Обработка платежа через кредитную карту
            System.out.println("Оплата через кредитную карту на сумму " + amount + " для пользователя ID " + userID);
            // Логика для обработки оплаты картой
        } else if (paymentChoice == 2) {
            // Обработка платежа через PayPal
            System.out.println("Оплата через PayPal на сумму " + amount + " для пользователя ID " + userID);
            // Логика для обработки оплаты через PayPal
        } else {
            System.out.println("Неверный выбор метода оплаты.");
        }
    }
}
