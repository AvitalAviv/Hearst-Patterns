//ID: 316125855
import java.util.Map;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.TreeMap;
import static java.util.stream.Collectors.toMap;

/**
 * MapCreator class
 * creating the map that contains as the key the hypernym and as the value a linked hash map, that has
 * the hyponyms and their number of appearances.
 * @author  Avital Aviv
 * @version 1.0
 */
public class MapCreator {
    private TreeMap<String, LinkedHashMap<String, Integer>> bigMap = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
    private TreeMap<String, ArrayList<String>> smallMap = new TreeMap<>();
    private ArrayList<PatternsReg> patternsRegs;
    private PatternsReg firstPatt;
    private PatternsReg secondPatt;
    private PatternsReg thirdPatt;
    private PatternsReg fourthPatt;
    private PatternsReg fifthPatt;
    private TreeMap<String, LinkedHashMap<String, Integer>> newMap;

    /**
     * MapCreator method.
     * constructor method.
     */
    public MapCreator() {
        this.patternsRegs = new ArrayList<>();
        this.firstPatt = new FirstPatt();
        this.secondPatt = new SecondPatt();
        this.thirdPatt = new ThirdPatt();
        this.fourthPatt = new FourthPatt();
        this.fifthPatt = new FifthPatt();
        this.patternsRegs.add(firstPatt);
        this.patternsRegs.add(secondPatt);
        this.patternsRegs.add(thirdPatt);
        this.patternsRegs.add(fourthPatt);
        this.patternsRegs.add(fifthPatt);
        this.newMap = new TreeMap<>();
    }

    /**
     * initSmallMap method.
     * check the patterns and update the map according to it.
     * @param line the line from the files.
     */
    public void initSmallMap(String line) {

        //go over all the patterns.
        for (PatternsReg p : this.patternsRegs) {
            if (p.checkPattern(line) != null) {

                //get the treemap from them
                this.smallMap = p.checkPattern(line);

                //update the bigmap
                if (!this.smallMap.isEmpty()) {
                    updateMap(this.smallMap);
                }
            }
        }
        this.smallMap.clear();
    }

    /**
     * sortSmallMap method.
     * sorting by value the linked hash map.
     * @param mini linked hash map.
     * @return sorted linked hash map.
     */
    public LinkedHashMap<String, Integer> sortSmallMap(LinkedHashMap<String, Integer> mini) {
        LinkedHashMap<String, Integer> newMini = new LinkedHashMap<>();
        newMini = mini .entrySet() .stream() .sorted(Collections.reverseOrder(Map.Entry.comparingByValue())) .
                collect(toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2, LinkedHashMap::new));
        return newMini;
    }

    /**
     * updateMap method.
     * check if new keys appear in the treemap and then update it accordingly.
     * @param map the treemap that created in the patterns.
     */
    public void updateMap(TreeMap<String, ArrayList<String>> map) {
        TreeMap<String, LinkedHashMap<String, Integer>> tempMap = new TreeMap<>();

        //go over all the new keys
        for (String key : map.keySet()) {
            LinkedHashMap<String, Integer> mini = new LinkedHashMap<>();

            //count their occurrences and put into a mini map
            for (String miniKey : map.get(key)) {
                int occurrence = Collections.frequency(map.get(key), miniKey);
                mini.put(miniKey, occurrence);
            }

            //build a temp treemap
            tempMap.put(key, mini);
        }

        //check if appear in the map and update it accordingly
        for (String key : tempMap.keySet()) {
            if (this.bigMap.containsKey(key)) {
                for (String miniKey : tempMap.get(key).keySet()) {
                    if (this.bigMap.get(key).containsKey(miniKey)) {
                        this.bigMap.get(key).replace(miniKey, this.bigMap.get(key).get(miniKey)
                        + tempMap.get(key).get(miniKey));
                    } else {
                        this.bigMap.get(key).put(miniKey, 1);
                    }
                }
            } else {
                this.bigMap.put(key, tempMap.get(key));
            }
        }
        tempMap.clear();
    }

    /**
     * getBigMap method.
     * getter method.
     * @return treemap.
     */
    public TreeMap<String, LinkedHashMap<String, Integer>> getBigMap() {
        return this.bigMap;
    }
}
