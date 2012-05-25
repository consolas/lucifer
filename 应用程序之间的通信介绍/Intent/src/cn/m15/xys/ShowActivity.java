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
	//第二个参数为默认值 意思就是如果在intent中拿不到的话
	//就用默认值
	int age  = intent.getIntExtra("age", 0);
	boolean isboy = intent.getBooleanExtra("boy", false);
	TextView textView0 = (TextView)findViewById(R.id.text0);
	
	textView0.setText("姓名  " + name + "年龄 " + age + "男孩?  " + isboy);
	
	
	Bundle bundle = intent.getExtras();
	name = bundle.getString("b_name");
	//第二个参数为默认值 意思就是如果在bundle中拿不到的话
	//就用默认值
	age = bundle.getInt("b_age",0);
	isboy = bundle.getBoolean("b_boy", false);
	
	TextView textView1 = (TextView)findViewById(R.id.text1);
	
	textView1.setText("姓名  " + name + "年龄 " + age + "男孩?  " + isboy);
	
	super.onCreate(savedInstanceState);
    }

}
