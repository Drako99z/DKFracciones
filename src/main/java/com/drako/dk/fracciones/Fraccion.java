package com.drako.dk.fracciones;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

/**
 * Clase para representar fracciones matemáticas.
 */
public class Fraccion {

    /**
     * Enumeración para representar el signo de la fracción
     */
    public enum Signo {
        POSITIVE, NEGATIVE
    }

    /**
     * El numerador de la fracción.
     */
    protected Integer numerador;

    /**
     * El denominador de la fracción.
     */
    protected Integer denominador;

    /**
     * El signo de la fracción.
     */
    protected Signo signo;

    /**
     * Constructor por defecto. Crea una fracción con numerador 0, denominador 1 y signo positivo.
     */
    public Fraccion() {
        this(0, 1, Signo.POSITIVE);
    }

    /**
     * Construye una nueva instancia de la clase Fraccion con el numerador, denominador y signo especificados.
     *
     * @param numerador   El numerador de la fracción.
     * @param denominador El denominador de la fracción.
     * @param signo       El signo de la fracción (POSITIVO o NEGATIVO).
     */
    public Fraccion(Integer numerador, Integer denominador, Signo signo) {
        this.numerador = Math.abs(numerador);
        this.denominador = Math.abs(denominador);
        this.signo = signo;
    }

    /**
     * Constructor que inicializa la fracción con el numerador y denominador dados.
     *
     * @param numerador   El numerador de la fracción.
     * @param denominador El denominador de la fracción.
     */
    public Fraccion(int numerador, int denominador) {
        initialize(numerador, denominador);
    }

    /**
     * Constructor que inicializa la fracción a partir de un número decimal.
     *
     * @param numero El número decimal del cual se creará la fracción.
     */
    public Fraccion(float numero) {
        Integer[] valores = getValuesFromDecimal(numero);
        initialize(valores[0], valores[1]);
    }

    /**
     * Constructor que inicializa la fracción a partir de una cadena que representa una fracción.
     *
     * @param fraccion La cadena que representa la fracción. Ejemplo: "3/4" o "-5/6".
     * @throws NumberFormatException Si la cadena no puede ser analizada correctamente.
     */
    public Fraccion(String fraccion) throws NumberFormatException {
        Integer[] valores = getValuesFromString(fraccion);
        int numerador, denominador;
        if (valores.length > 2) {
            numerador = Math.abs(valores[0] * valores[2]) + Math.abs(valores[1]);
            if (valores[0] < 0 || valores[1] < 0) {
                numerador *= -1;
            }
            denominador = valores[2];
        } else if (valores.length == 1) {
            numerador = valores[0];
            denominador = 1;
        } else {
            numerador = valores[0];
            denominador = valores[1];
        }
        initialize(numerador, denominador);
    }

    /**
     * Inicializa la fracción con el numerador y denominador dados.
     * El método también determina el signo de la fracción en función del numerador y denominador.
     * Si tanto el numerador como el denominador son negativos, el signo de la fracción será positivo.
     * Si uno de ellos es negativo, el signo de la fracción será negativo.
     *
     * @param numerador   El numerador de la fracción.
     * @param denominador El denominador de la fracción.
     */
    private void initialize(int numerador, int denominador) {
        this.numerador = Math.abs(numerador);
        this.denominador = Math.abs(denominador);
        this.signo = getSignoFraccion(numerador, denominador);
    }

    /**
     * Reduce la fracción a su forma irreducible.
     *
     * @return Una nueva instancia de Fraccion que representa la fracción reducida.
     */
    public Fraccion reducir() {
        int mcd = Operador.mcd(this.numerador, this.denominador);
        int nuevoNumerador = this.numerador / mcd;
        int nuevoDenominador = this.denominador / mcd;

        Fraccion fr = new Fraccion();
        fr.setNumerador(nuevoNumerador);
        fr.setDenominador(nuevoDenominador);
        fr.setSigno(signo);

        return fr;
    }

    /**
     * Convierte la fracción en una fracción mixta.
     *
     * @param fraccion La fracción que se convertirá en fracción mixta.
     * @return Una instancia de FraccionMixta que representa la fracción mixta equivalente.
     */
    public static FraccionMixta convertToFraccionMixta(Fraccion fraccion) {
        int entero = fraccion.numerador / fraccion.denominador;
        int numeradorResidual = Math.abs(fraccion.numerador % fraccion.denominador);

        if (numeradorResidual == 0) {
            return new FraccionMixta(0, 1, fraccion.signo, entero);
        } else {
            return new FraccionMixta(numeradorResidual, fraccion.denominador, fraccion.getSigno(), entero);
        }
    }

    /**
     * Extrae los valores numéricos de una cadena que representa una fracción.
     *
     * @param fraccion La cadena que representa la fracción. Debe estar en el formato "numerador/denominador".
     * @return Un arreglo de enteros que contiene el numerador y el denominador de la fracción.
     * @throws NumberFormatException Si la cadena no está en el formato esperado o si no se pueden convertir los valores en enteros.
     */
    protected static Integer[] getValuesFromString(String fraccion) throws NumberFormatException {
        String[] vals = fraccion.replaceAll("\\s+", "").split("/");
        Integer[] valores = new Integer[vals.length];

        for (int i = 0; i < vals.length; i++) {
            valores[i] = Integer.valueOf(vals[i]);
        }
        return valores;
    }

