package edu.sjsu.fall2016.cs175.fragmentdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        String pokemonName = intent.getStringExtra("pokemon_name");

        ImageView image = (ImageView) findViewById(R.id.pokemon);
        int imageId = getResources().getIdentifier(pokemonName.toLowerCase(),"drawable",getPackageName());
        image.setImageResource(imageId);

        TextView textView = (TextView) findViewById(R.id.name);
        textView.setText(pokemonName);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}
