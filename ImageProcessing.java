package kazukazu.test;

import android.graphics.Bitmap;
import android.util.Log;
import android.view.View;

/**
 * Created by MrAdipic on 2017/05/04.
 */

public class ImageProcessing {
    private int bitmapHeight;
    private int bitmapWidth;
    private int[] pixels;
    private int pixel;

    public Bitmap drawCenterLine(Bitmap bitmap) {

        bitmapHeight = bitmap.getHeight();
        bitmapWidth = bitmap.getWidth();
        pixels = new int[bitmapWidth * bitmapHeight];

        bitmap.getPixels(pixels, 0, bitmapWidth, 0, 0, bitmapWidth, bitmapHeight);
        for (int i = bitmapWidth / 2; i < bitmapHeight * bitmapWidth; i += bitmapWidth) {
            for (int n = 0; n < 10; n++) {
                pixels[i - n] = 0xFFFFFFFF;
                pixels[i + n] = 0xFFFFFFFF;
            }
        }
        bitmap.setPixels(pixels, 0, bitmapWidth, 0, 0, bitmapWidth, bitmapHeight);
        return bitmap;
    }

    public Bitmap drawLine(Bitmap bitmap) {

        return bitmap;
    }

    public Bitmap resizeBitmap(View view, Bitmap bitmap) {

        bitmap = Bitmap.createScaledBitmap(bitmap, view.getWidth(), view.getWidth() * bitmap.getHeight() / bitmap.getWidth(), false);
        return bitmap;
    }

    public Bitmap drawCrossHair(Bitmap bitmap, int x, int y) {
        bitmapHeight = bitmap.getHeight();
        bitmapWidth = bitmap.getWidth();

        try {


            for (int i = 0; i < bitmapHeight; i++) {
                bitmap.setPixel(x, i, 0x00000000);
            }
            for (int i = 0; i < bitmapWidth; i++) {
                bitmap.setPixel(i, y, 0x00000000);
            }
        }
        catch (ArrayIndexOutOfBoundsException e){
            Log.e("Array", "ArrayError");
        }
        return bitmap;
    }

    public int[] arrangeCordinate(View view, int[] XY) {

        if (XY[0] < (int) view.getLeft())
            XY[0] = (int) view.getLeft();
        else if ((int) view.getLeft() + view.getWidth() < XY[0])
            XY[0] = (int) view.getLeft() + view.getWidth();
        else
            XY[0] = XY[0] - (int) view.getLeft();

        if (XY[1] < (int) view.getTop())
            XY[1] = (int) view.getTop();
        else if ((int) view.getTop() + view.getHeight() < XY[1])
            XY[1] = (int) view.getTop() + view.getHeight();
        else
            XY[1] = XY[1] - (int) view.getTop();


        return XY;

    }

    
}
