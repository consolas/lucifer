package cn.m15.xys;



import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class HandlerActivity extends Activity implements Runnable{

    /**����ʱ��**/
    public final static int UPDATE_TIME =0;
    /**����ʱ��ɹ�**/
    public final static int UPDATE_COMPLETED =1;
    
    /**��¼��ʾʱ�� ����10������߳�**/
    private int mShowNumber = 0;
    
    /**��ʼ��ʱ��ť**/
    private Button mButton = null;
    
    /**��ʱ��ʾ����**/
    private TextView mTextView = null;
   
    /**�߳�**/
    private Thread mThread = null;
   
    /**�̹߳رյı�־**/
    private boolean mRunning = false;
    
    Handler handler = new Handler() {
	@Override
	public void handleMessage(Message msg) {
	    
	    Bundle bundle= msg.getData();
	    //ͨ��key�������õ�����ֵ
	    String  number = bundle.getString("number");
	    //msg.whatΪhandler���յ�����Ϣ���
	    switch(msg.what) {
	    case UPDATE_TIME:
		mTextView.setText("���ڸ���ʱ��" + number);
		break;
	    case UPDATE_COMPLETED:
		mTextView.setText("�������");
		break;
	    }
	    super.handleMessage(msg);
	}
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
	setContentView(R.layout.handler);
	
	/**�õ�button ��  TextView ����**/
	mButton = (Button)findViewById(R.id.button0);
	mTextView = (TextView)findViewById(R.id.textView0);
	mThread = new Thread(this);
	
	mButton.setOnClickListener(new OnClickListener() {
	    @Override
	    public void onClick(View arg0) {
		/**�����ť��ʼ�߳̿�ʼ��ʱ**/
		mRunning = true;
		mThread.start();
	    }
	});
	
	mTextView.setText("�����ť��ʼ����ʱ��");
	super.onCreate(savedInstanceState);
    }

    public void ShowDialog(String string) {
	AlertDialog.Builder builder = new AlertDialog.Builder(
		HandlerActivity.this);
	builder.setIcon(R.drawable.icon);
	builder.setTitle(string);
	builder.setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {
	    public void onClick(DialogInterface dialog, int whichButton) {
		finish();
	    }
	});
	builder.show();
    }

 

    @Override
    public void run() {

	while (mRunning) {
	    try {
		mShowNumber++;
		/** ����Ҫ�����ݷ���bandle�� **/
		Bundle bandle = new Bundle();
		bandle.putString("number", String.valueOf(mShowNumber));

		/** ����������Ϣ�ı��Ϊ����ʱ�� **/
		/** ��bandleд��message�� **/
		/** ������message���ͳ�ȥ **/
		/** mShowNumberС��10����ʱ�� ���������� **/
		Message msg = new Message();
		if(mShowNumber <=10) {
		    msg.what = UPDATE_TIME; 
		}else {
		    mRunning = false;
		    msg.what = UPDATE_COMPLETED;  
		}
		msg.setData(bandle);
		handler.sendMessage(msg);
		Thread.sleep(1000);
	    } catch (InterruptedException e) {
		e.printStackTrace();
	    }
	}
    }
}
