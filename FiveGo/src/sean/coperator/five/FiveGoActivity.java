package sean.coperator.five;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.PopupWindow;
import android.widget.Toast;

import sean.coperator.five.MySurfaceView;

public class FiveGoActivity extends Activity implements  OnItemClickListener, android.view.View.OnKeyListener {
    /** Called when the activity is first created. */
	private PopupWindow popupWindow;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        MySurfaceView sv = new MySurfaceView(this);
        setContentView(sv);
        GridView gvPopupWindowGridView = (GridView)getLayoutInflater().inflate(R.layout.popup_window, null);
        GridAdapter  gridAdapter  = new GridAdapter(this);
        gvPopupWindowGridView.setAdapter(gridAdapter);
        gvPopupWindowGridView.setOnKeyListener(this);
        gvPopupWindowGridView.setOnItemClickListener(this);
        popupWindow = new PopupWindow(gvPopupWindowGridView, LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);
        popupWindow.setFocusable(true);
    }
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		// TODO Auto-generated method stub
		popupWindow.dismiss();
		Toast.makeText(this, Const.Const_ITEM_TEXT_LIST[position], Toast.LENGTH_LONG);		
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu){
		menu.add("menu");
		return super.onCreateOptionsMenu(menu);		
	}
	
	@Override
	public boolean onMenuOpened(int featureId, Menu menu){
		if(popupWindow != null){
			if(popupWindow.isShowing())popupWindow.dismiss();
			else {
				View layoutView = getLayoutInflater().inflate(R.layout.main, null);
				popupWindow.showAtLocation(layoutView, Gravity.CENTER, 0, 0);
			}
		}
		return false;
	}
	@Override
	public boolean onKey(View v, int keyCode, KeyEvent event) {
		switch(keyCode){
		case KeyEvent.KEYCODE_BACK:
			if(popupWindow.isShowing())
				popupWindow.dismiss();
			break;
	}
	return false;
	}
}

