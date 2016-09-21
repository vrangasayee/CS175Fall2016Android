package edu.sjsu.fall2016.cs175.fragmentdemo;

import android.app.Fragment;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class Pokedex extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokedex);
    }


    public void showDetail(View view) {
        if (getResources().getConfiguration().orientation
                == Configuration.ORIENTATION_PORTRAIT) {
            Intent intent = new Intent(this, DetailActivity.class);
            intent.putExtra("pokemon_name", view.getTag().toString());
            startActivity(intent);
        } else {
            // Update the Fragment.
            DetailFragment details = (DetailFragment)getFragmentManager().findFragmentById(R.id.pokemon_fragment);
            details.setValue(view.getTag().toString());
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Toast.makeText(this, item.getTitle(), Toast.LENGTH_LONG).show();
        return super.onOptionsItemSelected(item);
    }
}
