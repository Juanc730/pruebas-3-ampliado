import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import com.example.Email;

class EmailTest {
    
    // Caso 1: Correo nulo o vacío
    @Test
    void testEmailNullOrEmptyThrowsException() {
        Email emailValidator = new Email();
        assertAll(
            () -> {
                IllegalArgumentException ex = assertThrows(
                        IllegalArgumentException.class,
                        () -> emailValidator.isValidEmail(null)
                );
                assertEquals("El correo no puede ser nulo o vacío", ex.getMessage());
            },
            () -> {
                IllegalArgumentException ex = assertThrows(
                        IllegalArgumentException.class,
                        () -> emailValidator.isValidEmail("")
                );
                assertEquals("El correo no puede ser nulo o vacío", ex.getMessage());
            }
        );
    }

    // Caso 2: Correo sin arroba
    @Test
    void testEmailWithoutAtSymbolReturnsFalse() {
        Email emailValidator = new Email();
        assertFalse(emailValidator.isValidEmail("usuario.gmail.com"));
    }

    // Caso 3: Correo con dominio inválido
    @Test
    void testEmailWithInvalidDomainReturnsFalse() {
        Email emailValidator = new Email();
        assertFalse(emailValidator.isValidEmail("usuario@com"));
    }

    // Caso 4: Correo válido estándar
    @Test
    void testValidEmailReturnsTrue() {
        Email emailValidator = new Email();
        assertTrue(emailValidator.isValidEmail("cliente123@tienda.com"));
    }

    // Caso 5: Correo demasiado corto
    @Test
    void testEmailTooShortReturnsFalse() {
        Email emailValidator = new Email();
        assertFalse(emailValidator.isValidEmail("a@b.c"));
    }

    // Caso 6: Correo con doble punto
    @Test
    void testEmailWithDoubleDotInLocalPartReturnsFalse() {
        Email emailValidator = new Email();
        assertFalse(emailValidator.isValidEmail("us..ario@dominio.com"));
    }
}
