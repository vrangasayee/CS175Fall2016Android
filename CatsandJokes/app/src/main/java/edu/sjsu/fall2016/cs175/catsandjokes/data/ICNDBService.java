package edu.sjsu.fall2016.cs175.catsandjokes.data;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ICNDBService {
    @GET("jokes/random}")
    Call<JokeResponse> getJoke();
}