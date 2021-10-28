package com.example.mainwork;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class Result extends AppCompatActivity {
    ProgressBar progress_bar;
    ImageView firtsIcon, secondIcon;
    TextView resultText, infText;
    double persent;
    String result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        infText = findViewById(R.id.maininf);
        infText.setText(R.string.medical_checkup);

        Bundle arguments = getIntent().getExtras();
        result = arguments.get("result").toString();
        persent = (double) arguments.get("percent");

        firtsIcon = findViewById(R.id.icon);
        secondIcon = findViewById(R.id.icon2);

        resultText = findViewById(R.id.resultText);


        progress_bar = findViewById(R.id.progressBar);
        progress_bar.setProgress((int) (100 - persent*100));
        switch (result) {
            case "good":
                resultText.setText(R.string.good_result);
                firtsIcon.setImageResource(R.drawable.good);
                secondIcon.setImageResource(R.drawable.goodem);
                break;
            case "okey":
                resultText.setText(R.string.normal_result);
                firtsIcon.setImageResource(R.drawable.normal);
                secondIcon.setImageResource(R.drawable.okey);
                break;
            case "bad":
                resultText.setText(R.string.bad_result);
                firtsIcon.setImageResource(R.drawable.unlike);
                secondIcon.setImageResource(R.drawable.badem);
                break;
        }

    }
}