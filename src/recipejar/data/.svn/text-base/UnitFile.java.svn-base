/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package recipejar.data;

import recipejar.data.Unit;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author james
 */
public class UnitFile extends AbstractCharDelineatedFile{

   private static ArrayList<Unit> units;

   public UnitFile(String s){
      super(s, ",");
      units = new ArrayList<Unit>();
      Reader in = null;
      try {
         in = new FileReader(this);
      } catch (FileNotFoundException ex) {
         in = new StringReader("Cups,Cup\nTbsp,Tbsps\ntsp,tsps\ncount\n");
      }
      try {
         int c = in.read();
         while (c != -1) {
            if (c == ';') {
               while (c != '\n' && c != -1) {
                  c = in.read();
               }
            }
            StringWriter u = new StringWriter();
            //the \r is important!
            while (c != '\n' && c != '\r' && c != -1) {
               u.write(c);
               c = in.read();
            }
            if (!u.toString().isEmpty()) {
               units.add(new Unit(u.toString().trim()));
            }
            c = in.read();
         }
         units.add(new Unit());
      } catch (IOException ex) {
         units.add(new Unit());
      }
      Collections.sort(units);
   }

   /**
    * Adds the new unit to the array, and attempts to write the file to disk.
    * @param ptext
    * @param stext
    * @return
    */
   public Unit addUnit(String ptext, String stext) {
      Unit newUnit;
      units.add(newUnit = new Unit(ptext, stext));
      Collections.sort(units);
      return newUnit;
   }

   public Unit getUnit(int i) {
      return units.get(i);
   }

   /**
    * Returns the unit in the list given by the parameter "s".  If it does not
    * exist null is returned.
    *
    * @param s The unit to search for, or create
    * @return The unit defined by s.
    */
   public Unit getUnit(String s) {
      for (int i = 0; i < units.size(); i++) {
         if (units.get(i).getPlural().toUpperCase().equals(s.toUpperCase()) || units.get(i).getSingular().toUpperCase().equals(s.toUpperCase())) {
            return units.get(i);
         }
      }
      return null;
   }

   public int getUnitCount() {
      return units.size();
   }

   public int indexOf(String value) {
      for (int i = 0; i < units.size(); i++) {
         if (units.get(i).toString().equals(value)) {
            return i;
         }
      }
      return -1;
   }

   @Override
   public void save() {
      FileWriter out = null;
      try {
         out = new FileWriter(this);
         for (int i = 0; i < units.size(); i++) {
            if (units.get(i).getPlural() != null && !units.get(i).getPlural().isEmpty()) {
               out.write(units.get(i).getPlural());
               if (units.get(i).getSingular() != null && !units.get(i).getSingular().isEmpty()) {
                  out.write("," + units.get(i).getSingular());
               }
               out.write("\n");
            }
         }
         out.close();
      } catch (IOException ex) {
         //do nothing
      }
   }

}
