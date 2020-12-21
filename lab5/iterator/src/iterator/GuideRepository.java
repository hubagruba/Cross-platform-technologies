package iterator;

public class GuideRepository implements Container{
	   public String[] attractions = {"Attraction 1","Attraction 2", "Attraction 3"};

	  
	   public Iterator getIterator() {
	      return new GuideIterator();
	   }

	   class GuideIterator implements Iterator{
	      int index;

	      
	      public boolean hasNext() {
	         if(index<attractions.length){
	            return true;
	         }
	         return false;
	      }

	      
	      public Object next() {
	         if(hasNext()){
	            return attractions[index++];
	         }
	         return null;
	      }
	   }
	}
