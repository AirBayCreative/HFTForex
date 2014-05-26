package com.forec.realtime;

import com.forec.data.DataProvider;
import com.forec.data.DataServiceSettings;



/**
 * 
 * @author Ettore Majorana
 *
 * Entry point class
 */
public class Main {
	
	public static void main(String[] args) throws Exception {
		AlgorithmSettings algoSetting = AlgorithmSettings.fromPropertiesFile(args[0]);
		DataServiceSettings dataSettings = DataServiceSettings.fromPropertiesFile(args[1]);
		DataProvider dataProvider = null;
		RequestCommunicator reqComm = new RequestCommunicator(dataSettings);
		AlgorithmWorker worker = new AlgorithmWorker(reqComm);
		Algorithm algo = new Algorithm(algoSetting, worker, dataProvider);
		//read data service settings
		//fetch data provider from somewhere
		//create new algo worker
		//create new algorithm
		
	}
}
