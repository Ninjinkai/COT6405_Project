package source;

import java.io.FileWriter;
import java.io.IOException;

public class Recursive_Fib {

	public static long recursive_fib(long n) {
		
		if (n <= 1) {
			return n;
		}
		
		return (recursive_fib(n-1) + recursive_fib(n-2));
	}
	
	public static void main(String[] args) {
		
		long n = 50;
		long elapsedTime;
		long startTime;
		long theoryTime;
		float c;
		long fibN;
		
		try {
			FileWriter writer = new FileWriter("results_recursive.csv");
			
			writer.append("n,value,exp. time,thr. time,c\n");
			
			for (int i = 1; i <= n; i++){
				
				System.out.println("Caculating Fib " + i + "\n");
				
				writer.append(Integer.toString(i) + ",");
				
				startTime = System.currentTimeMillis();
				fibN = recursive_fib(i);
				elapsedTime = System.currentTimeMillis() - startTime;
				
				theoryTime = (long) Math.pow(2, i);
				c = (float)elapsedTime/(float)theoryTime;
				
				writer.append(Long.toString(fibN) + "," + 
				Long.toString(elapsedTime) + "," + 
				Long.toString(theoryTime) + "," +
				Float.toString(c) + "," +
				"\n");
				
			}
			
			writer.flush();
			writer.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("Done.");
		
	}

}
