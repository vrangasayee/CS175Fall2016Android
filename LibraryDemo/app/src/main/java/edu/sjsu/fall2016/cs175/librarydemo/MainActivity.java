package edu.sjsu.fall2016.cs175.librarydemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemSelected;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.clickme)
    Button clickMe;

    @BindView(R.id.hello)
    TextView helloText;

    @BindView(R.id.cutedog)
    ImageView dog;

    @BindView (R.id.dogName)
    Spinner dogName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // General utility - Moew info at http://jakewharton.github.io/butterknife/
        ButterKnife.bind(this);
    }

    @OnClick(R.id.clickme)
    public void clickedClickMe(View view) {

        // Animation - More info at https://github.com/daimajia/AndroidViewAnimations
        YoYo.with(Techniques.Wobble)
                .duration(2000)
                .playOn(helloText);

        YoYo.with(Techniques.ZoomIn)
                .duration(2000)
                .playOn(dog);
    }

    @OnItemSelected(R.id.dogName)
    public void changePicture() {
        String url = "http://www.martystepp.com/dogs/";

        // Image manipulation: More info at http://square.github.io/picasso/
        Picasso.with(this)
                .load(url + dogName.getSelectedItem().toString())
                .resize(500,500)
                .rotate(20)
                .into(dog);
    }

    /*
     Lists of other useful android libraries
     https://github.com/codepath/android_guides/wiki/Must-Have-Libraries
     https://www.quora.com/What-are-some-important-open-source-libraries-for-Android-development
     https://android-arsenal.com/
     https://android-libs.com/
     ... many many more.
      */


}
