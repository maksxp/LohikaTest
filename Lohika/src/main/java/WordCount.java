
public class WordCount {

    public static void main(String[] args) {
        Text text = new Text();
        String contentOfFile = "";
        Long numberOfPairsToPrint = 0L;

        try {
            contentOfFile = OperationsWithFile.readTxtFileToString(args[0]);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("enter path to file as first argument of program");
        }

        try {
            numberOfPairsToPrint = Long.parseLong(args[1]);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("enter number of pairs to print as second argument of program");
        }

        text.setContent(contentOfFile);
        text.getMapOfLowCaseWordsSortedAlphabeticallyWithEntriesAsValue().
                entrySet().
                stream().
                limit(numberOfPairsToPrint).
                forEach(entry -> System.out.println(entry.getKey() + " = " + entry.getValue()));

    }
}
