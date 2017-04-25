import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class WordCount {
    public static void main(String[] args) {
        if(Integer.parseInt(args[1])<1)
            System.out.println("number of words to show should be more than 0");
        List<String> allWordsInFile = WordsInFile.getAllWordsFromFile(args[0]);
        Set<String> uniqueWordsInFile = WordsInFile.getUniqueWordsFromList(allWordsInFile);
        LinkedList <String> listSortedByEntriesAndAlphabetically =
                WordsInFile.sortListByEntriesAndAlphabetically(
                WordsInFile.appendEntriesQuantityToEveryWord(uniqueWordsInFile, allWordsInFile));
        listSortedByEntriesAndAlphabetically.stream().limit(Long.parseLong(args[1])).forEach(word -> System.out.println(word));
    }
}