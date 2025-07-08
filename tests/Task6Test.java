import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class Task6Test {

    final private static String PACKAGE = "";

    private static Stream<Arguments> listOfClasses() {
        return Stream.of(Arguments.of("MyUtils"));
    }

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

    @DisplayName("Check that class contain method")
    @Test
    void isMethodPresent() {
        String cl = "MyUtils";
        String m = "phoneNumbers";
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

    @DisplayName("Check if list have one number")
    @Test
    void checkOneNumber() {
        final List<Stream<String>> origin = List.of(Stream.of("0939876543"));
        final Map<String, List<String>> expected = Map.of("093", List.of("9876543"));
        Map<String, List<String>> actual = null;
        try {
            actual = new MyUtils().phoneNumbers(origin).entrySet().stream()
                    .collect(Collectors.<Map.Entry<String, Stream<String>>, String, List<String>>toMap(
                            entry -> entry.getKey(), entry -> entry.getValue().collect(Collectors.toList())));
            assertTrue(actual != null && expected.equals(actual));
        } catch (Exception e) {
            fail("Do not work for a list of one number " + e);
        }
    }

    @DisplayName("Check if list have duplicate numbers")
    @Test
    void checkDuplicateNumbers() {
        final List<Stream<String>> origin = List.of(Stream.of(new String[] { "0939876543", "0939876543", "0939876543" }));
        final Map<String, List<String>> expected = Map.of("093", Arrays.asList("9876543"));
        Map<String, List<String>> actual = null;
        try {
            actual = new MyUtils().phoneNumbers(origin).entrySet().stream()
                    .collect(Collectors.<Map.Entry<String, Stream<String>>, String, List<String>>toMap(
                            entry -> entry.getKey(), entry -> entry.getValue().collect(Collectors.toList())));
            assertTrue(actual != null && expected.equals(actual));
        }
        catch (Exception e) {
            fail("Do not work for a list of duplicate " + e);
        }
    }

    @DisplayName("Check if list have brackets")
    @Test
    void checkBrackets() {
        final List<Stream<String>> origin = List.of(Stream.of("(093)9876543"));
        final Map<String, List<String>> expected = Map.of("093", Arrays.asList("9876543"));
        Map<String, List<String>> actual = null;
        try {
            actual = new MyUtils().phoneNumbers(origin).entrySet().stream()
                    .collect(Collectors.<Map.Entry<String, Stream<String>>, String, List<String>>toMap(
                            entry -> entry.getKey(), entry -> entry.getValue().collect(Collectors.toList())));
            assertTrue(actual != null && expected.equals(actual));
        }
        catch (Exception e) {
            fail("Do not work for a list of brackets " + e);
        }
    }

    @DisplayName("Check if list have dash")
    @Test
    void checkDash() {
        final List<Stream<String>> origin = List.of(Stream.of("093-987-65-43"));
        final Map<String, List<String>> expected = Map.of("093", Arrays.asList("9876543"));
        Map<String, List<String>> actual = null;
        try {
            actual = new MyUtils().phoneNumbers(origin).entrySet().stream()
                    .collect(Collectors.<Map.Entry<String, Stream<String>>, String, List<String>>toMap(
                            entry -> entry.getKey(), entry -> entry.getValue().collect(Collectors.toList())));
            assertTrue(actual != null && expected.equals(actual));
        }
        catch (Exception e) {
            fail("Do not work for a list of dash " + e);
        }
    }

    @DisplayName("Check if list have extra spaces")
    @Test
    void checkExtraSpaces() {
        final List<Stream<String>> origin = List.of(Stream.of("093 987 65 43"));
        final Map<String, List<String>> expected = Map.of("093", Arrays.asList("9876543"));
        Map<String, List<String>> actual = null;
        try {
            actual = new MyUtils().phoneNumbers(origin).entrySet().stream()
                    .collect(Collectors.<Map.Entry<String, Stream<String>>, String, List<String>>toMap(
                            entry -> entry.getKey(), entry -> entry.getValue().collect(Collectors.toList())));
            assertTrue(actual != null && expected.equals(actual));
        }
        catch (Exception e) {
            fail("Do not work for a list of extra spaces " + e);
        }
    }

    @DisplayName("Check if list have unique numbers")
    @Test
    void checkUniqueNumbers() {
        final List<Stream<String>> origin = List.of(Stream.of(new String[] { "0939876543", "0501234567" }),
                Stream.of(new String[] { "0672143657", "224-19-28" }));
        final Map<String, List<String>> expected = Map.of("050", Arrays.asList("1234567"), "067",
                Arrays.asList("2143657"), "093", Arrays.asList("9876543"), "loc", Arrays.asList("2241928"));
        Map<String, List<String>> actual = null;
        try {
            actual = new MyUtils().phoneNumbers(origin).entrySet().stream()
                    .collect(Collectors.<Map.Entry<String, Stream<String>>, String, List<String>>toMap(
                            entry -> entry.getKey(), entry -> entry.getValue().collect(Collectors.toList())));
            assertTrue(actual != null && expected.equals(actual));
        }
        catch (Exception e) {
            fail("Do not work for a list of unique numbers " + e);
        }
    }
    @DisplayName("Check if list have local code")
    @Test
    void checkLocalCode() {
        final List<Stream<String>> origin = List.of(Stream.of("224-19-28"));
        final Map<String, List<String>> expected = Map.of("loc", Arrays.asList("2241928"));
        Map<String, List<String>> actual = null;
        try {
            actual = new MyUtils().phoneNumbers(origin).entrySet().stream()
                    .collect(Collectors.<Map.Entry<String, Stream<String>>, String, List<String>>toMap(
                            entry -> entry.getKey(), entry -> entry.getValue().collect(Collectors.toList())));
            assertTrue(actual != null && expected.equals(actual));
        }
        catch (Exception e) {
            fail("Do not work for a list of local code " + e);
        }
    }

    @DisplayName("Check if list have error code")
    @Test
    void checkErrorCode() {
        final List<Stream<String>> origin = List.of(Stream.of("12-345"));
        final Map<String, List<String>> expected = Map.of("err", Arrays.asList("12345"));
        Map<String, List<String>> actual = null;
        try {
            actual = new MyUtils().phoneNumbers(origin).entrySet().stream()
                    .collect(Collectors.<Map.Entry<String, Stream<String>>, String, List<String>>toMap(
                            entry -> entry.getKey(), entry -> entry.getValue().collect(Collectors.toList())));
            assertTrue(actual != null && expected.equals(actual));
        }
        catch (Exception e) {
            fail("Do not work for a list of error code " + e);
        }
    }

    @DisplayName("Check if list have one code")
    @Test
    void checkOneCode() {
        final List<Stream<String>> origin = List.of(Stream.of(new String[] { "093 987 65 43", "0939182736", "(093)-11-22-334" }));
        final Map<String, List<String>> expected = Map.of("093", Arrays.asList("1122334", "9182736", "9876543"));
        Map<String, List<String>> actual = null;
        try {
            actual = new MyUtils().phoneNumbers(origin).entrySet().stream()
                    .collect(Collectors.<Map.Entry<String, Stream<String>>, String, List<String>>toMap(
                            entry -> entry.getKey(), entry -> entry.getValue().collect(Collectors.toList())));
            assertTrue(actual != null && expected.equals(actual));
        }
        catch (Exception e) {
            fail("Do not work for a list of one code " + e);
        }
    }

    @DisplayName("Check if list have two code")
    @Test
    void checkTwoCode() {
        final List<Stream<String>> origin = List.of(Stream.of(new String[] { "093 987 65 43", "0939182736", "(093)-11-22-334" }),
                Stream.of(new String[] { "050-2345678", "(050)1234567" }));
        final Map<String, List<String>> expected = Map.of("050", Arrays.asList("1234567", "2345678"),
                "093", Arrays.asList("1122334", "9182736", "9876543"));
        Map<String, List<String>> actual = null;
        try {
            actual = new MyUtils().phoneNumbers(origin).entrySet().stream()
                    .collect(Collectors.<Map.Entry<String, Stream<String>>, String, List<String>>toMap(
                            entry -> entry.getKey(), entry -> entry.getValue().collect(Collectors.toList())));
            assertTrue(actual != null && expected.equals(actual));
        }
        catch (Exception e) {
            fail("Do not work for a list of two code " + e);
        }
    }

    @DisplayName("Check if list have three code")
    @Test
    void checkThreeCode() {
        final List<Stream<String>> origin = List.of(Stream.of(new String[] { "093 987 65 43", "(050)1234567" }),
                Stream.of(new String[] { "067-21-436-57", "050-2345678", "0939182736" }), Stream.of("(093)-11-22-334"));
        final Map<String, List<String>> expected = Map.of("050", Arrays.asList("1234567", "2345678"),
                "067", Arrays.asList("2143657"), "093", Arrays.asList("1122334", "9182736", "9876543"));
        Map<String, List<String>> actual = null;
        try {
            actual = new MyUtils().phoneNumbers(origin).entrySet().stream()
                    .collect(Collectors.<Map.Entry<String, Stream<String>>, String, List<String>>toMap(
                            entry -> entry.getKey(), entry -> entry.getValue().collect(Collectors.toList())));
            assertTrue(actual != null && expected.equals(actual));
        }
        catch (Exception e) {
            fail("Do not work for a list of two code " + e);
        }
    }

    @DisplayName("Check if list have one streams")
    @Test
    void checkOneStreams() {
        final List<Stream<String>> origin = List.of(Stream.of(new String[] { "093 987 65 43", "(050)1234567", "12-345" }));
        final Map<String, List<String>> expected = Map.of("050", Arrays.asList("1234567"),
                "093", Arrays.asList("9876543"), "err", Arrays.asList("12345"));
        Map<String, List<String>> actual = null;
        try {
            actual = new MyUtils().phoneNumbers(origin).entrySet().stream()
                    .collect(Collectors.<Map.Entry<String, Stream<String>>, String, List<String>>toMap(
                            entry -> entry.getKey(), entry -> entry.getValue().collect(Collectors.toList())));
            assertTrue(actual != null && expected.equals(actual));
        }
        catch (Exception e) {
            fail("Do not work for a list of one streams " + e);
        }
    }

    @DisplayName("Check if list have two streams")
    @Test
    void checkTwoStreams() {
        final List<Stream<String>> origin = List.of(Stream.of(new String[] { "093 987 65 43", "(050)1234567", "12-345" }),
                Stream.of(new String[] { "067-21-436-57", "050-2345678", "0939182736", "224-19-28" }));
        final Map<String, List<String>> expected = Map.of("050", Arrays.asList("1234567", "2345678"),
                "067", Arrays.asList("2143657"), "093", Arrays.asList("9182736", "9876543"),
                "err", Arrays.asList("12345"), "loc", Arrays.asList("2241928"));
        Map<String, List<String>> actual = null;
        try {
            actual = new MyUtils().phoneNumbers(origin).entrySet().stream()
                    .collect(Collectors.<Map.Entry<String, Stream<String>>, String, List<String>>toMap(
                            entry -> entry.getKey(), entry -> entry.getValue().collect(Collectors.toList())));
            assertTrue(actual != null && expected.equals(actual));
        }
        catch (Exception e) {
            fail("Do not work for a list of two streams " + e);
        }
    }

    @DisplayName("Check if list have three streams")
    @Test
    void checkThreeStreams() {
        final List<Stream<String>> origin = List.of(Stream.of(new String[] { "093 987 65 43", "(050)1234567", "12-345" }),
                Stream.of(new String[] { "067-21-436-57", "050-2345678", "0939182736", "224-19-28" }),
                Stream.of(new String[] { "(093)-11-22-334", "044 435-62-18", "721-73-45" }));
        final Map<String, List<String>> expected = Map.of("044", Arrays.asList("4356218"),
                "050", Arrays.asList("1234567", "2345678"), "067", Arrays.asList("2143657"),
                "093", Arrays.asList("1122334", "9182736", "9876543"), "err", Arrays.asList("12345"),
                "loc", Arrays.asList("2241928", "7217345"));
        Map<String, List<String>> actual = null;
        try {
            actual = new MyUtils().phoneNumbers(origin).entrySet().stream()
                    .collect(Collectors.<Map.Entry<String, Stream<String>>, String, List<String>>toMap(
                            entry -> entry.getKey(), entry -> entry.getValue().collect(Collectors.toList())));
            assertTrue(actual != null && expected.equals(actual));
        }
        catch (Exception e) {
            fail("Do not work for a list of three streams " + e);
        }
    }

    @DisplayName("Check if list have sorted streams")
    @Test
    void checkSortedStreams() {
        final List<Stream<String>> origin = List.of(Stream.of(new String[] { "093-1122334", "(093)9182736", "093 9876543" }));
        final Map<String, List<String>> expected = Map.of("093", Arrays.asList("1122334", "9182736", "9876543"));
        Map<String, List<String>> actual = null;
        try {
            actual = new MyUtils().phoneNumbers(origin).entrySet().stream()
                    .collect(Collectors.<Map.Entry<String, Stream<String>>, String, List<String>>toMap(
                            entry -> entry.getKey(), entry -> entry.getValue().collect(Collectors.toList())));
            assertTrue(actual != null && expected.equals(actual));
        }
        catch (Exception e) {
            fail("Do not work for a list of sorted streams " + e);
        }
    }

    @DisplayName("Check if list have empty string")
    @Test
    void checkEmptyStringPresent() {
        final List<Stream<String>> origin = List.of(Stream.of(new String[] { "", "(050)1234567" }));
        final Map<String, List<String>> expected = Map.of("050", Arrays.asList("1234567"));
        Map<String, List<String>> actual = null;
        try {
            actual = new MyUtils().phoneNumbers(origin).entrySet().stream()
                    .collect(Collectors.<Map.Entry<String, Stream<String>>, String, List<String>>toMap(
                            entry -> entry.getKey(), entry -> entry.getValue().collect(Collectors.toList())));
            assertTrue(actual != null && expected.equals(actual));
        }
        catch (Exception e) {
            fail("Do not work for a list of empty string " + e);
        }
    }

    @DisplayName("Check if list have empty string only")
    @Test
    void checkEmptyStringOnly() {
        final List<Stream<String>> origin = List.of(Stream.of(new String[] { "", " " }));
        final Map<String, List<String>> expected = Map.of();
        Map<String, List<String>> actual = null;
        try {
            actual = new MyUtils().phoneNumbers(origin).entrySet().stream()
                    .collect(Collectors.<Map.Entry<String, Stream<String>>, String, List<String>>toMap(
                            entry -> entry.getKey(), entry -> entry.getValue().collect(Collectors.toList())));
            assertTrue(actual != null && expected.equals(actual));
        }
        catch (Exception e) {
            fail("Do not work for a list of empty string only " + e);
        }
    }

    @DisplayName("Check if null present in list")
    @Test
    void checkNullPresent() {
        final List<Stream<String>> origin = List.of(Stream.of(new String[] { "093 987 65 43", null }));
        final Map<String, List<String>> expected = Map.of("093", Arrays.asList("9876543"));
        Map<String, List<String>> actual = null;
        try {
            actual = new MyUtils().phoneNumbers(origin).entrySet().stream()
                    .collect(Collectors.<Map.Entry<String, Stream<String>>, String, List<String>>toMap(
                            entry -> entry.getKey(), entry -> entry.getValue().collect(Collectors.toList())));
            assertTrue(actual != null && expected.equals(actual));
        }
        catch (Exception e) {
            fail("Do not work for a list of null present " + e);
        }
    }

    @DisplayName("Check if only null present in list")
    @Test
    void checkNullOnly() {
        final List<Stream<String>> origin = List.of(Stream.of(new String[1]));
        final Map<String, List<String>> expected = Map.of();
        Map<String, List<String>> actual = null;
        try {
            actual = new MyUtils().phoneNumbers(origin).entrySet().stream()
                    .collect(Collectors.<Map.Entry<String, Stream<String>>, String, List<String>>toMap(
                            entry -> entry.getKey(), entry -> entry.getValue().collect(Collectors.toList())));
            assertTrue(actual != null && expected.equals(actual));
        }
        catch (Exception e) {
            fail("Do not work for a list of only null present " + e);
        }
    }
}
