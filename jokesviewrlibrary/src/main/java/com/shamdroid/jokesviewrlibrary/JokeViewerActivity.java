package com.shamdroid.jokesviewrlibrary;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class JokeViewerActivity extends AppCompatActivity {

    String joke;

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke_viewr);

        joke = getIntent().getStringExtra("joke");
        textView = (TextView) findViewById(R.id.txtJoke);

        textView.setText(joke);


    }
}
