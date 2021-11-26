package com.vcholaklogger.aidl.service;

import android.os.IBinder;
import android.os.RemoteException;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Logger;

public class LoggerServiceImpl extends ILoggerService.Stub {
	static File logFile = null;

	@Override
	public boolean writeLog(String level, String content) throws RemoteException {
		if (logFile == null)
			logFile = new File("sdcard/log.txt");

		if (!logFile.exists())
		{
			try
			{
				logFile.createNewFile();
			}
			catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try
		{
			//BufferedWriter for performance, true to set append to file flag
			BufferedWriter buf = new BufferedWriter(new FileWriter(logFile, true));
			buf.append(String.format("[%s]: %s\n", level, content));
			buf.newLine();
			buf.close();
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return true;
	}

	@Override
	public IBinder asBinder() {
		return this;
	}
}
