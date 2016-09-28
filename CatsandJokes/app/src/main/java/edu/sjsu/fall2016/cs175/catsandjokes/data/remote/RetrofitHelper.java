package edu.sjsu.fall2016.cs175.catsandjokes.data.remote;

import edu.sjsu.fall2016.cs175.catsandjokes.data.ICNDBService;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by narayan on 9/28/16.
 */

public class RetrofitHelper {

    public static final String BASE_URL = "http://api.icndb.com/";
    private static Retrofit retrofit;

    public static void init() {
        retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    }


    public static ICNDBService getService() {
        return retrofit.create(ICNDBService.class);
    }
}
