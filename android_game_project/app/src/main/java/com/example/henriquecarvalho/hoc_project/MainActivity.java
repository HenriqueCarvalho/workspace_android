package com.example.henriquecarvalho.hoc_project;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.drawable.GradientDrawable;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.*;

public class MainActivity extends ActionBarActivity
{
    private String TAG = "MainActivity";
    private ArrayList<ImageView> monsters;
    private ArrayList<ImageView> blocks;
    private Random rand = new Random();
    private Timer updatesTimer;
    private static final int SECOND = 2000; // time is in milliseconds
    private int seconds;
    private int score;

    MediaPlayer player;

    ImageView imgView0;
    ImageView imgView1;
    ImageView imgView2;
    ImageView imgView3;
    ImageView imgView4;

    ImageView blockView0;
    ImageView blockView1;
    ImageView blockView2;

    ImageView soundBtn;
    ImageView restartBtn;
    ImageView gameOverBackground;

    TextView timeOutput;
    TextView scoreOutput;
    TextView gameOverOutput;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Setting the screen orientation
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        //Setting the outputs time and score
        setOutputsTimeAndScore();

        //Setting the images
        imgView0 = (ImageView) findViewById(R.id.monster0);
        imgView1 = (ImageView) findViewById(R.id.monster1);
        imgView2 = (ImageView) findViewById(R.id.monster2);
        imgView3 = (ImageView) findViewById(R.id.monster3);
        imgView4 = (ImageView) findViewById(R.id.monster4);

        blockView0 = (ImageView) findViewById(R.id.block0);
        blockView1 = (ImageView) findViewById(R.id.block1);
        blockView2 = (ImageView) findViewById(R.id.block2);

        //Setting the array list
        monsters = new ArrayList<ImageView>();
        monsters.add(imgView0);
        monsters.add(imgView1);
        monsters.add(imgView2);
        monsters.add(imgView3);
        monsters.add(imgView4);

        blocks = new ArrayList<ImageView>();
        blocks.add(blockView0);
        blocks.add(blockView1);
        blocks.add(blockView2);

        //Setting touch event
        addArrayListTouchEvent(monsters);
        addArrayListTouchEvent(blocks);

        //Setting the schedule
        reScheduleTimer(SECOND);

