package recipejar.data;

import java.util.HashMap;
import java.util.Set;

/**
 *
 * @author james
 */
public class Unit implements Comparable<Unit> {

   private HashMap<String, String> conversionUnits;
   private boolean useSingular = false;
   private String singular = "";
   private String plural = "";

   public Unit() {
      plural = "";
      singular = "";
      conversionUnits = null;
   }

   public Unit(String p, String s) {
      plural = p;
      singular = s;
      conversionUnits = null;
   }

   public Unit(String combined) {
      String[] parts = combined.split(",");
      if (parts.length == 1) {
         plural = parts[0];
         singular = "";
      } else if (parts.length == 2) {
         plural = parts[0];
         singular = parts[1];
      } else if (parts.length == 3) {
         plural = parts[0];
         singular = parts[1];
         conversionUnits = new HashMap<String, String>();
         String[] convUnts;
         if (parts[2].contains("|")) {
            convUnts = parts[2].split("\\|");
         } else {
            convUnts = new String[1];
            convUnts[0] = parts[2];
         }
         for (int i = 0; i < convUnts.length; i++) {
            int endKey = convUnts[i].lastIndexOf("(");
            String key = convUnts[i].substring(0, endKey);
            String factor = convUnts[i].substring(endKey + 1, convUnts[i].length() - 1);
            conversionUnits.put(key, factor);
         }
      }
   }

   public String getPlural() {
      return plural;
   }

   public String getSingular() {
      return singular;
   }

   @Override
   public String toString() {
      if (useSingular) {
         return singular;
      } else {
         return plural;
      }
   }

   @Override
   public int compareTo(Unit o) {
      if (this.equals(o)) {
         return 0;
      } else if (plural.toUpperCase().equals(o.getPlural().toUpperCase())) {
         //
         return singular.toUpperCase().compareTo(o.getSingular().toUpperCase());
      } else {
         return plural.toUpperCase().compareTo(o.getPlural().toUpperCase());
      }
   }

   /**
    *
    * @param o
    * @return
    */
   public boolean equals(Unit o) {
      if (this.getClass().isInstance(o)) {
         //A Unit
         if (this.plural.equals(o.getPlural()) && this.singular.equals(o.getSingular())) {
            return true;
         } else {
            return false;
         }
      } else if (o.toString().equals(this.plural)) {
         //Something else, probably a string.
         return true;
      } else {
         return false;
      }
   }

   @Override
   public int hashCode() {
      int hash = 7;
      hash = 41 * hash + (this.singular != null ? this.singular.hashCode() : 0);
      hash = 41 * hash + (this.plural != null ? this.plural.hashCode() : 0);
      return hash;
   }

   public boolean isConvertable() {
      return (this.conversionUnits != null && !conversionUnits.isEmpty());
   }

   public String getConversionFactor(String s) {
      return conversionUnits.get(s);
   }

   public int getConversionUnitCount() {
      return this.conversionUnits.size();
   }

   public Set<String> getConversionUnitKeySet() {
      return this.conversionUnits.keySet();
   }

   public void setSingular(boolean b) {
      useSingular = b;
   }

}
