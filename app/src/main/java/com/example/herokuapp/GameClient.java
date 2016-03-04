package com.example.herokuapp;
import retrofit2.http.GET;
import java.util.List;
import retrofit2.Call;

/**
 * Created by James on 2/26/2016.
 */
public interface GameClient {
    //TODO set up the retrofit http call interface
    @GET("games")
    Call<List<Game>> games();
}
