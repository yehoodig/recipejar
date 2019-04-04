/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package recipejar.data;

/**
 * A data source should contain: getters and setters for the source
 * @author James
 */
public class Index{
    private static IndexFile src = null;
    
    public static void setSource(IndexFile s) {
        src = s;
    }

    /**
     * 
     * Do not make up for deficiencies in other places by initializing the data
     * source here.  
     * 
     */
    public static IndexFile getSource() {
      if (src == null) {
         src = new IndexFile(Prefs.FILE_INDEX.toString());
      }
        return src;
    }

    public static boolean ready() {
       if(src == null){
           return false;
       }
       return true;
    }

    public void initializeSource() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void finalizeSource() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
