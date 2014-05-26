package com.forec.realtime;

import java.util.concurrent.atomic.AtomicBoolean;

import com.forec.data.DataListener;
import com.forec.data.DataProvider;
import com.forec.realtime.unsafe.ForexDataHandler;

public class Algorithm implements DataListener{

	private final ForexDataHandler dataHandler;
	private final DataProvider dataProvider;
	private final AlgorithmSettings settings;
	private final AlgorithmWorker worker;
	private final AtomicBoolean initialized;
	
	public Algorithm(AlgorithmSettings settings, AlgorithmWorker worker, DataProvider provider){
		this.dataHandler = new ForexDataHandler(settings.getDataLength(), settings.getCurrency());
		this.dataProvider = provider;
		this.settings = settings;
		this.worker = worker;
		this.initialized = new AtomicBoolean(false);
	}
	
	public void init(){
		register();
		worker.init();
		initialized.set(true);;
	}
	
	@Override
	public void register() {
		dataProvider.register(this,settings.getCurrency());
	}

	@Override
	public void onNewValue(long value, long timestamp) {
		if(initialized.get()){
			dataHandler.update(value, timestamp);
			worker.work(dataHandler);
		}
	}

}
