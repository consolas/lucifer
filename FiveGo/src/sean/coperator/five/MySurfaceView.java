package sean.coperator.five;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class MySurfaceView extends SurfaceView implements  android.view.SurfaceHolder.Callback{
	private static int GRIDW_SIZE = 14;
	private static int GRIDH_SIZE = 20;
	private static int startW = 10, startH = 10;
	private float tileW;
	private float tileH;
	private int screenW, screenH;
	private Canvas canvas;
	private SurfaceHolder sfHolder;
	private Bitmap bm;
	
	public MySurfaceView(Context context) {
		super(context);
		bm = BitmapFactory.decodeResource(getResources(), R.drawable.background);
		sfHolder = this.getHolder();
		sfHolder.addCallback(this);
		}
	
	public void surfaceCreated(SurfaceHolder sfHolder){
		screenW = this.getWidth();
		screenH = this.getHeight();
		tileW = (screenW - 2*startW)/GRIDW_SIZE;
		tileH = (screenH -2*startH)/GRIDH_SIZE;
		canvas = sfHolder.lockCanvas();
		canvas.drawColor(Color.WHITE);
		Paint paint = new Paint();
		canvas.drawBitmap(bm, 0, 0, paint);
		paint.setColor(Color.BLACK);
		paint.setStrokeWidth(1);
		paint.setStyle(Style.STROKE);
		float startX = 0, startY = 0;
		for(int i = 0; i <= GRIDW_SIZE; i++){
			startX = startW + i*tileW;
			startY = startH;
			canvas.drawLine(startX, startY, startX, screenH - startH, paint);
		}
		for(int j = 0; j <=GRIDH_SIZE; j++){
			startX = startW;
			startY = startH + j*tileH;
			canvas.drawLine(startX, startY, screenW - startW, startY, paint);
			
		}
		if(canvas != null)
			sfHolder.unlockCanvasAndPost(canvas);
	}
	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		
	}
		
}
