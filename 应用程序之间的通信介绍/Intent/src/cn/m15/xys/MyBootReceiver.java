package cn.m15.xys;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
public class MyBootReceiver extends BroadcastReceiver {
	@Override
    public void onReceive(Context context, Intent intent) {
	Intent i = new Intent(context, MyService.class);
	context.startService(i);
    }
}
