package singleton;

public class President {
	   /**
	    * The name of the President
	    */
	   private static String name = "NoName";
	   /**
	    * Term of President
	    */
	   private static int term = 0;

	   private static President ourInstance;

	   private President() {
	   }
	   
	   public static President getInstance() {
	      if(ourInstance == null){
	         ourInstance = new President();
	      }
	      return ourInstance;
	   }

	   public static void setName(String name) {
	      President.name = name;
	   }

	   public static String getName() {
	      return "The name of the President: " + name;
	   }

	   public static void setTerm(int term) {
	      if(term>0){
	         President.term = term;
	      }
	   }

	   public static String getTerm() {
	      return "Term of President: " + String.valueOf(term);
	   }
	}