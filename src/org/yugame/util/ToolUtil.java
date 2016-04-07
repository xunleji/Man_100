package org.yugame.util;

import java.io.File;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;
import android.widget.Toast;

/**
 * 宸ュ叿锟�
 * 
 * @author yuhongbing
 * 
 */
public class ToolUtil {

	private final static int kSystemRootStateUnknow = -1;
	private final static int kSystemRootStateDisable = 0;
	private final static int kSystemRootStateEnable = 1;
	private static int systemRootState = kSystemRootStateUnknow;

	/**
	 * 鍒ゆ柇鏄惁ROOT
	 * 
	 * @return
	 */
	public static boolean isRootSystem() {

		if (systemRootState == kSystemRootStateEnable) {
			return true;
		} else if (systemRootState == kSystemRootStateDisable) {

			return false;
		}
		File f = null;
		final String kSuSearchPaths[] = { "/system/bin/", "/system/xbin/",
				"/system/sbin/", "/sbin/", "/vendor/bin/" };
		try {
			for (int i = 0; i < kSuSearchPaths.length; i++) {
				f = new File(kSuSearchPaths[i] + "su");
				if (f != null && f.exists()) {
					systemRootState = kSystemRootStateEnable;
					return true;
				}
			}
		} catch (Exception e) {
		}
		systemRootState = kSystemRootStateDisable;
		return false;
	}

	/***
	 * 鑾峰彇SD鍗¤矾寰�
	 * 
	 * @return
	 */
	public static String getSDPath() {
		File sdDir = null;
		try {
			boolean sdCardExist = Environment.getExternalStorageState().equals(
					android.os.Environment.MEDIA_MOUNTED);
			if (sdCardExist) {
				sdDir = Environment.getExternalStorageDirectory();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sdDir == null ? "null" : sdDir.toString();
	}

	/***
	 * 缃戠粶杩炴帴鏄惁鏂紑
	 * 
	 * @param context
	 * @return
	 */
	public static boolean isNet(Context context) {

		ConnectivityManager cManager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo info = cManager.getActiveNetworkInfo();
		if (info != null && info.isAvailable()) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 鑾峰彇鏂囦欢涓嬬殑鍥剧墖
	 * 
	 * @param strPath
	 * @return
	 */
	public static Bitmap loadBitmapFromSDCard(String strPath) {
		try {
			if (new File(strPath).exists()) {
				BitmapFactory.Options newOpts = new BitmapFactory.Options();
				Bitmap bmp = BitmapFactory.decodeFile(strPath, newOpts);
				return bmp;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} catch (OutOfMemoryError e1) {
			e1.printStackTrace();
		}
		return null;
	}

	public static void showTip(final Context context, final String text) {
		((Activity) context).runOnUiThread(new Runnable() {
			@Override
			public void run() {
				Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
			}
		});
	}

	public static void showTip_Long(final Context context, final String text) {
		((Activity) context).runOnUiThread(new Runnable() {
			@Override
			public void run() {
				Toast.makeText(context, text, Toast.LENGTH_LONG).show();
			}
		});
	}

	public static void showTip(final Context context, final int text) {
		((Activity) context).runOnUiThread(new Runnable() {
			@Override
			public void run() {
				Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
			}
		});
	}

	/**
	 * 鍍忕礌杞夋彌
	 * 
	 * @param context
	 * @param dipValue
	 * @return
	 */
	public static int dip2px(Context context, float dipValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dipValue * scale + 0.5f);
	}

	/***
	 * 鍒涘缓鏂囦欢澶�
	 * 
	 * @param cache
	 * @return
	 */
	public static File createSDDir(String cache) {

		File path = new File(cache);// 鍒涘缓鐩綍
		if (!path.exists()) {// 鐩綍瀛樺湪杩斿洖false
			path.mkdirs();// 鍒涘缓锟�锟斤拷鐩綍
		}
		return path;
	}

	/**
	 * 娓呴櫎鎵�湁鏂囦欢澶逛腑鏂囦欢
	 * 
	 * @param file
	 */
	public static void DeleteFile(File file) {
		if (file.exists() == false) {
			return;
		} else {
			if (file.isFile()) {
				file.delete();
				return;
			}
			if (file.isDirectory()) {
				File[] childFile = file.listFiles();
				if (childFile == null || childFile.length == 0) {
					file.delete();
					return;
				}
				for (File f : childFile) {
					DeleteFile(f);
				}
				file.delete();
			}
		}
	}

	/***
	 * 鍒ゆ柇鏄惁绗﹀悎閭鏍煎紡
	 * 
	 * @param strEmail
	 * @return
	 */
	public static boolean isEmail(String strEmail) {
		String strPattern = "\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*";
		Pattern p = Pattern.compile(strPattern);
		Matcher m = p.matcher(strEmail);
		return m.matches();
	}

}
