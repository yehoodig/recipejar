/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package recipejar.data;

import recipejar.data.Macro;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author james
 */
public class MacroFile extends AbstractCharDelineatedFile{
   private ArrayList<Macro> ValidMacros;
   public MacroFile(String s){
      super(s, ",");
      ValidMacros = new ArrayList<Macro>();
      for(int i=0; i < super.getDataLength(); i++){
         if(this.getLine(i).length == 5){
            ValidMacros.add(new Macro(this.getLine(i)));
         }
      }
   }
   @Override
   public int getDataLength(){
      return ValidMacros.size();
   }

   
   public Macro getMacro(int n){
      return ValidMacros.get(n);
   }

   @Override
   public void save() {
      FileWriter out = null;
      try {
         out = new FileWriter(this);
         for (int i = 0; i < ValidMacros.size(); i++) {
            out.write(ValidMacros.get(i).toString());
            out.write("\n");
         }
         out.close();
      } catch (IOException ex) {
         JOptionPane.showMessageDialog(null, "Fatal Error.  See \"errorLog.txt\" for more information.");
         System.exit(1);
      } finally {
         try {
            out.close();
         } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Fatal Error.  See \"errorLog.txt\" for more information.");
            System.exit(1);
         }
      }

   }

}
