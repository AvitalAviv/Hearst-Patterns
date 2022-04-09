//ID: 316125855
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.TreeMap;

/**
 * DiscoverHypernym class
 * printing all the hypernyms that the hyponyms appear in them in a descending order.
 * @author  Avital Aviv
 * @version 1.0
 */
public class DiscoverHypernym {

    /**
     * main method.
     * get the file path that is needed to be read and the lemma string.
     * @param args two arguments - the path and a lemma.
     * @throws IOException ioexception.
     */
    public static void main(String[] args) throws IOException {
        String fromFile = args[0];
        String lemma = args[1];

        //getting the map from the fileToReader object.
        FilesToReader filesToReader = new FilesToReader(fromFile, null);
        filesToReader.readFromFile();

        //initialize the maps to get the map.
        TreeMap<String, LinkedHashMap<String, Integer>> map = filesToReader.getMap();
        LinkedHashMap<String, Integer> lemmaMap = new LinkedHashMap<>();

        //check if lemma appears
        for (String key : map.keySet()) {
            for (String miniKey : map.get(key).keySet()) {
                if (map.get(key).containsKey(lemma)) {
                    int countLemma = map.get(key).get(lemma);
                    lemmaMap.put(key, countLemma);
                }
            }
        }

        //sort the map that created with the lemma
        LinkedHashMap<String, Integer> lemmaNewMap = new LinkedHashMap<>();
        lemmaNewMap = filesToReader.getMapCreator().sortSmallMap(lemmaMap);

        //check if not empty
        if (lemmaMap.isEmpty()) {
            System.out.println("The lemma doesn't appear in the corpus.");
            return;
        }

        //printing the output
        for (String key : lemmaNewMap.keySet()) {
            System.out.println(key + ": " + "(" + lemmaNewMap.get(key) + ")");
        }
    }
}
