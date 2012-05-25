package cn.m15.xys;



import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class MyShowActivity extends Activity {
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
	setContentView(R.layout.show);
	
	Bundle bundle = getIntent().getExtras();
	String name = bundle.getString("name");
	TextView textView = (TextView)findViewById(R.id.text);
	textView.setText(name);
	
	super.onCreate(savedInstanceState);
    }

}
