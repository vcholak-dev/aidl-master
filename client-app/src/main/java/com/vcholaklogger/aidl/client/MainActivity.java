package com.vcholaklogger.aidl.client;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.widget.TextView;

import com.vcholaklogger.aidl.service.ILoggerService;
import com.vcholaklogger.aidl.service.ServiceIntentBuilder;

public class MainActivity extends Activity {
	private static final String LOGGING_TAG = MainActivity.class.getSimpleName();

	private ILoggerService loggerService;
	private TextView label;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		label = findViewById(R.id.label);

		connectToService();
	}

	private void connectToService() {
		ServiceConnection connection = new ServiceConnection() {
			@Override
			public void onServiceConnected(ComponentName name, IBinder service) {
				loggerService = ILoggerService.Stub.asInterface(service);

				try {
					boolean resStatus = loggerService.writeLog("DEBUG", "Service is inited!");
					label.setText(resStatus ? "Log is written" : "Error! Check service status and output!" );
				} catch (RemoteException e) {
					throw new RuntimeException(e);
				}
			}

			@Override
			public void onServiceDisconnected(ComponentName name) {
				loggerService = null;
			}
		};

		// The value returned by bindSearch() only indicates whether binding was successfully
		// initiated, based on some preliminary checks like whether the calling app has the
		// necessary permissions. Binding is not complete until onServiceConnected() is called.
		//
		// Binding is asynchronous. However, the process that calls onServiceConnected() gets queued
		// and is likely to (eventually) be run by the same thread that called bindService().
		// Because of that, it is not possible to block the thread that calls bindService() until
		// onServiceConnected() is called.
		boolean connected = bindService(
				ServiceIntentBuilder.buildLoggerBindIntent(),
				connection,
				Context.BIND_AUTO_CREATE);

		Log.i(LOGGING_TAG, "Connection initiated: " + connected);
	}
}
