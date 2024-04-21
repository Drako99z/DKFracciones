package com.drako.dk.fracciones;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OperadorTest {
    @Test
    void sumaTest() {
        Fraccion f1 = new Fraccion(1, 2);
        Fraccion f2 = new Fraccion(1, 3);
        Fraccion suma1 = Operador.suma(f1, f2);
        assertEquals(5, suma1.getNumerador());
        assertEquals(6, suma1.getDenominador());

        FraccionMixta mixta1 = new FraccionMixta(1, 4, 2);
        FraccionMixta mixta2 = new FraccionMixta(1, 6, 3);
        FraccionMixta suma2 = Operador.suma(mixta1, mixta2);
        assertEquals(5, suma2.getEntero());
        assertEquals(5, suma2.getNumerador());
        assertEquals(12, suma2.getDenominador());
    }

    @Test
    void restaTest() {
        Fraccion f1 = new Fraccion(3, 4);
        Fraccion f2 = new Fraccion(1, 2);
        Fraccion resta1 = Operador.resta(f1, f2);
        assertEquals(1, resta1.getNumerador());
        assertEquals(4, resta1.getDenominador());

        FraccionMixta mixta1 = new FraccionMixta(1, 4, 2);
        FraccionMixta mixta2 = new FraccionMixta(1, 6, 3);
        FraccionMixta resta2 = Operador.resta(mixta1, mixta2);
        assertEquals(0, resta2.getEntero());
        assertEquals(11, resta2.getNumerador());
        assertEquals(12, resta2.getDenominador());
        assertEquals(Fraccion.Signo.NEGATIVE, resta2.getSigno());
    }

    @Test
    void multiplicarTest() {
        Fraccion f1 = new Fraccion(2, 3);
        Fraccion f2 = new Fraccion(3, 4);
        Fraccion multiplicacion1 = Operador.multiplicar(f1, f2);
        assertEquals(1, multiplicacion1.getNumerador());
        assertEquals(2, multiplicacion1.getDenominador());

        FraccionMixta mixta1 = new FraccionMixta(1, 4, 2);
        FraccionMixta mixta2 = new FraccionMixta(1, 6, 3);
        FraccionMixta multiplicacion2 = Operador.multiplicar(mixta1, mixta2);
        assertEquals(7, multiplicacion2.getEntero());
        assertEquals(1, multiplicacion2.getNumerador());
        assertEquals(8, multiplicacion2.getDenominador());
    }

    @Test
    void divisionTest() {
        Fraccion f1 = new Fraccion(2, 3);
        Fraccion f2 = new Fraccion(3, 4);
        Fraccion division1 = Operador.division(f1, f2);
        assertEquals(8, division1.getNumerador());
        assertEquals(9, division1.getDenominador());

        FraccionMixta mixta1 = new FraccionMixta(1, 4, 2);
        FraccionMixta mixta2 = new FraccionMixta(1, 6, 3);
        FraccionMixta division2 = Operador.division(mixta1, mixta2);
        assertEquals(0, division2.getEntero());
        assertEquals(27, division2.getNumerador());
        assertEquals(38, division2.getDenominador());
        assertEquals(Fraccion.Signo.POSITIVE, division2.getSigno());
    }

    @Test
    void raizTest() {
        Fraccion f1 = new Fraccion(9, 4);
        Fraccion raiz1 = Operador.raiz(f1);
        assertEquals(3, raiz1.getNumerador());
        assertEquals(2, raiz1.getDenominador());

        FraccionMixta mixta = new FraccionMixta(1, 4, 2);
        FraccionMixta raiz2 = Operador.raiz(mixta);

        assertEquals(1, raiz2.getEntero());
        assertEquals(1, raiz2.getNumerador());
        assertEquals(2, raiz2.getDenominador());
    }

    @Test
    void raizDoubleTest() {
        Fraccion f1 = new Fraccion(9, 4);
        double raizDouble1 = Operador.raizDouble(f1);
        assertEquals(1.5, raizDouble1);

        FraccionMixta mixta = new FraccionMixta(1, 4, 2);
        double raizDouble2 = Operador.raizDouble(mixta);
        assertEquals(1.5, raizDouble2);
    }

    @Test
    void potenciaTest() {
        Fraccion f1 = new Fraccion(2, 3);
        Fraccion potencia1 = Operador.potencia(f1, 2.0);
        assertEquals(4, potencia1.getNumerador());
        assertEquals(9, potencia1.getDenominador());

        FraccionMixta mixta = new FraccionMixta(1, 4, 2);
        FraccionMixta potencia2 = Operador.potencia(mixta, 2.0);
        assertEquals(5, potencia2.getEntero());
        assertEquals(1, potencia2.getNumerador());
        assertEquals(16, potencia2.getDenominador());
    }

    @Test
    void mcdTest() {
        int mcd1 = Operador.mcd(24, 36);
        assertEquals(12, mcd1);

        int mcd2 = Operador.mcd(17, 23);
        assertEquals(1, mcd2);
    }

    @Test
    void lcmTest() {
        int lcm1 = Operador.lcm(24, 36);
        assertEquals(72, lcm1);

        int lcm2 = Operador.lcm(17, 23);
        assertEquals(391, lcm2);
    }
}