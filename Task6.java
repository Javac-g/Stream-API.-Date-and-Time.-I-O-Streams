import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MyUtils {
    
    public Map<String, Stream<String>> phoneNumbers(List<Stream<String>> list) {
        
        return list.stream()
        
            .filter(stream -> stream != null)
            .flatMap(phoneNumber -> phoneNumber)
            .filter(phoneNumber -> phoneNumber != null)
            .map(phoneNumber -> phoneNumber.replaceAll("[^\\d.]", ""))
            .filter(phoneNumber -> !phoneNumber.isEmpty())
            .distinct()
            .collect(Collectors.groupingBy(
                
                phoneNumber -> {
                    
                  if (phoneNumber.length() == 10) {
                      
                    return phoneNumber.substring(0, 3);
                    
                  } else if (phoneNumber.length() == 7) {
                      
                    return "loc";
                  }
                  return "err";
                  
                }, Collectors.mapping(
                    
                    phoneNumber -> {
                        
                      if (phoneNumber.length() == 10) {
                          
                        return phoneNumber.substring(3);
                      }
                      return phoneNumber;
                      
                    }, Collectors.collectingAndThen(
                        
                        Collectors.toList(),
                        
                        phoneNumberList -> phoneNumberList.stream().sorted()
                    )
                )));
      }
}
