import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class WordsInFile {
    static int entriesCounter = 0;

    public static List<String> getAllWordsFromFile(String source) {
        if (source == null) {
            throw new IllegalArgumentException("Null pointer to file");
        }
        List<String> wordsList = new ArrayList<>();
        String str;
        try (BufferedReader bufreader = new BufferedReader(new FileReader(source))) {
            for (; (str = bufreader.readLine()) != null; ) {
                String[] tokens = str.split(",|\\.|\\s+");
                List<String> wordListTemp = new ArrayList<>(Arrays.asList(tokens));
                wordsList.addAll(wordListTemp);
            }

        } catch (FileNotFoundException e) {
            System.out.println(e);
        } catch (IOException e) {
            System.out.println(e);
        }
        wordsList = wordsList.stream().filter(item -> (!item.isEmpty())).collect(Collectors.toList());
        List<String> wordsListLowerCase = new ArrayList<>();
        wordsList.stream().forEach(word -> wordsListLowerCase.add(word = word.toLowerCase()));
        return wordsListLowerCase;
    }

    public static Set<String> getUniqueWordsFromList(List<String> listWithDuplicates) {
        Iterator<String> iterator = listWithDuplicates.iterator();
        Set<String> uniqueWordsFromList = new TreeSet<>();
        while (iterator.hasNext()) {
            String temp = iterator.next();
            if (uniqueWordsFromList.add(temp)) {
                uniqueWordsFromList.add(temp);
            }
        }
        return uniqueWordsFromList;
    }

    //    append quantity of word's entries into List after symbol "="
//    for example: if word "object" appears twice we get word "object=2"
    public static LinkedList<String> appendEntriesQuantityToEveryWord(Set<String> uniqueWords, List<String> allWords) {
        LinkedList<String> listWithEntries = new LinkedList<>();
        Iterator<String> iterator = uniqueWords.iterator();
        while (iterator.hasNext()) {
            entriesCounter = 0;
            String temp = iterator.next();
            allWords.stream().forEach(word -> {
                if (word.equalsIgnoreCase(temp)) entriesCounter++;
            });
            listWithEntries.add(temp + "=" + entriesCounter);
        }
        return listWithEntries;
    }

    public static LinkedList<String> sortListByEntriesAndAlphabetically(LinkedList<String> listToSortWithEntriesQuantity) {
        Collections.sort(listToSortWithEntriesQuantity, new Comparator<String>() {
            @Override
            public int compare(String word1, String word2) {
                String[] token1 = word1.split("=");
                String[] token2 = word2.split("=");
                //compare by entries quantity
                if (Integer.parseInt(token1[1]) > Integer.parseInt(token2[1]))
                    return -1;
                else if (Integer.parseInt(token1[1]) < Integer.parseInt(token2[1]))
                    return 1;
                else
                    //compare alphabetically
                    return token1[0].compareTo(token2[0]);
            }
        });
        return listToSortWithEntriesQuantity;
    }
}