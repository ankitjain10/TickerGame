package com.example.dell.tickergame;


import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity implements View.OnClickListener {

    Button addButton,subButton;
    TextView outputText,resultText;
    int counter;

    private CountDownTimer countDownTimer;
    private long timeElapsed;
    private boolean timerHasStarted = false;
    private Button startButton;
    private TextView text;
    private TextView timeElapsedView;
    private final long startTime = 10000;
    private final long interval = 1000;


    @Override

    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        counter =0;

        startButton=(Button)findViewById(R.id.startButton);
        startButton.setOnClickListener(this);

        text = (TextView) this.findViewById(R.id.timer);
        timeElapsedView = (TextView) this.findViewById(R.id.timeElapsed);

        countDownTimer = new CountDownTimer(startTime, interval) {

            @Override
            public void onTick(long millisUntilFinished) {
                text.setText("Time remain:" +  (millisUntilFinished / 1000) + " sec");
                timeElapsed = startTime - millisUntilFinished;
                timeElapsedView.setText("Time Elapsed: " + (timeElapsed/1000+1) + " sec");
            }

            @Override
            public void onFinish() {
                resultText=(TextView)findViewById(R.id.resultText);
                resultText.setVisibility(resultText.VISIBLE);
                text.setText("Time's up!");
                long totalTime= (int) (startTime/1000);
                timeElapsedView.setText("Time Elapsed: " + totalTime + " sec");
                addButton.setVisibility(addButton.INVISIBLE);
                subButton.setVisibility(subButton.INVISIBLE);
                startButton.setText("RESET");
                if(counter>0){
                    resultText.setTextColor(Color.BLUE);
                    resultText.setText("Player 1 Wins!! ");
                    outputText.setText("Restart the Game!!");
                }
                else if(counter<0){
                    resultText.setTextColor(Color.GREEN);
                    resultText.setText("Player 2 Wins!! ");
                    outputText.setText("Restart the Game!!");

                }

                else{
                    resultText.setTextColor(Color.RED);
                    resultText.setText("Nobody Wins! Try Again");
                    outputText.setText("Restart the Game!!");

                }
                timerHasStarted=false;
                counter=0;

            }

        };

        text.setText(text.getText() + String.valueOf(startTime/1000)+ " sec");


        addButton=(Button)findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counter++;
                outputText=(TextView)findViewById(R.id.outputText);
                outputText.setText("counter value is : "+counter);

            }
        });
 /*       addButton.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                counter=100;
                outputText=(TextView)findViewById(R.id.outputText);
                outputText.setText("counter value is : "+counter);
                return true;
            }
        });
 */
        subButton=(Button)findViewById(R.id.subButton);
        subButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counter--;
                outputText = (TextView) findViewById(R.id.outputText);
                outputText.setText("counter value is : " + counter);

            }
        });

    }

    public void onClick(View v)
    {
        if (!timerHasStarted )
        {
            counter=0;
            countDownTimer.start();
            timerHasStarted = true;
            startButton.setText("RESTART");
            addButton.setVisibility(addButton.VISIBLE);
            subButton.setVisibility(subButton.VISIBLE);

        }
        else
        {
            counter=0;
            countDownTimer.cancel();
            timerHasStarted = false;
            startButton.setText("Start");
            outputText.setText("counter value is : 0");
            addButton.setVisibility(addButton.INVISIBLE);
            subButton.setVisibility(subButton.INVISIBLE);
            resultText.setVisibility(resultText.INVISIBLE);
        }
        }


}
