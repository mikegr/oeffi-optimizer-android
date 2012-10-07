package com.mintplex.oeffioptimizer;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Debug;

public class Log {

	private static String TAG = "TailoredAppsLib";

	public static void setTAG(String tag) {
		TAG = tag;
	}

	private static int logLevel = android.util.Log.VERBOSE;

	public static void setLogLevel(int level) {
		Log.logLevel = level;
	}

	public static void v(String msg) {
		if (logLevel >= android.util.Log.VERBOSE)
			android.util.Log.v(TAG, msg);
	}

	public static void v(String msg, Throwable t) {
		if (logLevel >= android.util.Log.VERBOSE)
			android.util.Log.v(TAG, msg, t);
	}

	public static void d(String msg) {
		if (logLevel >= android.util.Log.DEBUG)
			android.util.Log.d(TAG, msg);
	}

	public static void d(String msg, Throwable t) {
		if (logLevel >= android.util.Log.DEBUG)
			android.util.Log.d(TAG, msg, t);
	}

	public static void i(String msg) {
		if (logLevel >= android.util.Log.INFO)
			android.util.Log.i(TAG, msg);
	}

	public static void i(String msg, Throwable t) {
		if (logLevel >= android.util.Log.INFO)
			android.util.Log.i(TAG, msg, t);
	}

	public static void w(String msg) {
		if (logLevel >= android.util.Log.WARN)
			android.util.Log.w(TAG, msg);
	}

	public static void w(String msg, Throwable t) {
		if (logLevel >= android.util.Log.WARN)
			android.util.Log.w(TAG, msg, t);
	}

	public static void e(String msg) {
		if (logLevel >= android.util.Log.ERROR)
			android.util.Log.e(TAG, msg);
	}

	public static void e(String msg, Throwable t) {
		if (logLevel >= android.util.Log.ERROR)
			android.util.Log.e(TAG, msg, t);
	}

	public static boolean isLoggable(int level) {
		return android.util.Log.isLoggable(TAG, level);
	}

	public static void logMemoryStats(Context context) {
		String text = "";
		text += "\nLoadedClassCount="
				+ toMib(android.os.Debug.getLoadedClassCount());
		text += "\nGlobalAllocSize="
				+ toMib(android.os.Debug.getGlobalAllocSize());
		text += "\nGlobalFreedSize="
				+ toMib(android.os.Debug.getGlobalFreedSize());
		text += "\nGlobalExternalAllocSize="
				+ toMib(android.os.Debug.getGlobalExternalAllocSize());
		text += "\nGlobalExternalFreedSize="
				+ toMib(android.os.Debug.getGlobalExternalFreedSize());
		// text += "\nEpicPixels=" + toMib(EpicBitmap.getGlobalPixelCount()*4);
		text += "\nNativeHeapSize="
				+ toMib(android.os.Debug.getNativeHeapSize());
		text += "\nNativeHeapFree="
				+ toMib(android.os.Debug.getNativeHeapFreeSize());
		text += "\nNativeHeapAllocSize="
				+ toMib(android.os.Debug.getNativeHeapAllocatedSize());
		text += "\nThreadAllocSize="
				+ toMib(android.os.Debug.getThreadAllocSize());

		text += "\ntotalMemory()=" + toMib(Runtime.getRuntime().totalMemory());
		text += "\nmaxMemory()=" + toMib(Runtime.getRuntime().maxMemory());
		text += "\nfreeMemory()=" + toMib(Runtime.getRuntime().freeMemory());

		android.app.ActivityManager.MemoryInfo mi1 = new android.app.ActivityManager.MemoryInfo();
		ActivityManager am = (ActivityManager) context
				.getSystemService(Context.ACTIVITY_SERVICE);
		am.getMemoryInfo(mi1);
		text += "\napp.am.memoryClass=" + am.getMemoryClass();
		text += "\napp.mi.availMem=" + toMib(mi1.availMem);
		text += "\napp.mi.threshold=" + toMib(mi1.threshold);
		text += "\napp.mi.lowMemory=" + mi1.lowMemory;

		android.os.Debug.MemoryInfo mi2 = new android.os.Debug.MemoryInfo();
		Debug.getMemoryInfo(mi2);
		text += "\ndbg.mi.dalvikPrivateDirty=" + toMib(mi2.dalvikPrivateDirty);
		text += "\ndbg.mi.dalvikPss=" + toMib(mi2.dalvikPss);
		text += "\ndbg.mi.dalvikSharedDirty=" + toMib(mi2.dalvikSharedDirty);
		text += "\ndbg.mi.nativePrivateDirty=" + toMib(mi2.nativePrivateDirty);
		text += "\ndbg.mi.nativePss=" + toMib(mi2.nativePss);
		text += "\ndbg.mi.nativeSharedDirty=" + toMib(mi2.nativeSharedDirty);
		text += "\ndbg.mi.otherPrivateDirty=" + toMib(mi2.otherPrivateDirty);
		text += "\ndbg.mi.otherPss" + toMib(mi2.otherPss);
		text += "\ndbg.mi.otherSharedDirty=" + toMib(mi2.otherSharedDirty);

		d(text);

		// EpicLog.i("ArchPlatform[android].logStats() - " + text);
	}

	private static float toMib(float bytes) {
		return (bytes / 1048576f);
	}
}
