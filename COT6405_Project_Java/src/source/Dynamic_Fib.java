package source;

import java.io.FileWriter;
import java.io.IOException;

public class Dynamic_Fib {

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
		
		long n = 100;
		long elapsedTime;
		long startTime;
		long fibN;
		
		try {
			FileWriter writer = new FileWriter("results_dynamic.csv");
			
			writer.append("n, val, time\n");
			
			for (int i = 1; i <= n; i++){
				
				writer.append(Integer.toString(i) + ",");
				
				startTime = System.currentTimeMillis();
				fibN = dynamic_fib(i);
				elapsedTime = System.currentTimeMillis() - startTime;
				
				System.out.println("Fibonacci " + i + ": " + fibN);
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
