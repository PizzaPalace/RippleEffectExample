package com.matchify.ripple;

import android.graphics.Point;
import android.graphics.Rect;
import android.hardware.display.DisplayManager;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.squareup.picasso.Picasso;

import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends ActionBarActivity {

    RippleView rippleView;
    Handler mHandler;
    ImageView mImageView;
    RelativeLayout mRelativeLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mImageView = (ImageView)findViewById(R.id.center_screen);
        mRelativeLayout = (RelativeLayout) findViewById(R.id.container_view);

        WindowManager windowManager = (WindowManager)getSystemService(WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();
        Point point = new Point();
        display.getSize(point);
        final int x = point.x/2;
        final int y = point.y/2;


        float Y = mImageView.getLeft();
        float X = mImageView.getRight();

        Log.v("X", Float.toString(X));
        Log.v("Y", Float.toString(Y));

        final float centerX = mRelativeLayout.getX() + (mRelativeLayout.getWidth() /2);
        final float centerY = mRelativeLayout.getY() + (mRelativeLayout.getHeight() /2);

        rippleView = (RippleView) findViewById(R.id.more);
        mHandler = new Handler();

        Timer timer = new Timer();

        timer.scheduleAtFixedRate(new TimerTask(){

            @Override
            public void run() {

                mHandler.post(new Runnable(){

                    @Override
                    public void run() {

                        rippleView.animateRipple(x,y);


                    }
                });

            }
        },3000,3000);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
