package ejercicio1;


import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Ejercicio1 {

    /*1) Escribir un método que reciba por parámetro el nombre de un archivo que almacena una
serie de referencias y precios de artículos y actualice los precios de forma que los superiores a
100 euros se decrementen en un 50% y los inferiores se incrementen en un 50%. El método
capturará y tratará todas las excepciones que puedan producirse.*/

    final String dirPath = "src\\ejercicio1\\fichero\\";
    final String filePath = "src\\ejercicio1\\fichero\\articulo.dat";
    File directory = new File(dirPath);
    File articleFile = new File(filePath);

    public Ejercicio1() throws IOException {
        createFileIfNotExist(directory, "dir");
        createFileIfNotExist(articleFile, "file");
        writeArticleFile(filePath);
        readArticleFile(filePath);
        updatePriceArticle(filePath);
        readArticleFile(filePath);
    }

    private void writeArticleFile(String path) throws IOException {
        File file = new File(path);
        RandomAccessFile writeArticle = new RandomAccessFile(file, "rw");
        GeneratorArticles articles = new GeneratorArticles(10);

        for (int i = 0; i < articles.getArticuloList().size(); i++) {
            writeArticle.writeUTF(articles.getArticuloList().get(i).getName());
            writeArticle.writeDouble(articles.getArticuloList().get(i).getPrice());

        }
        writeArticle.close();
    }//15 bytes

    private void readArticleFile(String path) throws FileNotFoundException {

        RandomAccessFile readFileAfter = new RandomAccessFile(new File(path), "r");
        List<String> name2= new ArrayList<>();
        List<Double> price2= new ArrayList<>();
        int position =0;
        while (true) {
            try {
                readFileAfter.seek(position);
                name2.add(readFileAfter.readUTF());//
                price2.add(readFileAfter.readDouble());//19-8=11

                position += 19;

                if (readFileAfter.length() == position) {
                    break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            readFileAfter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < name2.size(); i++) {
            System.out.println("El nombre es "+name2.get(i));
            System.out.println("El precio es "+price2.get(i));
            System.out.println();
        }

    }


    private void updatePriceArticle(String path) {
        RandomAccessFile readFile = null, overwriteReadFile = null, readFileAfter = null;
        List<Double> price = new ArrayList<>();
        List<String> name = new ArrayList<>();
        int position = 0, count = 0;

        //Creacion de los randomAccessFile
        try {
            readFile = new RandomAccessFile(new File(path), "r");
            overwriteReadFile = new RandomAccessFile(new File(path), "rw");
            readFileAfter = new RandomAccessFile(new File(path), "r");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        //Lectura de los articulos
        while (true) {
            try {
                readFile.seek(position);
                name.add(readFile.readUTF());//
                price.add(readFile.readDouble());//19-8=11

                position += 19;

                if (readFile.length() == position) {
                    break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            readFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //Incremento o decremento del precio
        for (int i = 0; i < price.size(); i++) {
            if (price.get(i) > 100) {
                price.set(i, price.get(i) - price.get(i) * 0.5);
            } else {
                price.set(i, price.get(i) + price.get(i) * 0.5);
            }
        }
        //Overwrite del archivo
        position = 11;

        while (true) {
            try {
                overwriteReadFile.seek(position);
                overwriteReadFile.writeDouble(price.get(count++));

                if (overwriteReadFile.length() == position + 8) {
                    break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            position += 19;

        }
        try {
            overwriteReadFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    private void createFileIfNotExist(File file, String dirOrFile) throws IOException {
        if (!file.exists() && dirOrFile.equalsIgnoreCase("file")) {
            file.createNewFile();
        } else if (!file.exists() && dirOrFile.equalsIgnoreCase("dir")) {
            file.mkdir();
        }
    }

    public static void main(String[] args) throws IOException {
        new Ejercicio1();
    }
}
