/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package recipejar.data;

import java.io.File;

/**
 * A data source should contain: getters and setters for the source
 *
 * @author James
 */
public class Recipe {

    private static RecipeFile src;
    private static RecipeFile last;
    private static File recipeFolder;

    /**
     *
     * Because the purpose of these data holder classes is keep track of the
     * data source in a way that everybody can access it, they cannot make too
     * many judgments about the "rightness" of the data or it's usefulness for a
     * specific task. Therefore they should accept values such as null without
     * comment.
     */
    public static void setSource(RecipeFile s) {
        last = src;
        src = s;
        Prefs.FILE_CURRENT.set(src == null ? "" : src.getAbsolutePath());
        Prefs.FILE_LAST.set(last == null ? "" : last.getAbsolutePath());
    }

    /**
     *
     * Do not make up for deficiencies in other places by initializing the data
     * source here.
     *
     */
    public static RecipeFile getCurrent() {
        if (src == null) {
            if (Prefs.FILE_CURRENT.toString().isEmpty()) {
                return null;
            }
        }
        return src;
    }

    public static RecipeFile getLast() {
        return last;
    }

    public static File getDBFolder() {
        if (recipeFolder == null) {
            recipeFolder = new File(Prefs.DIR_DB.toString());
        }
        return recipeFolder;
    }

    public void initializeSource() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void finalizeSource() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
