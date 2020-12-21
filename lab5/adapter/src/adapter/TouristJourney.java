package adapter;

public class TouristJourney implements Journey {
	   JourneyAdapter journeyAdapter;
	 
	   public void travel(String typeJourney, int pathLength) {
	      if(!typeJourney.equals("car")&&!typeJourney.equals("boat")){
	        System.out.println("("+typeJourney+") This type of travel is not available.");
	      } else{
	        journeyAdapter = new JourneyAdapter(typeJourney);
	        journeyAdapter.travel(typeJourney, pathLength);
	      }
	   }
	}
