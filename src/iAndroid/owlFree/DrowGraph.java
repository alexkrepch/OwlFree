package iAndroid.owlFree;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class DrowGraph extends View {

	public DrowGraph(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		
		Paint p= new Paint();
		p.setColor(Color.BLUE);
		canvas.drawRect(100, 100, 200, 200, p);
		invalidate();
		
		}
	
	
	
	

}
