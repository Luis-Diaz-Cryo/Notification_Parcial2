package co.edu.unisabana.Notification_Parcial2.service;

import co.edu.unisabana.Notification_Parcial2.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class NotificationService {

    @Autowired
    private JavaMailSender emailSender;

    @Autowired
    private CustomerRepository customerRepository;

    private final ObjectMapper objectMapper = new ObjectMapper();

    public void sendPaymentSuccessNotification(String event) {
        Integer id = extractCustomerId(event); // Change from String to Integer
        String email = getEmailByCustomerId(id);
        String message = "Your payment was successful! Your order is being processed.";

        sendEmail(email, "Payment Confirmation", message);
    }

    public void sendPaymentFailedNotification(String event) {
        Integer id = extractCustomerId(event); // Change from String to Integer
        String email = getEmailByCustomerId(id);
        String message = "Unfortunately, your payment failed. Please try again.";

        sendEmail(email, "Payment Failed", message);
    }

    public void sendOrderShippedNotification(String event) {
        Integer id = extractCustomerId(event); // Change from String to Integer
        String email = getEmailByCustomerId(id);
        String message = "Your order has been shipped! Tracking Number: ";

        sendEmail(email, "Order Shipped", message);
    }


    private void sendEmail(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        emailSender.send(message);
    }

    private Integer extractCustomerId(String event) {
        try {
            JsonNode jsonNode = objectMapper.readTree(event);
            return jsonNode.get("id").asInt();
        } catch (Exception e) {
            System.err.println("Failed to extract customerId: " + e.getMessage());
            return null;
        }
    }


    private String getEmailByCustomerId(Integer customerId) {
        return customerRepository.findEmailById(customerId);
    }



}
