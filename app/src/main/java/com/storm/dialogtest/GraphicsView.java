package com.storm.dialogtest;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by HOME on 08.07.2015.
 */
public class GraphicsView extends ImageView {

    public GraphicsView(Context context) {
        super(context);
    }

    public GraphicsView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GraphicsView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        //paint.setColor(Color.RED);
       // canvas.drawColor(Color.RED);
        int colorFrom = Color.BLACK;
        int colorTo = Color.GREEN;
        int canvasHeight = canvas.getHeight();
        int canvasWidth = canvas.getWidth();
        LinearGradient linearGradient = new LinearGradient(0,canvasHeight/2,
                canvasWidth,canvasHeight/2,colorFrom,colorTo, Shader.TileMode.CLAMP);
        paint.setShader(linearGradient);

        canvas.drawRect(0,0,canvasWidth,canvasWidth,paint);


    }

}
