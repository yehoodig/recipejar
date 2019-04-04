/*
 * RecipeJarFrame.java
 * @author James McConnel
 * @version %I%, %G%
 *
 * Created on September 19, 2008, 6:36 AM
 */
package recipejar.ui;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JSeparator;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
import recipejar.event.AbstractTextAction;
import recipejar.Util;
import recipejar.data.Prefs;
import recipejar.data.UIMode;
import recipejar.event.Event_Type;

public class RecipeJarFrame extends javax.swing.JFrame {



   /*************Instance Fields************/
   private EditorPanel editPanel;
   /**
    * Constructor
    */
   public RecipeJarFrame() {

      /*************************************/
      initComponents();
      /*************************************/
      setIconImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getClassLoader().getResource("recipejar.gif")));
      setPreferredSize(new Dimension(700, 580));
      setSize(new Dimension(700, 580));
      if (Util.isOS("mac os x") && UIManager.getLookAndFeel().isNativeLookAndFeel()) {
         recipeMenu.remove(miExit);
         recipeMenu.remove(miPrefs);
      } else {
         jMenuBar2.remove(this.windowMenu); //Window menu
      }
   }

   public void setMode(UIMode mode) {
        switch (mode) {
            case OPEN:
                miOpen.setText(Event_Type.EDIT_START.getCMD());
                miOpen.setEnabled(true);
                miExport.setEnabled(true);
                miDuplicate.setEnabled(true);
                miDelete.setEnabled(true);
                if (Prefs.CURRENT_MODE.toString().equals(UIMode.EDITING.toString())) {
                    swapPresentation(rjTextPane1);
                }
                break;
            case INITIAL_STATE:
                miOpen.setEnabled(false);
                miOpen.setText(Event_Type.EDIT_START.getCMD());
                if (Prefs.CURRENT_MODE.toString().equals(UIMode.EDITING)) {
                    swapPresentation(rjTextPane1);
                }
                break;
            case EDITING:
                miOpen.setText(Event_Type.EDIT_CANCEL.getCMD());
                miOpen.setEnabled(true);
                swapPresentation(editPanel);
                break;
        }
        Prefs.CURRENT_MODE.set(mode.toString());
    }
   public AlphaTab getAlphaTab() {
      return alphaTab1;
   }

   public JMenuItem getMiEdit() {
      return miOpen;
   }
   public rjTextPane getRjTextPane1() {
      return rjTextPane1;
   }

   public void swapPresentation(javax.swing.JComponent c) {
      this.jSplitPane1.add(c, javax.swing.JSplitPane.RIGHT);
   }
   
    void addActionListener(Event_Type t, ActionListener actionListener) {
        switch(t){
            case NEW:
                miNew.addActionListener(actionListener);
                break;
            case EDIT_START:
            case EDIT_CANCEL:
                for(int i=0; i < miOpen.getActionListeners().length; i++)
                    if(miOpen.getActionListeners()[i].equals(actionListener)){
                        return;
                    }
                miOpen.addActionListener(actionListener);
                break;
            default: 
                
        }
    }

    public AlphaTab getAlphaTab1() {
        return alphaTab1;
    }

    public JMenu getHelpMenu() {
        return helpMenu;
    }

    public JCheckBoxMenuItem getjCheckBoxMenuItem1() {
        return jCheckBoxMenuItem1;
    }

    public JMenu getjMenu1() {
        return jMenu1;
    }

    public JMenu getjMenu2() {
        return jMenu2;
    }

    public JMenu getjMenu3() {
        return jMenu3;
    }

    public JMenuBar getjMenuBar2() {
        return jMenuBar2;
    }

    public JMenuItem getjMenuItem1() {
        return jMenuItem1;
    }

    public JMenuItem getjMenuItem7() {
        return jMenuItem7;
    }

    public JMenuItem getjMenuItem8() {
        return jMenuItem8;
    }

    public JPanel getjPanel1() {
        return jPanel1;
    }

    public JPopupMenu.Separator getjSeparator1() {
        return jSeparator1;
    }

    public JPopupMenu.Separator getjSeparator2() {
        return jSeparator2;
    }

    public JPopupMenu.Separator getjSeparator3() {
        return jSeparator3;
    }

    public JSeparator getjSeparator5() {
        return jSeparator5;
    }

    public JSplitPane getjSplitPane1() {
        return jSplitPane1;
    }

    public JTextField getjTextField1() {
        return jTextField1;
    }

    public JMenuItem getMiAbout() {
        return miAbout;
    }

    public JMenuItem getMiClip() {
        return miClip;
    }

    public JMenuItem getMiCopy() {
        return miCopy;
    }

    public JMenuItem getMiCut() {
        return miCut;
    }

    public JMenuItem getMiDelete() {
        return miDelete;
    }

    public JMenuItem getMiDuplicate() {
        return miDuplicate;
    }

    public JMenuItem getMiExit() {
        return miExit;
    }

    public JMenuItem getMiExport() {
        return miExport;
    }

    public JMenuItem getMiHelp() {
        return miHelp;
    }

    public JMenuItem getMiImport() {
        return miImport;
    }

    public JMenuItem getMiNew() {
        return miNew;
    }

    public JMenuItem getMiOpen() {
        return miOpen;
    }

    public JMenuItem getMiPaste() {
        return miPaste;
    }

    public JMenuItem getMiPrefs() {
        return miPrefs;
    }

    public JMenuItem getMiPrint() {
        return miPrint;
    }

    public JMenuItem getMiSave() {
        return miSave;
    }

    public JMenuItem getMiSelAll() {
        return miSelAll;
    }

    public JMenu getRecipeMenu() {
        return recipeMenu;
    }

    public JMenu getWindowMenu() {
        return windowMenu;
    }

   /******************Methods******************/
   /**
    * Subroutine to initialize all the actions.
    *
   private void setupActions() {
   //the statements below are sorted by what components they require.
   renameRecipe = new RenameAction(indexPane1, recipe);
   ema = new EditModeAction(recipe);
   exporter = new ExportAction(recipe);
   deleteRecipe = new DeleteAction(recipe, index, ema);
   openRecipe = new OpenAction(recipe, index);
   saveRecipe = new SaveAction(recipe, index, openRecipe);
   recipe.getEditor().getSaveButton().setAction(saveRecipe);
   recipe.getEditor().getCancelButton().addActionListener(new ActionListener() {

   public void actionPerformed(ActionEvent arg0) {
   ema.setEditing(false);
   }
   });
   aboutAction = new AboutAction(recipe);

   index.addHyperlinkListener(openRecipe);
   exitProgram = new ExitAction(this, recipe);
   help = new HelpAction(recipe);
   importer = new ImportAction(index);
   newRecipe = new NewAction(recipe, ema);
   newFromClip = new NewFromClipboard(newRecipe);
   lfMetal = new LAFAction("Metal");
   lfMotif = new LAFAction("Motif");
   lfSystem = new LAFAction("System");
   prefs = new PreferencesAction(this, lfMetal, lfMotif, lfSystem);
   showConv = new ShowConverterAction(new UnitConverterDialog(this, false));
   search = new SearchAction(this, index, openRecipe);
   reconciler = new ReconcileWithTemplateAction("Update Recipes", index.getSource(), index);
   recipe.getEditor().addEditListener(reconciler);
   }*/
   /**
    *
    *
   private void makeWindowMenu() {
   try {
   //If you don't do it this way, there will be an error on other systems;
   rjOSAdapter appMenu = (rjOSAdapter) Class.forName("recipejar.rjMacMenuListener").newInstance();
   appMenu.setup(this, aboutAction, exitProgram, prefs);

   ///////////Remove the non-mac items///////////////
   helpMenu.remove(miAbout);
   //Exit item and the last separator
   recipeMenu.remove(miExit);
   recipeMenu.remove(jSeparator5);
   toolsMenu.remove(miPrefs);
   toolsMenu.remove(toolsSep);

   //The Window Menu goes before the help menu.
   jMenuBar2.remove(helpMenu);
   JMenu m = new JMenu("Window");
   m.add(minAction = new MinimizeAction("Minimize", this));
   m.add(new ZoomAction("Zoom", this));
   m.addSeparator();

   //The Windows Section
   ButtonGroup bg = new ButtonGroup();

   JCheckBoxMenuItem item;
   windowMenuItem = new JCheckBoxMenuItem(new AbstractAction("Recipejar") {

   public void actionPerformed(ActionEvent e) {
   toFront();
   }
   });
   windowMenuItem.setSelected(true);
   bg.add(windowMenuItem);//Frame button
   m.add(windowMenuItem);
   //converter button
   item = showConv.getCheckBoxMenuItem(jMenuBar2, m, helpMenu);
   bg.add(item);
   m.add(item);
   //find button
   item = search.getCheckBoxMenuItem(jMenuBar2, m, helpMenu);
   bg.add(item);
   m.add(item);
   jMenuBar2.add(m);
   jMenuBar2.add(helpMenu);

   //If any of these are encountered, the window menu just isn't created.
   } catch (InstantiationException ex) {
   } catch (IllegalAccessException ex) {
   } catch (ClassNotFoundException ex) {
   }
   }


   /*************NetBeans stuff*****************/
   /** This method is called from within the constructor to
    * initialize the form.
    * WARNING: Do NOT modify this code. The content of this method is
    * always regenerated by the Form Editor.
    */
   @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSplitPane1 = new javax.swing.JSplitPane();
        jPanel1 = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();
        if(recipejar.Util.isOS("mac")){
            jTextField1.putClientProperty("JTextField.variant", "search");
        }
        alphaTab1 = new recipejar.ui.AlphaTab();
        rjTextPane1 = new recipejar.ui.rjTextPane();
        jMenuBar2 = new javax.swing.JMenuBar();
        recipeMenu = new javax.swing.JMenu();
        miNew = new javax.swing.JMenuItem();
        miOpen = new javax.swing.JMenuItem();
        miSave = new javax.swing.JMenuItem();
        miDuplicate = new javax.swing.JMenuItem();
        jSeparator5 = new javax.swing.JSeparator();
        jMenu3 = new javax.swing.JMenu();
        miImport = new javax.swing.JMenuItem();
        miClip = new javax.swing.JMenuItem();
        miExport = new javax.swing.JMenuItem();
        miDelete = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        miPrint = new javax.swing.JMenuItem();
        miPrefs = new javax.swing.JMenuItem();
        miExit = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        miCut = new javax.swing.JMenuItem();
        miCopy = new javax.swing.JMenuItem();
        miPaste = new javax.swing.JMenuItem();
        miSelAll = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jMenuItem1 = new javax.swing.JMenuItem();
        windowMenu = new javax.swing.JMenu();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenuItem8 = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JPopupMenu.Separator();
        jCheckBoxMenuItem1 = new javax.swing.JCheckBoxMenuItem();
        helpMenu = new javax.swing.JMenu();
        miHelp = new javax.swing.JMenuItem();
        miAbout = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("RecipeJar");
        setBounds(new java.awt.Rectangle(200, 100, 0, 0));

        jSplitPane1.setDividerLocation(200);
        jSplitPane1.setOneTouchExpandable(true);

        jPanel1.setLayout(new java.awt.BorderLayout());

        jTextField1.setToolTipText("Search");
        jTextField1.setFocusAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()).getKeyChar());
        jPanel1.add(jTextField1, java.awt.BorderLayout.PAGE_START);

        alphaTab1.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);
        jPanel1.add(alphaTab1, java.awt.BorderLayout.CENTER);

        jSplitPane1.setLeftComponent(jPanel1);
        jSplitPane1.setRightComponent(rjTextPane1);

        getContentPane().add(jSplitPane1, java.awt.BorderLayout.CENTER);

        recipeMenu.setMnemonic('R');
        recipeMenu.setText("Recipe");

        miNew.setAccelerator(Event_Type.NEW.getKeyStroke());
        miNew.setMnemonic('N');
        miNew.setText("New...");
        recipeMenu.add(miNew);

        miOpen.setText("Open");
        miOpen.setEnabled(false);
        recipeMenu.add(miOpen);

        miSave.setAccelerator(Event_Type.SAVE.getKeyStroke());
        miSave.setMnemonic('S');
        miSave.setText("Save");
        miSave.setToolTipText("or Save As");
        miSave.setEnabled(false);
        recipeMenu.add(miSave);

        miDuplicate.setAccelerator(Event_Type.DUPLICATE.getKeyStroke());
        miDuplicate.setMnemonic('D');
        miDuplicate.setText("Duplicate");
        miDuplicate.setEnabled(false);
        recipeMenu.add(miDuplicate);
        recipeMenu.add(jSeparator5);

        jMenu3.setMnemonic('i');
        jMenu3.setText("Import From");

        miImport.setAccelerator(Event_Type.IMPORT_FILE.getKeyStroke());
        miImport.setText("File...");
        jMenu3.add(miImport);

        miClip.setAccelerator(Event_Type.IMPORT_CLB.getKeyStroke());
        miClip.setText("Clipboard...");
        jMenu3.add(miClip);

        recipeMenu.add(jMenu3);

        miExport.setText("Export...");
        miExport.setEnabled(false);
        recipeMenu.add(miExport);

        miDelete.setAccelerator(Event_Type.DELETE.getKeyStroke());
        miDelete.setText("Delete");
        miDelete.setEnabled(false);
        recipeMenu.add(miDelete);
        recipeMenu.add(jSeparator2);

        miPrint.setAccelerator(Event_Type.PRINT.getKeyStroke());
        miPrint.setMnemonic('P');
        miPrint.setText("Print...");
        recipeMenu.add(miPrint);

        miPrefs.setAccelerator(Event_Type.SHOW_PREFS.getKeyStroke());
        miPrefs.setText("Preferences");
        recipeMenu.add(miPrefs);

        miExit.setAccelerator(Event_Type.EXIT.getKeyStroke());
        miExit.setText("Exit");
        recipeMenu.add(miExit);

        jMenuBar2.add(recipeMenu);

        jMenu1.setMnemonic('E');
        jMenu1.setText("Edit");

        miCut.setText("Cut");
        jMenu1.add(miCut);
        ((AbstractTextAction)miCut.getAction()).addField(jTextField1);

        jMenu1.add(miCopy);
        jMenu1.add(miPaste);
        jMenu1.add(miSelAll);

        jMenu2.setText("Format");
        jMenu2.setEnabled(false);
        jMenu1.add(jMenu2);
        jMenu1.add(jSeparator1);

        jMenuItem1.setAccelerator(Event_Type.SEARCH.getKeyStroke());
        jMenuItem1.setText("Find...");
        jMenu1.add(jMenuItem1);

        jMenuBar2.add(jMenu1);

        windowMenu.setText("Window");

        jMenuItem7.setAccelerator(Event_Type.MINIMIZE.getKeyStroke());
        jMenuItem7.setText("Minimize");
        windowMenu.add(jMenuItem7);

        jMenuItem8.setText("Zoom");
        windowMenu.add(jMenuItem8);
        windowMenu.add(jSeparator3);

        jCheckBoxMenuItem1.setText("Unit Converter");
        windowMenu.add(jCheckBoxMenuItem1);

        jMenuBar2.add(windowMenu);

        helpMenu.setMnemonic('H');
        helpMenu.setText("Help");

        miHelp.setText("On The Web");
        helpMenu.add(miHelp);

        miAbout.setAccelerator(Event_Type.HELP.getKeyStroke());
        miAbout.setText("About");
        helpMenu.add(miAbout);

        jMenuBar2.add(helpMenu);

        setJMenuBar(jMenuBar2);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private recipejar.ui.AlphaTab alphaTab1;
    private javax.swing.JMenu helpMenu;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItem1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JPopupMenu.Separator jSeparator3;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JMenuItem miAbout;
    private javax.swing.JMenuItem miClip;
    private javax.swing.JMenuItem miCopy;
    private javax.swing.JMenuItem miCut;
    private javax.swing.JMenuItem miDelete;
    private javax.swing.JMenuItem miDuplicate;
    private javax.swing.JMenuItem miExit;
    private javax.swing.JMenuItem miExport;
    private javax.swing.JMenuItem miHelp;
    private javax.swing.JMenuItem miImport;
    private javax.swing.JMenuItem miNew;
    private javax.swing.JMenuItem miOpen;
    private javax.swing.JMenuItem miPaste;
    private javax.swing.JMenuItem miPrefs;
    private javax.swing.JMenuItem miPrint;
    private javax.swing.JMenuItem miSave;
    private javax.swing.JMenuItem miSelAll;
    private javax.swing.JMenu recipeMenu;
    private recipejar.ui.rjTextPane rjTextPane1;
    private javax.swing.JMenu windowMenu;
    // End of variables declaration//GEN-END:variables


}
