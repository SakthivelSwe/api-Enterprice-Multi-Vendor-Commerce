package com.tvm.util;

import com.tvm.enums.EmailType;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class EmailTemplateUtil {
    public static String loadTemplate(EmailType type) throws IOException {
        String fileName = switch (type) {
            case ORDER_CONFIRMATION -> "templates/order_confirmation.txt";
            case PASSWORD_RESET -> "templates/password_reset.txt";
            case VENDOR_APPROVAL -> "templates/vendor_approval.txt";
        };
        return new String(Files.readAllBytes(Paths.get("src/main/resources/" + fileName)));
    }

    public static String populateTemplate(String template, String[] variables) {
        for (int i = 0; i < variables.length; i++) {
            template = template.replace("{{" + i + "}}", variables[i]);
        }
        return template;
    }
}
