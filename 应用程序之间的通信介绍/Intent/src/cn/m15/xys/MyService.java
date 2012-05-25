package cn.m15.xys;


import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.widget.Toast;

public class MyService extends Service {

    public final static String SEND_OK_MESSAGE = "send.ok.message";
    public final static String SEND_CANCLE_MESSAGE = "send.cancle.message";
    
    private BroadcastReceiver myBroadCast = new BroadcastReceiver() {

	@Override
	public void onReceive(Context context, Intent intent) {
	    String action = intent.getAction();
	    if (action.equals(SEND_OK_MESSAGE)) {
		Toast.makeText(context, "接收到了一条广播为" + SEND_OK_MESSAGE, Toast.LENGTH_LONG).show();
	    }else if(action.equals(SEND_CANCLE_MESSAGE)) {
		Toast.makeText(context, "接收到了一条广播为" + SEND_CANCLE_MESSAGE, Toast.LENGTH_LONG).show();
	    }
	}

    };

    @Override
    public void onCreate() {
	super.onCreate();
    }

    @Override
    public void onStart(Intent intent, int startId) {
	//注册这两个广播
	IntentFilter myFilter = new IntentFilter();
	myFilter.addAction(SEND_OK_MESSAGE);
	myFilter.addAction(SEND_CANCLE_MESSAGE);
	this.registerReceiver(myBroadCast, myFilter);
        super.onStart(intent, startId);
    }
    @Override
    public IBinder onBind(Intent arg0) {
	return null;
    }

}
