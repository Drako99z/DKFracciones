package com.drako.dk.fracciones;

import java.util.Objects;

/**
 * Clase para representar fracciones mixtas matemáticas.
 */
public class FraccionMixta extends Fraccion {

    /**
     * El número entero de la fracción mixta.
     */
    private Integer entero;

    /**
     * Constructor por defecto. Crea una fracción mixta con entero 0, numerador 0, denominador 1 y signo positivo.
     */
    public FraccionMixta() {
        super();
        this.entero = 0;
    }

    /**
     * Construye una nueva instancia de la clase FraccionMixta con el numerador, denominador, signo y parte entera especificados.
     *
     * @param numerador   El numerador de la fracción mixta.
     * @param denominador El denominador de la fracción mixta.
     * @param signo       El signo de la fracción mixta (POSITIVO o NEGATIVO).
     * @param entero      La parte entera de la fracción mixta.
     */
    public FraccionMixta(Integer numerador, Integer denominador, Signo signo, Integer entero) {
        super(numerador, denominador, signo);
        this.entero = Math.abs(entero);
    }

    /**
     * Constructor que inicializa la fracción mixta con el entero, numerador y denominador dados.
     *
     * @param numerador   El numerador de la fracción mixta.
     * @param denominador El denominador de la fracción mixta.
     * @param entero      El número entero de la fracción mixta.
     */
    public FraccionMixta(int numerador, int denominador, int entero) {
        initialize(numerador, denominador, entero);
    }

    /**
     * Constructor que inicializa la fracción mixta a partir de un número decimal.
     *
     * @param numero El número decimal del cual se creará la fracción mixta.
     */
    public FraccionMixta(float numero) {
        FraccionMixta fr = Fraccion.convertToFraccionMixta(new Fraccion(numero));
        this.numerador = fr.numerador;
        this.denominador = fr.denominador;
        this.entero = fr.entero;
        this.signo = fr.signo;
    }

    /**
     * Constructor que inicializa la fracción mixta a partir de una cadena que representa una fracción mixta.
     *
     * @param fraccion La cadena que representa la fracción mixta comenzando por el valor entero. Ejemplo: "1/3/4" o "-2/5/6".
     * @throws NumberFormatException Si la cadena no está en el formato esperado o si no se pueden convertir los valores en enteros.
     */
    public FraccionMixta(String fraccion) throws NumberFormatException {
        int entero, numerador, denominador;
        Integer[] valores = getValuesFromString(fraccion);
        if (valores.length > 2) {
            entero = valores[0];
            numerador = valores[1];
            denominador = valores[2];
        } else if (valores.length == 1) {
            entero = valores[0];
            numerador = 0;
            denominador = 1;
        } else {
            entero = 0;
            numerador = valores[0];
            denominador = valores[1];
        }
        initialize(numerador, denominador, entero);
    }

    /**
     * Inicializa la fracción mixta con el entero, numerador y denominador dados.
     *
     * @param numerador   El numerador de la fracción mixta.
     * @param denominador El denominador de la fracción mixta.
     * @param entero      El número entero de la fracción mixta.
     */
    private void initialize(int numerador, int denominador, int entero) {
        this.numerador = Math.abs(numerador);
        this.denominador = Math.abs(denominador);
        this.entero = Math.abs(entero);
        this.signo = getSignoFraccion(numerador, denominador, entero);
    }

    /**
     * Reduce la fracción mixta a su forma irreducible.
     *
     * @return Una nueva instancia de FraccionMixta que representa la fracción mixta reducida.
     */
    @Override
    public FraccionMixta reducir() {
        Fraccion fraccion = convertToFraccion(this).reducir();
        return Fraccion.convertToFraccionMixta(fraccion);
    }

    /**
     * Convierte la fracción mixta en una fracción impropia.
     *
     * @param fraccion La fracción mixta que se convertirá en fracción impropia.
     * @return Una instancia de Fraccion que representa la fracción impropia equivalente.
     */
    public static Fraccion convertToFraccion(FraccionMixta fraccion) {
        int numerador = fraccion.entero * fraccion.denominador + fraccion.numerador;
        return new Fraccion(numerador, fraccion.denominador, fraccion.getSigno());
    }

