package com.iac.onboarding.util;

import com.iac.onboarding.model.User;

public class UTMGenerator {

    public static String generateCode(User user) {
        String namePart = user.getName()
                .replaceAll("\\s+", "")
                .toLowerCase();

        long timestamp = System.currentTimeMillis();

        return namePart + "_" + timestamp;
    }

    public static String generateLink(String utmCode) {
        return "https://iac.com/register?ref=" + utmCode;
    }
}