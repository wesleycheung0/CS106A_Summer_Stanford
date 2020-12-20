/*
 * File: NameSurferDataBase.java
 * -----------------------------
 * This class keeps track of the complete database of names.
 * The constructor reads in the database from a file, and
 * the only public method makes it possible to look up a
 * name and get back the corresponding NameSurferEntry.
 * Names are matched independent of case, so that "Eric"
 * and "ERIC" are the same names.
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class NameSurferDatabase implements NameSurferConstants {

    /* Constructor: NameSurferDataBase(filename) */
    /**
     * Creates a new NameSurferDataBase and initializes it using the
     * data in the specified file.  The constructor throws an error
     * exception if the requested file does not exist or if an error
     * occurs as the file is being read.
     *
     * @throws FileNotFoundException
     */


    private ArrayList<NameSurferEntry> database = new ArrayList<NameSurferEntry>();

    public NameSurferDatabase(String filename) throws FileNotFoundException {
        // You fill this in //
        File file = new File(filename);
        Scanner input = new Scanner(file);

        while (input.hasNextLine()) {
            database.add(new NameSurferEntry(input.nextLine()));
        }
        input.close();
    }

    /* Method: findEntry(name) */

    /**
     * Returns the NameSurferEntry associated with this name, if one
     * exists.  If the name does not appear in the database, this
     * method returns null.
     */
    public NameSurferEntry findEntry(String name) {
//		 You need to turn this stub into a real implementation //
        for (int i = 0; i < database.size(); i++) {
            if (name.equals(database.get(i).getName())) {
                return database.get(i);
            }
        }
        return null;
    }
}

