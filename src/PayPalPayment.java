public class PayPalPayment implements PaymentStrategy {
    @Override
    public void processPayment(int bookingID, double amount) {
        System.out.println("Оплата через PayPal на сумму " + amount + " за бронирование ID " + bookingID);
    }
}