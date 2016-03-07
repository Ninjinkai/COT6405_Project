package source;

import java.io.FileWriter;
import java.io.IOException;

public class Fibonacci_n {

	public static long recursive_fib(int n) {
		
		if (n <= 1) {
			return n;
		}
		
		return (recursive_fib(n-1) + recursive_fib(n-2));
	}
	
	public static long dynamic_fib(int n) {
		
		long[] fib_nums = new long[n+1];

		fib_nums[0] = 0;
		fib_nums[1] = 1;
		
		for (int i = 2; i <= n; i++) {
			fib_nums[i] = fib_nums[i-1] + fib_nums[i-2];
		}
		
		return fib_nums[n];
	}
	
	public static void main(String[] args) {
		
		int n = 50;
		long elapsedTime;
		long startTime;
		long fibN;
		
		try {
			FileWriter writer = new FileWriter("results.csv");
			
			writer.append("n, val rec, time rec, val dyn, time dyn\n");
			
			for (int i = 1; i <= n; i++){
				
				writer.append(Integer.toString(i) + ",");
				
				startTime = System.currentTimeMillis();
				fibN = recursive_fib(i);
				elapsedTime = System.currentTimeMillis() - startTime;
				
				System.out.println("recursive Fibonacci " + i + ": " + fibN);
				System.out.println("time: " + elapsedTime);
				writer.append(Long.toString(fibN) + "," + Long.toString(elapsedTime) + ",");
				
				startTime = System.currentTimeMillis();
				fibN = dynamic_fib(i);
				elapsedTime = System.currentTimeMillis() - startTime;
				
				System.out.println("dynamic Fibonacci " + i + ": " + dynamic_fib(i));
				System.out.println("time: " + elapsedTime + "\n");
				writer.append(Long.toString(fibN) + "," + Long.toString(elapsedTime) + "\n");
			}
			
			writer.flush();
			writer.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}