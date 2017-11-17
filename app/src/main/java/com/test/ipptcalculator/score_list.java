package com.test.ipptcalculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

import com.github.pwittchen.swipe.library.Swipe;
import com.github.pwittchen.swipe.library.SwipeListener;

import java.util.ArrayList;
import java.util.List;

public class score_list extends AppCompatActivity {

    private Swipe swipe; //Declaring swipe from a 3rd party library


    @Override
    public boolean dispatchTouchEvent(MotionEvent event) { //on motion event using the 3rd party swipe library
        swipe.dispatchTouchEvent(event);
        return super.dispatchTouchEvent(event);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_list);
        swipe = new Swipe(); //initializing swipe
        swipe.setListener(new SwipeListener() { //set swipe listener

            @Override
            public void onSwipingLeft(MotionEvent event) {

            }

            @Override public void onSwipedLeft(final MotionEvent event) {

            }

            @Override
            public void onSwipingRight(MotionEvent event) {

            }


            @Override public void onSwipedRight(final MotionEvent event) { //on right swiped event
                Intent i = new Intent(score_list.this, calculator.class); //intent to open calculator.class
                startActivity(i); //start activity intent
                finish(); // end of activity
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
        Button home = (Button) findViewById(R.id.homeButton); //Initializing app navigation buttons
        Button cal = (Button) findViewById(R.id.calButton);
        Button sL = (Button) findViewById(R.id.scoreList);
        final RadioButton pu = (RadioButton) findViewById(R.id.puButton);  //Initializing the radio buttons for users to select
        final RadioButton su = (RadioButton) findViewById(R.id.suButton);
        final RadioButton run = (RadioButton) findViewById(R.id.runButton);
        final ImageView image = (ImageView) findViewById(R.id.imageView); //Initializing ImageView to be displayed on screen

        pu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//on radio button clicked (push up)
                image.setImageResource(R.drawable.pushup);  // set imageView to push up scoring list


            }
        });
        su.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //on radio button clicked  (sit up)
                image.setImageResource(R.drawable.situp); //set imageView to sit up scoring list


            }
        });
        run.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //on radio button clicked (2.4km)
                image.setImageResource(R.drawable.km24); //set imageView to 2.4km scoring list


            }
        });







        cal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { // on click listener for navigation bar (calculator)
                Intent i = new Intent(score_list.this, calculator.class); // initializing intent i to open calculator.class
                startActivity(i); //start activity i
                finish(); //end current activity

            }
        });

        sL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { // on click listener for navigation bar (scorelist)
                Toast.makeText(score_list.this, "You are on score list screen", Toast.LENGTH_SHORT).show();
            }
        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { // on click listener for navigation bar (home)
                Intent i = new Intent(score_list.this, MainActivity.class);// initializing intent i to open MainActivity.class
                startActivity(i); //start activity i
                finish(); // end current activity
            }
        });

    }
}
