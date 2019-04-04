/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package recipejar.data;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringWriter;

/**
 * A data source should contain: getters and setters for the source
 * @author James
 */
public class WelcomeMsg{
    private static File src;
    
    public static void setSource(File s) {
        src = s;
    }

    /**
     * 
     * Do not make up for deficiencies in other places by initializing the data
     * source here.  
     * 
     */
    public static File getSource() {
         if (src == null) {
         src = new File(Prefs.FILE_WELCOME.toString());
      }
    return src;
    }
   public String getWelcomeMessage() {
      try {
         StringWriter s = new StringWriter();
         FileReader in = new FileReader(src);
         int c = in.read();
         while (c != -1) {
            s.write(c);
            c = in.read();
         }
         return s.toString();
      } catch (IOException ex) {
         System.exit(1);
         return null;
      }
   }

    public void initializeSource() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void finalizeSource() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
