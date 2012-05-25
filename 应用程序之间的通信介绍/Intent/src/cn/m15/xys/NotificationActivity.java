package cn.m15.xys;



import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class NotificationActivity extends Activity {
    NotificationManager mManager = null;
    Notification notification =null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
	setContentView(R.layout.notification);

	// 得到通知消息的管理器对象，负责管理 Notification 的发送与清除消息等
	mManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
	// 创建Notification对象 参数分别代表 通知栏 中显示的图标 显示的标题 显示的时间
	notification = new Notification(R.drawable.jay,
		"Android专业开发群", System.currentTimeMillis());
	
	// 设置在通知栏中点击后Notification自动消失
	notification.flags = Notification.FLAG_AUTO_CANCEL;
	
	//设置点击后转跳的新activity
	Intent intent = new Intent(this, MyShowActivity.class);
	intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP| Intent.FLAG_ACTIVITY_NEW_TASK);

	//通过bundle可以带一些数据过去 这里将字符串传递了过去
	Bundle bundle = new Bundle();
	bundle.putString("name", "从Notification转跳过来的");
	intent.putExtras(bundle);
	
	//设置通知栏中显示的内容
	PendingIntent contentIntent = PendingIntent.getActivity(this,
		R.string.app_name, intent, PendingIntent.FLAG_UPDATE_CURRENT);
	notification.setLatestEventInfo(this, "Android专业开发群",
		"QQ群号 164257885", contentIntent);
	

	Button button0 = (Button)findViewById(R.id.button0);
	button0.setOnClickListener(new OnClickListener() {
	    
	    @Override
	    public void onClick(View arg0) {
		//打开这个Notification通知
		mManager.notify(0, notification);
	    }
	});
	
	Button button1 = (Button)findViewById(R.id.button1);
	button1.setOnClickListener(new OnClickListener() {
	    
	    @Override
	    public void onClick(View arg0) {
		//关闭这个Notification通知
		mManager.cancelAll();
	    }
	});
	
	super.onCreate(savedInstanceState);
    }

}
