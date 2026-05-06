package co.edu.udistrital.model.usecases;

import co.edu.udistrital.model.entities.Politician;
import co.edu.udistrital.model.generator.DataPoliticianGenerator;
import co.edu.udistrital.model.sort.*;
import co.edu.udistrital.model.structures.Node;
import co.edu.udistrital.model.structures.SimpleList;
import java.util.Comparator;

/**
 * Caso de uso que ejecuta la logica de ordenamiento de APOCO y lo retorna en un
 * objeto de transferencia de datos parecido a JSON
 *
 * @author Juan David Diaz Perez
 */
public class ApocoUseCase {

    /**
     * Procesa toda la lógica de negocio requerida por la petición.
     *
     * @param sizeParam Parametro importado del servlet, contiene el numero de
     * politicos
     * @param algorithmParam contiene si eligio todos o solo un algorirmo en
     * especifico
     *
     * @return el objeto con lo requerido en la vista (DTO)
     */
    public ApocoDTO processPetition(String sizeParam, String algorithmParam) {

        // 1. Validar y parsear el tamaño
        int size = 50;
        if (sizeParam != null && !sizeParam.isEmpty()) {
            size = Integer.parseInt(sizeParam);
        }

        // 2. Generar los datos
        SimpleList<Politician> listP = DataPoliticianGenerator.generatePoliticians(size);

        // 3. Preparar el comparador (Lambda descendente por dinero a robar)
        Comparator<Politician> comparator = (Politician p1, Politician p2)
                -> Double.compare(p2.getMoneyToSteal(), p1.getMoneyToSteal());

        // 4. Instanciar los algoritmos
        Sorter<Politician>[] algorithms = new Sorter[]{
            new InsertionSort<>(), new SelectionSort<>(), new BubbleSort<>(),
            new CocktailSort<>(), new CombSort<>(), new ShellSort<>(),
            new MergeSort<>(), new QuickSort<>()
        };

        int[] iterations = new int[8];
        SimpleList<Politician> finalSortedList = null;
        SimpleList<Politician> copy = copyList(listP);

        // 5. Ejecutar la lógica de ordenamiento según el parámetro
        if ("all".equals(algorithmParam)) {
            for (int i = 0; i < algorithms.length - 1; i++) {
                iterations[i] = algorithms[i].sort(copy, comparator);
                copy = copyList(listP);
            }

            // Iteracion final para evitar sobreescritura sin uso
            iterations[7] = algorithms[7].sort(copy, comparator);
            finalSortedList = copy;
        } else {
            int index = Integer.parseInt(algorithmParam);
            iterations[index] = algorithms[index].sort(copy, comparator);
            finalSortedList = copy;
        }

        // 6. Empaquetar y retornar resultados
        return new ApocoDTO(iterations, listP, finalSortedList, size);
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
    private SimpleList<Politician> copyList(SimpleList<Politician> original) {
        SimpleList<Politician> copy = new SimpleList<>();
        Node<Politician> actual = original.getHead();
        while (actual != null) {
            copy.add(actual.getData());
            actual = actual.getNext();
        }
        return copy;
    }
}
