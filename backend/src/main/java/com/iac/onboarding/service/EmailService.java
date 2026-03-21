package com.iac.onboarding.service;

import org.springframework.stereotype.Service;

@Service
public class EmailService {

    public void sendWelcomeEmail(String email, String utmLink) {

        //  Mock email (console log)
        System.out.println("=================================");
        System.out.println("📧 Sending Email to: " + email);
        System.out.println("🎉 Welcome to IAC Ambassador Program!");
        System.out.println("🔗 Your UTM Link: " + utmLink);
        System.out.println("=================================");
    }
}