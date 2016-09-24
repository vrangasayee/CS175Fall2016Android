package edu.sjsu.fall2016.cs175.dictionary;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class VocabQuiz extends AppCompatActivity {

    private HashMap<String, String> dictionary = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vocab_quiz);
        setListStandardAdapter();
    }

    private void readAll() {
        Scanner scan = new Scanner(getResources().openRawResource(R.raw.gre_words));
        while(scan.hasNextLine()) {
            String line = scan.nextLine();
            String[] pieces = line.split("\t");
            dictionary.put(pieces[0], pieces[1]);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_words, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.customview)
            setListCustomView();
        if (item.getItemId() == R.id.customadapter)
            setListCustomAdapter();
        if (item.getItemId() == R.id.standardadapter)
            setListStandardAdapter();
        return super.onOptionsItemSelected(item);
    }

    private void setListCustomAdapter() {
        if(dictionary.isEmpty())
            readAll();
        ArrayList<String> word_list = new ArrayList<>(dictionary.keySet());
        CustomDictionaryAdapter adapter = new CustomDictionaryAdapter(
                this,
                R.layout.word_layout,
                word_list);
        final ListView listView = (ListView) findViewById(R.id.word_meaning);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(VocabQuiz.this, parent.getAdapter().getItem(position).toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setListCustomView() {
        if(dictionary.isEmpty())
            readAll();
        ArrayList<String> word_list = new ArrayList<>(dictionary.keySet());
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this,
                R.layout.word_layout,
                R.id.lower,
                word_list);
        final ListView listView = (ListView) findViewById(R.id.word_meaning);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(VocabQuiz.this, parent.getAdapter().getItem(position).toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setListStandardAdapter() {
        if(dictionary.isEmpty())
            readAll();
        ArrayList<String> word_list = new ArrayList<>(dictionary.keySet());
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                word_list);
        final ListView listView = (ListView) findViewById(R.id.word_meaning);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(VocabQuiz.this, parent.getAdapter().getItem(position).toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}

