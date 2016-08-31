package edu.sjsu.fall2016.cs175.biggernumer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private int points = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setNumbers();
    }

    private void setNumbers() {
        // We do not need to keep doing this in every function.
        // Later in course we will use annotations to tie ui with code.
        Button  leftButton = (Button) findViewById(R.id.leftButton);
        Button  rightButton = (Button) findViewById(R.id.rightButton);
        Random r = new Random();
        leftButton.setText("" + r.nextInt(100));
        rightButton.setText("" + r.nextInt(100));
    }

    public void leftButtonClicked(View view) {
        Button  leftButton = (Button) findViewById(R.id.leftButton);
        int numLeft = Integer.parseInt(leftButton.getText().toString());

        Button  rightButton = (Button) findViewById(R.id.rightButton);
        int numRight = Integer.parseInt(rightButton.getText().toString());

        checkAnswer(numLeft, numRight);
        setNumbers();
    }

    // We do not need to duplicate this code. Combining these two functions is left as an exercise
    public void rightButtonClicked(View view) {
        Button  leftButton = (Button) findViewById(R.id.leftButton);
        int numLeft = Integer.parseInt(leftButton.getText().toString());

        Button  rightButton = (Button) findViewById(R.id.rightButton);
        int numRight = Integer.parseInt(rightButton.getText().toString());

        checkAnswer(numRight, numLeft);
        setNumbers();
    }

    private void checkAnswer(int numBigger, int numSmaller) {
        if (numBigger > numSmaller) {
            // The answer is right.
            points++;
            Toast.makeText(this, "You are a GENIUS", Toast.LENGTH_SHORT).show();
        } else {
            points--;
            Toast.makeText(this, "Hmm! Remedial Math!!!", Toast.LENGTH_SHORT).show();
        }
        TextView pointsText = (TextView) findViewById(R.id.pointsText);
        pointsText.setText("Points:" + points);
    }


}
