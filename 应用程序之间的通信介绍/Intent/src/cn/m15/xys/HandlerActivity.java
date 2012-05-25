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

    /**更新时间**/
    public final static int UPDATE_TIME =0;
    /**更新时间成功**/
    public final static int UPDATE_COMPLETED =1;
    
    /**记录显示时间 超过10秒结束线程**/
    private int mShowNumber = 0;
    
    /**开始计时按钮**/
    private Button mButton = null;
    
    /**计时显示内容**/
    private TextView mTextView = null;
   
    /**线程**/
    private Thread mThread = null;
   
    /**线程关闭的标志**/
    private boolean mRunning = false;
    
    Handler handler = new Handler() {
	@Override
	public void handleMessage(Message msg) {
	    
	    Bundle bundle= msg.getData();
	    //通过key的名称拿到它的值
	    String  number = bundle.getString("number");
	    //msg.what为handler接收到的消息编号
	    switch(msg.what) {
	    case UPDATE_TIME:
		mTextView.setText("正在更新时间" + number);
		break;
	    case UPDATE_COMPLETED:
		mTextView.setText("更新完毕");
		break;
	    }
	    super.handleMessage(msg);
	}
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
	setContentView(R.layout.handler);
	
	/**拿到button 与  TextView 对象**/
	mButton = (Button)findViewById(R.id.button0);
	mTextView = (TextView)findViewById(R.id.textView0);
	mThread = new Thread(this);
	
	mButton.setOnClickListener(new OnClickListener() {
	    @Override
	    public void onClick(View arg0) {
		/**点击按钮后开始线程开始计时**/
		mRunning = true;
		mThread.start();
	    }
	});
	
	mTextView.setText("点击按钮开始更新时间");
	super.onCreate(savedInstanceState);
    }

    public void ShowDialog(String string) {
	AlertDialog.Builder builder = new AlertDialog.Builder(
		HandlerActivity.this);
	builder.setIcon(R.drawable.icon);
	builder.setTitle(string);
	builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
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
		/** 把须要的数据放入bandle中 **/
		Bundle bandle = new Bundle();
		bandle.putString("number", String.valueOf(mShowNumber));

		/** 设置这条信息的编号为更新时间 **/
		/** 将bandle写入message中 **/
		/** 最后将这个message发送出去 **/
		/** mShowNumber小于10更新时间 否则更新完毕 **/
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
