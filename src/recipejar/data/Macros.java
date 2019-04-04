/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package recipejar.data;

import recipejar.data.MacroFile;

/**
 * A data source should contain: getters and setters for the source
 * @author James
 */
public class Macros{
    private static MacroFile src;
    
    public static void setSource(MacroFile s) {
        src = s;
    }

    /**
     * 
     * Do not make up for deficiencies in other places by initializing the data
     * source here.  
     * 
     */
    public static MacroFile getSource() {
        return src;
    }

    public void initializeSource() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void finalizeSource() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
