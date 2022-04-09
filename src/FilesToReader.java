//ID: 316125855
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.TreeMap;

/**
 * FilesToReader class
 * reading all the files from the folder, and go over line by line and check the patterns.
 * @author  Avital Aviv
 * @version 1.0
 */
public class FilesToReader {
    private String fromFile;
    private String toFile;
    private Writer writer;
    private MapCreator mapCreator;

    /**
     * FilesToReader method.
     * constructor method.
     * @param fromFile the file to read from.
     * @param toFile the file to write to.
     */
    public FilesToReader(String fromFile, String toFile) {
        this.fromFile = fromFile;
        this.toFile = toFile;
        this.mapCreator = new MapCreator();
        this.writer = new Writer(toFile, this.mapCreator);
    }

    /**
     * readFromFile method.
     * initialize all the objects like the bufferedReader to read line by line from the files in the folder.
     * @throws IOException ioexception.
     */
    public void readFromFile() throws IOException  {
        BufferedReader bufferedReader = null;

        //try and catch
        try {

            //get the folder
            File folder = new File(fromFile);
            File[] listOfFiles = folder.listFiles();
            String line;

            //go over all the files in the folder
            for (File file : listOfFiles) {
                bufferedReader = new BufferedReader(new java.io.FileReader(file));

                //check if there is a pattern that fit
                while ((line = bufferedReader.readLine()) != null) {
                    mapCreator.initSmallMap(line);
                }
            }
            TreeMap<String, LinkedHashMap<String, Integer>> map = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
            map = mapCreator.getBigMap();

            //initialize the writer
            writer.writingToFile(map);
        } catch (FileNotFoundException fex) {
            throw new FileNotFoundException();
        } catch (IOException ioex) {
            throw new IOException();
        } finally {
            if (bufferedReader != null) {
                bufferedReader.close();
            }
        }
    }

    /**
     * getMap method.
     * @return treemap.
     */
    public TreeMap<String, LinkedHashMap<String, Integer>> getMap() {
        return this.mapCreator.getBigMap();
    }

    /**
     * getMapCreator method.
     * @return MapCreator object.
     */
    public MapCreator getMapCreator() {
        return this.mapCreator;
    }
}
