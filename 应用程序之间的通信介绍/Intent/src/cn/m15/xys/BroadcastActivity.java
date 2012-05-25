package cn.m15.xys;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class BroadcastActivity extends Activity {


   
    Button mButton0 = null;
    Button mButton1 = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
	setContentView(R.layout.broadcast);
	
	mButton0 = (Button)findViewById(R.id.button0);
	mButton0.setOnClickListener(new OnClickListener() {
	    
	    @Override
	    public void onClick(View arg0) {
                Intent intent = new Intent(MyService.SEND_OK_MESSAGE);
                intent.putExtra("name", "您发送了OK这条广播哦");
                sendBroadcast(intent);
	    }
	});

	mButton1 = (Button)findViewById(R.id.button1);
	mButton1.setOnClickListener(new OnClickListener() {
	    
	    @Override
	    public void onClick(View arg0) {
                Intent intent = new Intent(MyService.SEND_CANCLE_MESSAGE);
                intent.putExtra("name", "您发送了Cancle这条广播哦");
                sendBroadcast(intent);
	    }
	});
	
	//启动Service 
	Intent i = new Intent(this, MyService.class);
	startService(i);
	super.onCreate(savedInstanceState);
    }
}
