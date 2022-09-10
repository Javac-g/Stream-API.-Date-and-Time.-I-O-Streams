 private static String prepareData(String str) {
     
    StringBuilder stringBuilder = new StringBuilder();
    
    for (int i = 0; i < str.length(); i += 7) {
        
       String substring = str.substring(i, i + 7);
       
       char character = (char) Integer.parseInt(substring, 2);
       
       stringBuilder.append(character);
      
    }
    
    return stringBuilder.toString();
  }

public static String readFile(String filename) {
    
    StringBuilder stringBuilder = new StringBuilder();
    
    try (FileReader fileReader = new FileReader(filename)) {
        
      int i;
      
      while ((i = fileReader.read()) != -1) {
          
        stringBuilder.append((char) i);
        
      }
      
    } catch (IOException e) {
        
      e.printStackTrace();
      
    }
    
    return prepareData(stringBuilder.toString());
  }

  
