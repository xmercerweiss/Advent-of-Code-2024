
package utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


public class FileIO {

    private static final String INV_FILE_ERR_MSG = "Attempted to open invalid file %s";

    public static ArrayList<String> getLinesOf(String path) {
        ArrayList<String> output = new ArrayList<>();
        try {
            Scanner fileReader = getFileReader(path);
            while (fileReader.hasNextLine())
                output.add(fileReader.nextLine());
        }
        catch (FileNotFoundException e) {
            e.printStackTrace(System.out);
            System.exit(1);
        }
        return output;
    }

    private static Scanner getFileReader(String path)
    throws FileNotFoundException {
        File file = new File(path);
        if (!file.exists() || file.isDirectory()) 
            throw new FileNotFoundException(INV_FILE_ERR_MSG.formatted(path));
        return new Scanner(file);
    }
}
