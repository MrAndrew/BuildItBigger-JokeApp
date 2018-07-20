package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Pair;

import com.example.displaydadjoke.FullscreenDadJoke;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;

import java.io.IOException;

public class EndpointsAsyncTask extends AsyncTask<Context, Void, String> {
    private static MyApi myApiService = null;
    private Context mContext;

    public EndpointsAsyncTask(Context context) {
        mContext = context;
    }

    @Override
    protected String doInBackground(Context... params) {
        if (myApiService == null) {  // Only do this once
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    // options for running against local devappserver
                    // - 10.0.2.2 is localhost's IP address in Android emulator
                    // - turn off compression when running against local devappserver
                    //NOTE TO REVIEWER: I tried moving this string to a hardcoded
                    //value and it caused app crashes
                    .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });
            // end options for devappserver

            myApiService = builder.build();
        }

        try {
//            String greeting = myApiService.sayHi(name).execute().getData();
            String dadJoke = myApiService.tellDadJoke().execute().getData();
            return (dadJoke);
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String dadJoke) {
//        Toast.makeText(context, greeting, Toast.LENGTH_LONG).show();
        Intent displayDadJokeIntent = new Intent(mContext, FullscreenDadJoke.class);
        //NOTE TO REVIEWER: I tried moving this string to a hardcoded
        //value and it caused app crashes
        displayDadJokeIntent.putExtra("DAD_JOKE_KEY", dadJoke);
        mContext.startActivity(displayDadJokeIntent);
    }
}
