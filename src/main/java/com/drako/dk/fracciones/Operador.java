package com.drako.dk.fracciones;

/**
 * Esta clase proporciona métodos estáticos para realizar operaciones matemáticas con fracciones y fracciones mixtas.
 * Incluye métodos para la suma, resta, multiplicación, división, cálculo de raíces, potenciación, así como para calcular
 * el Máximo Común Divisor (MCD) y el Mínimo Común Múltiplo (MCM) de dos números enteros.
 */
public final class Operador {

    /**
     * Realiza la suma de dos fracciones.
     *
     * @param f1 La primera fracción.
     * @param f2 La segunda fracción.
     * @return La fracción resultante de la suma.
     */
    public static Fraccion suma(Fraccion f1, Fraccion f2) {
        f1 = f1.getFraccionWithSign();
        f2 = f2.getFraccionWithSign();
        int resNum = f1.numerador * f2.denominador + f1.denominador * f2.numerador;
        int resDen = f1.denominador * f2.denominador;

        return new Fraccion(resNum, resDen).reducir();
    }

    /**
     * Realiza la suma de dos fracciones mixtas.
     *
     * @param f1 La primera fracción mixta.
     * @param f2 La segunda fracción mixta.
     * @return La fracción mixta resultante de la suma.
     */
    public static FraccionMixta suma(FraccionMixta f1, FraccionMixta f2) {
        Fraccion resf = suma(FraccionMixta.convertToFraccion(f1), FraccionMixta.convertToFraccion(f2));
        return Fraccion.convertToFraccionMixta(resf);
    }

    /**
     * Realiza la resta de dos fracciones.
     *
     * @param f1 La primera fracción.
     * @param f2 La segunda fracción.
     * @return La fracción resultante de la resta.
     */
    public static Fraccion resta(Fraccion f1, Fraccion f2) {
        f1 = f1.getFraccionWithSign();
        f2 = f2.getFraccionWithSign();
        int resNum = f1.numerador * f2.denominador - f1.denominador * f2.numerador;
        int resDen = f1.denominador * f2.denominador;

        return new Fraccion(resNum, resDen).reducir();
    }

    /**
     * Realiza la resta de dos fracciones mixtas.
     *
     * @param f1 La primera fracción mixta.
     * @param f2 La segunda fracción mixta.
     * @return La fracción mixta resultante de la resta.
     */
    public static FraccionMixta resta(FraccionMixta f1, FraccionMixta f2) {
        Fraccion resf = resta(FraccionMixta.convertToFraccion(f1), FraccionMixta.convertToFraccion(f2));
        return Fraccion.convertToFraccionMixta(resf);
    }

    /**
     * Realiza la multiplicación de dos fracciones.
     *
     * @param f1 La primera fracción.
     * @param f2 La segunda fracción.
     * @return La fracción resultante de la multiplicación.
     */
    public static Fraccion multiplicar(Fraccion f1, Fraccion f2) {
        f1 = f1.getFraccionWithSign();
        f2 = f2.getFraccionWithSign();
        int resNum = (f1.numerador * f2.numerador);
        int resDen = (f1.denominador * f2.denominador);

        return new Fraccion(resNum, resDen).reducir();
    }

    /**
     * Realiza la multiplicación de dos fracciones mixtas.
     *
     * @param f1 La primera fracción mixta.
     * @param f2 La segunda fracción mixta.
     * @return La fracción mixta resultante de la multiplicación.
     */
    public static FraccionMixta multiplicar(FraccionMixta f1, FraccionMixta f2) {
        Fraccion resf = multiplicar(FraccionMixta.convertToFraccion(f1), FraccionMixta.convertToFraccion(f2));
        return Fraccion.convertToFraccionMixta(resf);
    }

    /**
     * Realiza la división de dos fracciones.
     *
     * @param f1 La fracción que se dividirá.
     * @param f2 La fracción por la cual se dividirá.
     * @return La fracción resultante de la división.
     */
    public static Fraccion division(Fraccion f1, Fraccion f2) {
        f1 = f1.getFraccionWithSign();
        f2 = f2.getFraccionWithSign();
        int resNum = (f1.numerador * f2.denominador);
        int resDen = (f1.denominador * f2.numerador);

        return new Fraccion(resNum, resDen).reducir();
    }

