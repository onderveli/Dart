package chatapp.yazilimciakli.dart;

import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {


    private static final String TAG = "MainActivity";
    ImageView arrow;

    int width, height, limit;
    boolean isTouched = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        arrow = findViewById(R.id.imageView2);


        width = getWindow().getWindowManager().getDefaultDisplay().getWidth();
        height = getWindow().getWindowManager().getDefaultDisplay().getHeight();


        arrow.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                isTouched = true;

                Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
                vibrator.vibrate(250);


                return false;
            }
        });


    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        Log.d(TAG, "onTouch: x:" + event.getX());

        if (isTouched) {


            arrow.setX(event.getX() - arrow.getWidth() / 2);
            arrow.setY(event.getY() - arrow.getHeight() / 2 - 250);

            float scaleRatio = (event.getY() - limit)/(height - limit);


            if (limit < event.getY()) {

                arrow.setScaleY(scaleRatio);
                arrow.setScaleX(scaleRatio);

            } else if (limit == event.getY()) {
                arrow.setScaleY(1f);
                arrow.setScaleX(1f);
            } else {
                arrow.setScaleY(scaleRatio);
                arrow.setScaleX(scaleRatio);
            }

        }

        return super.onTouchEvent(event);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

        limit = height - (height / 2) - (arrow.getHeight() / 2);


        arrow.setX(width - (width / 2) - (arrow.getWidth() / 2));
        arrow.setY(limit);

    }


}
