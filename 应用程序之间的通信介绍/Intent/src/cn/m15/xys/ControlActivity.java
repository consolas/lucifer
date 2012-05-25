package cn.m15.xys;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
/**
 * 
 * @author 宣雨松
 * email:xuanyusong@gmail.com
 * blog:http://blog.csdn.net/xys289187120
 */
public class ControlActivity extends Activity {
   
    Context mContext = null;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        mContext = this;
        /**使用Handler传递消息**/
        Button botton0 = (Button)findViewById(R.id.button0);
        botton0.setOnClickListener(new OnClickListener() {
	    
	    @Override
	    public void onClick(View arg0) {
		 Intent intent = new Intent(mContext,HandlerActivity.class); 
		 startActivity(intent);
	    }
	}); 
        /**Notification**/
        Button botton1 = (Button)findViewById(R.id.button1);
        botton1.setOnClickListener(new OnClickListener() {
	    
	    @Override
	    public void onClick(View arg0) {
		 Intent intent = new Intent(mContext,NotificationActivity.class); 
		 startActivity(intent);
	    }
	}); 
        /**广播的接收**/
        Button botton2 = (Button)findViewById(R.id.button2);
        botton2.setOnClickListener(new OnClickListener() {
	    
	    @Override
	    public void onClick(View arg0) {
		 Intent intent = new Intent(mContext,BroadcastActivity.class); 
		 startActivity(intent);
	    }
	}); 
        
        
        
        /**Activity之间传递值**/
        Button botton3 = (Button)findViewById(R.id.button3);
        botton3.setOnClickListener(new OnClickListener() {
	    
	    @Override
	    public void onClick(View arg0) {
		 Intent intent = new Intent(mContext,ShowActivity.class); 
		 //使用intent.putExtra()直接传递
		 intent.putExtra("name", "雨松MOMO");
		 intent.putExtra("age", 25);
		 intent.putExtra("boy", true);
		
		 //把数值放进bundle 然后在把整个bundle通过intent.putExtra()传递
		 Bundle bundle = new Bundle();
		 bundle.putString("b_name", "小可爱");
		 bundle.putInt("b_age", 23);
		 bundle.putBoolean("b_boy", false);
		 //在这里把整个bundle 放进intent中
		 intent.putExtras(bundle);
		 //开启一个新的 activity 将intent传递过去
		 startActivity(intent);
	    }
	});   
    }
}