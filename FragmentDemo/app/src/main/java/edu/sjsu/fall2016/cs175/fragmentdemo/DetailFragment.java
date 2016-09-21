package edu.sjsu.fall2016.cs175.fragmentdemo;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class DetailFragment extends Fragment {


    public DetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Activity act = getActivity();
        Intent intent = act.getIntent();
        String pokemonName = intent.getStringExtra("pokemon_name");
        if (pokemonName == null)
            pokemonName = "squirtle";
        setValue(pokemonName);

    }

    public void setValue(String pokemonName) {
        Activity act = getActivity();
        ImageView image = (ImageView) act.findViewById(R.id.pokemon);
        int imageId = getResources().getIdentifier(pokemonName.toLowerCase(),"drawable",
                act.getPackageName());
        image.setImageResource(imageId);

        TextView textView = (TextView) act.findViewById(R.id.name);
        textView.setText(pokemonName);
    }

}

