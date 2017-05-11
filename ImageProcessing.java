import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

/**
 * Created by Tomoya on 2017/05/09.
 */

public class ImageProcessing {

    private int bitmapHeight;
    private int bitmapWidth;
    private int[] pixels;
    private Bitmap tmpBitmap;
    private int idxLineThickness;


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

    public Bitmap drawCrossHair(ImageView imageView, Bitmap bitmap, int x, int y, int lineThickness, int color) {
        tmpBitmap = bitmap.copy(Bitmap.Config.ARGB_8888, true);
        bitmapHeight = bitmap.getHeight();
        bitmapWidth = bitmap.getWidth();

        if (x == bitmapWidth)
            x--;
        if (y == bitmapHeight)
            y--;

        if (x > bitmapWidth - lineThickness - 1)
            x -= x - (bitmapWidth - lineThickness - 1);
        if (x < lineThickness)
            x += lineThickness - x;
        if (y > bitmapHeight - lineThickness - 1)
            y -= y - (bitmapHeight - lineThickness - 1);
        if (y < lineThickness)
            y += lineThickness - y;

        for (int i = 0; i < bitmapHeight; i++) {
            for (idxLineThickness = 0; idxLineThickness < lineThickness + 1; idxLineThickness++) {
                tmpBitmap.setPixel(x + idxLineThickness, i, color);
                tmpBitmap.setPixel(x - idxLineThickness, i, color);
            }
        }
        for (int i = 0; i < bitmapWidth; i++) {
            for (idxLineThickness = 0; idxLineThickness < lineThickness + 1; idxLineThickness ++) {
                tmpBitmap.setPixel(i, y + idxLineThickness, color);
                tmpBitmap.setPixel(i, y - idxLineThickness, color);

            }
        }

        imageView.setImageBitmap(tmpBitmap);

        return tmpBitmap;
    }


    public Bitmap drawRectangle(ImageView imageView, Bitmap bitmap, int startX, int startY, int endX, int endY, int lineThickness, int color) {
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

        if (startX > bitmapWidth - lineThickness - 1)
            startX -= startX - (bitmapWidth - lineThickness - 1);
        if (startX < lineThickness)
            startX += lineThickness - startX;
        if (startY > bitmapHeight - lineThickness - 1)
            startY -= startY - (bitmapHeight - lineThickness - 1);
        if (startY < lineThickness)
            startY += lineThickness - startY;

        if (endX > bitmapWidth - lineThickness - 1)
            endX -= endX - (bitmapWidth - lineThickness - 1);
        if (endX < lineThickness)
            endX += lineThickness - endX;
        if (endY > bitmapHeight - lineThickness - 1)
            endY -= endY - (bitmapHeight - lineThickness - 1);
        if (endY < lineThickness)
            endY += lineThickness - endY;

        for (int i = startX - lineThickness; i < endX + lineThickness; i++) {
            for (idxLineThickness = 0; idxLineThickness < lineThickness + 1; idxLineThickness++) {
                tmpBitmap.setPixel(i, startY + idxLineThickness, color);
                tmpBitmap.setPixel(i, endY + idxLineThickness, color);
                tmpBitmap.setPixel(i, startY - idxLineThickness, color);
                tmpBitmap.setPixel(i, endY - idxLineThickness, color);
            }
        }
        for (int i = startY; i < endY; i++) {
            for (idxLineThickness = 0; idxLineThickness < lineThickness + 1; idxLineThickness++) {
                tmpBitmap.setPixel(startX + idxLineThickness, i, color);
                tmpBitmap.setPixel(endX + idxLineThickness, i, color);
                tmpBitmap.setPixel(startX - idxLineThickness, i, color);
                tmpBitmap.setPixel(endX - idxLineThickness, i, color);
            }
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
        else if (rectView.bottom < XY[1])
            XY[1] = rectView.height();
        else                                            //Y coordinates is the inner side of View
            XY[1] -= rectView.top;

        return XY;

    }


}
