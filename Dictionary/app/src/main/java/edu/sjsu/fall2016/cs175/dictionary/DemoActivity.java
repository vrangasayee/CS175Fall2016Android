package edu.sjsu.fall2016.cs175.dictionary;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DemoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);
        Intent intent = getIntent();
        String name = intent.getStringExtra("Name");
        TextView hello = (TextView) findViewById(R.id.hello);
        hello.setText("Hello " + name);
    }
}
