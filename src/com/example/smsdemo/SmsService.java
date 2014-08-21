package com.example.smsdemo;

import android.app.Service;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.os.IBinder;
import android.provider.Telephony.Sms;
import android.util.Log;
import android.widget.Toast;

public class SmsService extends Service {

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {

		// Log.d("Test", "sms:service start...");
		// // 判断服务一开启便注册一个SMS广播接收器
		// Receiver mReceiver = null; // 广播接收类 对象
		// IntentFilter iFilter = null; // 意图过滤对象
		// mReceiver = new Receiver(); // 广播接收类初始化
		// iFilter = new
		// IntentFilter("android.provider.Telephony.SMS_RECEIVED"); // 意图过滤初始化
		// iFilter.setPriority(Integer.MAX_VALUE * Integer.MAX_VALUE); // 设置优先级
		// registerReceiver(mReceiver, iFilter); // 注册广播接

		ContentObserver mObserver = new ContentObserver(new Handler()) {

			@Override
			public void onChange(boolean selfChange) {
				super.onChange(selfChange);
				ContentResolver resolver = getContentResolver();
				Cursor cursor = resolver.query(
						Uri.parse("content://sms/inbox"), new String[] { "_id",
								"address", "body" }, null, null, "_id desc");
				long id = -1;

				if (cursor.getCount() > 0 && cursor.moveToFirst()) {
					id = cursor.getLong(0);
					String address = cursor.getString(1);
					String body = cursor.getString(2);
					
					Log.d("Test", "resolver.." + body);

					Toast.makeText(
							SmsService.this,
							String.format("address: %s\n body: %s", address,
									body), Toast.LENGTH_SHORT).show();
				}
				cursor.close();

//				if (id != -1) {
//					int count = resolver.delete(Sms.CONTENT_URI, "_id=" + id,
//							null);
//					Toast.makeText(SmsService.this,
//							count == 1 ? "删除成功" : "删除失败", Toast.LENGTH_SHORT)
//							.show();
//				}
			}

		};

		getContentResolver().registerContentObserver(
				Uri.parse("content://sms/"), true, mObserver);

		return START_STICKY;
	}

}
