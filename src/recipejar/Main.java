/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package recipejar;

import recipejar.ui.UserInterface;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Date;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import recipejar.data.Data;
import recipejar.data.Prefs;
import recipejar.event.DefaultActionClearingHouse;

/**
 *
 * @author james
 */
public class Main {
    private static String[] commandLine;
    
    /**
     * @param args the command line arguments
     */
    public static void main(final String args[]) {
        if (!(args == null || args.length == 0) && args[0].equalsIgnoreCase("-h")) {
            printCommandLineHelp();
            System.exit(0);
        }
        commandLine = args;
        java.awt.EventQueue.invokeLater(GUIrunner);
    }

    private static void printCommandLineHelp() {
        System.out.println("Usage: RecipeJar [Options] [Argument]");
        System.out.println("   If no Option is specified, will attempt to start a new recipe with");
        System.out.println("   the text of the given argument.  ");
        System.out.println("   Options: \"-f\": Attempts to start a new recipe with a given file.");
        System.out.println("            \"-h\": Shows this help message");
        System.out.println("            \"-c\": Attempts to start a new recipe with the clipboard contents.");
    }

    private static Runnable GUIrunner = new Runnable() {
        @Override
        public void run() {
        //Check to see if this is first run
        if (!Prefs.programNodeExists()) {
            //First Run
            JOptionPane.showInputDialog("Either this is the first time I have been on this computer, or I have forgotten where I put everything. \nPlease input the directory where I should put stuff, or just leave it default.",
                    Prefs.getDefaultWorkingPath());
            Prefs.unpackToRegistry();
            unpack(Prefs.DIR_PROGRAM.toString());

            //TODO provide a progress thingy for this part
        }
        setupErrorStream(Prefs.DIR_RESOURCE.toString());
        
        //////Initialize data sources first to make sure they are availiable
        Data d = new Data();
        d.setCommandLine(commandLine);
        
        
        UserInterface u = new UserInterface();
        DefaultActionClearingHouse a = new DefaultActionClearingHouse();
        a.setData(d);
        u.addListener(a);
        
        u.getFrame().setVisible(true);
            
            /*Thread t = new Thread() {

             @Override
             public void run() {
             //TODO theFrame.index.initialize();
             }
             };
             t.setDaemon(true);
             t.start();*/
            System.gc();
        }
    };

   /**
    * Redirects stderr to a file for debugging purposes.
    * @param workingPath
    */
   public static void setupErrorStream(String path) {
      File errorFile = new File(path + "errorLog.txt");
      //Error log gets reset every 100kb, so that it doesn't eat up to much memory.
      //It prints the date and time every time the program starts up, remember.
      if (errorFile.exists() && errorFile.length() >= 100000) {
         errorFile.deleteOnExit();
      }
      PrintStream errorlog = null;
      try {
         errorlog = new PrintStream(new FileOutputStream(errorFile, true));
         errorlog.print(System.getProperty("line.separator")+System.getProperty("line.separator")+ new Date(System.currentTimeMillis()).toString() + System.getProperty("line.separator"));
         InputStream inAbout = ClassLoader.getSystemResourceAsStream("about.txt");
         int c = inAbout.read();
         while (c != System.getProperty("line.separator").charAt(0)) {
            errorlog.print((char)c);
            c = inAbout.read();
         }
         System.setErr(errorlog);
      } catch (IOException ex) {
          //TODO Error log not initialized.
      }
   }

   /**
    * Checks to make sure the program resources have been installed, and
    * if they haven't then it installs them.
    * @param workingPath
    */
   public static void unpack(String workingPath) {
      try {
         File p = new File(workingPath);
         if (!p.exists()) {
            p.mkdir();
         }
         InputStream in = ClassLoader.getSystemResourceAsStream("filetree.txt");
         String s = new String();
         int c = in.read();
         while (c != -1) {
            s = s + (char) c;
            c = in.read();
         }
         if (!s.isEmpty()) {
            String[] files = s.split("\n");
            for (int i = 0; i < files.length; i++) {
               File test = new File(p.getPath() + File.separator+ files[i].trim());
               if (!test.exists()) {
                  extractFile(test);
               }
            }
         }
      } catch (IOException ex) {
         JOptionPane.showMessageDialog(null, "Unpacking Failed. IOE");
         System.exit(1);
      } catch (NullPointerException npe) {
         JOptionPane.showMessageDialog(null, "Unpacking Failed. NPE");
         System.exit(1);
      } finally {
         System.gc();
      }
   }

   /**
    * Extracts files from the system resources
    * @param test
    * @see #unpack(java.lang.String)
    */
   public static void extractFile(File test) {
      //Directories
      if (test.getName().indexOf(".") == -1) {
         test.mkdir();
      } else {
         //Files
         try {
            InputStream in = ClassLoader.getSystemResourceAsStream(test.getName());
            if (in == null) {
               throw new NullPointerException("Failed to access: \"" + test.getName() + "\"");
            }
            if (test.createNewFile()) {
               //emphasis on the successfully.
               String format;
               if ((format = getImageType(test)) != null) {//If the file is an image.
                  ImageIO.write(ImageIO.read(in), format, test);
               } else {//Text files
                  OutputStream out = new FileOutputStream(test);
                  int c = in.read();
                  while (c != -1) {
                     out.write(c);
                     c = in.read();
                  }
                  out.close();
               }
            }
         } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Fatal Error.  See \"errorLog.txt\" for more information.");
            System.exit(1);
         } catch (NullPointerException npe) {
            JOptionPane.showMessageDialog(null, "Fatal Error.  See \"errorLog.txt\" for more information.");
            System.exit(1);
         }
      }
   }

   /**
    * Called by extractFile
    * @param test
    * @return
    * @see #extractFile(java.io.File)
    */
   private static String getImageType(File test) {
      String[] suffixes = ImageIO.getWriterFormatNames();
      int x = test.getName().indexOf(".");
      if (x == -1) {
         return null;//file does not have an extension
      }
      for (int i = 0; i < suffixes.length; i++) {
         if (test.getName().substring(x + 1).equals(suffixes[i])) {
            return suffixes[i];
         }
      }
      return null;
   }

}
