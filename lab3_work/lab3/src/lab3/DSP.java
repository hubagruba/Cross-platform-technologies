package lab3;


import java.util.ArrayList;
import java.util.Collections;
public class DSP {
	public  static int Ds( ArrayList<Integer> list){	
		return Collections.max(list)-Collections.min(list);
    } 
	    
    public static long  Es(ArrayList<Integer> list){
    	int Es=0;
    	for (int i = 0; i < list.size(); i++) {
			Es+=list.get(i)*list.get(i);
		}
    	return Es;
    }
    
    public static long Ps(ArrayList<Integer> list){
    	return Es(list)*1/list.size();
    }
    
    
    public static int ms(ArrayList<Integer> list){
    
    	int ms=0;
    	
    	for (int i = 0; i < list.size(); i++) {
			ms+=(list.get(i));
		}
    	
    	return ms/list.size();
    }
    
    public static int ds(ArrayList<Integer> list){
    	int ds=0;
    	int ms= ms(list);
    	
    	for (int i = 0; i < list.size(); i++) {
			ds+=(list.get(i)-ms)*(list.get(i)-ms);
		}
    	return  ds/list.size();
    }
}

