//ID: 316125855
import java.io.IOException;

/**
 * CreateHypernymDatabase class
 * read all the files, arrange them into a map and write into the file.
 * @author  Avital Aviv
 * @version 1.0
 */
public class CreateHypernymDatabase {

    /**
     * constructor method.
     * creating an object CreateHypernymDatabase.
     */
    public CreateHypernymDatabase() {
    }

    /**
     * main method.
     * the main method that gets the args - the path to the files.
     * creating an object to read the file and go to the method from this object.
     * @param args not get any parameters.
     * @throws IOException ioexception.
     */
    public static void main(String[] args) throws IOException {
        String fromFile = args[0];
        String toFile = args[1];

        //creating a new object to read all the files.
        FilesToReader filesToReader = new FilesToReader(fromFile, toFile);
        filesToReader.readFromFile();
    }
}
