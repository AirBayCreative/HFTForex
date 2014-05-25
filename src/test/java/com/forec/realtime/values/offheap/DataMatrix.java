package com.forec.realtime.values.offheap;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.forec.realtime.unsafe.ForexDataHandler;


public class DataMatrix {

	static ForexDataHandler handler;
	
	@BeforeClass
	public static void setupClass(){
		handler = new ForexDataHandler(3, "EURUSD");
	}
	
	@Before
	public void setup(){
		init();
	}
	
	private void init(){
		handler.initRecord(0,10,12,9,11,100,10);
		handler.initRecord(1,20,22,19,21,200,20);
		handler.initRecord(2,30,32,29,31,300,30);
	}
	
	@Test
	public void onInit() {
		assertEquals(10,handler.getOpen(0));
		assertEquals(22,handler.getHigh(1));
		assertEquals(29,handler.getLow(2));
		assertEquals(11,handler.getClose(0));
		assertEquals(300,handler.getVolume(2));
		assertEquals(20,handler.getTime(1));
	}
	
	@Test
	public void onUpdateWithinTheMinute() {
		handler.update(32, 35);
		assertEquals(32, handler.getLastClose());
	}
	
	@Test
	public void onUpdateWhenMinuteChange() {
		System.out.println("/--------- TEST START ------------/");
		long nextTimestamp = handler.getLastTimestamp() + ForexDataHandler.ONE_MINUTE;
		handler.update(32, nextTimestamp);
		assertEquals(32, handler.getLastClose());
		assertEquals(nextTimestamp, handler.getLastTimestamp());
		assertEquals(31, handler.getOpen(2));
		assertEquals(31, handler.getLow(2));
		assertEquals(32, handler.getHigh(2));
	}
	
}
