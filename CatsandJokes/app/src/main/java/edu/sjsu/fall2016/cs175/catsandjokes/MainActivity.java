package edu.sjsu.fall2016.cs175.catsandjokes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.async.future.FutureRunnable;
import com.koushikdutta.ion.Ion;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void jokeClicked(View view) {
        Ion.with(this)
                .load("http://api.icndb.com/jokes/random")
                .asString()
                .setCallback(new FutureCallback<String>() {
                    @Override
                    public void onCompleted(Exception e, String result) {
                        processData(result);
                    }
                });

    }

    /**
     * {
     *  type: "success",
     *  value: {
     *      id: 233,
     *      joke: "When Bruce Banner gets mad, he turns into the Hulk. When the Hulk gets mad, he turns into Chuck Norris.",
     *      categories: [ ]
     *      }
     * }
     */
    private void processData(String result) {
        try {
            JSONObject json = new JSONObject(result);
            JSONObject value = json.getJSONObject("value");
            String joke = value.getString("joke");


            TextView jokeText = (TextView) findViewById(R.id.joke);
            jokeText.setText(joke);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
