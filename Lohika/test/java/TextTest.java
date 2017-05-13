import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;

import static junit.framework.TestCase.assertEquals;

public class TextTest {
    LinkedList<String> listForTest = new LinkedList<>(Arrays.asList("aaaa=3", "aaaa=2", "aa=5", "bbbb=4", "bbbb=3","aaab=3"));
    @Test
    public void testSortListByEntriesAndAlphabetically() throws Exception {
        Text.sortListByEntriesAndAlphabetically(listForTest);
        assertEquals("aa=5", listForTest.get(0));
        assertEquals("bbbb=4", listForTest.get(1));
        assertEquals("aaab=3", listForTest.get(3));
    }
}