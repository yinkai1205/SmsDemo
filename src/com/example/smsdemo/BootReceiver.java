package com.example.smsdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/***
 * 开机启动广播接收
 * 
 * @author Administrator
 * 
 */
public class BootReceiver extends BroadcastReceiver {

	/** 广播接收 */
	public void onReceive(Context context, Intent intent) {
		
		Log.d("Test", "sms: boot receiver...");
//		
//		// 接收到系统开机信息时 启动服务
//		Intent in = new Intent();
//		in.setAction("com.example.smsdemo.SmsService");
//		context.startService(in);
//		Toast.makeText(context, intent.getAction(), Toast.LENGTH_LONG).show();
//		System.out.println("启动项目 : " + intent.getAction());
	}

}