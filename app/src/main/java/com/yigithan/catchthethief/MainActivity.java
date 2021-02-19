package com.yigithan.catchthethief;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.graphics.Point;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Display;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    TextView scoreTextView, timeTextView;
    int score, screenX, screenY, maxY, minY;
    ImageView thiefImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        screenX = size.x;
        screenY = size.y;
        score = 0;
        thiefImage = findViewById(R.id.imageView);
        scoreTextView = findViewById(R.id.scoreTextView);
        timeTextView = findViewById(R.id.timeTextView);
        startGame();
    }
        public void startGame(){
            score=0;
            scoreTextView.setText("Score: "+ score);
            new CountDownTimer(10000, 600) {
                @Override
                public void onTick(long millisUntilFinished) {
                    timeTextView.setText("Time: "+ millisUntilFinished/1000);
                    maxY=screenY-2*thiefImage.getMeasuredHeight();
                    float randomX= new Random().nextInt(screenX - thiefImage.getMeasuredWidth());
                    float randomY= new Random().nextInt((maxY-170)+1) + 170;
                    thiefImage.setX(randomX);
                    thiefImage.setY(randomY);
                }

                @Override
                public void onFinish() {
                    timeTextView.setText("Time is finish!");
                    thiefImage.setVisibility(View.INVISIBLE);
                    Toast.makeText(MainActivity.this, "Game Over", Toast.LENGTH_SHORT).show();

                    AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                    alert.setTitle("Restart");
                    alert.setCancelable(false);
                    alert.setMessage("Do you wanna play again ?");
                    alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        startGame();
                        thiefImage.setVisibility(View.VISIBLE);
                        }
                    });
                    alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    });
                    alert.show();

                }
            }.start();
        }

        public void clickThief(View view){
        score++;
        scoreTextView.setText("Score: " + score);
        }
}