

    private static String prepareData(String text) {
        
        byte[] bytes = text.getBytes();
    
        StringBuilder binary = new StringBuilder();
        
        for (byte b : bytes) {
            
          String str = Integer.toBinaryString(b);
          
          for (int i = str.length(); i < 7; i++) {
              
                str = String.format("0%s", str);
                
            }

            binary.append(str);
            
        }
        
        return binary.toString();
    }
    
    public static void writeFile(String filename, String text) {
    
            final String result = prepareData(text);
        
            try(OutputStream writer = new FileOutputStream(filename)) {
                
                writer.write(result.getBytes());
              
            } catch (IOException e) {
                
                e.printStackTrace();
              
            }
        
    }
    
    
