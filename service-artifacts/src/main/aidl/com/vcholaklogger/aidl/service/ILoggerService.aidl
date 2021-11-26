package com.vcholaklogger.aidl.service;

interface ILoggerService {
	boolean writeLog(String level, String content);
}