    /**
     * Convierte un número decimal en una fracción de dos enteros.
     *
     * @param decimal El número decimal del cual se creará la fracción.
     * @return Un arreglo de enteros que contiene el numerador y el denominador de la fracción equivalente al decimal.
     */
    private static Integer[] getValuesFromDecimal(float decimal) {
        BigDecimal bd = new BigDecimal(Float.toString(decimal)).setScale(5, RoundingMode.HALF_UP);
        float decimalRed = bd.floatValue();

        int entero = (int) decimalRed;
        float decimales = decimalRed - entero;
        long valor = (long) Math.pow(10, String.valueOf(decimales).length());

        int numerador = (int) (entero * valor + decimales * valor);
        int denominador = (int) valor;

        Integer[] valores = new Integer[2];
        valores[0] = numerador;
        valores[1] = denominador;

        return valores;
    }

    /**
     * Obtiene el numerador de la fracción.
     *
     * @return El numerador de la fracción.
     */
    public Integer getNumerador() {
        return numerador;
    }

    /**
     * Establece el numerador de la fracción.
     *
     * @param numerador El nuevo valor del numerador.
     */
    public void setNumerador(Integer numerador) {
        this.numerador = numerador;
    }

    /**
     * Obtiene el denominador de la fracción.
     *
     * @return El denominador de la fracción.
     */
    public Integer getDenominador() {
        return denominador;
    }

    /**
     * Establece el denominador de la fracción.
     *
     * @param denominador El nuevo valor del denominador.
     */
    public void setDenominador(Integer denominador) {
        this.denominador = denominador;
    }

    /**
     * Obtiene el signo de la fracción.
     *
     * @return El signo de la fracción.
     */
    public Signo getSigno() {
        return signo;
    }

    /**
     * Establece el signo de la fracción.
     *
     * @param signo El nuevo signo de la fracción.
     */
    public void setSigno(Signo signo) {
        this.signo = signo;
    }

    /**
     * Devuelve una representación en forma de cadena de la fracción.
     * Si la fracción es un número entero, solo se muestra el numerador.
     * Si la fracción es una fracción propia o impropia, se muestra en formato "numerador/denominador".
     * Si la fracción es negativa, se muestra precedida por el signo "-".
     *
     * @return Una cadena que representa la fracción.
     */
    @Override
    public String toString() {
        String signo = (this.signo == Signo.NEGATIVE) ? "-" : "";
        if (numerador % denominador == 0) {
            return signo + numerador / denominador;
        }
        return signo + String.format("%d/%d", numerador, denominador);
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
        Fraccion fraccion = (Fraccion) o;
        return Objects.equals(numerador, fraccion.numerador) && Objects.equals(denominador, fraccion.denominador) && signo == fraccion.signo;
    }

    /**
     * Devuelve un código hash para esta fracción.
     *
     * @return El código hash calculado para esta fracción.
     */
    @Override
    public int hashCode() {
        return Objects.hash(numerador, denominador, signo);
    }

    /**
     * Verifica si esta fracción es equivalente a otra fracción dada.
     *
     * @param otra la otra fracción a comparar.
     * @return {@code true} si las fracciones son equivalentes, {@code false} de lo contrario.
     */
    public boolean isEquivalente(Fraccion otra) {
        if (this.signo != otra.signo) {
            return false;
        }
        int productoCruzado1 = this.numerador * otra.denominador;
        int productoCruzado2 = otra.numerador * this.denominador;
        return productoCruzado1 == productoCruzado2;
    }

    /**
     * Convierte la fracción en un número decimal de punto flotante.
     *
     * @return El valor decimal de la fracción.
     */
    public float toFloat() {
        float res = (numerador * 1.0f) / (denominador * 1.0f);
        res *= (signo == Signo.NEGATIVE) ? -1 : 1;
        return res;
    }

    /**
     * Determina el signo de una fracción basándose en el numerador y el denominador.
     * Si tanto el numerador como el denominador son negativos, el signo de la fracción es positivo.
     * Si uno de ellos es negativo, el signo de la fracción es negativo.
     * Si ambos son positivos, el signo de la fracción es positivo.
     *
     * @param numerador   El numerador de la fracción.
     * @param denominador El denominador de la fracción.
     * @return El signo de la fracción (POSITIVO o NEGATIVO).
     */
    private static Signo getSignoFraccion(int numerador, int denominador) {
        if (numerador < 0 && denominador < 0) {
            return Signo.POSITIVE;
        } else if (numerador < 0 || denominador < 0) {
            return Signo.NEGATIVE;
        } else {
            return Signo.POSITIVE;
        }
    }

    /**
     * Aplica el signo de la fracción al numerador de la misma.
     * Si la fracción original es negativa, la fracción devuelta será su contraparte positiva,
     * manteniendo el mismo valor absoluto. Es decir, si el signo original es NEGATIVO, el
     * numerador de la fracción devuelta será negativo y el signo será POSITIVO. Si la fracción
     * original ya es positiva, se devuelve una copia de la misma fracción sin cambios.
     *
     * @return Una nueva instancia de Fraccion con el mismo valor absoluto pero con signo positivo.
     */
    public Fraccion getFraccionWithSign() {
        Fraccion fr = new Fraccion(this.numerador, this.denominador, Signo.POSITIVE);
        if (signo == Signo.NEGATIVE)
            fr.numerador *= -1;
        return fr;
    }
}
