package source;

import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;

public class Dynamic_Fib_Big {

	public static BigInteger dynamic_fib(int n) {
		
		BigInteger[] fib_nums = new BigInteger[n+1];

		fib_nums[0] = BigInteger.ZERO;
		fib_nums[1] = BigInteger.ONE;
		
		for (int i = 2; i <= n; i++) {
			fib_nums[i] = fib_nums[i-1].add(fib_nums[i-2]);
		}
		
		return fib_nums[n];
	}

	public static void main(String[] args) {
		
		BigInteger n = BigInteger.valueOf(150000);
		long elapsedTime;
		long startTime;
		long theoryTime;
		float c;
		BigInteger fibN;
		
		try {
			FileWriter writer = new FileWriter("results_dynamic_big.csv");
			
			writer.append("n,value,exp. time,thr. time,c\n");
			
			for (int i = 3000; i <= n.intValue(); i = i + 3000){
				
				System.out.println("Caculating Fib " + i + "\n");
				
				writer.append(Integer.toString(i) + ",");
				
				startTime = System.currentTimeMillis();
				fibN = dynamic_fib(i);
				elapsedTime = System.currentTimeMillis() - startTime;
				
				theoryTime = i;
				c = (float)elapsedTime/(float)theoryTime;
				
				writer.append(fibN.toString() + "," + 
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