    /**
     * Devuelve el número entero de la fracción mixta.
     *
     * @return El número entero de la fracción mixta.
     */
    public Integer getEntero() {
        return entero;
    }

    /**
     * Establece el número entero de la fracción mixta.
     *
     * @param entero El nuevo número entero de la fracción mixta.
     */
    public void setEntero(Integer entero) {
        this.entero = entero;
    }

    /**
     * Devuelve una representación en forma de cadena de la fracción mixta.
     *
     * @return Una cadena que representa la fracción mixta.
     */
    @Override
    public String toString() {
        String signo = (this.signo == Signo.NEGATIVE) ? "-" : "";
        if (entero != 0) {
            if (numerador != 0) return signo + String.format("%d %d/%d", entero, numerador, denominador);
            else return signo + String.format("%d", entero);
        } else {
            if (numerador != 0) return signo + String.format("%d/%d", numerador, denominador);
            else return "0";
        }
    }

    /**
     * Compara si un objeto recibido es igual a este objeto.
     *
     * @param o El objeto a comparar.
     * @return {@code true} si son iguales, {@code false} si son diferentes.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FraccionMixta that = (FraccionMixta) o;
        return Objects.equals(numerador, that.numerador) && Objects.equals(denominador, that.denominador) && Objects.equals(entero, that.entero) && signo == that.signo;
    }

    /**
     * Devuelve un código hash para esta fracción mixta.
     *
     * @return El código hash calculado para esta fracción mixta.
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), entero);
    }

    /**
     * Verifica si esta fracción mixta es equivalente a otra fracción mixta dada.
     *
     * @param otra la otra fracción mixta a comparar.
     * @return true si las fracciones mixtas son equivalentes, false de lo contrario.
     */
    public boolean isEquivalente(FraccionMixta otra) {
        Fraccion fraccion1 = convertToFraccion(this);
        Fraccion fraccion2 = convertToFraccion(otra);
        return fraccion1.isEquivalente(fraccion2);
    }

    /**
     * Convierte la fracción mixta en un número decimal.
     *
     * @return El valor decimal de la fracción mixta.
     */
    @Override
    public float toFloat() {
        float res = entero + ((numerador * 1.0f) / (denominador * 1.0f));
        res *= (signo == Signo.NEGATIVE) ? -1 : 1;
        return res;
    }

    /**
     * Determina el signo de la fracción mixta basándose en el numerador, denominador y número entero.
     *
     * @param numerador   El numerador de la fracción mixta.
     * @param denominador El denominador de la fracción mixta.
     * @param entero      El número entero de la fracción mixta.
     * @return El signo de la fracción mixta (POSITIVO o NEGATIVO).
     */
    private static Signo getSignoFraccion(int numerador, int denominador, int entero) {
        if ((entero < 0 || numerador < 0) && denominador < 0) return Signo.POSITIVE;
        else return entero < 0 || numerador < 0 || denominador < 0 ? Signo.NEGATIVE : Signo.POSITIVE;
    }

    /**
     * Aplica el signo de la fracción a la parte entera o numerador en caso de ser 0.
     * Si la fracción original es negativa, la fracción devuelta será su contraparte positiva,
     * manteniendo el mismo valor absoluto. Es decir, si el signo original es NEGATIVO, la parte entera o el
     * numerador de la fracción devuelta será negativo y el signo será POSITIVO. Si la fracción
     * original ya es positiva, se devuelve una copia de la misma fracción sin cambios.
     *
     * @return Una nueva instancia de FraccionMixta con el mismo valor absoluto pero con signo positivo.
     */
    @Override
    public FraccionMixta getFraccionWithSign() {
        FraccionMixta fm = new FraccionMixta(this.numerador, this.denominador, Signo.POSITIVE, this.entero);
        if (signo == Signo.NEGATIVE) {
            if (entero > 0)
                fm.entero *= -1;
            else
                fm.numerador *= -1;
        }
        return fm;
    }
}
