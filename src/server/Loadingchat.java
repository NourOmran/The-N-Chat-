
package server;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author nouro
 */
public class Loadingchat {
    public void write(String filename , String message) 
  throws IOException {
    BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true));
    writer.write(message);
    writer.newLine();
    writer.close();
}
    public void CreateFile(String filename ) {
      System.out.print(filename);
     
    
      System.out.print(filename);
      try {
      File myObj = new File(filename);
      if (myObj.createNewFile()) {
        System.out.println("File created: " + myObj.getName());
      } else {
        System.out.println("File already exists.");
      }
    } catch (IOException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
    }
}
    

