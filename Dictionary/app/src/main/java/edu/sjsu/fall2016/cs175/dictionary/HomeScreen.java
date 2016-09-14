package edu.sjsu.fall2016.cs175.dictionary;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class HomeScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
    }
    private static final int REQ_CODE_TAKE_PICTURE = 30210;
    public void takePhoto(View view) {
        Intent picIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(picIntent, REQ_CODE_TAKE_PICTURE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent intent) { if (requestCode == REQ_CODE_TAKE_PICTURE
            && resultCode == RESULT_OK) {
        Bitmap bmp = (Bitmap) intent.getExtras().get("data");
        ImageView img = (ImageView) findViewById(R.id.photo);
        img.setImageBitmap(bmp);
    }
    }

    public void openDictionary(View view) {
        Intent demoIntent = new Intent(this, VocabQuiz.class);
        startActivity(demoIntent);
    }
    public void openDemo(View view) {
        Intent demoIntent = new Intent(this, DemoActivity.class);
        demoIntent.putExtra("Name", new String("John Doe"));
        startActivity(demoIntent);
    }
}
