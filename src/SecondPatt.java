//ID: 316125855
import java.util.ArrayList;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * SecondPatt class extends the PatternsReg.
 * the second pattern of regex.
 * @author  Avital Aviv
 * @version 1.0
 */
public class SecondPatt extends PatternsReg {
    private String regex;
    private int indexHypernym = 0;

    /**
     * SecondPatt method.
     * constructor method.
     */
    public SecondPatt() {
        super();
        this.regex = "such( )<np>([^<]+)<\\/np>( )(?:, )?as( )<np>(?:[^<]+)<\\/np>(?: (?:, )"
                + "<np>(?:[^<]+)<\\/np>)*(?: (?:, )?(?:or|and) <np>(?:[^<]+)<\\/np>)?";
    }

    /**
     * checkPattern method.
     * get a line from a file and check according to the given regex if fits it.
     * if it is, it returns a treemap that arrange all the strings.
     * @param text a single line from the file.
     * @return treemap
     */
    public TreeMap<String, ArrayList<String>> checkPattern(String text) {
        Pattern pattern = Pattern.compile(this.regex);
        Matcher matcher = pattern.matcher(text);
        ArrayList<String> str = new ArrayList<>();
        boolean flag = false;
        super.getMap().clear();

        //check matches
        while (matcher.find()) {
            str = splitNP(text.substring(matcher.start(), matcher.end()));
            String hypernym = str.get(0);
            ArrayList<String> newStr = new ArrayList<>(str);
            newStr.remove(0);
            super.getMap().put(hypernym, newStr);
            flag = true;
        }
        return super.getMap();
    }
}
