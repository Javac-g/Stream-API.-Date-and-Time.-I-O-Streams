package jom.com.softserve.s6.task5;

import jom.com.softserve.s6.task5.MyUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class Task5Test {

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
        String m = "nameList";
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

    @DisplayName("Check if list have one participant")
    @Test
    void checkOneParticipant() {
        final Map<String, Stream<String>> origin = new HashMap<String, Stream<String>>();
        origin.put("Desktop", Stream.of("Ivan"));
        final List<String> expected = Arrays.asList("Ivan");
        List<String> actual = null;
        try {
            actual = (List<String>) new MyUtils().nameList((Map) origin).collect(Collectors.toList());
            assertTrue(actual != null && expected.equals(actual));
        } catch (Exception e) {
            fail("Do not work correct with one participant" + e);
        }
    }

    @DisplayName("Check if list have duplicate participants")
    @Test
    void checkDuplicateParticipants() {
        final Map<String, Stream<String>> origin = new HashMap<String, Stream<String>>();
        origin.put("Desktop", Stream.of(new String[]{"Ivan", "Ivan", "Ira"}));
        final List<String> expected = Arrays.asList("Ira", "Ivan");
        List<String> actual = null;
        try {
            actual = (List<String>) new MyUtils().nameList((Map) origin).collect(Collectors.toList());
            assertTrue(actual != null && expected.equals(actual));
        } catch (Exception e) {
            fail("Do not work correct with duplicate participants" + e);
        }
    }

    @DisplayName("Check if list have case sensitivity")
    @Test
    void checkCaseSensitivity() {
        final Map<String, Stream<String>> origin = new HashMap<String, Stream<String>>();
        origin.put("Desktop", Stream.of(new String[]{"iVan", "PeTRo ", "Petro", "Ivan"}));
        final List<String> expected = Arrays.asList("Ivan", "Petro");
        List<String> actual = null;
        try {
            actual = (List<String>) new MyUtils().nameList((Map) origin).collect(Collectors.toList());
            assertTrue(actual != null && expected.equals(actual));
        } catch (Exception e) {
            fail("Do not work correct with case sensitivity" + e);
        }
    }

    @DisplayName("Check if list extra spaces")
    @Test
    void checkExtraSpaces() {
        final Map<String, Stream<String>> origin = new HashMap<String, Stream<String>>();
        origin.put("Desktop", Stream.of(new String[]{" i v  a n", "  Pet  ro  "}));
        final List<String> expected = Arrays.asList("Ivan", "Petro");
        List<String> actual = null;
        try {
            actual = (List<String>) new MyUtils().nameList((Map) origin).collect(Collectors.toList());
            assertTrue(actual != null && expected.equals(actual));
        } catch (Exception e) {
            fail("Do not work correct with extra spaces" + e);
        }
    }

    @DisplayName("Check if list have unique participants")
    @Test
    void checkUniqueParticipants() {
        final Map<String, Stream<String>> origin = new HashMap<String, Stream<String>>();
        origin.put("Desktop", Stream.of(new String[]{"ivan", "Petro"}));
        origin.put("Web", Stream.of(new String[]{"Stepan", "ira"}));
        origin.put("Spring", Stream.of(new String[]{"Andriy", "Anna"}));
        final List<String> expected = Arrays.asList("Andriy", "Anna", "Ira", "Ivan", "Petro", "Stepan");
        List<String> actual = null;
        try {
            actual = (List<String>) new MyUtils().nameList((Map) origin).collect(Collectors.toList());
            assertTrue(actual != null && expected.equals(actual));
        } catch (Exception e) {
            fail("Do not work correct with unique participants" + e);
        }
    }

    @DisplayName("Check if list have one streams")
    @Test
    void checkOneStreams() {
        final Map<String, Stream<String>> origin = new HashMap<String, Stream<String>>();
        origin.put("Desktop", Stream.of(new String[]{" ivan", "Petro ", " Ira "}));
        final List<String> expected = Arrays.asList("Ira", "Ivan", "Petro");
        List<String> actual = null;
        try {
            actual = (List<String>) new MyUtils().nameList((Map) origin).collect(Collectors.toList());
            assertTrue(actual != null && expected.equals(actual));
        } catch (Exception e) {
            fail("Do not work correct with one streams" + e);
        }
    }

    @DisplayName("Check if list have two streams")
    @Test
    void checkTwoStreams() {
        final Map<String, Stream<String>> origin = new HashMap<String, Stream<String>>();
        origin.put("Desktop", Stream.of(new String[]{"ivan", "Petro", " Ira"}));
        origin.put("Web", Stream.of(new String[]{"Stepan", "ira ", "Andriy ", "anna"}));
        final List<String> expected = Arrays.asList("Andriy", "Anna", "Ira", "Ivan", "Petro", "Stepan");
        List<String> actual = null;
        try {
            actual = (List<String>) new MyUtils().nameList((Map) origin).collect(Collectors.toList());
            assertTrue(actual != null && expected.equals(actual));
        } catch (Exception e) {
            fail("Do not work correct with two streams" + e);
        }
    }

    @DisplayName("Check if list have three streams")
    @Test
    void checkThreeStreams() {
        final Map<String, Stream<String>> origin = new HashMap<String, Stream<String>>();
        origin.put("Desktop", Stream.of(new String[]{" ivan", "Petro ", " Ira ", "", null}));
        origin.put("Web", Stream.of(new String[]{"Stepan", "ira ", " Andriy ", "anna"}));
        origin.put("Spring", Stream.of(new String[]{"Ivan", "Anna"}));
        final List<String> expected = Arrays.asList("Andriy", "Anna", "Ira", "Ivan", "Petro", "Stepan");
        List<String> actual = null;
        try {
            actual = (List<String>) new MyUtils().nameList((Map) origin).collect(Collectors.toList());
            assertTrue(actual != null && expected.equals(actual));
        } catch (Exception e) {
            fail("Do not work correct with three streams" + e);
        }
    }

    @DisplayName("Check if list have sorted streams")
    @Test
    void checkSortedStreams() {
        final Map<String, Stream<String>> origin = new HashMap<String, Stream<String>>();
        origin.put("Desktop", Stream.of(new String[]{"Stepan", "Petro", "Ivan", "Ira", "Anna", "Andriy"}));
        final List<String> expected = Arrays.asList("Andriy", "Anna", "Ira", "Ivan", "Petro", "Stepan");
        List<String> actual = null;
        try {
            actual = (List<String>) new MyUtils().nameList((Map) origin).collect(Collectors.toList());
            assertTrue(actual != null && expected.equals(actual));
        } catch (Exception e) {
            fail("Do not work correct with sorted streams" + e);
        }
    }

    @DisplayName("Check if empty string present in list")
    @Test
    void checkEmptyStringPresent() {
        final Map<String, Stream<String>> origin = new HashMap<String, Stream<String>>();
        origin.put("Desktop", Stream.of(new String[]{" ivan", "Petro ", " Ira ", ""}));
        origin.put("Web", Stream.of(new String[]{"Stepan", "", "ira ", " Andriy ", "anna"}));
        origin.put("Spring", Stream.of(new String[]{"Ivan", "", "Anna"}));
        final List<String> expected = Arrays.asList("Andriy", "Anna", "Ira", "Ivan", "Petro", "Stepan");
        List<String> actual = null;
        try {
            actual = (List<String>) new MyUtils().nameList((Map) origin).collect(Collectors.toList());
            assertTrue(actual != null && expected.equals(actual));
        } catch (Exception e) {
            fail("Do not work correct if empty string present in list" + e);
        }
    }

    @DisplayName("Check if list have empty string only")
    @Test
    void checkEmptyStringOnly() {
        final Map<String, Stream<String>> origin = new HashMap<String, Stream<String>>();
        origin.put("Desktop", Stream.of(new String[]{"", " ", "  ", ""}));
        final List<String> expected = new ArrayList<String>();
        List<String> actual = null;
        try {
            actual = (List<String>) new MyUtils().nameList((Map) origin).collect(Collectors.toList());
            assertTrue(actual != null && expected.equals(actual));
        } catch (Exception e) {
            fail("Do not work correct if list have empty string only" + e);
        }
    }

    @DisplayName("Check if list have null participant")
    @Test
    void checkNullParticipantPresent() {
        final Map<String, Stream<String>> origin = new HashMap<String, Stream<String>>();
        origin.put("Desktop", Stream.of(new String[]{" ivan", null, "Petro ", " Ira ", null}));
        origin.put("Web", Stream.of(new String[]{"Stepan", "ira ", null, " Andriy ", "anna"}));
        origin.put("Spring", Stream.of(new String[]{"Ivan", null, "Anna"}));
        final List<String> expected = Arrays.asList("Andriy", "Anna", "Ira", "Ivan", "Petro", "Stepan");
        List<String> actual = null;
        try {
            actual = (List<String>) new MyUtils().nameList((Map) origin).collect(Collectors.toList());
            assertTrue(actual != null && expected.equals(actual));
        } catch (Exception e) {
            fail("Do not work correct if list have null participant" + e);
        }
    }

    @DisplayName("Check if list have null participant only")
    @Test
    void checkNullParticipantOnly() {
        final Map<String, Stream<String>> origin = new HashMap<String, Stream<String>>();
        origin.put("Desktop", Stream.of(new String[3]));
        final List<String> expected = new ArrayList<String>();
        List<String> actual = null;
        try {
            actual = (List<String>) new MyUtils().nameList((Map) origin).collect(Collectors.toList());
            assertTrue(actual != null && expected.equals(actual));
        } catch (Exception e) {
            fail("Do not work correct if list have null participant only" + e);
        }
    }

    @DisplayName("Check if list have null stream")
    @Test
    void checkNullStream() {
        final Map<String, Stream<String>> origin = new HashMap<String, Stream<String>>();
        origin.put("Desktop", null);
        final List<String> expected = new ArrayList<String>();
        List<String> actual = null;
        try {
            actual = (List<String>) new MyUtils().nameList((Map) origin).collect(Collectors.toList());
            assertTrue(actual != null && expected.equals(actual));
        } catch (Exception e) {
            fail("Do not work correct if list have null stream" + e);
        }
    }
    
}
