package recipejar.data;

public class Macro {

   private String name;
   private char mnemonic;
   private char accelerator;
   private String maskAsText;
   private String text;

   public char getAccelerator() {
      return accelerator;
   }

   public String getMaskAsText() {
      return maskAsText;
   }

   public char getMnemonic() {
      return mnemonic;
   }

   public String getName() {
      return name;
   }

   public String getText() {
      return text;
   }

   public Macro(String[] line) {
      name = line[0].trim();
      if (line[1].trim().isEmpty()) {
         mnemonic = name.toUpperCase().charAt(0);
      } else {
         mnemonic = line[1].trim().toUpperCase().charAt(0);
      }
      if (line[2].trim().isEmpty()) {
         accelerator = 0;
      } else {
         accelerator = line[2].trim().toUpperCase().charAt(0);
      }
      maskAsText = line[3].trim();
      text = line[4];
   }

   @Override
   public String toString(){
      return name + ", " + mnemonic + ", " + accelerator + ", " + maskAsText + ", " + text;
   }
}
