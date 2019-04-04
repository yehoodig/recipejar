/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package recipejar.event;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import recipejar.ui.AlphaTab;

/**
 *
 * @author james
 */
public class IndexKeyListener extends KeyAdapter {

   private final AlphaTab teller;

   public IndexKeyListener(AlphaTab i) {
      teller = i;
   }

   @Override
   public void keyPressed(KeyEvent e) {
      //The up and down functions are really only needed if the event comes from somewhere
      //other than the index tabbed pane.
      if (e.getComponent() != teller && e.getKeyCode() == KeyEvent.VK_DOWN) {
         if (teller.getSelectedIndex() < 0 || teller.getSelectedIndex() == teller.getTabCount() - 1) {
            teller.setSelectedIndex(0);
         } else {
            teller.setSelectedIndex(teller.getSelectedIndex() + 1);
         }
      } else if (e.getComponent() != teller && e.getKeyCode() == KeyEvent.VK_UP) {
         if (teller.getSelectedIndex() <= 0) {
            teller.setSelectedIndex(teller.getTabCount() - 1);
         } else {
            teller.setSelectedIndex(teller.getSelectedIndex() - 1);
         }
      } else //Basically, this is a list of keys to ignore.
      if (/*Ignore keystrokes with modifiers*/!e.isAltDown()
              && !e.isControlDown()
              && !e.isMetaDown()
              && !e.isShiftDown()
              && /*Ignore non visible*/ !(e.getKeyCode() == KeyEvent.VK_DELETE)
              && !(e.getKeyCode() == KeyEvent.VK_BACK_SPACE)) {

         teller.setSelectedTab(e.getKeyChar());
      }
   }
}
