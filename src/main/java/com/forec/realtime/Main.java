package com.forec.realtime;

import java.util.stream.DoubleStream;
import java.util.stream.IntStream;


/**
 * 
 * @author Ettore Majorana
 *
 * Entry point class
 */
public class Main {
	
	public static void main(String[] args) {
		int sum = IntStream.range(0,10).filter(n -> n > 4).map(n -> n*2).reduce(0,(a,b) -> a + b);
		System.out.println(sum);
		
	}
}
