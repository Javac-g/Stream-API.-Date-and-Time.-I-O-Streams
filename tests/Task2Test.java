import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.lang.reflect.Method;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class Task2Test {

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
        String m = "getDateAfterToday";
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

    @DisplayName("Check that method getDateAfterToday return correct results")
    @ParameterizedTest
    @MethodSource("getDateAfterToday")
    void isLeapYearsTrue(int years, int months, int days) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_LOCAL_DATE;
        LocalDate localDate = LocalDate.now().plusYears(years).plusMonths(months).plusDays(days);
        assertEquals(localDate.format(dateTimeFormatter), MyUtils.getDateAfterToday(years, months, days));
    }

    private static Stream<Arguments> getDateAfterToday() {
        return Stream.of(Arguments.of(3, 18, 27), Arguments.of(-2, 5, 18), Arguments.of(8, 0, 24),
                Arguments.of(2, -37, 9), Arguments.of(0, 0, 0));
    }


}
