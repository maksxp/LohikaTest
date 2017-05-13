import java.util.*;
import java.util.stream.Collectors;

public class Text {
    private static int entriesCounter = 0;
    private String content;

    public Text() {
    }

    public Text(String content) {
        this.content = content;
    }
    
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<String> getListOfAllWords() {
        List<String> wordsList = new ArrayList<>();
        String[] tokens = content.split(",|\\.|\\s+");
        List<String> wordListTemp = new ArrayList<>(Arrays.asList(tokens));
        wordsList.addAll(wordListTemp);
        wordsList = wordsList.stream().filter(item -> (!item.isEmpty())).collect(Collectors.toList());
        return wordsList;
    }

    public List<String> getListOfWordsToLowerCase() {
        List<String> wordsList = getListOfAllWords();
        List<String> wordsListLowerCase = new ArrayList<>();
        wordsList.stream().forEach(word -> wordsListLowerCase.add(word = word.toLowerCase()));
        return wordsListLowerCase;

    }

    public Set<String> getSetOfUniqueLowCaseWords() {
        List<String> listWithDuplicates = getListOfWordsToLowerCase();
        Iterator<String> iterator = listWithDuplicates.iterator();
        Set<String> setOfUniqueLowCaseWords = new TreeSet<>();
        while (iterator.hasNext()) {
            String temp = iterator.next();
            if (setOfUniqueLowCaseWords.add(temp)) {
                setOfUniqueLowCaseWords.add(temp);
            }
        }
        return setOfUniqueLowCaseWords;
    }

    public Set<String> getSetOfUniqueWords() {
        List<String> listWithDuplicates = getListOfAllWords();
        Iterator<String> iterator = listWithDuplicates.iterator();
        Set<String> setOfUniqueWords = new TreeSet<>();
        while (iterator.hasNext()) {
            String temp = iterator.next();
            if (setOfUniqueWords.add(temp)) {
                setOfUniqueWords.add(temp);
            }
        }
        return setOfUniqueWords;
    }

    //    append quantity of word's entries into List after symbol "="
//    for example: if word "object" appears twice we get word "object=2"
    private static LinkedList<String> appendEntriesQuantityToEveryWord(Set<String> uniqueWords, List<String> allWords) {
        LinkedList<String> linkedListWithEntries = new LinkedList<>();
        Iterator<String> iterator = uniqueWords.iterator();
        while (iterator.hasNext()) {
            entriesCounter = 0;
            String temp = iterator.next();
            allWords.stream().forEach(word -> {
                if (word.equalsIgnoreCase(temp)) entriesCounter++;
            });
            linkedListWithEntries.add(temp + "=" + entriesCounter);
        }
        return linkedListWithEntries;
    }

    // TODO: Change to private after tests.
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

    private LinkedList<String> sortLowCaseWordsInStringByEntriesAndAlphabetically() {
        List<String> listOfWordsToLowerCase = getListOfWordsToLowerCase();
        Set<String> setOfUniqueWords = getSetOfUniqueLowCaseWords();
        LinkedList<String> linkedListWithEntries = Text.appendEntriesQuantityToEveryWord(setOfUniqueWords, listOfWordsToLowerCase);
        LinkedList<String> sortedList = Text.sortListByEntriesAndAlphabetically(linkedListWithEntries);
        return sortedList;
    }

    public Map<String, Integer> getMapOfLowCaseWordsSortedAlphabeticallyWithEntriesAsValue() {
        LinkedList<String> sortedListByEntriesAndAlphabetically = sortLowCaseWordsInStringByEntriesAndAlphabetically();
        Map<String, Integer> mapOfWordsWithEntriesAsValue = new LinkedHashMap<>();
        sortedListByEntriesAndAlphabetically.stream().forEach(element -> mapOfWordsWithEntriesAsValue.put(element.split("=")[0], Integer.parseInt(element.split("=")[1])));
        return mapOfWordsWithEntriesAsValue;
    }
}

