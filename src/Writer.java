//ID: 316125855
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.TreeMap;

/**
 * Writer class
 * writing the map into the file path that is given in the args.
 * @author  Avital Aviv
 * @version 1.0
 */
public class Writer {
    private String toFile;
    private MapCreator mapCreator;
    private TreeMap<String, LinkedHashMap<String, Integer>> newMap;

    /**
     * Writer method.
     * constructor method.
     * @param toFile the file path.
     * @param mapCreator MapCreator object.
     */
    public Writer(String toFile, MapCreator mapCreator) {
        this.toFile = toFile;
        this.mapCreator = mapCreator;
        this.newMap = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
    }

    /**
     * writingToFile method.
     * @param map the map with all the data.
     * @throws IOException ioexception.
     */
    public void writingToFile(TreeMap<String, LinkedHashMap<String, Integer>> map) throws IOException {

        //if there is no file to write to than return back.
        if (this.toFile == null) {
            return;
        }
        BufferedWriter bufferedWriter = null;
        //int x = 0;

        //sort the map according to the values.
        for (String key : map.keySet()) {
            this.newMap.put(key, mapCreator.sortSmallMap(map.get(key)));
        }

        //trying to print to the file.
        try {
            File fileToWrite = new File(this.toFile);
            bufferedWriter = new BufferedWriter(new FileWriter(fileToWrite));
            if (!fileToWrite.exists()) {
                fileToWrite.createNewFile();
            }
            for (String key : this.newMap.keySet()) {
                LinkedHashMap<String, Integer> smallMap = this.newMap.get(key);
                //LinkedHashMap<String, Integer> smallMap = this.mapCreator.getSmallMap(key);
                if (smallMap.keySet().size() < 3) {
                    continue;
                }
                bufferedWriter.write(key + ": ");
                int counter = 1;
                //x++;
                for (String smallKey : smallMap.keySet()) {
                    if (counter == smallMap.keySet().size()) {
                        bufferedWriter.write(smallKey + " (" + smallMap.get(smallKey) + ")");
                        counter = 0;
                        continue;
                    }
                    bufferedWriter.write(smallKey + " (" + smallMap.get(smallKey) + "), ");
                    counter++;
                }
                bufferedWriter.write("\n");
            }
            //System.out.println(x);
            bufferedWriter.close();
        } catch (IOException e) {
            throw new IOException();
        } finally {
            bufferedWriter.close();
        }
    }
}
