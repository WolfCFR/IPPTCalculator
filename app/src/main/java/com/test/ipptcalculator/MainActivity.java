package com.test.ipptcalculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.github.pwittchen.swipe.library.Swipe;
import com.github.pwittchen.swipe.library.SwipeListener;

public class MainActivity extends AppCompatActivity {
    private Swipe swipe; // Declaring swipe from a 3rd party library

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) { //on motion event using the 3rd party swipe library
        swipe.dispatchTouchEvent(event);
        return super.dispatchTouchEvent(event);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        swipe = new Swipe(); // initializing swipe
        swipe.setListener(new SwipeListener() { //setting swipe listener

            @Override
            public void onSwipingLeft(MotionEvent event) {

            }

            @Override public void onSwipedLeft(final MotionEvent event) { //on swiped left event
                Intent i = new Intent(MainActivity.this, calculator.class); // initializing intent i to open calculator.class
                startActivity(i);// start activity i
                finish(); //end current activity
            }

            @Override
            public void onSwipingRight(MotionEvent event) {

            }


            @Override public void onSwipedRight(final MotionEvent event) {

            }
            @Override
            public void onSwipingUp(MotionEvent event) {
            }

            @Override
            public void onSwipedUp(MotionEvent event) {
            }
            @Override
            public void onSwipingDown(MotionEvent event) {

            }

            @Override
            public void onSwipedDown(MotionEvent event) {

            }

        });

        Button home = (Button) findViewById(R.id.homeButton); // initializing buttons for navigation bar
        Button cal = (Button) findViewById(R.id.calButton);
        Button sL = (Button) findViewById(R.id.scoreList);


        cal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //on click listener (calculator)
                Intent i = new Intent(MainActivity.this, calculator.class); // Initializing intent i to open calculator.class
                startActivity(i); //start activity i
                finish(); // end current activity

            }
        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//on click listener (home)
                Toast.makeText(MainActivity.this, "You are on Home screen", Toast.LENGTH_SHORT).show(); // Toast to notify user that they are on the same screen
            }
        });

        sL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//on click listener (scorelist)
                Intent i = new Intent(MainActivity.this, score_list.class); // Initializing intent i to open score_list.class
                startActivity(i); // start activity i
                finish(); // end current activity
            }
        });
    }
}
