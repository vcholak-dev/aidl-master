package com.vcholaklogger.aidl.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class LoggerService extends Service {
	private LoggerServiceImpl serviceImplementation = new LoggerServiceImpl();

	@Override
	public IBinder onBind(Intent intent) {
		return serviceImplementation;
	}
}
