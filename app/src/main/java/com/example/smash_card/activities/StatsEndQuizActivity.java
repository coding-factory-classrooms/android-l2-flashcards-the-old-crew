package com.example.smash_card.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.smash_card.R;

public class StatsEndQuizActivity extends AppCompatActivity implements View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats_end_quiz);
        Intent srcIntent = getIntent();

        int score = srcIntent.getIntExtra("score", 0);
        int question = srcIntent.getIntExtra("numberQuestion", 1);
        int percent = Math.round(((float) score / (float) question) * 100.0f);
        TextView modeText = findViewById(R.id.modeText);
        modeText.setText(srcIntent.getStringExtra("mode"));
        TextView scoreText = findViewById(R.id.scoreText);
        scoreText.setText(score + "");
        TextView questionText = findViewById(R.id.questionText);
        questionText.setText(question + "");
        TextView percentText = findViewById(R.id.scorePercent);
        percentText.setText(percent + "%");
        Button buttonBackHome = findViewById(R.id.buttonBackHome);
        buttonBackHome.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.buttonBackHome:
                Intent intent = new Intent(StatsEndQuizActivity.this, HomeActivity.class);
                StatsEndQuizActivity.this.startActivity(intent);
                break;
        }
    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(StatsEndQuizActivity.this, HomeActivity.class);
        StatsEndQuizActivity.this.startActivity(intent);
    }
}