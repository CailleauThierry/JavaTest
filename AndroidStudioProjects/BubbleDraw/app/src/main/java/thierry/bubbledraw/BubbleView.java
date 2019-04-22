package thierry.bubbledraw;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.os.Handler;

import java.util.ArrayList;

/**
 * Created on 04_19_2019 by Thierry Cailleau with help from  https://github.com/mayuezhu 's implementation
 * Android Studio 3.4
 * Build #AI-183.5429.30.34.5452501, built on April 9, 2019
 * JRE: 1.8.0_152-release-1343-b01 amd64
 * JVM: OpenJDK 64-Bit Server VM by JetBrains s.r.o
 * Windows Server 2012 R2 6.3
 * Tested to work on Samsung S9+ (SM-G956F)
 */
public class BubbleView extends ImageView implements View.OnTouchListener{
    private ArrayList<Bubble> bubbleList;
    private final int DELAY = 16;
    private Paint myPaint = new Paint();
    private Handler h;
    private int fingerDrawSize = 50;
    private final int fingerThickness = 50; // give a constant representing the min space between 2 adult fingers touching

    public BubbleView(Context context, AttributeSet attrs) {
        super(context, attrs);

        bubbleList = new ArrayList<Bubble>();
        myPaint.setColor(Color.WHITE);
        h = new Handler();

        // we set an ontouchlistener, similar to a mouse but with touch display

        this.setOnTouchListener(this);
    }

    private Runnable r = new Runnable() {
        @Override
        public void run() {

            // update all the bubbles
            for (Bubble bubble : bubbleList) {
                bubble.update();
            }

            // redraw the screen, invalidate is like a paint component for the other applications
            invalidate();
        }
    };

    protected void onDraw(Canvas c) {
        for (Bubble bubble : bubbleList) {
            myPaint.setColor(bubble.color);
            c.drawOval(bubble.x - (int)(bubble.size / 2), bubble.y - (int)(bubble.size / 2), bubble.x + (int)(bubble.size / 2), bubble.y + (int)(bubble.size / 2), myPaint);
        }

        myPaint.setColor(Color.WHITE);
        myPaint.setTextSize(50);
        c.drawText("Count:" + bubbleList.size(), 5, 40, myPaint);
        c.drawText("Size:" + fingerDrawSize, 5, 85, myPaint);
        h.postDelayed(r, DELAY);
    }

    @Override
    public boolean onTouch(View v, MotionEvent motionEvent) {
        // handle multi-touch events
        // first handles the 2 finger touch to change the size, by shrinking on X or Y axis > calculate the area (so no negative values)
        if (motionEvent.getPointerCount() == 2) fingerDrawSize =
                Math.abs((((int)motionEvent.getX(0) - (int) motionEvent.getX(1) )) / fingerThickness) *
                Math.abs((((int)motionEvent.getY(0) - (int) motionEvent.getY(1) )) / fingerThickness);
        // then handles the 1 finger touch to draw and stay in the same direction (drawing animated)
        else if (motionEvent.getPointerCount() == 1) {
            bubbleList.add(new Bubble((int) motionEvent.getX(), (int) motionEvent.getY(), fingerDrawSize));
            if (bubbleList.size() > 1) {
                bubbleList.get(bubbleList.size() - 1).xspeed = bubbleList.get(bubbleList.size() - 2).xspeed;
                bubbleList.get(bubbleList.size() - 1).yspeed = bubbleList.get(bubbleList.size() - 2).yspeed;
            }
        }
        // then handles the case where there 3 or more than 3 fingers and drawn random bubbles
        else {
            for (int n = 0;  n < motionEvent.getPointerCount(); n++) {
                bubbleList.add(new Bubble((int) motionEvent.getX(n), (int) motionEvent.getY(n), (int) (Math.random() * 50 + 50)));
            }
        }
        // if we wanted to use more touch events (for other application) we could return false
        return true;
    }

    private class Bubble {
        /** A bubble needs an x,y location and a size */
        public int x;
        public int y;
        public int size;
        public int color;
        public int xspeed;
        public int yspeed;
        private final int MAX_SPEED = 10;

        public Bubble(int newX, int newY, int newSize){
            x = newX;
            y = newY;
            size = newSize;
            color = Color.argb((int) (Math.random() * 256),
                    (int) (Math.random() * 256),
                    (int)(Math.random() * 256),
                    (int)(Math.random() * 256) );
            xspeed = (int) (Math.random() * MAX_SPEED * 2 - MAX_SPEED);
            yspeed = (int) (Math.random() * MAX_SPEED * 2 - MAX_SPEED);

            if (xspeed == 0 && yspeed == 0)
            {
                xspeed = 1;
                yspeed = 1;
            }

        }

        public void update(){
            x += xspeed;
            y += yspeed;

            // collision detection with the edges of the panel
            if ( x <= size / 2 || x + size / 2 >= getWidth() )
                xspeed = -1 * xspeed;
            if ( y <= size / 2 || y + size / 2 >= getHeight() )
                yspeed = -yspeed;

        }
    }
}