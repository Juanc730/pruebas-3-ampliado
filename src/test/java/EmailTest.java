import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import com.example.Email;

class EmailTest {
    
    // Caso 1: Correo nulo o vacío
    @Test
    void CorreoNuloOVacio() {
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
    void CorreoSinArroba() {
        Email emailValidator = new Email();
        assertFalse(emailValidator.isValidEmail("usuario.gmail.com"));
    }

    // Caso 3: Correo con dominio inválido
    @Test
    void CorreoConDominioInvalido() {
        Email emailValidator = new Email();
        assertFalse(emailValidator.isValidEmail("usuario@com"));
    }

    // Caso 4: Correo válido estándar
    @Test
    void CorreoValidoEstandar() {
        Email emailValidator = new Email();
        assertTrue(emailValidator.isValidEmail("cliente123@tienda.com"));
    }

    // Caso 5: Correo demasiado corto
    @Test
    void CorreoDemasiadoCorto() {
        Email emailValidator = new Email();
        assertFalse(emailValidator.isValidEmail("a@b.c"));
    }

    // Caso 6: Correo con doble punto
    @Test
    void CorreoConDoblePuntoEnParteLocal() {
        Email emailValidator = new Email();
        assertFalse(emailValidator.isValidEmail("us..ario@dominio.com"));
    }

    Email emailValidator = new Email();

    @Test
    void CorreoConEspacios() {
        assertFalse(emailValidator.isValidEmail("usuario @dominio.com"));
    }

    @Test
    void CorreoQueEmpiezaConPunto() {
        assertFalse(emailValidator.isValidEmail(".usuario@dominio.com"));
    }

    @Test
    void UsuarioQueTerminaConPunto() {
        assertFalse(emailValidator.isValidEmail("usuario.@dominio.com"));
    }

    @Test
    void CorreoConExtensionInvalida() {
        assertFalse(emailValidator.isValidEmail("usuario@dominio.xyz"));
    }

    @Test
    void CorreosConExtensionesValidas() {
        assertAll(
            () -> assertTrue(emailValidator.isValidEmail("usuario@empresa.net")),
            () -> assertTrue(emailValidator.isValidEmail("usuario@ong.org")),
            () -> assertTrue(emailValidator.isValidEmail("usuario@universidad.edu")),
            () -> assertTrue(emailValidator.isValidEmail("usuario@gobierno.gov")),
            () -> assertTrue(emailValidator.isValidEmail("usuario@tienda.pe"))
        );
    }

    @Test
    void CorreoMaximoLongitudValido() {
        String local = "a".repeat(64);
        String domain = "b".repeat(187) + ".com"; // total = 254
        String email = local + "@" + domain;
        assertTrue(emailValidator.isValidEmail(email));
    }

    @Test
    void CorreoDemasiadoLargo() {
        String local = "a".repeat(65);
        String domain = "b".repeat(190) + ".com"; // total > 254
        String email = local + "@" + domain;
        assertFalse(emailValidator.isValidEmail(email));
    }
}
