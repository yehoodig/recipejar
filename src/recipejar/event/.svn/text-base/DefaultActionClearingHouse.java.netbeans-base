/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package recipejar.event;

import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.event.HyperlinkEvent;
import recipejar.ui.UserInterface;
import recipejar.Util;
import recipejar.data.Data;
import recipejar.ui.RecipeJarFrame;
import recipejar.data.UIMode;
import recipejar.data.Index;
import recipejar.data.Prefs;
import recipejar.data.Recipe;
import recipejar.data.WelcomeMsg;
import recipejar.data.RecipeFile;

/**
 * Vision: The purpose of this class is to contain the major processing
 * functions, and provide UI components with convenient methods to handle
 * events.
 *
 * The UI class should keep a BackEnd object on hand and in the event handlers
 * invoke one of the BackEnd's methods. Most (if not all) of the public methods
 * will expect a UserInterface object.
 *
 * The BackEnd will publicly show "handle" methods intended for specific events.
 * Other procedures should be private.
 *
 * @author james
 */
public class DefaultActionClearingHouse {

    private Data data;
    private UserInterface UI;
    private WindowListener windowListener_frame = new WindowListener() {
        @Override
        public void windowOpened(WindowEvent we) {
            //aes.fireApplicationEvent(new ApplicationEvent(Event_Type.FRAME_OPEN, evt));
            if (data.getCommandLine().length > 0) {
                if (data.getCommandLine()[0].equalsIgnoreCase("-f")) {
                    String fileBuffer = new String();
                    File inputfile = new File(data.getCommandLine()[1]);
                    if (inputfile.exists()) {
                        try {
                            FileReader inputReader = new FileReader(inputfile);
                            int c = inputReader.read();
                            while (c != -1) {
                                fileBuffer = fileBuffer + ((char) c);
                                c = inputReader.read();
                            }
                            inputReader.close();
                            //  newRecipe.startNewWith(fileBuffer);
                        } catch (FileNotFoundException ex) {
                        } catch (IOException ex) {
                        }
                    }
                } else if (data.getCommandLine()[0].equalsIgnoreCase("-c")) {
                    //from the clipboard
                    //newFromClip.go();
                } else {
                    // newRecipe.startNewWith(Util.getProgramParameters()[0]);
                }
            }
        }

        @Override
        public void windowClosing(WindowEvent we) {
            if (Util.isOS("mac")) {
                //      this.setVisible(false);
            }
            //    aes.fireApplicationEvent(new ApplicationEvent(Event_Type.FRAME_CLOSE, evt));
        }

        @Override
        public void windowClosed(WindowEvent we) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void windowIconified(WindowEvent we) {
            if (Util.isOS("mac")) {
                //       this.setVisible(false);
            }
//      aes.fireApplicationEvent(new ApplicationEvent(Event_Type.FRAME_ICONIFY, evt));
        }

        @Override
        public void windowDeiconified(WindowEvent we) {
            //     setVisible(true);
//      aes.fireApplicationEvent(new ApplicationEvent(Event_Type.FRAME_DEICON, evt));
        }

        @Override
        public void windowActivated(WindowEvent we) {
            // aes.fireApplicationEvent(new ApplicationEvent(Event_Type.FRAME_ACTIVATE, evt));
        }

        @Override
        public void windowDeactivated(WindowEvent we) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    };
    private ActionListener actionListener_OpenRecipeForEditing = new ActionListener() {
        private long lastEditEvent = 0;

        @Override
        public void actionPerformed(ActionEvent evt) {
            if (lastEditEvent == 0 || lastEditEvent < evt.getWhen()) {
                if (evt.getID() == java.awt.event.ActionEvent.ACTION_PERFORMED) {
                    if (evt.getActionCommand().equals(Event_Type.EDIT_START.getCMD())) {
                        //TODO aes.fireApplicationEvent(new ApplicationEvent(Event_Type.EDIT_START, evt));
                    } else if (evt.getActionCommand().equals(Event_Type.EDIT_CANCEL.getCMD())) {
                        //TODO aes.fireApplicationEvent(new ApplicationEvent(Event_Type.EDIT_CANCEL, evt));
                    }
                    lastEditEvent = evt.getWhen();
                }
            }
        }
    };
    private ActionListener actionListener_NewRecipe = new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent ae) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    };

    public DefaultActionClearingHouse() {
    }

    public void setData(Data d) {
        data = d;
    }
   
    /**
     * For use ONLY by certain initialization methods. DO NOT USE.
     */
    public void linkUserInterface(UserInterface aThis) {
        UI = aThis;
        data.linkUserInterface(aThis);
    }

    public ActionListener getActionListener(Event_Type t) {
        switch (t) {
            case NEW:
                return actionListener_NewRecipe;
            case EDIT_START:
            case EDIT_CANCEL:
                return actionListener_OpenRecipeForEditing;
            default:
                return null;
        }
    }

    public  void handleEvent_About() {
        try {
            UI.getPresentationPanel().setPage(WelcomeMsg.getSource());
            InputStream in = ClassLoader.getSystemResourceAsStream("about.txt");
            String s = new String();
            int c = in.read();
            while (c != -1) {
                s = s + (char) c;
                c = in.read();
            }
            JOptionPane.showMessageDialog(UI.getFrame(), s, "About RecipeJar", JOptionPane.INFORMATION_MESSAGE);
            UI.getFrame().setMode(UIMode.INITIAL_STATE);
            Recipe.setSource(null);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Fatal Error.  See \"errorLog.txt\" for more information.");
            System.exit(1);
        }
    }

    public static void handleEvent_Delete() {
        /*   try {
         if (!UI.getEditPanel()) {
         JOptionPane.showMessageDialog(UI.getRecipePanel(), "Can't delete nonexistent file!");
         } else {
         int input = JOptionPane.showConfirmDialog(UI.getRecipePanel(),
         "Are you sure you want to delete: \"" + UI.getTitleText() + "\"?",
         "Are you sure?",
         JOptionPane.YES_NO_OPTION,
         JOptionPane.WARNING_MESSAGE);
         if (input == JOptionPane.YES_OPTION) {
         //try {
         //TODO index.delete(recipe.getEditor().getOpen());
         //setEditing(false);

         //} catch (IOException ex) {
         JOptionPane.showMessageDialog(null, "Fatal Error while removing recipe from index.  See \"errorLog.txt\" for more information.");
         System.exit(1);
         //} catch (NullPointerException ex) {
         JOptionPane.showMessageDialog(null, "Fatal Error while removing recipe from index.  See \"errorLog.txt\" for more information.");
         System.exit(1);
         //}
         //               UI.getRecipePanel().deleteRecipe();
         }
         }
         } finally {
         System.gc();
         }
         */
    }

    public void handleEvent_Exit() {
        if (Prefs.CURRENT_MODE.equals(UIMode.EDITING.toString())
                && UI.getEditPanel().isChanged(Recipe.getCurrent())) {
            int option = JOptionPane.showConfirmDialog(UI.getFrame(),
                    "You have unsaved changes.\n"
                    + "Would you like to save this recipe?\n"
                    + "Any unsaved changes will be discarded.");
            if (option != JOptionPane.CANCEL_OPTION) {
                if (option == JOptionPane.YES_OPTION) {
                    saveChanges();
                }
                if (!Util.isOS("mac")) {
                    System.exit(0);
                }
            }
        } else if (!Util.isOS("mac")) {
            System.exit(0);
        }
    }

    public void handleEvent_Help() {
        if (java.awt.Desktop.isDesktopSupported()) {
            try {
                java.awt.Desktop.getDesktop().browse(new java.net.URI(Prefs.HELP_URL.toString()));
            } catch (URISyntaxException ex) {
                JOptionPane.showMessageDialog(UI.getFrame(), "You can get help, by visiting \"" + Prefs.HELP_URL.toString() + "\"\n Thanks, \n   -mgmt");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(UI.getFrame(), "You can get help, by visiting \"" + Prefs.HELP_URL.toString() + "\"\n Thanks, \n   -mgmt");
            }
        } else {
            JOptionPane.showMessageDialog(UI.getFrame(), "You can get help, by visiting \"" + Prefs.HELP_URL.toString() + "\"\n Thanks, \n   -mgmt");
        }
    }

    public  void handleEvent_Minimize() {
        UI.getFrame().setExtendedState(RecipeJarFrame.ICONIFIED);
    }

    public  void handleEvent_NewFromClip() {
        Transferable contents = Toolkit.getDefaultToolkit().getSystemClipboard().getContents(null);
        if (contents != null) {
            if (contents.isDataFlavorSupported(DataFlavor.stringFlavor)) {
                try {
                    startNewWithContent((String) contents.getTransferData(DataFlavor.stringFlavor));
                } catch (UnsupportedFlavorException ex) {
                } catch (IOException ex) {
                }
            }
        }

    }

    /**
     * Attempts to parse s as a recipe and put it into a new recipe.
     *
     * @param s
     */
    private  void startNewWithContent(String s) {
        startNew();

        if (s != null) {
            String notes = new String();
            String ingredients = new String();
            String procedure = new String();
            int i = 0;
            char c = '\0';
            if (s.length() > 0) {
                c = s.charAt(i);
            }

            //Notes
            while (i < s.length()) {
                if (i == 0) {
                    if (Character.isDigit(c)) {//no notes
                        break;
                    }
                }
                if (c == '\n') {
                    if (i + 1 >= s.length()) {
                        break;
                    }
                    c = s.charAt(i + 1);
                    if (Character.isDigit(c)) {//starting ingredients
                        i++;
                        break;
                    }
                    c = s.charAt(i);
                }
                notes = notes + c;

                i++;
                if (i >= s.length()) {
                    break;
                }
                c = s.charAt(i);
            }

            //Ingredients
            while (i < s.length()) {
                ingredients = ingredients + "<li>";
                while (c != '\n') {
                    ingredients = ingredients + c;
                    i++;
                    if (i >= s.length()) {
                        break;
                    }
                    c = s.charAt(i);
                }
                ingredients = ingredients + "</li>";
                i++;
                if (i >= s.length()) {
                    break;
                }
                c = s.charAt(i);
                char d = '\0';
                if (!s.substring(i).trim().isEmpty()) {
                    d = s.substring(i).trim().charAt(0);
                }
                if (d != '\0' && !Character.isDigit(d)) {//the first non whitespace char
                    int nextline = s.indexOf("\n", i);
                    if (nextline == -1 || nextline + 1 >= s.length()) {
                        break;
                    }
                    d = '\0';
                    if (!s.substring(nextline + 1).trim().isEmpty()) {
                        d = s.substring(nextline + 1).trim().charAt(0);
                    }
                    if (!Character.isDigit(d)) {//if the next line doesn't start with a digit too, then we've probably finished the ingredient list.
                        break;
                    }
                }
            }

            //Procedure
            while (i < s.length()) {
                procedure = procedure + c;
                i++;
                if (i >= s.length()) {
                    break;
                }
                c = s.charAt(i);
            }

            UI.getEditPanel().getNotesField().setText(notes);
//         UserIn.getIngredientList().load(ingredients);
            UI.getEditPanel().getProcedureField().setText(procedure);
        }
    }

    private static void startNew() {
        if (true) {//UI.getRecipePanel().startNew()) {
//         setEditing(true);
        }
        System.gc();
    }

    public  void handleEvent_Prefs() {
        UI.getPrefsDialog().setVisible(true);
    }

    public  void handleEvent_ZoomWindow() {
        UI.getFrame().setSize(UI.getFrame().getPreferredSize());
    }

    public  void handleEvent_Export() {
        if (UI.getFileChooser().showSaveDialog(UI.getFrame()) == JFileChooser.APPROVE_OPTION) {
            try {
                if (Recipe.getCurrent() != null) {
                    RecipeFile rf = Recipe.getCurrent();
                    rf.export(UI.getFileChooser().getSelectedFile());
                }
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(UI.getFrame(), "There was an error.  Export Failed.");
            }
        }
    }

    /*public void handleImport() {
     if (UI.getFileChooser().showOpenDialog(UI.getIndexPanel()) == JFileChooser.APPROVE_OPTION) {
     String title = "";
     try {
     FileReader reader = new FileReader(UI.getFileChooser().getSelectedFile());
     StringWriter sw = new StringWriter();
     int c = reader.read();
     while (c != -1) {
     sw.write(c);
     c = reader.read();
     }
     if (sw.toString().contains("<title>") && sw.toString().contains("<style type=\"text/css\">")
     && sw.toString().contains("<div id=\"notes\">")
     && sw.toString().contains("<div id=\"ingredients\">")
     && sw.toString().contains("<div id=\"procedure\">")) {

     title = sw.toString().substring(sw.toString().indexOf("<title>") + 7,
     sw.toString().indexOf("</title>"));

     if (rjUtil.removeBadChars(title).trim().toUpperCase().equals("TEMPLATE_RECIPE")
     || rjUtil.removeBadChars(title).trim().toUpperCase().equals("FILE_INDEX")
     || rjUtil.removeBadChars(title).trim().toUpperCase().equals("CINDEX")
     || title.isEmpty()) {
     title = "Imported Recipe";
     }
     RecipeFile importedRecipe = new RecipeFile(Prefs.RECIPES")
     + rjUtil.removeBadChars(title.trim()) + ".html");
     while (importedRecipe.exists()) {
     title = JOptionPane.showInputDialog("A Recipe with the same Title(" + title
     + ") already exists.\nPlease supply a new Title, or press Cancel to stop the operation.");
     if (title == null) {
     return;
     } else {
     importedRecipe = new RecipeFile(Prefs.RECIPES")
     + rjUtil.removeBadChars(title.trim()) + ".html");
     }
     }
     if (!importedRecipe.getTitle().equals(title)) {
     importedRecipe.setTitle(title);
     }

     RecipeFile old = new RecipeFile(UI.getFileChooser().getSelectedFile());
     int x = 0;
     importedRecipe.setStyle(old.getStyle());
     importedRecipe.setNotes(old.getNotes());
     //importedRecipe.setIngredientsFromHTML(old.getIngredientsAsString());
     importedRecipe.setProcedure(old.getProcedure());

     importedRecipe.save();
     //index.addRecipe(importedRecipe);
     }
     } catch (IOException ex) {
     if (ex.getMessage().equals("unable to add recipe.")) {
     JOptionPane.showMessageDialog(UI.getIndexPanel(), "The recipe \"" + title
     + "\" already exists.  Please change the title given in the file to be imported,"
     + "\nor delete the current recipe of this name and try again.");
     } else {
     return;
     }
     } catch (StringIndexOutOfBoundsException exx) {
     return;
     }
     }
     }*/
    public  void handleEvent_Open(HyperlinkEvent e) {
        recipejar.data.RecipeFile f = Index.getSource().lookup(e.getDescription());
        if (f != null) {
            UI.getPresentationPanel().setPage(f);
            UI.getFrame().setMode(UIMode.OPEN);
            Recipe.setSource(f);
            UI.getEditPanel().sync();
        } else {
        }
    }

    public  void handleEvent_Duplicate() {
        String newTitle = JOptionPane.showInputDialog("New Title:");
        while (Index.getSource().containsTitle(newTitle)) {
            JOptionPane.showMessageDialog(UI.getFrame(), "A Recipe by that name already exists, please enter a different title");
            newTitle = JOptionPane.showInputDialog("New Title:");
        }
        if (newTitle.isEmpty()) {
            return;
        }
        recipejar.data.RecipeFile f = Recipe.getCurrent();
        recipejar.data.RecipeFile g = null;
        try {
            g = new RecipeFile(Util.buildAbsoluteFileNameFrom(newTitle));
        } catch (IOException ex) {
            Logger.getLogger(DefaultActionClearingHouse.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (g != null) {
            g.setNotes(f.getNotes());
            g.setProcedure(f.getProcedure());
            for (int i = 0; i < f.getIngredientListSize(); i++) {
                g.setIngredient(i, f.getIngredient(i));
            }
            String l = "";
            for (int i = 0; i < f.getLabels().length; i++) {
                l = l + f.getLabels()[i];
                if (i < f.getLabels().length - 1) {
                    l = l + ", ";
                }
            }
            g.setLabels(l);
            try {
                Index.getSource().add(g);
                UI.getFrame().getAlphaTab().setSelectedTab(newTitle.charAt(0));
            } catch (IOException ex) {
                Logger.getLogger(DefaultActionClearingHouse.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static void handleEvent_Save() {
        //try {
     /* if (UI.isRecipeChanged()) {
         if (UI.getTitleText().isEmpty()) {
         JOptionPane.showMessageDialog(UI.getRecipePanel(), "Please enter a Title.", "You Forgot the Title", JOptionPane.ERROR_MESSAGE);
         } else if (Resources.getInstance().getIndex().containsTitle(UI.getTitleText())) {
         JOptionPane.showMessageDialog(UI.getRecipePanel(),
         "A Recipe by that name already exists.\n"
         + "You can may want to try:\n"
         + "1. Changing the Title of this one.\n"
         + "2. Editing the one that already has the Title " + UI.getTitleText() + "\n"
         + "3. Removing the old one, and starting fresh.",
         "Oops, I can't do that!", JOptionPane.ERROR_MESSAGE);
         } else if (true) {

         //                  index.addRecipe(recipe.getEditor().getOpen());
         UI.getIndexPanel().setSelectedIndex(UI.getTitleText().charAt(0));
         //handleOpen(UI.getRecipePanel().getEditor().getOpen());
         }

         } else {
         //UI.getEditPanel().save();
         //ApplicationScope.getInstance().getIndex().updateCategoriesOf(UI.getRecipePanel().getEditor().getOpen());
         //handleOpen(UI.getRecipePanel().getEditor().getOpen());
         }
         //} catch (IOException ex) {
         //ex.printStackTrace();
         JOptionPane.showMessageDialog(null, "Fatal Error Adding Link to index.  See \"errorLog.txt\" for more information.");
         //   System.exit(1);
         //} finally {
         System.gc();
         //}
         */
    }

    public static void handleEvent_Search() {
        //dialog.setVisibleWithTitleTagsNotes();
    }

    public  void handleEvent_ShowConv() {
        UI.getUnitConverterDialog().setVisible(true);
    }

    public static void handleEvent_NewEmpty() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public  void handleEvent_Edit() {
        UI.getFrame().setMode(UIMode.EDITING);
    }

    private static void saveChanges() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public  void handleEvent_Cancel() {
        UI.getFrame().setMode(UIMode.OPEN);
    }

    public static void handleEvent_Cut(ApplicationEvent e) {
        e.getTextComponent().cut();
    }

    public static void handleEvent_Copy(ApplicationEvent e) {
        e.getTextComponent().copy();
    }

    public static void handleEvent_Paste(ApplicationEvent e) {
        e.getTextComponent().paste();
    }

    public static void handleEvent_SelectAll(ApplicationEvent e) {
        e.getTextComponent().selectAll();
    }

    public WindowListener getWindowListener() {
        return windowListener_frame;
    }

}
