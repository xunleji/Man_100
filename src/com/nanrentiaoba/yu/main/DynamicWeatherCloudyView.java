package com.nanrentiaoba.yu.main;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.Log;

public class DynamicWeatherCloudyView implements Runnable {

	/**
	 * Ҫ�����ͼ
	 */
	private Bitmap bitmap;

	private int left;
	private int top;

	/**
	 * ͼƬ�ƶ�Ƶ��
	 */
	private int dx = 1;
	private int dy = 1;

	private int sleepTime;

	private int sw;

	/**
	 * ͼƬ�Ƿ����ƶ�
	 */
	public static boolean IsRunning = true;

	public DynamicWeatherCloudyView(Context context, int resource, int left, int top,
			int sleepTime, int sw) {

		bitmap = BitmapFactory.decodeResource(context.getResources(), resource);
		this.left = left;
		this.top = top;
		this.sleepTime = sleepTime;
		this.sw = sw;
		IsRunning = true;

	}

	public void move() {
		new Thread(this).start();
	}

	public void onDraw(Canvas canvas) {
		canvas.drawBitmap(bitmap, left, top, null);
	}

	@Override
	public void run() {

		while (DynamicWeatherCloudyView.IsRunning) {
			if ((bitmap != null) && (left > (sw))) {
				left = -bitmap.getWidth();
			}
			left = left + dx;
			Log.i("icer", left + "");
			try {
				Thread.sleep(sleepTime);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
