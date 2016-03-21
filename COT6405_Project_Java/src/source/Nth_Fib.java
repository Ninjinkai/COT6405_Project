package source;

import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;

public class Nth_Fib {

//	Recursive implementation of the algorithm.
	public static long recursive_fib(long n) {
		
//		Base case.  When n = 1, Fn = 1.  When n = 0, Fn = 0.
		if (n <= 1) {
			return n;
		}
		
//		Recursive call to find the previous two Fibonacci Numbers and sum them.
//		Returns Fn after reaching base cases.
		return (recursive_fib(n-1) + recursive_fib(n-2));
	}
	
//	Dynamic programming implementation of the algorithm.
	public static BigInteger dynamic_fib(long n) {
		
//		Create the array to hold previously calculated values of Fn.
		BigInteger[] fib_nums = new BigInteger[(int) (n+1)];

//		Initialize the first two values or the array and start the series.
		fib_nums[0] = BigInteger.ZERO;
		fib_nums[1] = BigInteger.ONE;
		
//		Iterate through the array, adding Fibonacci Numbers as they are calculated.
		for (int i = 2; i <= (int) n; i++) {
			fib_nums[i] = fib_nums[i-1].add(fib_nums[i-2]);
		}
//		Final result Fn is returned.
		return fib_nums[(int) n];
	}
	
//	Handles method calling and output.
	public static void run_algo(long n, String fileName, boolean isRecursive, int start, int step) {
		
//		Declare time keeping variables.
		long elapsedTime;
		long startTime;
		double theoryTime;
		
//		The hidden constant, c, the ratio of actual execution time to theoretical execution time.
		double c;
		
//		Declare variables to hold the results of the calculation.
		long RFibN;
		BigInteger DFibN;
		
//		Try block is required for FileWriter so that IOExceptions are caught.
		try {
			
//			Open the specified file for output.
			FileWriter writer = new FileWriter(fileName);
			
//			Write table headers.
			writer.append("n,value,exp. time,thr. time,c\n");
			
//			Although only the nth Fibonacci Number is needed, all preceding Fibonacci Numbers
//			are calculated.  This shows the growth of algorithm runtime as n increases.  For
//			tuning output, start and step variable allow ranges of Fn to be examined.
			for (int i = start; i <= n; i = i + step){
				
//				Console output to show current task.
				System.out.println("Caculating Fibonacci " + i + ".\n");
				
//				Add the current value of n to the results table.
				writer.append(Integer.toString(i) + ",");
				
//				Conditional to determine whether to run recursive or dynamic programming algorithm.
				if (isRecursive) {
					
//					Run the recursive algorithm and record its runtime.
					startTime = System.currentTimeMillis();
					RFibN = recursive_fib(i);
					elapsedTime = System.currentTimeMillis() - startTime;
					
//					The theoretical runtime is 2^n.  This estimates about 0.001ms to calculate F0 and F1.
					theoryTime = Math.pow(2, i)/1000;
					
//					Calculate c.
					c = (double)elapsedTime/theoryTime;
					
//					Write experimental results to the table.
					writer.append(Long.toString(RFibN) + "," + 
					Long.toString(elapsedTime) + "," + 
					Double.toString(theoryTime) + "," +
					Double.toString(c) + "," +
					"\n");
					
				}
				else {
					
//					Run the recursive algorithm and record its runtime.
					startTime = System.currentTimeMillis();
					DFibN = dynamic_fib(i);
					elapsedTime = System.currentTimeMillis() - startTime;
					
//					The theoretical runtime is n.  This estimates about 0.001ms to calculate F0 and F1.
					theoryTime = ((double) i)/1000;
					
//					Calculate c.
					c = (double)elapsedTime/theoryTime;
					
//					Write experimental results to the table.
					writer.append(DFibN.toString() + "," + 
					Long.toString(elapsedTime) + "," + 
					Double.toString(theoryTime) + "," +
					Double.toString(c) + "," +
					"\n");
				}

			}
			
//			Close the FileWriter.
			writer.flush();
			writer.close();
		
		} 
		
//		Catch exceptions when opening file.
		catch (IOException e) {
			e.printStackTrace();
		}

//		Console output that method is completed.
		System.out.println("Done.");
		
	}
	
	public static void main(String[] args) {
		
//		Comparison of algorithms on the same values of n, up to 50.  After this, the
//		recursive function takes minutes to find each number.
		run_algo(50, "rec_results.csv", true, 1, 1);
		run_algo(50, "dyn_results.csv", false, 1, 1);
		
//		To examine the asymptotic runtime of the dynamic programming algorithm, extremely
//		large values of n are needed.  However, memory limitations become a problem after
//		n = 180,000.  Steps are needed because the out file becomes unnecessarily long.
		run_algo(150000, "dyn_big_results.csv", false, 3000, 3000);

	}

}
