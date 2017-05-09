import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

/**
 * Created by TomoyaFujita on 2017/05/04.
 */

public class ImageProcessing {
    private int bitmapHeight;
    private int bitmapWidth;
    private int[] pixels;
    private int pixel;
    private Bitmap tmpBitmap;

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
            bitmap.setPixel(x, i, 0x00000000);
        }
        for (int i = 0; i < bitmapWidth; i++) {
            bitmap.setPixel(i, y, 0x00000000);
        }

        imageView.setImageBitmap(bitmap);

        return tmpBitmap;
    }

    public int[] globalCoordinateToLocal(View view, int[] XY, Activity activity) {

        Rect rectActionBar = new Rect();
        Window window = activity.getWindow();
        window.getDecorView().getWindowVisibleDisplayFrame(rectActionBar);

        Rect rectView = new Rect();
        view.getGlobalVisibleRect(rectView);

        if (XY[0] < rectView.left)   //X coordinates is the outer side of View
            XY[0] = 0;
        else if (rectView.right < XY[0])
            XY[0] = rectView.width();
        else                        //X coordinates is the inner side of View
            XY[0] -= rectView.left;

        if (XY[1] < rectView.top + rectActionBar.top)   //Y coordinates is the outer side of View
            XY[1] = 0;
        else if (rectView.bottom + rectActionBar.top < XY[1])
            XY[1] = rectView.height();
        else                                            //Y coordinates is the inner side of View
            XY[1] -= rectActionBar.top + rectView.top;

        return XY;

    }


}
