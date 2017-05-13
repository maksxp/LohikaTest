import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class OperationsWithFile {

    public static String readTxtFileToString(String pathToFile) {
        String content = "";
        File file = new File(pathToFile);
        FileReader reader = null;

        try {
            reader = new FileReader(file);
            char[] chars = new char[(int) file.length()];
            reader.read(chars);
            content = new String(chars);
            reader.close();
        } catch (IOException e) {
            System.out.println("file not found");
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println("can't close");
                }
            }
        }
        return content;
    }

}
