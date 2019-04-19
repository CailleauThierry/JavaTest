package thierry.bubbledraw;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;


/**
 * Created on 04_19_2019 by Thierry Cailleau with help from  https://github.com/mayuezhu 's implementation
 */
public class BubbleView extends ImageView implements View.OnTouchListener {
    private List<Bubble> bubbleList;
    private final int DELAY = 16;
    private Paint myPaint = new Paint();
    private Handler h;
    private int size = 50;

    public BubbleView(Context context, AttributeSet attrs) {
        super(context, attrs);

        bubbleList = new ArrayList<Bubble>();
        myPaint.setColor(Color.WHITE);
        h = new Handler();

        // we set an ontouchlistener, similar to a mouse but with touch display

        this.setOnTouchListener(this);
        /*
        // test the ability to draw bubbles
        for (int i = 0; i < 100; i++) {
            bubbleList.add(new Bubble((int)(Math.random() * 300), (int)(Math.random() * 400), 50));
        }
        */
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
        /*
        // test the ability to draw bubbles
        for (int i = 0; i < 100; i++) {
            bubbleList.add(new Bubble((int)(Math.random() * 300), (int)(Math.random() * 400), 50));
        }
        */
        for (Bubble bubble : bubbleList) {
            myPaint.setColor(bubble.color);
            c.drawOval(bubble.x - bubble.size / 2, bubble.y - bubble.size / 2, bubble.x + bubble.size / 2, bubble.y + bubble.size / 2, myPaint);
        }

        myPaint.setColor(Color.WHITE);
        myPaint.setTextSize(50);
        c.drawText("Count:" + bubbleList.size(), 5, 40, myPaint);
        c.drawText("Size:" + size, 5, 85, myPaint);

        // similar to a timer, just wakes up the thread after the DELAY. Also says "we are finished with this frame"
        h.postDelayed(r, DELAY);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        // handle multi-touch events
        if (event.getPointerCount() == 1) {
            bubbleList.add(new Bubble((int) event.getX(), (int) event.getY(), size));
            if (bubbleList.size() > 1) {
                bubbleList.get(bubbleList.size() - 1).xspeed = bubbleList.get(bubbleList.size() - 2).xspeed;
                bubbleList.get(bubbleList.size() - 1).yspeed = bubbleList.get(bubbleList.size() - 2).yspeed;
            }
        }

        if (event.getPointerCount() == 2) {
            size = (int) event.getAction();
        }

        else {
            for (int i = 0; i < event.getPointerCount(); i++) {
                bubbleList.add(new Bubble((int) event.getX(i), (int) event.getY(i), size));
            }
        }
        // if we wanted to use more touch events (for other application) we could return false
        return true;
    }

    private class Bubble {
        /**
         * A bubble needs an x,y location and a size
         */
        public int x;
        public int y;
        public int size;
        public int color;
        public int xspeed;
        public int yspeed;
        private final int MAX_SPEED = 10;

        public Bubble(int newX, int newY, int newSize) {
            x = newX;
            y = newY;
            size = newSize;
            color = Color.argb((int) (Math.random() * 256),
                    (int) (Math.random() * 256),
                    (int) (Math.random() * 256),
                    (int) (Math.random() * 256));
            xspeed = (int) (Math.random() * MAX_SPEED * 2 - MAX_SPEED);
            yspeed = (int) (Math.random() * MAX_SPEED * 2 - MAX_SPEED);

            if (xspeed == 0 && yspeed == 0) {
                xspeed = 1;
                yspeed = 1;
            }

        }

        public void update() {
            x += xspeed;
            y += yspeed;

            // collision detection with the edges of the panel
            if (x <= size / 2 || x + size / 2 >= getWidth())
                xspeed = -1 * xspeed;
            if (y <= size / 2 || y + size / 2 >= getHeight())
                yspeed = -yspeed;

        }
    }
}