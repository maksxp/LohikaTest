package Lohika;

public class WordCount {
    public static void main(String[] args) {

        try {
            String contentOfFile = OperationsWithFile.readTxtFileToString(args[0]);
            TextParser.printSortedWords(contentOfFile, args[1]);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("enter path to file and number of pairs to print in format \"pathToFile numberOfPairs\"");
        }
    }
}
