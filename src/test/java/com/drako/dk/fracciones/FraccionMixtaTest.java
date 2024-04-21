package com.drako.dk.fracciones;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FraccionMixtaTest {
    @Test
    void constructorTest() {
        FraccionMixta fraccion = new FraccionMixta();
        assertEquals(0, fraccion.getEntero());
        assertEquals(0, fraccion.getNumerador());
        assertEquals(1, fraccion.getDenominador());
        assertEquals(Fraccion.Signo.POSITIVE, fraccion.getSigno());
    }

    @Test
    void constructorIntIntIntTest() {
        FraccionMixta mixta = new FraccionMixta(3, 4, 2);
        assertEquals(2, mixta.getEntero());
        assertEquals(3, mixta.getNumerador());
        assertEquals(4, mixta.getDenominador());
        assertEquals(Fraccion.Signo.POSITIVE, mixta.getSigno());
    }

    @Test
    void constructorFloatTest() {
        FraccionMixta mixta = new FraccionMixta(2.75f).reducir();
        assertEquals(2, mixta.getEntero());
        assertEquals(3, mixta.getNumerador());
        assertEquals(4, mixta.getDenominador());
        assertEquals(Fraccion.Signo.POSITIVE, mixta.getSigno());
    }

    @Test
    void constructorStringValidTest() {
        // Casos válidos
        FraccionMixta mixta1 = new FraccionMixta("-5/-2 / -4");
        assertEquals(5, mixta1.getEntero());
        assertEquals(2, mixta1.getNumerador());
        assertEquals(4, mixta1.getDenominador());
        assertEquals(Fraccion.Signo.POSITIVE, mixta1.getSigno());

        FraccionMixta mixta2 = new FraccionMixta("5/ -2 / -4");
        assertEquals(5, mixta2.getEntero());
        assertEquals(2, mixta2.getNumerador());
        assertEquals(4, mixta2.getDenominador());
        assertEquals(Fraccion.Signo.POSITIVE, mixta1.getSigno());

        FraccionMixta mixta3 = new FraccionMixta("5/2/-4");
        assertEquals(5, mixta3.getEntero());
        assertEquals(2, mixta3.getNumerador());
        assertEquals(4, mixta3.getDenominador());
        assertEquals(Fraccion.Signo.NEGATIVE, mixta3.getSigno());

        FraccionMixta mixta4 = new FraccionMixta("5/2/4");
        assertEquals(5, mixta4.getEntero());
        assertEquals(2, mixta4.getNumerador());
        assertEquals(4, mixta4.getDenominador());
        assertEquals(Fraccion.Signo.POSITIVE, mixta4.getSigno());
    }

    @Test
    void toStringTest() {
        FraccionMixta mixta1 = new FraccionMixta(1, 3, -2);
        assertEquals("-2 1/3", mixta1.toString());

        FraccionMixta mixta2 = new FraccionMixta(2, 5, 3);
        assertEquals("3 2/5", mixta2.toString());
    }

    @Test
    void reducirTest() {
        FraccionMixta mixta = new FraccionMixta(7, 3, 5).reducir();
        assertEquals(7, mixta.getEntero());
        assertEquals(1, mixta.getNumerador());
        assertEquals(3, mixta.getDenominador());
    }

    @Test
    void convertToFraccionTest() {
        Fraccion fraccion = FraccionMixta.convertToFraccion(new FraccionMixta(1, 4, 3));
        assertEquals(13, fraccion.getNumerador());
        assertEquals(4, fraccion.getDenominador());
        assertEquals(Fraccion.Signo.POSITIVE, fraccion.getSigno());
    }

    @Test
    void toFloatTest() {
        FraccionMixta mixta = new FraccionMixta(3, 4, 2);
        assertEquals(2.75f, mixta.toFloat());
    }

    @Test
    void constructorStringInvalidTest() {
        // Intenta crear una fracción con una cadena no válida
        assertThrows(NumberFormatException.class, () -> new FraccionMixta("2/abc/5"));

        assertThrows(NumberFormatException.class, () -> new FraccionMixta("2.5"));
    }

    @Test
    void equalsTest() {
        FraccionMixta fraccion1 = new FraccionMixta(9, 16, -5);
        FraccionMixta fraccion2 = new FraccionMixta(3, 4, 1);
        FraccionMixta fraccion3 = new FraccionMixta(9, 16, -5);

        assertEquals(fraccion1, fraccion3);
        assertNotEquals(fraccion1, fraccion2);
        assertNotEquals(fraccion2, fraccion3);
    }

    @Test
    void isEquivalenteTest() {
        FraccionMixta fraccion1 = new FraccionMixta(9, 4, 0);
        FraccionMixta fraccion2 = new FraccionMixta(3, 4, 1);
        FraccionMixta fraccion3 = new FraccionMixta(1, 4, 2);

        assertTrue(fraccion1.isEquivalente(fraccion3));
        assertFalse(fraccion1.isEquivalente(fraccion2));
        assertFalse(fraccion2.isEquivalente(fraccion3));
    }

}