package co.edu.udistrital.model.usecases;

import co.edu.udistrital.model.entities.Thug;
import co.edu.udistrital.model.generator.DataThugGenerator;
import co.edu.udistrital.model.sort.*;
import co.edu.udistrital.model.structures.Node;
import co.edu.udistrital.model.structures.SimpleList;
import java.util.Comparator;

/**
 * Caso de uso que ejecuta la logica de ordenamiento de los hampones y retorna
 * lo necesario para mostrar con la vista en el DTO
 *
 * @author Juan David Diaz Perez
 */
public class ThugUserCase {

    /**
     * Metodo que procesa lo requerido de java en el formulario, crea la lista y organiza la lista
     * 
     * @param requestedRows La cantidad de filas de la matriz
     * @param requestedColumns La cantidad de columnas de la matriz
     * @param selectedSort El o los algoritmos de sorteo seleccionados para la peticion
     * 
     * @return El DTO de comunicacion con el servlet (Objeto de transferencia de datos)
     */
    public ThugDTO processRequest(String requestedRows, String requestedColumns, String selectedSort) {

        // Obtener y verificar el numero de filas y columnas
        int rows = 5;
        int colums = 5;

        if (requestedRows != null && !requestedRows.isEmpty()) {
            rows = Integer.parseInt(requestedRows);
        }
        if (requestedColumns != null && !requestedColumns.isEmpty()) {
            colums = Integer.parseInt(requestedColumns);
        }

        int size = rows * colums; // Tamano de la matriz

        // Generar datos de los hampones
        SimpleList<Thug> listT = DataThugGenerator.generatePoliticians(size);

        // Funcion lamda de criterios de comparacion, primero por edad, y despues por dinero robado si son iguales, sobreescribe un metodo superior
        Comparator<Thug> comparator = (Thug t1, Thug t2) -> {
            int answer = Integer.compare(t1.getAge(), t2.getAge());

            if (answer != 0) {
                return answer;
            }

            return Double.compare(t2.getStolenMoney(), t1.getStolenMoney());
        };
        
        //Iniciar algoritmos
        Sorter<Thug>[] algorithms = new Sorter[]{
            new InsertionSort<>(), new SelectionSort<>(), new BubbleSort<>(),
            new CocktailSort<>(), new CombSort<>(), new ShellSort<>(),
            new MergeSort<>(), new QuickSort<>()
        };
        
        int[] iterations = new int[8];
        SimpleList<Thug> finalSortedList = null;
        SimpleList<Thug> copy = copyList(listT);
        
        // Ejecurtar segun seleccion
        if ("all".equals(selectedSort)) {
            for (int i = 0; i < algorithms.length - 1; i++) {
                iterations[i] = algorithms[i].sort(copy, comparator);
                copy = copyList(listT);
            }

            // Iteracion final para evitar sobreescritura sin uso
            iterations[7] = algorithms[7].sort(copy, comparator);
            finalSortedList = copy;
        } else {
            int index = Integer.parseInt(selectedSort);
            iterations[index] = algorithms[index].sort(copy, comparator);
            finalSortedList = copy;
        }
        
        return new ThugDTO(iterations, listT, finalSortedList, colums, rows);
    }
    
    /**
     * Metodo de apoyo para clonar la lista original nodo por nodo. Esto es
     * necesario para que cada algoritmo reciba el mismo nivel de desorden, o
     * sea mismo arreglo y las comparativas de iteraciones sean justas.
     *
     * @param original la lista desordenada que acaba de salir del generador
     * @return una nueva lista independiente pero con los mismos datos en el
     * mismo orden
     */
    private SimpleList<Thug> copyList(SimpleList<Thug> original) {
        SimpleList<Thug> copy = new SimpleList<>();
        Node<Thug> actual = original.getHead();
        while (actual != null) {
            copy.add(actual.getData());
            actual = actual.getNext();
        }
        return copy;
    }
}
