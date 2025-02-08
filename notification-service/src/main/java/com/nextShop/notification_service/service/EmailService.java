package com.nextShop.notification_service.service;

import com.nextShop.notification_service.dto.ProductResponse;
import com.nextShop.notification_service.model.enums.EmailTemplate;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Service
public class EmailService {
    private final JavaMailSender mailSender;
    private final SpringTemplateEngine templateEngine;

    @Async
    public void sendPaymentSuccessEmail(String targetEmail, String userFullName, BigDecimal amount, String orderReferenceId, String paymentMethod) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(
                    mimeMessage, MimeMessageHelper.MULTIPART_MODE_RELATED, StandardCharsets.UTF_8.name());
            mimeMessageHelper.setFrom("noreply@nextshop.com");
            final String templateName = EmailTemplate.PAYMENT_CONFIRMATION.getTemplate();
            Map<String, Object> variables = new HashMap<>();
            variables.put("userFullName", userFullName);
            variables.put("amount", amount);
            variables.put("paymentMethod", paymentMethod);
            variables.put("orderReferenceId", orderReferenceId);
            log.info("Payment confirmation template variables: {}", variables);

            Context context = new Context();
            context.setVariables(variables);
            mimeMessageHelper.setSubject(EmailTemplate.PAYMENT_CONFIRMATION.getSubject());


            try {
                String htmlTemplate = templateEngine.process(templateName, context);
                mimeMessageHelper.setText(htmlTemplate, true);
                mimeMessageHelper.setTo(targetEmail);
                mailSender.send(mimeMessage);
                log.info("Email sent successfully");
            } catch (MessagingException e) {
                log.error("Error while sending email to {}. Error: ", targetEmail, e);
                throw new RuntimeException(e);
            }

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }


    }

    @Async
    public void sendOrderConfirmationEmail(String targetEmail, String userFullName, BigDecimal amount, String orderReferenceId, List<ProductResponse> products) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(
                    mimeMessage, MimeMessageHelper.MULTIPART_MODE_RELATED, StandardCharsets.UTF_8.name());
            mimeMessageHelper.setFrom("noreply@nextshop.com");
            final String templateName = EmailTemplate.ORDER_CONFIRMATION.getTemplate();
            Map<String, Object> variables = new HashMap<>();
            variables.put("userFullName", userFullName);
            variables.put("totalAmount", amount);
            variables.put("orderReferenceId", orderReferenceId);
            variables.put("products", products);
            log.info("Order confirmation template variables: {}", variables);

            Context context = new Context();
            context.setVariables(variables);
            mimeMessageHelper.setSubject(EmailTemplate.ORDER_CONFIRMATION.getSubject());


            try {
                String htmlTemplate = templateEngine.process(templateName, context);
                mimeMessageHelper.setText(htmlTemplate, true);
                mimeMessageHelper.setTo(targetEmail);
                mailSender.send(mimeMessage);
                log.info("Email sent successfully");
            } catch (MessagingException e) {
                log.error("Error while sending email to {}. Error: ", targetEmail, e);
                throw new RuntimeException(e);
            }

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }


    }
}
