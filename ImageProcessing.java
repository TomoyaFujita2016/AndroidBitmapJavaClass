import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

/**
 * Created by Tomoya on 2017/05/09.
 */

public class ImageProcessing1 {

    private final int RED = 0x00FF0000;
    private final int BLACK = 0x00000000;

    private int bitmapHeight;
    private int bitmapWidth;
    private int[] pixels;
    private Bitmap tmpBitmap;


    public Bitmap drawLine(Bitmap bitmap) {

        return bitmap;
    }

    public Bitmap bitmapToMutable(View view, Bitmap bitmap) {
        //to mutable
        Rect rectView = new Rect();
        view.getGlobalVisibleRect(rectView);

        bitmap = Bitmap.createScaledBitmap(bitmap, rectView.width(), rectView.height(), false);
        return bitmap;
    }

    public Bitmap drawCrossHair(ImageView imageView, Bitmap bitmap, int x, int y) {
        tmpBitmap = bitmap.copy(Bitmap.Config.ARGB_8888, true);
        bitmapHeight = bitmap.getHeight();
        bitmapWidth = bitmap.getWidth();

        if (x == bitmapWidth)
            x--;
        if (y == bitmapHeight)
            y--;

        for (int i = 0; i < bitmapHeight; i++) {
            tmpBitmap.setPixel(x, i, BLACK);
        }
        for (int i = 0; i < bitmapWidth; i++) {
            tmpBitmap.setPixel(i, y, BLACK);
        }

        imageView.setImageBitmap(tmpBitmap);

        return tmpBitmap;
    }


    public Bitmap drawRectangle(ImageView imageView, Bitmap bitmap, int startX, int startY, int endX, int endY) {
        tmpBitmap = bitmap.copy(Bitmap.Config.ARGB_8888, true);
        bitmapHeight = bitmap.getHeight();
        bitmapWidth = bitmap.getWidth();

        if (startX == bitmapWidth)
            startX--;
        if (startY == bitmapHeight)
            startY--;
        if (endX == bitmapWidth)
            endX--;
        if (endY == bitmapHeight)
            endY--;

        for (int i = startX; i < endX; i++) {
            tmpBitmap.setPixel(i, startY, RED);
            tmpBitmap.setPixel(i, endY, RED);
        }
        for (int i = startY; i < endY; i++) {
            tmpBitmap.setPixel(startX, i, RED);
            tmpBitmap.setPixel(endX, i, RED);
        }

        imageView.setImageBitmap(tmpBitmap);
        return tmpBitmap;


    }

    public int[] globalCoordinateToLocal(View view, int[] XY, Activity activity) {


        Rect rectView = new Rect();
        view.getGlobalVisibleRect(rectView);

        if (XY[0] < rectView.left)   //X coordinates is the outer side of View
            XY[0] = 0;
        else if (rectView.right < XY[0])
            XY[0] = rectView.width();
        else                        //X coordinates is the inner side of View
            XY[0] -= rectView.left;

        if (XY[1] < rectView.top)   //Y coordinates is the outer side of View
            XY[1] = 0;
        else if (rectView.bottom< XY[1])
            XY[1] = rectView.height();
        else                                            //Y coordinates is the inner side of View
            XY[1] -= rectView.top;

        return XY;

    }


}
