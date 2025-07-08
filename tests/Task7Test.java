import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class Task7Test {

    final private static String PACKAGE = "";


    private static Stream<Arguments> listOfClasses() {
        return Stream.of(Arguments.of("MyUtils"));
    }

    @DisplayName("Check that class contain method")
    @Test
    void isMethodPresent() {
        String cl = "MyUtils";
        String m = "duplicateElements";
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

    @DisplayName("Check unique numbers")
    @Test
    void checkUniqueNumbers() {
        Stream<Integer> origin = Stream.of(3, 2, 1, 12, 8, 4);
        List<Integer> expected = Arrays.asList();
        //
        List<Integer> actual = null;
        try {
            actual = new MyUtils().duplicateElements(origin)
                    .collect(Collectors.toList());
            assertTrue((actual != null) && expected.equals(actual));
        } catch (Exception e) {
            fail("Do not work correct with unique numbers" + e);
        }
    }

    @DisplayName("Check one duplicate")
    @Test
    void checkOneDuplicate() {
        Stream<Integer> origin = Stream.of(2, 2);
        List<Integer> expected = Arrays.asList(2);
        //
        List<Integer> actual = null;
        try {
            actual = new MyUtils().duplicateElements(origin)
                    .collect(Collectors.toList());
            assertTrue((actual != null) && expected.equals(actual));
        } catch (Exception e) {
            fail("Do not work correct with one duplicate" + e);
        }
    }

    @DisplayName("Check more duplicate")
    @Test
    void checkMoreDuplicate() {
        Stream<Integer> origin = Stream.of(3, 2, 1, 1, 12, 4, 8, 3);
        List<Integer> expected = Arrays.asList(1, 3);
        //
        List<Integer> actual = null;
        try {
            actual = new MyUtils().duplicateElements(origin)
                    .collect(Collectors.toList());
            assertTrue((actual != null) && expected.equals(actual));
        } catch (Exception e) {
            fail("Do not work correct with more duplicate" + e);
        }
    }

    @DisplayName("Check sequence duplicate")
    @Test
    void checkSequenceDuplicate() {
        Stream<Integer> origin = Stream.of(3, 3, 2, 2, 1, 1, 12, 8, 4);
        List<Integer> expected = Arrays.asList(1, 2, 3);
        //
        List<Integer> actual = null;
        try {
            actual = new MyUtils().duplicateElements(origin)
                    .collect(Collectors.toList());
            assertTrue((actual != null) && expected.equals(actual));
        } catch (Exception e) {
            fail("Do not work correct with sequence duplicate" + e);
        }
    }

    @DisplayName("Check three duplicate")
    @Test
    void checkThreeDuplicate() {
        Stream<Integer> origin = Stream.of(3, 2, 1, 1, 1, 12, 3, 8, 2, 3, 2);
        List<Integer> expected = Arrays.asList(1, 2, 3);
        //
        List<Integer> actual = null;
        try {
            actual = new MyUtils().duplicateElements(origin)
                    .collect(Collectors.toList());
            assertTrue((actual != null) && expected.equals(actual));
        } catch (Exception e) {
            fail("Do not work correct with three duplicate" + e);
        }
    }

    @DisplayName("Check one number")
    @Test
    void checkOneNumber() {
        Stream<Integer> origin = Stream.of(3);
        List<Integer> expected = Arrays.asList();
        //
        List<Integer> actual = null;
        try {
            actual = new MyUtils().duplicateElements(origin)
                    .collect(Collectors.toList());
            assertTrue((actual != null) && expected.equals(actual));
        } catch (Exception e) {
            fail("Do not work correct with one number" + e);
        }
    }

    @DisplayName("Check zero numbers")
    @Test
    void checkZeroNumbers() {
        Stream<Integer> origin = Stream.of(0, 0);
        List<Integer> expected = Arrays.asList(0);
        //
        List<Integer> actual = null;
        try {
            actual = new MyUtils().duplicateElements(origin)
                    .collect(Collectors.toList());
            assertTrue((actual != null) && expected.equals(actual));
        } catch (Exception e) {
            fail("Do not work correct with zero number" + e);
        }
    }

    @DisplayName("Check negative numbers")
    @Test
    void checkNegativeNumbers() {
        Stream<Integer> origin = Stream.of(-3, -2, 1, 1, 12, -3, 8, 2, 4);
        List<Integer> expected = Arrays.asList(-3, 1);
        //
        List<Integer> actual = null;
        try {
            actual = new MyUtils().duplicateElements(origin)
                    .collect(Collectors.toList());
            assertTrue((actual != null) && expected.equals(actual));
        } catch (Exception e) {
            fail("Do not work correct with negative number" + e);
        }
    }

    @DisplayName("Check contains null")
    @Test
    void checkContainsNull() {
        Stream<Integer> origin = Stream.of(3, 2, 1, 1, 12, 3, null, 2, null, 2);
        List<Integer> expected = Arrays.asList(1, 2, 3);
        //
        List<Integer> actual = null;
        try {
            actual = new MyUtils().duplicateElements(origin)
                    .collect(Collectors.toList());
            assertTrue((actual != null) && expected.equals(actual));
        } catch (Exception e) {
            fail("Do not work correct with contains null" + e);
        }
    }

    @DisplayName("Check empty stream")
    @Test
    void checkEmptyStream() {
        Stream<Integer> origin = Stream.of();
        List<Integer> expected = Arrays.asList();
        //
        List<Integer> actual = null;
        try {
            actual = new MyUtils().duplicateElements(origin)
                    .collect(Collectors.toList());
            assertTrue((actual != null) && expected.equals(actual));
        } catch (Exception e) {
            fail("Do not work correct with empty stream" + e);
        }
    }
    
}
