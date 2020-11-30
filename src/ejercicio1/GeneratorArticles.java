package ejercicio1;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GeneratorArticles {

    private final List<Articulo> articuloList = new ArrayList<Articulo>();
    private final Random random = new Random();

    public GeneratorArticles(int numberOfArticles) {
        for (int i = 0; i < numberOfArticles; i++) {
            articuloList.add(new Articulo("Articulo"+i,random.nextInt(20)+90));
        }

    }

    public List<Articulo> getArticuloList() {
        return articuloList;
    }
}
