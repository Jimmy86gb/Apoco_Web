package co.edu.udistrital.model.sort;

import co.edu.udistrital.model.structures.*;
import java.util.Comparator;

/**
 * algoritmo de ordenamiento por insercion.
 * utilizar reconexion de punteros para reducir iteraciones y optimizar el rendimiento.
 *
 * @author Jimmy86gb
 * @param <T> el tipo de dato a ordenar
 */
public class InsertionSort<T> implements Sorter<T> {

    /**
     * ordenar la lista insertando cada nodo entero en su posicion correcta.
     *
     * @param list la lista a ordenar
     * @param comparator las reglas para saber quien es mayor
     * @return el numero de iteraciones realizadas
     */
    @Override
    public int sort(SimpleList<T> list, Comparator<T> comparator) {
        int iterations = 0;
        
        if (list.isEmpty() || list.getSize() <= 1) {
            return iterations;
        }

        // definir la cabeza de la nueva sub-lista ordenada
        Node<T> sortedHead = null;
        
        // recorrer la lista original desordenada desde el inicio
        Node<T> current = list.getHead();

        while (current != null) {
            // guardar el siguiente nodo original antes de romper sus flechas
            Node<T> nextNode = current.getNext();

            // evaluar si la lista ordenada esta vacia o si el actual es el menor
            iterations++; 
            if (sortedHead == null || comparator.compare(current.getData(), sortedHead.getData()) <= 0) {
                // ejecutar insercion al principio
                current.setNext(sortedHead);
                sortedHead = current;
            } else {
                // buscar el espacio correcto en el medio o al final
                Node<T> search = sortedHead;

                // recorrer la sub-lista ordenada buscando el lugar exacto
                while (search.getNext() != null) {
                    iterations++; 
                    // romper el ciclo si el siguiente es mayor que el nodo actual
                    if (comparator.compare(search.getNext().getData(), current.getData()) > 0) {
                        break; 
                    }
                    search = search.getNext();
                }

                // conectar las flechas correspondientes
                current.setNext(search.getNext());
                search.setNext(current);
            }

            // avanzar al siguiente nodo de la lista desordenada
            current = nextNode;
        }

        // actualizar la estructura principal con la nueva cabeza ordenada
        list.setHead(sortedHead);

        // reconstruir la cola para evitar errores al agregar datos en el futuro
        Node<T> tempTail = sortedHead;
        while (tempTail != null && tempTail.getNext() != null) {
            tempTail = tempTail.getNext();
        }
        list.setTail(tempTail);

        return iterations;
    }
}