        //Setting the sound
        setBackgroundColor();
        setBackgroundAudio();
    }

    public void setBackgroundColor()
    {
        View layout = findViewById(R.id.FrameLayout1);

        GradientDrawable myGradient = new GradientDrawable(
                GradientDrawable.Orientation.TOP_BOTTOM,
                new int[] {0xff00ffff,0xffffffff}
        );

        myGradient.setCornerRadius(0f);

        layout.setBackground(myGradient);
    }

    public void setBackgroundAudio()
    {
        //Setting a image to sound button
        soundBtn = (ImageView) findViewById(R.id.soundBtn);

        //Setting the player
        player = MediaPlayer.create(this, R.raw.mysong);
        player.setLooping(true);
        player.start();
    }

    public void setOutputsTimeAndScore()
    {
        seconds = 20;
        score = 0;

        timeOutput = (TextView) findViewById(R.id.timeOutput);
        timeOutput.setText(String.valueOf(seconds));

        scoreOutput = (TextView) findViewById(R.id.scoreOutput);
        scoreOutput.setText(String.valueOf(score));

        gameOverOutput = (TextView) findViewById(R.id.gameOverOutput);
        gameOverOutput.setText("Your final score was: " + String.valueOf(score));
        gameOverOutput.setPadding(10,10,10,10);
        gameOverOutput.setVisibility(View.INVISIBLE);

        restartBtn = (ImageView) findViewById(R.id.restartBtn);
        restartBtn.setVisibility(View.INVISIBLE);

        gameOverBackground = (ImageView) findViewById(R.id.gameOverBackground);
        gameOverBackground.setVisibility(View.INVISIBLE);
    }

    public void addArrayListTouchEvent(ArrayList<ImageView> imageArrayList)
    {
        for (ImageView image : imageArrayList )
        {
            addTouchEvent(image);
        }
    }

    public void animate(final ImageView img)
    {
        ObjectAnimator anim = ObjectAnimator.ofFloat(img, "translationY", 0f, -100f);
        anim.addListener(new Animator.AnimatorListener()
        {
            @Override
            public void onAnimationStart(Animator animation)
            {
                //add touch event when the image is animated because after click it is removed
                addTouchEvent(img);
            }

            @Override
            public void onAnimationEnd(Animator animation)
            {
                //Setting the original image
                img.setImageResource(R.drawable.mole_1);
                removeTouchEvent(img);
            }

            @Override
            public void onAnimationCancel(Animator animation)
            {
            }

            @Override
            public void onAnimationRepeat(Animator animation)
            {
            }
        });

        anim.setDuration(900);
        anim.setRepeatCount(1);
        anim.setRepeatMode(2);
        anim.start();
    }

    @Override
    public void onStart()
    {
        super.onStart();
        player.start();
        Log.i(TAG, "onStart method");
    }

    @Override
    public void onStop()
    {
        super.onStop();
        player.pause();
        Log.i(TAG, "onStop method");
    }

    @Override
    public void onRestart()
    {
        super.onRestart();
        Log.i(TAG, "onRestart method");
    }

    public String scored(int point)
    {
        score = score + point;
        return String.valueOf(score);
    }

    public Boolean WillImageBeAnimated()
    {
        return rand.nextBoolean();
    }

    public void menuClicked(View view)
    {
        Log.i(TAG, "menuClicked method");
        Intent myIntent = new Intent(this, MenuActivity.class);
        MainActivity.this.startActivity(myIntent);
    }

    public void soundBtnClicked(View view)
    {
        if(player.isPlaying())
        {
            player.pause();
            soundBtn.setImageResource(R.drawable.soundoff);
        }
        else
        {
            player.start();
            soundBtn.setImageResource(R.drawable.soundon);
        }

    }

    public void restartBtnClicked(View view)
    {
        restartTheGame();
    }

    public String whatIsTheTimeNow(){
        seconds = seconds - 1;
        return String.valueOf(seconds);
    }

    public void removeTouchEvent(ImageView img)
    {
        img.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });
    }

    public void addTouchEvent(final ImageView img)
    {
        img.setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                int action = event.getAction();
                int x = (int) event.getX();
                int y = (int) event.getY();

                switch (action)
                {
                    case MotionEvent.ACTION_DOWN:
                        //Checking if the user clicked the block
                        if(blockView0.equals(img) || blockView1.equals(img) || blockView2.equals(img))
                        {
                            scoreOutput.setText(scored(0));
                        }
                        else
                        {
                            img.setImageResource(R.drawable.mole_2);
                            scoreOutput.setText(scored(100));
                            Log.d(TAG, "Monster was touched at (" + x + ", " + y + ")");
                        }
                        return true;

                    case MotionEvent.ACTION_UP:
                        removeTouchEvent(img);
                        v.performClick();
                        return true;

                    case MotionEvent.ACTION_MOVE:
                        return true;

                    default:
                        return true; // handle all actions that start on the balloon
                }

            }

        });
    }

    @Override
    public void onDestroy()
    {
        updatesTimer.cancel();
    }

    class myNotifier extends TimerTask
    {
        private Handler handler = new Handler();

        @Override
        public void run()
        {
            handler.post(doUpdateGUI);
        }
    };

   private Runnable doUpdateGUI = new Runnable()
   {
        public void run()
        {
            //Updating the input time
            timeOutput.setText(whatIsTheTimeNow());

            if(seconds == 0)
            {
                gameOver();
            }
            else
            {
                //Foreach to each image to animate
                for (ImageView monster : monsters) {
                    if (WillImageBeAnimated()) {
                        animate(monster);
                    }
                }
            }
        }
    };

    public void gameOver()
    {
        onDestroy();
        gameOverOutput.setText("Your final score was: " + String.valueOf(score));
        gameOverOutput.setVisibility(View.VISIBLE);
        restartBtn.setVisibility(View.VISIBLE);
        gameOverBackground.setVisibility(View.VISIBLE);
    }

    public void restartTheGame()
    {

        //restart the outputs time and score
        setOutputsTimeAndScore();

        //restart the background sound - in the first moment stop the old music
        // and start a new one
        player.stop();
        setBackgroundAudio();

        //start a new schedule
        reScheduleTimer(SECOND);
    }

    public void reScheduleTimer(int interval)
    {
        updatesTimer = new Timer();
        updatesTimer.schedule(new myNotifier(), interval, interval);
    }
}
