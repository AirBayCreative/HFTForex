package com.forec.realtime;

import com.forec.realtime.unsafe.ForexDataHandler;

public class AlgorithmWorker implements RequestHandler{

	public AlgorithmWorker(RequestCommunicator reqComm) {
		super();
		this.reqComm = reqComm;
	}

	private final RequestCommunicator reqComm;
	
	public void work(ForexDataHandler dataHandler) {
		//TODO
	}

	public void init() {
		//TODO
	}

	@Override
	public void buy() {
		// TODO
	}

	@Override
	public void sell() {
		// TODO
	}

	@Override
	public void close() {
		// TODO
		
	}
	
	

}
