import java.util.Map;
import java.util.stream.Stream;

public class MyUtils {
        
    public Stream<String> nameList(Map<String, Stream<String>> map) {
        if (map.isEmpty()) {
          throw new NullPointerException();
        }
    
        return map.values()
            .stream()
            .flatMap(f -> f)
            .filter(s -> s != null && !s.trim().isEmpty())
            .map(spaces -> spaces.replaceAll("\\s", ""))
            .map(str -> str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase())
            .distinct()
            .sorted();
        }
    }
