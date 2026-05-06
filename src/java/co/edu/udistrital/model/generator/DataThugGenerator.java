package co.edu.udistrital.model.generator;

import co.edu.udistrital.model.entities.Thug;
import co.edu.udistrital.model.enums.EnumLastNames;
import co.edu.udistrital.model.enums.MenEnumNames;
import co.edu.udistrital.model.enums.WomenEnumNames;
import co.edu.udistrital.model.structures.SimpleList;
import java.util.Random;

/**
 * Clase de utilidad para crear datos aleatorios de los hampones y no hacerlos a
 * mano
 *
 * @author Juan David Diaz Perez
 */
public class DataThugGenerator {

    /**
     * Genera una lista de hampones con nombres, edad y dinero al azar entre $1000 y
     * $50000000
     *
     * @param size la cantidad de hampones que queremos crear
     * @return una lista simple llena de hampones
     */
    public static SimpleList<Thug> generatePoliticians(double size) {
        SimpleList<Thug> listT = new SimpleList<>();
        Random ran = new Random();
        double minMoney = 1000;
        double maxMoney = 50000000;
        int randomUtility = 0;
        double ranMoney;

        //Importar los nombres del enum
        MenEnumNames[] menNames = MenEnumNames.values();
        WomenEnumNames[] womenNames = WomenEnumNames.values();
        EnumLastNames[] lastNames = EnumLastNames.values();

        for (int i = 0; i < size; i++) {

            randomUtility = ran.nextInt(2);

            String name = (randomUtility == 0) ? 
                    menNames[ran.nextInt(menNames.length)] + " " + 
                        lastNames[ran.nextInt(lastNames.length)] + " " + 
                            "(Hampon No." + (i + 1) + ")" : 
                                womenNames[ran.nextInt(womenNames.length)] + 
                                    " " + lastNames[ran.nextInt(lastNames.length)] 
                                            + " " +  "(Hampon No." + (i + 1) + ")";
            
            ranMoney = Math.round((minMoney + (maxMoney - minMoney) * 
                        ran.nextDouble()) * 100) / 100;
            randomUtility = ran.nextInt(52) + 18; // Maximo 70 para ser hampon
            
            listT.add(new Thug(name, randomUtility, ranMoney));
        }
        return listT;
    }
}
