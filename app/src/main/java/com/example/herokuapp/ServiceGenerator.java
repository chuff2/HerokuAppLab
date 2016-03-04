package com.example.herokuapp;

import android.util.Base64;
import android.widget.Toast;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import android.util.Log;

/**
 * Created by James on 2/25/2016.
 *
 * DO NOT ALTER THIS CLASS
 */
public class ServiceGenerator {

    //TODO set the base url for this client
    //https://id.heroku.com/login
    //http://mean407.herokuapp.com/api/
    public static final String API_BASE_URL = "https://mean407.herokuapp.com/api/";

    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

    private static Retrofit.Builder builder =
            new Retrofit.Builder()
                    .baseUrl(API_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create());

    public static <S> S createService(Class<S> serviceClass) {
       return createService(serviceClass, null, null);
    }

    public static <S> S createService(Class<S> serviceClass, final String username, final String password) {
        if (username != null && password != null) {
            String credentials = username + ":" + password;
            final String basic =
                    "Basic " + Base64.encodeToString(credentials.getBytes(), Base64.NO_WRAP);

            httpClient.addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Interceptor.Chain chain) throws IOException {
                    Request original = chain.request();

                    //debug
                    //Log.d("LOGIN INFO: ",  username +" " + password);

                    Request.Builder requestBuilder = original.newBuilder()
                            .header("Authorization", basic)
                            .header("Accept", "/api/json")
                            //TODO add additional headers here
                            .header("email", username)
                            .header("password", password)
                            .method(original.method(), original.body());

                    Request request = requestBuilder.build();
                    return chain.proceed(request);
                }
            });
        }

        OkHttpClient client = httpClient.build();
        Retrofit retrofit = builder.client(client).build();
        return retrofit.create(serviceClass);
    }


}
