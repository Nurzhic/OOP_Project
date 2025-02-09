public class CreditCardPayment implements PaymentStrategy {
    @Override
    public void processPayment(int bookingID, double amount) {
        System.out.println("Оплата через кредитную карту на сумму " + amount + " за бронирование ID " + bookingID);
    }
}