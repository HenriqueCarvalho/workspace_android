package com.example.henriquecarvalho.hoc_assignment6;

import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.ImageView;
import android.widget.FrameLayout;

public class MainActivity extends ActionBarActivity {

    private String TAG = "Assignment6";
    private AnimationDrawable balloonPopAnimation;
    private ImageView ivBalloon;
    private Boolean show;
    private FrameLayout mainFrame;

    // helper method to calculate the total duration of an animated sequence
    public int getTotalAnimationDuration(AnimationDrawable animation) {
        int result = 0;
        int numFrames = animation.getNumberOfFrames();

        for (int i=0; i<numFrames; i++) {
            result = animation.getDuration(i);
        }

        return result;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainFrame = (FrameLayout) this.findViewById(R.id.FrameLayout1);
        ivBalloon = (ImageView) this.findViewById(R.id.ivBalloon);
        balloonPopAnimation = (AnimationDrawable) ivBalloon.getDrawable();
        ivBalloon.setVisibility(View.INVISIBLE);
        show = false;
        balloonTouched(ivBalloon);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int positionX = (int)event.getX();
        int positionY = (int)event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
            case MotionEvent.ACTION_UP:
        }

        if (show)
            moveToTouched(positionX, positionY);
        else
            showBalloon(positionX, positionY);

        return false;
    }

    public void balloonTouched(ImageView img){

        img.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
            int action = event.getAction();
            int x = (int) event.getX();
            int y = (int) event.getY();

            switch (action) {
                case (MotionEvent.ACTION_DOWN):
                    Log.d(TAG, "Balloon was touched at (" + x + ", " + y + ")");
                    balloonAnimation();
                    return true;
                case MotionEvent.ACTION_UP:
                    v.performClick();
                    return true;
                default:
                    return true; // handle all actions that start on the balloon
            }

            }

        });
    }

    public void balloonAnimation(){
        ivBalloon.setImageResource(0);
        balloonPopAnimation = ((AnimationDrawable)ivBalloon.getDrawable());
        balloonPopAnimation.start();
        int animationTime = getTotalAnimationDuration(balloonPopAnimation);
        new Handler().postDelayed(new BalloonHider(), animationTime);
    }

    public void showBalloon(int x, int y)
    {
        int top = this.mainFrame.getTop() + ((View)this.mainFrame.getParent()).getTop();
        final FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) ivBalloon.getLayoutParams();

        params.leftMargin = x;
        params.topMargin = y - top;

        this.ivBalloon.setLayoutParams(params);
        this.ivBalloon.setVisibility(View.VISIBLE);
        this.show = true;
    }

    public void moveToTouched(int x, int y)
    {
        int top = mainFrame.getTop() + ((View)mainFrame.getParent()).getTop();
        Log.i(TAG, "top = " + top);

        final FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) ivBalloon.getLayoutParams();

        final int currentLeftMargin = params.leftMargin;
        final int currentTopMargin = params.topMargin;
        final int newLeftMarginOffset = x - currentLeftMargin;
        final int newTopMarginOffset = y - top - currentTopMargin;

        // set the new margins using an animation
        Animation animation = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
            params.leftMargin = currentLeftMargin + (int) (newLeftMarginOffset * interpolatedTime);
            params.topMargin = currentTopMargin + (int) (newTopMarginOffset * interpolatedTime);

            ivBalloon.setLayoutParams(params);
            }
        };

        animation.setDuration(500); // duration is in milliseconds
        ivBalloon.startAnimation(animation);

        Log.i(this.TAG, "Screen was touched at (" + x + ", " + y + ")");
    }


    private class BalloonHider implements Runnable
    {
        private BalloonHider()
        {
        }

        public void run()
        {
            Log.i(TAG, "Hiding the balloon");
            if (balloonPopAnimation != null)
            {
                balloonPopAnimation.stop();
                balloonPopAnimation = null;
            }
            ivBalloon.setImageResource(0);
            ivBalloon.setVisibility(View.INVISIBLE);
            show = false;
        }
    }

}
