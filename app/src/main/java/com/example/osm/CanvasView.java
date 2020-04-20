package com.example.osm;

import android.content.Context;
import android.graphics.*;
import android.util.AttributeSet;
import android.view.*;
import android.widget.Toast;
import androidx.annotation.Nullable;

public class CanvasView extends View implements ICanvasView{

    private GameManager gameManager;
    private static int width;
    private static int height;
    private Paint paint;
    private Canvas canvas;
    private Toast toast;


    public CanvasView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initWidthAndHeight(context);
        gameManager = new GameManager(this, width, height);
        initPaint();
    }

    private void initWidthAndHeight(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();
        Point point = new Point();
        display.getSize(point);
        width = point.x;
        height = point.y;

    }

    private void initPaint() {
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
        paint.setShadowLayer(5, 5, 3, Color.BLACK);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.canvas = canvas;
        GameManager.onDraw();

    }

    @Override
    public void drawCircle(Circle circle) {
        paint.setColor(circle.getColor());
        canvas.drawCircle(circle.getX(), circle.getY(), circle.getRadius(), paint);
    }

    @Override
    public void redraw() {
        invalidate();
    }

    @Override
    public void showMessage(String s) {
        if (toast != null){toast.cancel();}
        toast = Toast.makeText(getContext(), s, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();
        if (event.getAction() == MotionEvent.ACTION_MOVE){gameManager.onTouchEvent(x, y);}
        invalidate();
        return true;
    }
}

