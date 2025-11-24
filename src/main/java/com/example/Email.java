
package com.example;

import java.util.regex.Pattern;

public class Email {

    // Patrón simplificado que cubre los requisitos básicos
    private static final String EMAIL_REGEX =
            "^(?!\\.)[A-Za-z0-9._%+-]+(?<!\\.)@" +                // parte local (no empieza ni termina con punto)
            "(?=.{2,}\\.)[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";         // dominio con al menos un punto y extensión válida

    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

    public boolean isValidEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("El correo no puede ser nulo o vacío");
        }

        // Validar longitud
        if (email.length() < 6 || email.length() > 254) {
            return false;
        }

        // Validar espacios
        if (email.contains(" ")) {
            return false;
        }

        // Validar doble punto en la parte local
        String[] parts = email.split("@");
        if (parts.length != 2) {
            return false;
        }
        String localPart = parts[0];
        if (localPart.contains("..")) {
            return false;
        }

        // Validar patrón general
        return EMAIL_PATTERN.matcher(email).matches();
    }
}
