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

	// �õ�֪ͨ��Ϣ�Ĺ��������󣬸������ Notification �ķ����������Ϣ��
	mManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
	// ����Notification���� �����ֱ���� ֪ͨ�� ����ʾ��ͼ�� ��ʾ�ı��� ��ʾ��ʱ��
	notification = new Notification(R.drawable.jay,
		"Androidרҵ����Ⱥ", System.currentTimeMillis());
	
	// ������֪ͨ���е����Notification�Զ���ʧ
	notification.flags = Notification.FLAG_AUTO_CANCEL;
	
	//���õ����ת������activity
	Intent intent = new Intent(this, MyShowActivity.class);
	intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP| Intent.FLAG_ACTIVITY_NEW_TASK);

	//ͨ��bundle���Դ�һЩ���ݹ�ȥ ���ｫ�ַ��������˹�ȥ
	Bundle bundle = new Bundle();
	bundle.putString("name", "��Notificationת��������");
	intent.putExtras(bundle);
	
	//����֪ͨ������ʾ������
	PendingIntent contentIntent = PendingIntent.getActivity(this,
		R.string.app_name, intent, PendingIntent.FLAG_UPDATE_CURRENT);
	notification.setLatestEventInfo(this, "Androidרҵ����Ⱥ",
		"QQȺ�� 164257885", contentIntent);
	

	Button button0 = (Button)findViewById(R.id.button0);
	button0.setOnClickListener(new OnClickListener() {
	    
	    @Override
	    public void onClick(View arg0) {
		//�����Notification֪ͨ
		mManager.notify(0, notification);
	    }
	});
	
	Button button1 = (Button)findViewById(R.id.button1);
	button1.setOnClickListener(new OnClickListener() {
	    
	    @Override
	    public void onClick(View arg0) {
		//�ر����Notification֪ͨ
		mManager.cancelAll();
	    }
	});
	
	super.onCreate(savedInstanceState);
    }

}
