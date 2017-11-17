package com.test.ipptcalculator;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.github.pwittchen.swipe.library.Swipe;
import com.github.pwittchen.swipe.library.SwipeListener;

import java.util.ArrayList;
import java.util.List;

public class calculator extends AppCompatActivity {

    private Swipe swipe;// Declaring swipe using a 3rd party library

    String nameSaved ="",ageSaved ="", puSaved ="", suSaved ="", runSaved ="", totalSaved =""; // initializing String name, age, push up, sit up, 2.4km and total score for preference shares
    private int puScore,suScore,runScore; //initializing integer for the push up, sit up and 2.4km score
    private Spinner secSpin,minSpin,puSpin,suSpin,ageSpin; //initializing Spinner(Dropdown)
    private String puList = "",suList = "" ,runList =""; //initializing String puList(push up) and suList(sit up)
    @Override
    public boolean dispatchTouchEvent(MotionEvent event) { //on motion event using swipe event
        swipe.dispatchTouchEvent(event);
        return super.dispatchTouchEvent(event);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        swipe = new Swipe(); //initializing swipe
        swipe.setListener(new SwipeListener() { //set swipe listener
            @Override
            public void onSwipingLeft(MotionEvent event) {
            }
            @Override public void onSwipedLeft(final MotionEvent event) { //on swiped left event
                Intent i = new Intent(calculator.this, score_list.class); //initializing intent i to open score_list.class
                startActivity(i); //start activity i
                finish(); //end current activity
            }

            @Override
            public void onSwipingRight(MotionEvent event) {

            }


            @Override public void onSwipedRight(final MotionEvent event) { //on swiped right event
                Intent i = new Intent(calculator.this, MainActivity.class); //initializing intent i to open MainActivity.class
                startActivity(i); //start activity i
                finish(); //end current activity
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

        // Input for Name

        final EditText name = (EditText) findViewById(R.id.nameText); // initializing name equals to the input name

        // All the Result text view displayed below the calculate button
        final TextView nameR = (TextView) findViewById(R.id.nameResult); //(name) initializing all the textview which displays the results
        final TextView ageR = (TextView) findViewById(R.id.ageResult);// (age)
        final TextView puR = (TextView) findViewById(R.id.puResult); // (push up)
        final TextView suR = (TextView) findViewById(R.id.suResult); // (sit up)
        final TextView runR = (TextView) findViewById(R.id.runResult); // (2.4 km)
        final TextView totalR = (TextView) findViewById(R.id.totalResult); //(total score)

        // SharedPreferences initializing and setting
        final SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE); // initializing SharedPreferences
        nameSaved= sharedPref.getString("name", ""); // (saved name) get Saved sharedpreferences
        ageSaved = sharedPref.getString("age", "");// (saved age)
        puSaved = sharedPref.getString("pu", ""); // (push up saved score)
        suSaved = sharedPref.getString("su",""); //(sit up saved score)
        runSaved = sharedPref.getString("run",""); //(2.4km saved score)
        totalSaved =sharedPref.getString("total",""); // (total saved score)
        nameR.setText(nameSaved);// set text view as the saved sharedpreferences
        ageR.setText(ageSaved); //~
        puR.setText(puSaved); //~
        suR.setText(suSaved);//~
        runR.setText(runSaved);//~
        totalR.setText(totalSaved);//set text view as the saved sharedpreferences

        // Home Button and Calculate Button
        Button home = (Button) findViewById(R.id.homeButton); //initializing home button
        Button cal = (Button) findViewById(R.id.calButton); //initializing calculate button
        Button sL = (Button) findViewById(R.id.scoreList);
        sL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(calculator.this, score_list.class);
                startActivity(i);
                finish();
            }
        });
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { // (Home) on click listener
                Intent i = new Intent(calculator.this, MainActivity.class); //initializing intent to open MainActivity.class file
                startActivity(i); //startActivity intent
                finish(); //close calculator.this file
            }
        });

        cal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { // (calculator) on click listener
                Toast.makeText(calculator.this, "You are on the calculator screen", Toast.LENGTH_SHORT).show(); // Toast display error msg
            }
        });



        //******************Calculate IPPT score!*****************

        Button calculate = (Button) findViewById(R.id.calculateButton); //initializing calculate button
        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //(calculate) on click listener
                int age = Integer.parseInt(ageSpin.getSelectedItem().toString()); //initializing integer age from selected age spinner
                int run = 0, check = 0, totalScore = 0; //initializing integer run, check and totalScore for calculation
                int pu = Integer.parseInt(puSpin.getSelectedItem().toString()); //initializing integer pu(push up reps) from selected push up spinner
                int su = Integer.parseInt(suSpin.getSelectedItem().toString()); //initializing integer su(sit up reps) from selected sit up spinner
                int runMin = Integer.parseInt(minSpin.getSelectedItem().toString()); //initializing integer runMin(2.4km minutes) from selected 2.4km minute spinner
                int runSec = Integer.parseInt(secSpin.getSelectedItem().toString()); //initializing integer runSec(2.4km seconds) from selected 2.4km second spinner

                if(age<22){ // age below 22
                    puList = "25,24,24,24,24,23,23,23,23,22,22,22,22,21,21,21,21,20,20,20,20,19,19,19,18,18,18,17,17,17,16,16,16,15,15,14,13,12,11,10,9,8,6,4,2,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0"; //Scoring List for push up
                    suList = "25,24,24,24,24,23,23,23,23,22,22,22,22,21,21,21,21,20,20,20,20,19,19,18,18,17,16,15,14,14,13,13,12,11,10,9,8,7,7,6,6,5,4,3,2,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0";//Scoring List for sit up
                } else if (age>= 22 && age <=24 ) { //age between 22 and 24
                    puList = "25,25,24,24,24,24,23,23,23,23,22,22,22,22,21,21,21,21,20,20,20,20,19,19,19,18,18,18,17,17,17,16,16,16,15,15,14,13,12,11,10,9,8,6,4,2,1,0,0,0,0,0,0,0,0,0,0,0,0,0";//Scoring List for push up
                    suList = "25,25,24,24,24,24,23,23,23,23,22,22,22,22,21,21,21,21,20,20,20,20,19,19,18,18,17,16,15,14,14,13,13,12,11,10,9,8,7,7,6,6,5,4,3,2,1,0,0,0,0,0,0,0,0,0,0,0,0,0";//Scoring List for sit up
                    run = 1; //2.4 km calculation
                } else if (age>= 25 && age <=27 ) { // age between 25 and 27
                    puList = "25,25,25,24,24,24,24,23,23,23,23,22,22,22,22,21,21,21,21,20,20,20,20,19,19,19,18,18,18,17,17,17,16,16,16,15,15,14,13,12,11,10,9,8,6,4,2,1,0,0,0,0,0,0,0,0,0,0,0,0";//Scoring List for push up
                    suList = "25,25,25,24,24,24,24,23,23,23,23,22,22,22,22,21,21,21,21,20,20,20,20,19,19,18,18,17,16,15,14,14,13,13,12,11,10,9,8,7,7,6,6,5,4,3,2,1,0,0,0,0,0,0,0,0,0,0,0,0";//Scoring List for sit up
                    run = 2;//2.4 km calculation
                } else if (age>= 28 && age <=30 ) { //age between 28 and 30
                    puList = "25,25,25,25,24,24,24,24,23,23,23,23,22,22,22,22,21,21,21,21,20,20,20,20,19,19,19,18,18,18,17,17,17,16,16,16,15,15,14,13,12,11,10,9,8,6,4,2,1,0,0,0,0,0,0,0,0,0,0,0";//Scoring List for push up
                    suList = "25,25,25,25,24,24,24,24,23,23,23,23,22,22,22,22,21,21,21,21,20,20,20,20,19,19,18,18,17,16,15,14,14,13,13,12,11,10,9,8,7,7,6,6,5,4,3,2,1,0,0,0,0,0,0,0,0,0,0,0";//Scoring List for sit up
                    run = 4;//2.4 km calculation
                } else if (age>= 31 && age <=33 ) { //age between 31 and 33
                    puList = "25,25,25,25,25,24,24,24,24,23,23,23,23,22,22,22,22,21,21,21,21,20,20,20,20,19,19,19,18,18,18,17,17,17,16,16,16,15,15,14,13,12,11,10,9,8,6,4,2,1,0,0,0,0,0,0,0,0,0,0";//Scoring List for push up
                    suList = "25,25,25,25,25,24,24,24,24,23,23,23,23,22,22,22,22,21,21,21,21,20,20,20,20,19,19,18,18,17,16,15,14,14,13,13,12,11,10,9,8,7,7,6,6,5,4,3,2,1,0,0,0,0,0,0,0,0,0,0";//Scoring List for sit up
                    run = 5;//2.4 km calculation
                } else if (age>= 34 && age <=36 ) { //age between 34 and 36
                    puList = "25,25,25,25,25,25,24,24,24,24,23,23,23,23,22,22,22,22,21,21,21,21,20,20,20,20,19,19,19,18,18,18,17,17,17,16,16,16,15,15,14,13,12,11,10,9,8,6,4,2,1,0,0,0,0,0,0,0,0,0";//Scoring List for push up
                    suList = "25,25,25,25,25,25,24,24,24,24,23,23,23,23,22,22,22,22,21,21,21,21,20,20,20,20,19,19,18,18,17,16,15,14,14,13,13,12,11,10,9,8,7,7,6,6,5,4,3,2,1,0,0,0,0,0,0,0,0,0";//Scoring List for sit up
                    run = 6;//2.4 km calculation
                } else if (age>= 37 && age <=39 ) { //age between 37 and 39
                    puList = "25,25,25,25,25,25,25,24,24,24,24,23,23,23,23,22,22,22,22,21,21,21,21,20,20,20,20,19,19,19,18,18,18,17,17,17,16,16,16,15,15,14,13,12,11,10,9,8,6,4,2,1,0,0,0,0,0,0,0,0";//Scoring List for push up
                    suList = "25,25,25,25,25,25,25,24,24,24,24,23,23,23,23,22,22,22,22,21,21,21,21,20,20,20,20,19,19,18,18,17,16,15,14,14,13,13,12,11,10,9,8,7,7,6,6,5,4,3,2,1,0,0,0,0,0,0,0,0";//Scoring List for sit up
                    run = 7;//2.4 km calculation
                } else if (age>= 40 && age <=42 ) { //age between 40 and 42
                    puList = "25,25,25,25,25,25,25,25,24,24,24,24,23,23,23,23,22,22,22,22,21,21,21,21,20,20,20,20,19,19,19,18,18,18,17,17,17,16,16,16,15,15,14,13,12,11,10,9,8,6,4,2,1,0,0,0,0,0,0,0";//Scoring List for push up
                    suList = "25,25,25,25,25,25,25,25,24,24,24,24,23,23,23,23,22,22,22,22,21,21,21,21,20,20,20,20,19,19,18,18,17,16,15,14,14,13,13,12,11,10,9,8,7,7,6,6,5,4,3,2,1,0,0,0,0,0,0,0";//Scoring List for sit up
                    run = 8;//2.4 km calculation
                } else if (age>= 43 && age <=45 ) { //age between 43 and 45
                    puList = "25,25,25,25,25,25,25,25,25,25,24,24,24,24,23,23,23,23,22,22,22,22,21,21,21,21,20,20,20,19,19,19,18,18,18,17,17,17,16,16,16,15,15,14,13,12,11,10,9,8,6,4,2,1,0,0,0,0,0,0";//Scoring List for push up
                    suList = "25,25,25,25,25,25,25,25,25,25,24,24,24,24,23,23,23,23,22,22,22,22,21,21,21,21,20,20,20,19,19,18,18,17,16,15,14,14,13,13,12,11,10,9,8,7,7,6,6,5,4,3,2,1,0,0,0,0,0,0";//Scoring List for sit up
                    run = 9;//2.4 km calculation
                } else if (age>= 46 && age <=48 ) { //age between 46 and 48
                    puList = "25,25,25,25,25,25,25,25,25,25,25,25,24,24,24,24,23,23,23,23,22,22,22,22,21,21,21,20,20,20,19,19,19,18,18,18,17,17,17,16,16,16,15,15,14,13,12,11,10,9,8,6,4,2,1,0,0,0,0,0";//Scoring List for push up
                    suList = "25,25,25,25,25,25,25,25,25,25,25,25,24,24,24,24,23,23,23,23,22,22,22,22,21,21,21,20,20,20,19,19,18,18,17,16,15,14,14,13,13,12,11,10,9,8,7,7,6,6,5,4,3,2,1,0,0,0,0,0";//Scoring List for sit up
                    run = 10;//2.4 km calculation
                } else if (age>= 49 && age <=51 ) { //age between 49 and 51
                    puList = "25,25,25,25,25,25,25,25,25,25,25,25,25,25,24,24,24,24,23,23,23,23,22,22,22,21,21,21,20,20,20,19,19,19,18,18,18,17,17,17,16,16,16,15,15,14,13,12,11,10,9,8,6,4,2,1,0,0,0,0";//Scoring List for push up
                    suList = "25,25,25,25,25,25,25,25,25,25,25,25,25,24,24,24,24,23,23,23,23,22,22,22,22,21,21,21,20,20,20,19,19,18,18,17,16,15,14,14,13,13,12,11,10,9,8,7,7,6,6,5,4,3,2,1,0,0,0,0";//Scoring List for sit up
                    run = 11;//2.4 km calculation
                } else if (age>= 52 && age <=54 ) { //age between 52 and 54
                    puList = "25,25,25,25,25,25,25,25,25,25,25,25,25,25,25,25,24,24,24,24,23,23,23,22,22,22,21,21,21,20,20,20,19,19,19,18,18,18,17,17,17,16,16,16,15,15,14,13,12,11,10,9,8,6,4,2,1,0,0,0";//Scoring List for push up
                    suList = "25,25,25,25,25,25,25,25,25,25,25,25,25,25,25,24,24,24,24,23,23,23,23,22,22,22,21,21,21,20,20,20,19,19,18,18,17,16,15,14,14,13,13,12,11,10,9,8,7,7,6,6,5,4,3,2,1,0,0,0";//Scoring List for sit up
                    run = 12;//2.4 km calculation
                } else if (age>= 55 && age <=57 ) { //age between 55 and 57
                    puList = "25,25,25,25,25,25,25,25,25,25,25,25,25,25,25,25,25,25,25,24,24,24,23,23,23,22,22,22,21,21,21,20,20,19,19,19,18,18,18,17,17,17,16,16,16,15,15,14,13,12,11,10,9,8,6,4,2,1,0,0";//Scoring List for push up
                    suList = " 25,25,25,25,25,25,25,25,25,25,25,25,25,25,25,25,25,24,24,24,24,23,23,23,22,22,22,21,21,21,20,20,20,19,19,18,18,17,16,15,14,14,13,13,12,11,10,9,8,7,7,6,6,5,4,3,2,1,0,0";//Scoring List for sit up
                    run = 13;//2.4 km calculation
                } else if (age>= 58 && age <=60 ) { //age between 58 and 60
                    puList = "25,25,25,25,25,25,25,25,25,25,25,25,25,25,25,25,25,25,25,25,25,24,24,24,23,23,23,22,22,22,21,21,20,20,19,19,19,18,18,18,17,17,17,16,16,16,15,15,14,13,12,11,10,9,8,6,4,2,1,0";//Scoring List for push up
                    suList = "25,25,25,25,25,25,25,25,25,25,25,25,25,25,25,25,25,25,25,24,24,24,23,23,23,22,22,22,21,21,21,20,20,20,19,19,18,18,17,16,15,14,14,13,13,12,11,10,9,8,7,7,6,6,5,4,3,2,1,0";//Scoring List for sit up
                    run = 14;//2.4 km calculation
                }
                String[] susplit = suList.split(","); //initializing sit up split string from the sit up list
                String[] pusplit = puList.split(",");//initializing push up split string from the push up list
                for(int x=60; x> 0; x--){ //for loop to get the the scores for sit up and push up
                    if(x == su){ //if x = to sit up int (spinner) selected by user
                        suScore = Integer.parseInt(susplit[60-x]); //intializing suScore equals to situp split list
                    }else if(x == pu){ //if x = to push up up int (spinner) selected by user
                        puScore = Integer.parseInt(pusplit[60-x]);//intializing suScore equals to pushup split list
                    }


                }
                runList = "0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,2,4,6,8,10,12,14,16,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,35,36,36,37,37,38,38,39,40,41,42,43,44,45,46,47,48,49,50";//Scoring List for 2.4km
                String[] runsplit = runList.split(","); //initializing 2.4km split string from the 2.4km list
                for (int z = 8; z <=18; z++) { //loop to get the minutes
                    for (int y = 0; y <60; y += 10) { //loop to get the seconds

                        if (z == 8 && y == 0) { // if z(minute) and y(seconds) equals to 8 and 0 respectively
                            y = 20; //initialize y(seconds) to 20 (starts from 20 second)
                        }
                        if(z == 18 && y == 30){// if z(minute) and y(seconds) equals to 18 and 30 respectively
                            break; //end of loop
                        }
                        check++;//calculate amount of loops

                        if (z == runMin && y == runSec) { //if z(minute) and y(seconds) equals to selected spinner
                            if((60-check+1+run)<60 && (60-check+1+run>=0)) { //if calculation within 0 and 60
                                runScore = Integer.parseInt(runsplit[60-check+1+run]); // set 2.4km score to runsplit(calculation)
                            }else if((60-check+1+run<0)) { // if calculation is below 0
                                runScore = 0; // initialize 2.4km score to 0
                            }else{ //else - more than 59
                                runScore = 50; //initialize 2.4km score to 50

                            }

                            break; //end loop
                        }

                    }
                }
                totalScore = runScore + puScore + suScore;//initialize total score by adding 2.4km, push up and sit up scores.
                String type = ""; // declaring string type
                if(totalScore <= 50){ //if totalscore is less than 50
                    type = "Fail"; // initializing string
                    Toast.makeText(calculator.this, "You can do better next time!!", Toast.LENGTH_SHORT).show(); //notify user
                }else if(totalScore <= 60 && totalScore >= 51 ){//total score between 51 and 60
                    type = "Pass";// initializing string
                    Toast.makeText(calculator.this, "Congratulation! You Passed!!", Toast.LENGTH_SHORT).show();//notify user
                }else if(totalScore <= 74 && totalScore >= 61 ) {//total score between 61 and 74
                    type = "Pass with Incentive";// initializing string
                    Toast.makeText(calculator.this, "Congratulation! You Passed with Incentive!!", Toast.LENGTH_SHORT).show();//notify user
                }else if(totalScore <= 84 && totalScore >= 75 ){//total score between 75 and 84
                    type = "Silver";// initializing string
                    Toast.makeText(calculator.this, "Congratulation! You have achieved Silver!!", Toast.LENGTH_SHORT).show();//notify user
                }else if(totalScore <= 89 && totalScore >= 85 ){//total score between 85 and 89
                    type = "Gold";// initializing string
                    Toast.makeText(calculator.this, "Congratulation! You have achieved Gold!!", Toast.LENGTH_SHORT).show();//notify user
                }else if(totalScore <= 100 && totalScore >= 90 ){//total score between 90 and 100
                    type = "Gold(C/G)";// initializing string
                    Toast.makeText(calculator.this, "Congratulation! You have achieved Gold(C/G)!!", Toast.LENGTH_SHORT).show();//notify user
                }
                nameSaved = "Name:" + name.getText().toString();//initializing String
                ageSaved = "Age:" + age; // initializing string
                puSaved = "Push Up ("+pu+"): "+puScore;// initializing string
                suSaved = "Sit Up ("+su+"): "+suScore;// initializing string
                runSaved = "2.4km ("+runMin +":"+runSec+"): "+runScore;// initializing string
                totalSaved = "Total: " +totalScore +"\n ("+type+")";// initializing string
                nameR.setText(nameSaved); //set TextView to string saved
                ageR.setText(ageSaved);//set TextView to string saved
                puR.setText(puSaved);//set TextView to string saved
                suR.setText(suSaved);//set TextView to string saved
                runR.setText(runSaved);//set TextView to string saved
                totalR.setText(totalSaved);//set TextView to string saved
                SharedPreferences.Editor editor = sharedPref.edit(); //edit shared preference
                editor.putString("name", nameSaved); // set String to respective array
                editor.putString("age", ageSaved);// set String to respective array
                editor.putString("pu", puSaved);// set String to respective array
                editor.putString("su", suSaved);// set String to respective array
                editor.putString("run", runSaved);// set String to respective array
                editor.putString("total", totalSaved);// set String to respective array
                editor.commit();//commit shared preference






            }
        });




        // Spinner for Age
        List age = new ArrayList<Integer>();//declaring list age array
        for (int i = 18; i <= 60; i++) { //loop from 18 to 60
            age.add(Integer.toString(i)); // add string i to age array
        }
        //Array Adapter to put into the spinner
        ArrayAdapter<Integer> agespinnerArrayAdapter = new ArrayAdapter<Integer>(
                this, android.R.layout.simple_spinner_item, age);
        agespinnerArrayAdapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item );

        ageSpin = (Spinner) findViewById(R.id.ageSpinner); //initializing Spinner
        ageSpin.setAdapter(agespinnerArrayAdapter); //put array added into the Spinner

        // Spinner for Push up and Sit up
        List score = new ArrayList<Integer>();//Declaring list for sit up and push up array
        for (int i = 1; i<=60; i++){ //loop from 1 to 60
            score.add(Integer.toString(i)); //add string i to score array
        }
        //Array Adapter to put into the spinner
        ArrayAdapter<Integer> scorespinnerArrayAdapter = new ArrayAdapter<Integer>(
                this, android.R.layout.simple_spinner_item, score);
        scorespinnerArrayAdapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item );

        puSpin = (Spinner) findViewById(R.id.puSpinner);//initializing Spinner
        suSpin = (Spinner) findViewById(R.id.suSpinner);//initializing Spinner
        puSpin.setAdapter(scorespinnerArrayAdapter); //put array added into the Spinner
        suSpin.setAdapter(scorespinnerArrayAdapter); //put array added into the Spinner

        // Spinner for 2.4km - min and seconds
        List min = new ArrayList<Integer>();
        List sec = new ArrayList<Integer>();
        for(int i = 8; i<= 18; i++){
            min.add(Integer.toString(i));
        }
        sec.add(Integer.toString(0));
        sec.add(Integer.toString(10));
        sec.add(Integer.toString(20));
        sec.add(Integer.toString(30));
        sec.add(Integer.toString(40));
        sec.add(Integer.toString(50));

        //minutes
        ArrayAdapter<Integer> minspinnerArrayAdapter = new ArrayAdapter<Integer>(
                this, android.R.layout.simple_spinner_item, min);
        minspinnerArrayAdapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item );

        minSpin = (Spinner) findViewById(R.id.runMinSpinner);
        minSpin.setAdapter(minspinnerArrayAdapter);

        //seconds
        ArrayAdapter<Integer> secspinnerArrayAdapter = new ArrayAdapter<Integer>(
                this, android.R.layout.simple_spinner_item, sec);
        secspinnerArrayAdapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item );

        secSpin = (Spinner) findViewById(R.id.runSecSpinner);
        secSpin.setAdapter(secspinnerArrayAdapter);








    }


}
