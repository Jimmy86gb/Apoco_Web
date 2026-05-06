package co.edu.udistrital.model.sort;

import co.edu.udistrital.model.structures.*;
import java.util.Comparator;

/**
 * Algoritmo de ordenamiento de burbuja adaptado para listas enlazadas
 *
 * @author Jimmy86gb
 * @param <T> el tipo de dato que vamos a ordenar
 */
public class BubbleSort<T> implements Sorter<T> {

    /**
     * Ordena la lista comparando vecinos y los mueve si estan mal
     *
     * @param list la lista que queremos ordenar
     * @param comparator las reglas para saber quien es mayor
     * @return el numero total de veces que tuvo que comparar
     */
    @Override
    public int sort(SimpleList<T> list, Comparator<T> comparator) {
        int iterations = 0;

        if (list.isEmpty() || list.getSize() <= 1) {
            return iterations;
        }

        boolean swapped;
        Node<T> lastSorted = null;
        do {
            swapped = false;
            Node<T> current = list.getHead();

            while (current != null && current.getNext() != lastSorted) {
                iterations++; // cuenta la iteracion de la comparacion

                if (comparator.compare(current.getData(),
                        current.getNextData()) > 0) {
                    // intercambio de datos
                    T temp = current.getData();
                    current.setData(current.getNextData());
                    current.setNextData(temp);
                    swapped = true;
                }

                // avanza al siguiente par
                current = current.getNext();
            }
            lastSorted = current;
        } while (swapped);

        return iterations;
    }
}
