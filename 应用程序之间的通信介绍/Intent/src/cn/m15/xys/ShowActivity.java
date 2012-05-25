package cn.m15.xys;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ShowActivity extends Activity {
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
	setContentView(R.layout.my);
	
	Intent intent = getIntent();
	
	String name = intent.getStringExtra("name");
	//�ڶ�������ΪĬ��ֵ ��˼���������intent���ò����Ļ�
	//����Ĭ��ֵ
	int age  = intent.getIntExtra("age", 0);
	boolean isboy = intent.getBooleanExtra("boy", false);
	TextView textView0 = (TextView)findViewById(R.id.text0);
	
	textView0.setText("����  " + name + "���� " + age + "�к�?  " + isboy);
	
	
	Bundle bundle = intent.getExtras();
	name = bundle.getString("b_name");
	//�ڶ�������ΪĬ��ֵ ��˼���������bundle���ò����Ļ�
	//����Ĭ��ֵ
	age = bundle.getInt("b_age",0);
	isboy = bundle.getBoolean("b_boy", false);
	
	TextView textView1 = (TextView)findViewById(R.id.text1);
	
	textView1.setText("����  " + name + "���� " + age + "�к�?  " + isboy);
	
	super.onCreate(savedInstanceState);
    }

}
