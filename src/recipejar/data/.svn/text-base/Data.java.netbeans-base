/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package recipejar.data;

import java.io.File;
import java.io.IOException;
import recipejar.ui.UserInterface;

/**
 *
 * @author James McConnel <jamesmcconnel@recipejar>
 */
public class Data {
    //What data items does the program depend on?
    IndexFile index;
    IndexFile indexFileTemplate;
    File indexStyleSheet;
    
    RecipeFile recipeTemplate;
    File recipeStyleSheet;
    
    String[] commandLine;
    
    MacroFile macros;
    
    Prefs prefs;
    
    File welcomeMsg;
    
    UnitFile units;
    
    public Data(){
        try {
            RecipeTemplate.setSource(new RecipeFile(Prefs.TEMPLATE_RECIPE.toString()));
        } catch (IOException ex) {
            System.err.print(System.getProperty("line.separator")+"Recipe Template could not be initialized:"+Prefs.TEMPLATE_RECIPE.toString()+System.getProperty("line.separator"));
            System.exit(1);
        }
        IndexTemplate.setSource(new IndexFile(Prefs.TEMPLATE_INDEX.toString()));
        Index.setSource(new IndexFile(Prefs.FILE_INDEX.toString()));
        IndexStyle.setSource(new File(Prefs.CSS_INDEX.toString()));
        
    }

    public void setCommandLine(String[] c) {
        commandLine = c;
    }

    public String[] getCommandLine() {
        return commandLine;
    }

    public void linkUserInterface(UserInterface aThis) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
