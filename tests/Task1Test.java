
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.lang.reflect.Method;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class Task1Test {

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
        String m = "isLeapYear";
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

    @DisplayName("Check 'isLeapYear()' is True")
    @ParameterizedTest
    @MethodSource("listOfYearsTrue")
    void isLeapYearsTrue(int year) {
        assertTrue(MyUtils.isLeapYear(year), "The year " + year + " must be True");
    }

    private static Stream<Arguments> listOfYearsTrue() {
        return Stream.of(Arguments.of(1952), Arguments.of(2020), Arguments.of(2000));
    }

    @DisplayName("Check 'isLeapYear()' is False")
    @ParameterizedTest
    @MethodSource("listOfYearsFalse")
    void isLeapYearsFalse(int year) {
        assertFalse(MyUtils.isLeapYear(year), "The year " + year + " must be False");
    }

    private static Stream<Arguments> listOfYearsFalse() {
        return Stream.of(Arguments.of(1974), Arguments.of(1990));
    }
    
}
