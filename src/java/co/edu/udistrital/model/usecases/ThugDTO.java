package co.edu.udistrital.model.usecases;

import co.edu.udistrital.model.entities.Thug;
import co.edu.udistrital.model.structures.SimpleList;

/**
 * Objeto encargado de servir de comuncionacion entre la logica y el controlador
 * paresido a un JSON de lso hampones
 *
 * @author Juan David Diaz Perez
 */
public class ThugDTO {

    private final int[] iterations;
    private final SimpleList<Thug> unorderedList;
    private final SimpleList<Thug> orderedList;
    private final int columns;
    private final int rows;

    /**
     * Contructor del objeto de transferencia de datos
     *
     * @param iterations iteraciones de cada algoritmo
     * @param unorderedList lista desorganizada de hampones
     * @param orderedList lista organizada de hampones
     * @param columns columnas de la matriz
     * @param rows filas de la matriz
     */
    public ThugDTO(int[] iterations, SimpleList<Thug> unorderedList, SimpleList<Thug> orderedList, int columns, int rows) {
        this.iterations = iterations;
        this.unorderedList = unorderedList;
        this.orderedList = orderedList;
        this.columns = columns;
        this.rows = rows;
    }

    /**
     * Getter del las iteraciones de cada algoritmo
     *
     * @return el arreglo de iteraciones de cada algoritmo
     */
    public int[] getIterations() {
        return iterations;
    }

    /**
     * Getter de la lista ordenada de hampones
     *
     * @return la lista ordenada de hampones
     */
    public SimpleList<Thug> getOrderedList() {
        return orderedList;
    }

    /**
     * Getter de las columnas
     *
     * @return el numero de columnas de la matriz
     */
    public int getColumns() {
        return columns;
    }

    /**
     * Getter de las filas
     *
     * @return el numero de filas de la matriz
     */
    public int getRows() {
        return rows;
    }

    /**
     * Getter de la lista desordenada
     *
     * @return la lista desordenada
     */
    public SimpleList<Thug> getUnorderedList() {
        return unorderedList;
    }
}
