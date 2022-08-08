/**
 * 
 */
package com.doil.test;

import java.util.Random;

import com.doil.main.GetExecuteTime;
import com.doil.main.GetExecuteTime.DataWrapper;

/**
 * @author doil
 *
 */
public class Sample {
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		System.out.println("*------------------------------------------*");
		System.out.println("\r\n" + 
				" _   _  _____  _      _      _____    \r\n" + 
				"| | | ||  ___|| |    | |    |  _  |   \r\n" + 
				"| |_| || |__  | |    | |    | | | |   \r\n" + 
				"|  _  ||  __| | |    | |    | | | |   \r\n" + 
				"| | | || |___ | |____| |____\\ \\_/ / _ \r\n" + 
				"\\_| |_/\\____/ \\_____/\\_____/ \\___/ ( )\r\n" + 
				"                                   |/ \r\n" + 
				"");
		System.out.println("\t\tthis is the TEST function");
		System.out.println("*------------------------------------------*");
		
		
		GetExecuteTime dGet = new GetExecuteTime();
		DataWrapper result = null;
		
		dGet.getExecutionTime("void_withParam" , new Object[] { 133 } , true);
		dGet.makeHardLine();
		
		sayMethodName("test");
		result = dGet.getJustExecutionTime("test", null);
		System.out.printf( "time : %.3f sec\n" , result.getTime() * 0.001);
		dGet.makeHardLine();
		
		
		Integer n = 10;
		sayMethodName("return_test");
		result = dGet.getJustExecutionTime("return_test", new Object[] { n });
		n = (Integer) result.getData();
		
		System.out.printf( "result : %d | time : %.3f sec\n", n , result.getTime() * 0.001);
		
		System.out.println("\n------------------------------------------------------");
		System.out.println("sample code is");
		System.out.println(
				"      ::::::::::       ::::    :::       ::::::::: \r\n" + 
				"     :+:              :+:+:   :+:       :+:    :+: \r\n" + 
				"    +:+              :+:+:+  +:+       +:+    +:+  \r\n" + 
				"   +#++:++#         +#+ +:+ +#+       +#+    +:+   \r\n" + 
				"  +#+              +#+  +#+#+#       +#+    +#+    \r\n" + 
				" #+#              #+#   #+#+#       #+#    #+#     \r\n" + 
				"##########       ###    ####       #########       \r\n" );
		System.out.println("\t\t\t\t\t\tbye!");
		System.out.println("------------------------------------------------------");
		
		
		
	}
	
	private static void sayMethodName(String methodNm) {
		 
		System.out.printf("[Method Name : %s]\n" , methodNm );
	}

	public static void test() {
		System.out.println("* This method is just test method. There is nothing to do");
	}
	
	public static void void_withParam(int n) {
		System.out.println("* Your parameter is " + n);
	}
	
	public static int return_test(int n) throws InterruptedException {
		Random rand = new Random();
		long waitTime_rand = (long) (rand.nextDouble() * 5000);
		System.out.println("* This method will be return the 10 times value of the parameter and wait for " + waitTime_rand + " ms");
		
		
		// wait
		Thread.sleep(waitTime_rand);
		
		return n * 10;
	}
}
