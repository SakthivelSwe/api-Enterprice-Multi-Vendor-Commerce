package com.tvm.Controller;

import com.tvm.Client.Dailyreport;
import com.tvm.Client.Orderclient;
import com.tvm.Client.Weeklyreport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service

public class ReportSchedular {
    @Autowired
    private Orderclient orderclient;

    @Autowired
    private Dailyreport dailyreport;

    @Autowired
    private Weeklyreport weeklyreport;

    @Autowired
    private JavaMailSender javaMailSender;
    @Scheduled(cron = "0 0 9 * * ?")
    public void sendDailyReport() {
        BigDecimal dailySales = dailyreport.getdailyamount();
        String adminEmail ="ramudukumar19@gmai.com";
        sendEmail(adminEmail, "Daily Sales Report", "Total Daily Sales: ₹" + dailySales);
    }

    // Send weekly every Monday at 10 AM
    @Scheduled(cron = "0 0 10 ? * MON")
    public void sendWeeklyReport() {
        BigDecimal weeklySales = weeklyreport.getweeklyamount();
        String adminEmail = "ramudukumar19@gmail.com";
        sendEmail(adminEmail, "Weekly Sales Report", "Total Weekly Sales: ₹" + weeklySales);
    }

    private void sendEmail(String to, String subject, String text) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo();
        msg.setSubject(subject);
        msg.setText(text);
        javaMailSender.send(msg);
    }

    @Scheduled(cron = "0 0 2 * * ?") // Every day at 2 AM
    public void cleanupCarts() {
        try {
            orderclient.cleanupOldCarts();
            System.out.println("Cart cleanup triggered successfully");
        } catch (Exception e) {
            System.err.println("Cart cleanup failed: " + e.getMessage());
        }
    }
}

