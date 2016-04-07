package com.nanrentiaoba.yu.main;

import org.yugame.util.ToolUtil;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

public class BackgroundView {
	// ��Ϸ������ͼƬ��Դ
	// Ϊ��ѭ�����ţ����ﶨ������λͼ����
	// ����Դ���õ���ͬһ��ͼƬ
	private Bitmap bmpBackGround1;
	private Bitmap bmpBackGround2;
	// ��Ϸ��������
	private int bg1x, bg1y, bg2x, bg2y;
	// ���������ٶ�
	private int speed = 2;

	private int screenH;

	// ��Ϸ�������캯��
	public BackgroundView(Context context, Bitmap bmpBackGround, int screenH) {
		
		speed = ToolUtil.dip2px(context, 2);
		this.bmpBackGround1 = bmpBackGround;
		this.bmpBackGround2 = bmpBackGround;
		this.screenH = screenH;
		// �����õ�һ�ű����ײ���������������Ļ
		bg1y = -Math.abs(bmpBackGround1.getHeight() - screenH);
		// �ڶ��ű���ͼ�����ڵ�һ�ű������Ϸ�
		// +101��ԭ����Ȼ���ű���ͼ�޷�϶���ӵ�����ΪͼƬ��Դͷβ
		// ֱ�����Ӳ���г��Ϊ�����Ӿ�������������ͼ���Ӷ�������λ��
		bg2y = bg1y - bmpBackGround1.getHeight() + 111;
	}

	// ��Ϸ�����Ļ�ͼ����
	public void draw(Canvas canvas, Paint paint) {
		// �������ű���
		canvas.drawBitmap(bmpBackGround1, bg1x, bg1y, paint);
		canvas.drawBitmap(bmpBackGround2, bg2x, bg2y, paint);
	}

	// ��Ϸ�������߼�����
	public void logic() {
		bg1y += speed;
		bg2y += speed;
		// ����һ��ͼƬ��Y���곬����Ļ��
		// ���������������õ��ڶ���ͼ���Ϸ�
		if (bg1y > screenH) {
			bg1y = bg2y - bmpBackGround1.getHeight() + 111;
		}
		// ���ڶ���ͼƬ��Y���곬����Ļ��
		// ���������������õ���һ��ͼ���Ϸ�
		if (bg2y > screenH) {
			bg2y = bg1y - bmpBackGround1.getHeight() + 111;
		}
	}
}
