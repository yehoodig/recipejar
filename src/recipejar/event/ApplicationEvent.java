/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package recipejar.event;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import javax.swing.event.DocumentEvent;
import javax.swing.event.HyperlinkEvent;
import javax.swing.text.JTextComponent;

/**
 *
 * @author james
 */
public class ApplicationEvent {
   private ActionEvent actE = null;
   private DocumentEvent docE = null;
   private WindowEvent winE = null;
   private MouseEvent mousE = null;
   private HyperlinkEvent hyperlinkE = null;
   private JTextComponent textComponent = null;

   public JTextComponent getTextComponent() {
      return textComponent;
   }

   public HyperlinkEvent getHyperlinkE() {
      return hyperlinkE;
   }

   public ActionEvent getActE() {
      return actE;
   }

   public DocumentEvent getDocE() {
      return docE;
   }

   public MouseEvent getMousE() {
      return mousE;
   }

   public Event_Type getType() {
      return type;
   }

   public WindowEvent getWinE() {
      return winE;
   }

   private Event_Type type = null;

   public ApplicationEvent(Event_Type e, ActionEvent evt){
      actE = evt;
      type = e;
   }

   public ApplicationEvent(Event_Type e, DocumentEvent de){
      docE = de;
      type = e;
   }

   public ApplicationEvent(Event_Type e, WindowEvent we){
      winE = we;
      type = e;
   }

   public ApplicationEvent(Event_Type e, MouseEvent me){
      mousE = me;
      type = e;
   }

   public ApplicationEvent(Event_Type e, HyperlinkEvent he){
      hyperlinkE = he;
      type = e;
   }

   public ApplicationEvent(Event_Type e, JTextComponent t){
      type = e;
      textComponent = t;
   }
}
