package com.udacity.gradle.builditbigger;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.shamdroid.backend.myApi.MyApi;
import com.shamdroid.jokesviewrlibrary.JokeViewerActivity;

import java.io.IOException;

/**
 * Created by mohammad on 29/08/16.
 */

class MyAsyncTask extends AsyncTask<Context,Void,String> {
    static MyApi myApiService;
    Context context ;
    ProgressDialog progressDialog;

    @Override
    protected String doInBackground(Context... contexts) {

        context = contexts[0];



        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                progressDialog = new ProgressDialog(context);
                progressDialog.setMessage(context.getString(R.string.pleaseWait));
                progressDialog.show();
            }
        });

        if (myApiService == null ){

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


        try {
            return myApiService.tellJoke().execute().getData();
        } catch (IOException e) {
            return e.getMessage();
        }

    }


    @Override
    protected void onPostExecute(String s) {

        progressDialog.dismiss();
        Intent intent = new Intent(context , JokeViewerActivity.class);
        intent.putExtra("joke",s);
        context.startActivity(intent);
    }
}

