package dennis.learning.colorapp.graphics;

import java.util.Random;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * TODO:
 * http://stackoverflow.com/questions/3542333/how-to-prevent-custom-views-from
 * -losing-state-across-screen-orientation-changes/3542895#3542895
 * 
 * @author Dennis
 * 
 */
class CircleView extends View {
	private static final String TAG = "TapCircleActivity";
	private Paint paint = new Paint();
	private Random random = new Random();
	private int startX = 40;
	private int startY = 40;
	private int radius = 40;
	private int lowerXCircle = 0;
	private int lowerYCircle = 0;
	private int upperXCircle = 0;
	private int upperYCircle = 0;

	public CircleView(Context context, AttributeSet attrs) {
		super(context, attrs);
		paint.setFlags(Paint.ANTI_ALIAS_FLAG);
	}

	@Override
	public void onDraw(Canvas canvas) {
		int xOffset = random.nextInt(300);
		int yOffset = random.nextInt(300);
		canvas.drawColor(Color.DKGRAY);
		drawCircle(canvas, xOffset, yOffset);
	}

	private void drawCircle(Canvas canvas, int xOffset, int yOffset) {
		int centerXCircle = startX + xOffset;
		int centerYCircle = startY + yOffset;

		lowerXCircle = centerXCircle - radius;
		lowerYCircle = centerYCircle - radius;
		upperXCircle = centerXCircle + radius;
		upperYCircle = centerYCircle + radius;
		paint.setColor(Color.BLACK);
		paint.setStrokeWidth(0);
		canvas.drawCircle(centerXCircle, centerYCircle, radius, paint);
		paint.setColor(Color.RED);
		canvas.drawCircle(centerXCircle, centerYCircle, radius - 4, paint);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		float tapX = event.getX();
		float tapY = event.getY();
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
		case MotionEvent.ACTION_MOVE:
		case MotionEvent.ACTION_UP:
			if (tapX >= lowerXCircle && tapX <= upperXCircle
					&& tapY >= lowerYCircle && tapY <= upperYCircle) {
				invalidate();
			}
			Log.i(TAG, tapX + "," + tapY);
		}
		return super.onTouchEvent(event);
	}
}