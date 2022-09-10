import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MyUtils {
   public Stream<Integer> duplicateElements(Stream<Integer> stream) {
        return stream
        .filter(v -> v != null)
        .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
        .entrySet().stream()
        .filter(v -> v.getValue() > 1)
        .map(v -> v.getKey())
        .sorted();
    }
}
