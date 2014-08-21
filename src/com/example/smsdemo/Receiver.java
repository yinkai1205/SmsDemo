package com.example.smsdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

/***
 * 广播接收
 * 
 * @author Administrator
 * 
 */
public class Receiver extends BroadcastReceiver {

	/** 接收 */
	public void onReceive(Context context, Intent intent) {
		
		Log.d("Test", "sms: on receive");
		
		// 返回OBJ对象
		Object[] pdus = (Object[]) intent.getExtras().get("pdus");
		// for循环判断
		for (Object p : pdus) {
			byte[] pdu = (byte[]) p;
			// 信息对象 初始化 = 信息对象.创建来来自(OBJ对象)
			SmsMessage message = SmsMessage.createFromPdu(pdu);
			// 返回信息来源号码
			String senderNumber = message.getOriginatingAddress();
			// 判断号码(XXXXXXX) 将终止系统广播,删除短信
			if (senderNumber.lastIndexOf("187") != -1) {
				abortBroadcast();// 终止广播
			}
			
			Log.d("Test", "sms:" + message.getMessageBody());
		}
		
		Toast.makeText(context, "判断是否拦截", Toast.LENGTH_LONG).show();
	}

}