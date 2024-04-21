package com.drako.dk.fracciones;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FraccionTest {
    @Test
    void constructorTest() {
        Fraccion fraccion = new Fraccion();
        assertEquals(0, fraccion.getNumerador());
        assertEquals(1, fraccion.getDenominador());
        assertEquals(Fraccion.Signo.POSITIVE, fraccion.getSigno());
    }

    @Test
    void constructorIntTest() {
        Fraccion fraccion = new Fraccion(2, 3);
        assertEquals(2, fraccion.getNumerador());
        assertEquals(3, fraccion.getDenominador());
        assertEquals(Fraccion.Signo.POSITIVE, fraccion.getSigno());
    }

    @Test
    void constructorFloatTest() {
        Fraccion fraccion = new Fraccion(1.5f).reducir();
        assertEquals(3, fraccion.getNumerador());
        assertEquals(2, fraccion.getDenominador());
        assertEquals(Fraccion.Signo.POSITIVE, fraccion.getSigno());
    }

    @Test
    void constructorStringTest() {
        Fraccion fraccion1 = new Fraccion("2/3");
        assertEquals(2, fraccion1.getNumerador());
        assertEquals(3, fraccion1.getDenominador());
        assertEquals(Fraccion.Signo.POSITIVE, fraccion1.getSigno());

        Fraccion fraccion2 = new Fraccion("-1/4");
        assertEquals(1, fraccion2.getNumerador());
        assertEquals(4, fraccion2.getDenominador());
        assertEquals(Fraccion.Signo.NEGATIVE, fraccion2.getSigno());
    }

    @Test
    void toStringTest() {
        Fraccion fraccion1 = new Fraccion(2, 3);
        assertEquals("2/3", fraccion1.toString());

        Fraccion fraccion2 = new Fraccion(5, 1);
        assertEquals("5", fraccion2.toString());

        Fraccion fraccion3 = new Fraccion(-1, 4);
        assertEquals("-1/4", fraccion3.toString());
    }

    @Test
    void reducirTest() {
        Fraccion fraccion = new Fraccion(6, 8).reducir();
        assertEquals(3, fraccion.getNumerador());
        assertEquals(4, fraccion.getDenominador());
    }

    @Test
    void convertToFraccionMixtaTest() {
        Fraccion fraccion = new Fraccion(7, 3);
        FraccionMixta mixta = Fraccion.convertToFraccionMixta(fraccion);
        assertEquals(2, mixta.getEntero());
        assertEquals(1, mixta.getNumerador());
        assertEquals(3, mixta.getDenominador());
    }

    @Test
    void constructorStringInvalidTest() {
        assertThrows(NumberFormatException.class, () -> new Fraccion("2/abc"));

        assertThrows(NumberFormatException.class, () -> new Fraccion("2.5"));
    }

    @Test
    void getFraccionWithSignTest() {
        Fraccion fraccion = new Fraccion(-5, 9);

        assertEquals(5, fraccion.getNumerador());
        assertEquals(9, fraccion.getDenominador());
        assertEquals(Fraccion.Signo.NEGATIVE, fraccion.getSigno());

        Fraccion conSigno = fraccion.getFraccionWithSign();
        assertEquals(-5, conSigno.getNumerador());
        assertEquals(9, conSigno.getDenominador());
        assertEquals(Fraccion.Signo.POSITIVE, conSigno.getSigno());
    }

    @Test
    void equalsTest() {
        Fraccion fraccion1 = new Fraccion(-5, 9);
        Fraccion fraccion2 = new Fraccion(3, 4);
        Fraccion fraccion3 = new Fraccion(-5, 9);

        assertEquals(fraccion1, fraccion3);
        assertNotEquals(fraccion1, fraccion2);
        assertNotEquals(fraccion2, fraccion3);
    }

    @Test
    void isEquivalenteTest() {
        Fraccion fraccion1 = new Fraccion(1, 2);
        Fraccion fraccion2 = new Fraccion(3, 4);
        Fraccion fraccion3 = new Fraccion(2, 4);

        assertTrue(fraccion1.isEquivalente(fraccion3));
        assertFalse(fraccion1.isEquivalente(fraccion2));
        assertFalse(fraccion2.isEquivalente(fraccion3));
    }
}