    /**
     * Realiza la división de dos fracciones mixtas.
     *
     * @param f1 La fracción mixta que se dividirá.
     * @param f2 La fracción mixta por la cual se dividirá.
     * @return La fracción mixta resultante de la división.
     */
    public static FraccionMixta division(FraccionMixta f1, FraccionMixta f2) {
        Fraccion resf = division(FraccionMixta.convertToFraccion(f1), FraccionMixta.convertToFraccion(f2));
        return Fraccion.convertToFraccionMixta(resf);
    }

    /**
     * Calcula la raíz cuadrada de una fracción y devuelve el resultado como un número de punto flotante.
     *
     * @param f1 La fracción de la cual se calculará la raíz cuadrada.
     * @return El valor de la raíz cuadrada como un número de punto flotante.
     */
    public static double raizDouble(Fraccion f1) {
        f1 = f1.getFraccionWithSign();
        return Math.sqrt(f1.toFloat());
    }

    /**
     * Calcula la raíz cuadrada de una fracción mixta y devuelve el resultado como un número de punto flotante.
     *
     * @param f1 La fracción mixta de la cual se calculará la raíz cuadrada.
     * @return El valor de la raíz cuadrada como un número de punto flotante.
     */
    public static double raizDouble(FraccionMixta f1) {
        return raizDouble(FraccionMixta.convertToFraccion(f1));
    }

    /**
     * Calcula la raíz cuadrada de una fracción y devuelve el resultado como una nueva fracción reducida.
     *
     * @param f1 La fracción de la cual se calculará la raíz cuadrada.
     * @return La fracción resultante de la raíz cuadrada.
     */
    public static Fraccion raiz(Fraccion f1) {
        int resNum = (int) Math.round(Math.sqrt(f1.numerador));
        int resDen = (int) Math.round(Math.sqrt(f1.denominador));
        return new Fraccion(resNum, resDen).reducir();
    }

    /**
     * Calcula la raíz cuadrada de una fracción mixta y devuelve el resultado como una nueva fracción mixta reducida.
     *
     * @param f1 La fracción mixta de la cual se calculará la raíz cuadrada.
     * @return La fracción mixta resultante de la raíz cuadrada.
     */
    public static FraccionMixta raiz(FraccionMixta f1) {
        Fraccion resf = raiz(FraccionMixta.convertToFraccion(f1));
        return Fraccion.convertToFraccionMixta(resf);
    }

    /**
     * Calcula el cuadrado de una fracción y devuelve el resultado como una nueva fracción reducida.
     *
     * @param f1 La fracción que se elevará al cuadrado.
     * @return La fracción resultante de elevar al cuadrado.
     */
    public static Fraccion potencia(Fraccion f1, double exponente) {
        f1 = f1.getFraccionWithSign();
        int resNum = (int) (Math.pow(f1.numerador, exponente));
        int resDen = (int) (Math.pow(f1.denominador, exponente));

        return new Fraccion(resNum, resDen).reducir();
    }

    /**
     * Calcula el cuadrado de una fracción mixta y devuelve el resultado como una nueva fracción mixta reducida.
     *
     * @param f1 La fracción mixta que se elevará al cuadrado.
     * @return La fracción mixta resultante de elevar al cuadrado.
     */
    public static FraccionMixta potencia(FraccionMixta f1, double exponente) {
        Fraccion resf = potencia(FraccionMixta.convertToFraccion(f1), exponente);
        return Fraccion.convertToFraccionMixta(resf);
    }

    /**
     * Calcula el Máximo Común Divisor (MCD) de dos números.
     *
     * @param a El primer número.
     * @param b El segundo número.
     * @return El Máximo Común Divisor de los dos números.
     */
    public static int mcd(int a, int b) {
        a = Math.abs(a);
        b = Math.abs(b);
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    /**
     * Calcula el Mínimo Común Múltiplo (MCM) de dos números.
     *
     * @param a El primer número.
     * @param b El segundo número.
     * @return El Mínimo Común Múltiplo de los dos números.
     */
    public static int lcm(int a, int b) {
        int producto = Math.abs(a * b);
        return producto / mcd(a, b);
    }

}
