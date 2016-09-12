package edu.sjsu.fall2016.cs175.tmnt;

import android.media.MediaPlayer;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i("lifecyle", "onCreate called");

        if (savedInstanceState != null)
            Toast.makeText(this, savedInstanceState.getString("tmnt_image", "none").toString(), Toast.LENGTH_LONG).show();

        mp = MediaPlayer.create(this, R.raw.tmnt);
        Spinner spin = (Spinner) findViewById(R.id.turtle_chooser);
        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> spin, View v, int i, long id) {
                TextView result = (TextView) findViewById(R.id.turtle_result);
                result.setText("You chose " + spin.getSelectedItem());

            }

            public void onNothingSelected(AdapterView<?> parent) {
            } // empty });
        });
    }


    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        outState.putString("tmnt_image", "raph");
        Log.i("lifecyle", "SavingInstance called");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("lifecyle", "OnStop called");
        //mp.pause();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("lifecyle", "onPause called");
        //mp.start();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("lifecyle", "onResume called");
        //mp.start();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("lifecyle", "onDestroy called");
        //mp.start();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("lifecyle", "onStart called");
        //mp.start();
    }

    public void toggleButton(View view) {
        ImageButton ib = (ImageButton) findViewById(R.id.imageButton);
        assert ib != null;
        CheckBox cb = (CheckBox) findViewById(R.id.toggleDisplay);
        if (cb.isChecked()) {
            ib.setVisibility(View.VISIBLE);
        } else {
            ib.setVisibility(View.INVISIBLE);
        }
    }
    public void changePicture(View view) {
        ImageButton ib = (ImageButton) findViewById(R.id.imageButton);
        assert ib != null;
        if (view.getId() == R.id.don) {
            ib.setImageResource(R.drawable.donatello);
        }
        if (view.getId() == R.id.leo) {
            ib.setImageResource(R.drawable.leo);
        }
        if (view.getId() == R.id.mikey) {
            ib.setImageResource(R.drawable.mikey);
        }
        if (view.getId() == R.id.raph) {
            ib.setImageResource(R.drawable.raphael);
        }
    }

}
