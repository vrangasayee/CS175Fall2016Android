package edu.sjsu.fall2016.cs175.dictionary;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

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

        if(dictionary.isEmpty())
            readAll();
        ArrayList<String> word_list = new ArrayList<>(dictionary.keySet());
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                word_list);
        ListView listView = (ListView) findViewById(R.id.word_meaning);
        listView.setAdapter(adapter);
    }

    private void readAll() {
        Scanner scan = new Scanner(getResources().openRawResource(R.raw.gre_words));
        while(scan.hasNextLine()) {
            String line = scan.nextLine();
            String[] pieces = line.split("\t");
            dictionary.put(pieces[0], pieces[1]);
        }
    }
}

