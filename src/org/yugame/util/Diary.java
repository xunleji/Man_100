package org.yugame.util;

import android.util.Log;

public class Diary {

	private static boolean debug = true;

	public static void out(String str) {
		if (debug) {
			Log.e("tag", str);
		}
	}
}
