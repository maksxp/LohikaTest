package Lohika;

import java.util.*;
import java.util.stream.Collectors;

public class TextParser {
    private static int entriesCounter = 0;

    public TextParser() {
    }

    public static List<String> getListOfAllWordsFromString(String textToParse) {
        List<String> wordsList = new ArrayList<>();
        String[] tokens = textToParse.split(",|\\.|\\s+");
        List<String> wordListTemp = new ArrayList<>(Arrays.asList(tokens));
        wordsList.addAll(wordListTemp);
        wordsList = wordsList.stream().filter(item -> (!item.isEmpty())).collect(Collectors.toList());
        return wordsList;
    }

    public static List<String> listOfWordsToLowerCase(List<String> wordsList) {
        List<String> wordsListLowerCase = new ArrayList<>();
        wordsList.stream().forEach(word -> wordsListLowerCase.add(word = word.toLowerCase()));
        return wordsListLowerCase;

    }

    public static Set<String> getSetOfUniqueWordsFromList(List<String> listWithDuplicates) {
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
    public static LinkedList<String> appendEntriesQuantityToEveryWord(Set<String> uniqueWords, List<String> allWords) {
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

    public static LinkedList<String> sortWordsInStringByEntriesAndAlphabetically(String textAsString) {
        List<String> listOfAllWordsFromString = TextParser.getListOfAllWordsFromString(textAsString);
        //changing words to lower case as asked in task
        List<String> listOfWordsToLowerCase = TextParser.listOfWordsToLowerCase(listOfAllWordsFromString);
        Set<String> setOfUniqueWords = TextParser.getSetOfUniqueWordsFromList(listOfWordsToLowerCase);
        LinkedList<String> linkedListWithEntries = TextParser.appendEntriesQuantityToEveryWord(setOfUniqueWords, listOfWordsToLowerCase);
        LinkedList<String> sortedList = TextParser.sortListByEntriesAndAlphabetically(linkedListWithEntries);
        return sortedList;
    }

    public static void printSortedWords(String textAsString, String wordsQuantity) {
        if (Integer.parseInt(wordsQuantity) < 1)
            System.out.println("number of words to show should be more than 0");
        LinkedList<String> listToPrint;
        listToPrint = TextParser.sortWordsInStringByEntriesAndAlphabetically(textAsString);
        listToPrint.stream().limit(Long.parseLong(wordsQuantity)).forEach(word -> System.out.println(word));

    }
}