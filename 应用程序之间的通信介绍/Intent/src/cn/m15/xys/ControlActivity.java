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
 * @author ������
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
        /**ʹ��Handler������Ϣ**/
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
        /**�㲥�Ľ���**/
        Button botton2 = (Button)findViewById(R.id.button2);
        botton2.setOnClickListener(new OnClickListener() {
	    
	    @Override
	    public void onClick(View arg0) {
		 Intent intent = new Intent(mContext,BroadcastActivity.class); 
		 startActivity(intent);
	    }
	}); 
        
        
        
        /**Activity֮�䴫��ֵ**/
        Button botton3 = (Button)findViewById(R.id.button3);
        botton3.setOnClickListener(new OnClickListener() {
	    
	    @Override
	    public void onClick(View arg0) {
		 Intent intent = new Intent(mContext,ShowActivity.class); 
		 //ʹ��intent.putExtra()ֱ�Ӵ���
		 intent.putExtra("name", "����MOMO");
		 intent.putExtra("age", 25);
		 intent.putExtra("boy", true);
		
		 //����ֵ�Ž�bundle Ȼ���ڰ�����bundleͨ��intent.putExtra()����
		 Bundle bundle = new Bundle();
		 bundle.putString("b_name", "С�ɰ�");
		 bundle.putInt("b_age", 23);
		 bundle.putBoolean("b_boy", false);
		 //�����������bundle �Ž�intent��
		 intent.putExtras(bundle);
		 //����һ���µ� activity ��intent���ݹ�ȥ
		 startActivity(intent);
	    }
	});   
    }
}