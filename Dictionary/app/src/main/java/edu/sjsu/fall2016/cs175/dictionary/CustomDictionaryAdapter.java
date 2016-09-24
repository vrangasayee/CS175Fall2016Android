package edu.sjsu.fall2016.cs175.dictionary;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by narayan on 9/24/16.
 */
public class CustomDictionaryAdapter extends ArrayAdapter<String> {

    private final Context context;
    private final List<String> words;
    private final int resource;


    public CustomDictionaryAdapter(Context context, int resource, @NonNull ArrayList<String> objects) {
        super(context, resource);
        this.context = context;
        this.resource = resource;
        this.words = objects;
    }

    @Override
    public int getCount() {
        return words.size();
    }

    @Override
    public String getItem(int position) {
        return words.get(position);
    }

    @Override
    public View getView(int position, View row, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.word_layout, parent, false);
        TextView tv1 = (TextView) rowView.findViewById(R.id.lower);
        tv1.setText(words.get(position).toString().toUpperCase());
        return rowView;
    }
}
