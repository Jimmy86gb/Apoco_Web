package co.edu.udistrital.model.usecases;

import co.edu.udistrital.model.entities.Politician;
import co.edu.udistrital.model.structures.SimpleList;

/**
 * Objeto encargado de servir de comuncionacion entre la logica y el controlador
 *
 * @author Juan David Diaz Perez
 */
public class ApocoDTO {

    private final int[] iterations;
    private final SimpleList<Politician> unorderedList;
    private final SimpleList<Politician> orderedList;
    private final int usedSize;

    /**
     * Constructor de ApocoDTO
     * 
     * @param iteraciones Numero de iterations de cada algoritmo usado
     * @param listaDesordenada Lista desordenada para mostrar en la vista
     * @param listaOrdenada Lista ya ordenada para mostrar en la vista
     * @param sizeUtilizado Numero de politicos usados en el ejemplo actual
     */
    public ApocoDTO(int[] iteraciones, SimpleList<Politician> listaDesordenada, SimpleList<Politician> listaOrdenada, int sizeUtilizado) {
        this.iterations = iteraciones;
        this.unorderedList = listaDesordenada;
        this.orderedList = listaOrdenada;
        this.usedSize = sizeUtilizado;
    }

    /**
     * Getter de las iteraciones que tuvo cada algoritmo
     *
     * @return el numero de iteraciones por cada sorteo usado
     */
    public int[] getIterations() {
        return iterations;
    }

    /**
     * Getter de la lista ordenada
     *
     * @return la lista ordenada de todos lo politicos
     */
    public SimpleList<Politician> getOrderedList() {
        return orderedList;
    }

    /**
     * Getter del size
     *
     * @return del numero de politicos usados en el algoritmo
     */
    public int getUsedSize() {
        return usedSize;
    }

    /**
     * Getter del la lista desordenada
     *
     * @return la lista inicial desordenada
     */
    public SimpleList<Politician> getUnorderedList() {
        return unorderedList;
    }
}
