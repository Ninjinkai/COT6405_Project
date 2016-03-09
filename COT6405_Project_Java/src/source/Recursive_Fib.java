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
		long fibN;
		
		try {
			FileWriter writer = new FileWriter("results_recursive.csv");
			
			writer.append("n, val, time\n");
			
			for (int i = 1; i <= n; i++){
				
				writer.append(Integer.toString(i) + ",");
				
				startTime = System.currentTimeMillis();
				fibN = recursive_fib(i);
				elapsedTime = System.currentTimeMillis() - startTime;
				
				System.out.println("Fibonacci " + i + ": " + fibN);
				System.out.println("time: " + elapsedTime);
				writer.append(Long.toString(fibN) + "," + Long.toString(elapsedTime) + "\n");
				
			}
			
			writer.flush();
			writer.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
