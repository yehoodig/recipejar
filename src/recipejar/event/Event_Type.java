package recipejar.event;

import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import javax.swing.KeyStroke;
import recipejar.Util;

public enum Event_Type {

   //Recipe Menu Items
   NEW("New...", KeyStroke.getKeyStroke(KeyEvent.VK_N, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask())),
   EDIT_START("Open...", KeyStroke.getKeyStroke(KeyEvent.VK_O, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask())),
   EDIT_CANCEL("Close", KeyStroke.getKeyStroke(KeyEvent.VK_O, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask())),
   SAVE("Save", KeyStroke.getKeyStroke(KeyEvent.VK_S, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask())),
   DUPLICATE("Duplicate...", KeyStroke.getKeyStroke(KeyEvent.VK_D, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask())),
   IMPORT_FILE,
   IMPORT_CLB,
   EXPORT("Export..."),
   DELETE,
   PRINT("Print...", KeyStroke.getKeyStroke(KeyEvent.VK_P, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask())),
   SHOW_PREFS("Preferences"),
   EXIT, 
   //Edit Menu
   CUT("Cut", KeyStroke.getKeyStroke(KeyEvent.VK_X, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask())), 
   COPY("Copy", KeyStroke.getKeyStroke(KeyEvent.VK_C, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask())),
   PASTE("Paste", KeyStroke.getKeyStroke(KeyEvent.VK_V, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask())),
   SELECT_ALL("Select All", KeyStroke.getKeyStroke(KeyEvent.VK_A, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask())),
   //Tools Menu
   SHOW_CONV, 
   MINIMIZE("Minimize", KeyStroke.getKeyStroke(KeyEvent.VK_M, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask())),
   ZOOM,
   ABOUT,
   HELP,
   POPUP,
   FRAME_ICONIFY,
   FRAME_DEICON,
   FRAME_ACTIVATE,
   FRAME_OPEN,
   FRAME_CLOSE,
   SEARCH("Find...", KeyStroke.getKeyStroke(KeyEvent.VK_F, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask())),
   OPEN;

   private String command = null;
   private KeyStroke accel = null;

   private Event_Type(String s, KeyStroke k) {
      command = s;
      if (Util.isAllowableAccelerator(k)) {
         accel = k;
      }
   }

   private Event_Type(String s) {
      command = s;
   }

   private Event_Type() {
   }

   public String getCMD() {
      return command;
   }

   public KeyStroke getKeyStroke() {
      return accel;
   }
}
