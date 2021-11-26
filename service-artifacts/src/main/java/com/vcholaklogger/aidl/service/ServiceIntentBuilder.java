package com.vcholaklogger.aidl.service;

import android.content.Intent;

public class ServiceIntentBuilder {
	public static Intent buildLoggerBindIntent() {
		// The acton is from the Service's intent-filter declaration in the manifest
		return new Intent("com.vcholaklogger.aidl.service.LoggerService.BIND")
				.setPackage("com.vcholaklogger.aidl.service");
	}
}
