package com.adilahui_app.canvastest;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.media.Image;
import android.nfc.Tag;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by Tomoya on 2017/05/19.
 */

public class CanvasBitmap extends ImageProcessing {

    private Bitmap bitmap, tmpBitmap;
    private ImageView imageView;
    private Activity activity;
    private Canvas canvas;
    private Paint paint, paintImg;
    private int color;

    private int[] bmpSize;

    CanvasBitmap(Bitmap bitmap, ImageView imageView, Activity activity, int color) {
        Log.d("Constructor", "Loaded Constructor");

        this.bitmap = bitmap;
        this.imageView = imageView;
        this.activity = activity;
        this.color = color;

        canvas = new Canvas(bitmap);
        canvas.drawBitmap(bitmap, 0, 0, null);



        bmpSize = new int[2];
        bmpSize[0] = bitmap.getWidth();
        bmpSize[1] = bitmap.getHeight();

        tmpBitmap = this.bitmap.copy(Bitmap.Config.ARGB_8888, true);

        paint = new Paint();
        paint.setColor(color);
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);

        paintImg = new Paint();
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setAntiAlias(true);
    }


    private int[] GLtoLC(int[] XY) {
        Rect rectView = new Rect();
        imageView.getGlobalVisibleRect(rectView);

        XY[0] -= rectView.left;
        XY[1] -= rectView.top;
        XY[0] = XY[0] * bitmap.getWidth() / imageView.getWidth();
        XY[1] = XY[1] * bitmap.getHeight() / imageView.getHeight();

        return XY;
    }

    private int[] ToInner(int[] XY) {
        XY = GLtoLC(XY);
        if (XY[0] < 0) {
            XY[0] = 0;
        }
        if (bitmap.getWidth() < XY[0]) {
            XY[0] = bitmap.getWidth();
        }
        if (XY[1] < 0) {
            XY[1] = 0;
        }
        if (bitmap.getHeight() < XY[1]) {
            XY[1] = bitmap.getHeight();
        }
        return XY;
    }

    public void drawCrossHair(int[] XY) {
        XY = ToInner(XY);

        canvas.drawBitmap(tmpBitmap, 0, 0, paintImg);
        canvas.drawLine(0, XY[1], bitmap.getWidth(), XY[1], paint);
        canvas.drawLine(XY[0], 0, XY[0], bitmap.getHeight(), paint);
        imageView.setImageBitmap(bitmap);


    }


}
