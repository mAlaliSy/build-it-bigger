package com.udacity.gradle.builditbigger;

import android.os.AsyncTask;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.shamdroid.backend.myApi.MyApi;

import java.io.IOException;

/**
 * Created by mohammad on 29/08/16.
 */

class MyAsyncTaskTest extends AsyncTask<Void, Void, Void> {
    static MyApi myApiService;

    @Override
    protected Void doInBackground(Void... voids) {


        if (myApiService == null) {

            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    // options for running against local devappserver
                    // - 10.0.2.2 is localhost's IP address in Android emulator
                    // - turn off compression when running against local devappserver
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


        String s = null;

        try {
            s = myApiService.tellJoke().execute().getData();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (s == null || s.isEmpty())
            throw new AssertionError("Empty String Returned !");

        return null;
    }

}
