import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class Task3Test {

    final private static String PACKAGE = "";

    @DisplayName("Check that Classes is present")
    @ParameterizedTest
    @MethodSource("listOfClasses")
    void isTypePresent(String cl) {
        try {
            assertNotNull(Class.forName(PACKAGE + cl));
            assertEquals(cl, Class.forName(PACKAGE + cl).getSimpleName());
        } catch (ClassNotFoundException e) {
            fail("There is no class " + cl);
        }
    }

    private static Stream<Arguments> listOfClasses() {
        return Stream.of(Arguments.of("MyUtils"));
    }

    @DisplayName("Check that class contain method")
    @Test
    void isMethodPresent() {
        String cl = "MyUtils";
        String m = "writeFile";
        Method[] methods = null;
        try {
            methods = Class.forName(PACKAGE + cl).getDeclaredMethods();
            boolean isMethod = false;
            for (Method method : methods) {
                if (method.getName().equals(m)) {
                    isMethod = true;
                    break;
                }
            }
            assertTrue(isMethod, "Class do not have method " + m);
        } catch (ClassNotFoundException e) {
            fail("There is no class " + cl);
        }
    }

    @DisplayName("Check that method writeFile write correct data to file")
    @ParameterizedTest
    @MethodSource("getDataFromFile")
    void isDataCorrect(String filename, String text, String expectedResult) {
        MyUtils.writeFile(filename, text);
        Path fileName = Path.of(filename);
        try {
            String s = Files.readString(fileName);
            assertEquals(s, expectedResult);
        } catch (IOException e) {
            fail("File have not created " + e);
        }
    }

    private static Stream<Arguments> getDataFromFile() {
        return Stream.of(Arguments.of("data1.txt", "Example of text for test case #1",
                        "100010111110001100001110110111100001101100110010101000001101111110011001000001110100110010" +
                                "11111000111010001000001100110110111111100100100000111010011001011110011111010001" +
                                "000001100011110000111100111100101010000001000110110001"),
                Arguments.of("data2.txt", "Example of text\nfor test case #2",
                        "100010111110001100001110110111100001101100110010101000001101111110011001" +
                        "00000111010011001011111000111010000010101100110110111111100100100000111010011001011110011111" +
                        "010001000001100011110000111100111100101010000001000110110010"),
                Arguments.of("data3.txt", "Example of\ntext for\ntest case #3",
                        "1000101111100011000011101101111000011011001100101010000011011111100110000" +
                        "1010111010011001011111000111010001000001100110110111111100100001010111010011001011110011111" +
                        "010001000001100011110000111100111100101010000001000110110011"));
    }
    
}
