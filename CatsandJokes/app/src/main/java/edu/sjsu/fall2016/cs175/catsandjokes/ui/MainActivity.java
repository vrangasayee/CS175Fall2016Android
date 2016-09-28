package edu.sjsu.fall2016.cs175.catsandjokes.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.async.future.FutureRunnable;
import com.koushikdutta.ion.Ion;

import org.json.JSONException;
import org.json.JSONObject;

import edu.sjsu.fall2016.cs175.catsandjokes.R;
import edu.sjsu.fall2016.cs175.catsandjokes.data.ICNDBService;
import edu.sjsu.fall2016.cs175.catsandjokes.data.JokeResponse;
import edu.sjsu.fall2016.cs175.catsandjokes.data.remote.RetrofitHelper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {
    private ICNDBService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RetrofitHelper.init();
        apiService =  RetrofitHelper.getService();
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

    public void jokeClickedRetrofit(View view) {
        Call<JokeResponse> jokeResponse = apiService.getJoke();
        jokeResponse.enqueue(new Callback<JokeResponse>() {
            @Override
            public void onResponse(Call<JokeResponse> call, Response<JokeResponse> response) {
                TextView jokeText = (TextView) findViewById(R.id.joke);
                jokeText.setText(response.body().getJokeString());
            }

            @Override
            public void onFailure(Call<JokeResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "API Failure", Toast.LENGTH_LONG).show();
            }
        });


    }
}
