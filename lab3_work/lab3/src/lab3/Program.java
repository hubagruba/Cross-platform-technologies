package lab3;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class Program {
	public static void main (String args[ ]) throws IOException
	{
		int i;
		FileInputStream fin;
		
                ArrayList<Integer> list = new ArrayList<Integer>(); 
		try {
			try {
				fin = new FileInputStream(args[0]);
			} catch (FileNotFoundException e) {
				System.out.println ("Input file not found");
				return;
			}
			
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println ("Usage: CopyFile original target");
			return;
		}
		try {
			do {
				i = fin.read();
				if(i !=- 1)list.add(i);
                                
			} while (i != -1);
		} catch (IOException e) {
			System.out.println ("File error");
		}
		fin.close();
		
		try {
			System.out.println ("Ds="+DSP.Ds(list));
			System.out.println ("Es="+DSP.Es(list));
			System.out.println ("Ps="+DSP.Ps(list));
			System.out.println ("ms="+DSP.ms(list));        
			System.out.println ("ds="+DSP.ds(list));
		}catch (Exception e) {
			System.out.println ("Calculation error");
		}
		
	}
}