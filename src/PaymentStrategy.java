public interface PaymentStrategy {
    void processPayment(int bookingID, double amount);
}