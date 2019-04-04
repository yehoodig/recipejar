/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package recipejar.data;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * A data source should contain: getters and setters for the source
 * @author James
 */
public class IndexStyle{
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
         src = new File(Prefs.CSS_INDEX.toString());
      }
        return src;
    }

    public void initializeSource() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void finalizeSource() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
   public static FileReader getReader() {
      FileReader r;

      try {
         r = new FileReader(src);
      } catch (FileNotFoundException ex) {
         return null;
      }

      return r;
   }
}
