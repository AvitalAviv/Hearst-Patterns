//ID: 316125855
import java.util.ArrayList;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * PatternsReg class
 * arrange all the regex patterns.
 * @author  Avital Aviv
 * @version 1.0
 */
public class PatternsReg {
    private TreeMap<String, ArrayList<String>> map;

    /**
     * PatternsReg method.
     * constructor method.
     */
    public PatternsReg() {
        this.map = new TreeMap<>();
    }

    /**
     * splitNP method.
     * spliting the strings from the files to be without the "borders" in the text.
     * @param text line from the file.
     * @return arraylist
     */
    public ArrayList<String> splitNP(String text) {
        ArrayList<String> str = new ArrayList<>();

        //pattern to put aside the np border
        Pattern pattern = Pattern.compile("<np>([^<]+)<\\/np>", Pattern.DOTALL);
        Matcher matcher = pattern.matcher(text);

        //find match and creating new arraylist.
        while (matcher.find()) {
            String s = matcher.group();
            String sNew = s.replaceAll("(<np>|<\\/np>)", "");
            str.add(sNew);
        }
        return str;
    }

    /**
     * checkPattern method.
     * check the pattern from the five patterns.
     * @param text line from the file.
     * @return treemap.
     */
    public TreeMap<String, ArrayList<String>> checkPattern(String text) {
        return null;
    }

    /**
     * getMap method.
     * getter method.
     * @return treemap.
     */
    public TreeMap<String, ArrayList<String>> getMap() {
        return this.map;
    }
}
