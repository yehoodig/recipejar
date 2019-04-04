
/*
 * TODO Make the stuff in here non-static.
 */
package recipejar.ui;

//~--- non-JDK imports --------------------------------------------------------
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import recipejar.event.DefaultActionClearingHouse;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import recipejar.Main;
import recipejar.Util;
import recipejar.data.IndexStyle;
import recipejar.data.Prefs;
import recipejar.event.Event_Type;

/**
 *
 * @author james
 */
public class UserInterface {

    private static EditorPanel editPanel = new EditorPanel();
    private static JFileChooser fc;
    private static RecipeJarFrame frame = new RecipeJarFrame();
    private static PreferencesDialog prefDiag;
    private static UnitConverterDialog unitDiag;

    public UserInterface() {
        setLookAndFeel(Prefs.LAF.toString());
        frame.getAlphaTab().setStyle(IndexStyle.getReader());
        frame.getAlphaTab().setSelectedTab(Prefs.TAB.toString().charAt(0));
        frame.getRjTextPane1().setStyle(IndexStyle.getSource());
    }

    /**
     * Adds DefaultActionClearingHouse members as listeners on the actionable
     * visible UI components.
     *
     * 
     *
     */
    public void addListener(DefaultActionClearingHouse a) {
        a.linkUserInterface(this);
        frame.addWindowListener(a.getWindowListener());
        for (int i = 0; i < Event_Type.values().length; i++) {
            frame.addActionListener(Event_Type.values()[i], a.getActionListener(Event_Type.values()[i]));
        }
    }

    public static rjTextPane getPresentationPanel() {
        return frame.getRjTextPane1();
    }

    public static EditorPanel getEditPanel() {
        return editPanel;
    }

    public RecipeJarFrame getFrame() {
        return frame;
    }

    public static JFileChooser getFileChooser() {
        return fc;
    }

    public static UnitConverterDialog getUnitConverterDialog() {
        if (unitDiag == null) {
            unitDiag = new UnitConverterDialog(frame, false);
        }
        return unitDiag;
    }

    public static String getTitleText() {
        return editPanel.getTitleField().getText();
    }

    public static void setTitleText(String s) {
        editPanel.getTitleField().setText(s);
    }

    public static PreferencesDialog getPrefsDialog() {
        if (prefDiag == null) {
            prefDiag = new PreferencesDialog(frame, true);
            FilePrefPanel.setFileChooser(fc);
        }
        return prefDiag;
    }

    /**
     *
     * @param lafName
     */
    public static void setLookAndFeel(String lafName) {
        if (Util.isOS("mac os x")) {
            System.setProperty("apple.laf.useScreenMenuBar", "true");
            System.setProperty("apple.awt.brushMetalLook", "true");
        }
        JFrame.setDefaultLookAndFeelDecorated(true);
        JDialog.setDefaultLookAndFeelDecorated(true);
        try {
            UIManager.setLookAndFeel(lafName);
        } catch (UnsupportedLookAndFeelException ex) {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (ClassNotFoundException ex1) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex1);
            } catch (InstantiationException ex1) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex1);
            } catch (IllegalAccessException ex1) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex1);
            } catch (UnsupportedLookAndFeelException ex1) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } catch (InstantiationException ex) {
            JOptionPane.showMessageDialog(null, "Fatal Error.  See \"errorLog.txt\" for more information.");
            System.exit(1);
        } catch (IllegalAccessException ex) {
            JOptionPane.showMessageDialog(null, "Fatal Error.  See \"errorLog.txt\" for more information.");
            System.exit(1);
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Fatal Error.  See \"errorLog.txt\" for more information.");
            System.exit(1);
        }
    }
}
