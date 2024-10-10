package co.edu.unisabana.Notification_Parcial2.listner;

import co.edu.unisabana.Notification_Parcial2.service.NotificationService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class NotificationListener {

    private final NotificationService notificationService;

    public NotificationListener(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @KafkaListener(topics = "payment-events", groupId = "notification-group")
    public void handlePaymentEvents(String event) {
        // Parse the event (use a proper object mapper in a real-world application)
        if (event.contains("PaymentSuccessful")) {
            notificationService.sendPaymentSuccessNotification(event);
        } else if (event.contains("PaymentFailed")) {
            notificationService.sendPaymentFailedNotification(event);
        }
    }

    @KafkaListener(topics = "shipping-events", groupId = "notification-group")
    public void handleShippingEvents(String event) {
        if (event.contains("OrderShipped")) {
            notificationService.sendOrderShippedNotification(event);
        }
    }
}
