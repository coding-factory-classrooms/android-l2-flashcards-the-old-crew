package com.example.smash_card.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.lifecycle.ProcessLifecycleOwner;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.smash_card.InfoGame;
import com.example.smash_card.MusicPlayerService;
import com.example.smash_card.R;

import java.io.IOException;

import static com.example.smash_card.Utils.playWavSound;

/**
 * Activity Stats Quiz
 */
public class StatsEndQuizActivity extends AppCompatActivity implements View.OnClickListener, LifecycleObserver {

    private boolean isContext = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats_end_quiz);
        InfoGame infoGame = getIntent().getParcelableExtra("infoGame");

        ProcessLifecycleOwner.get().getLifecycle().addObserver(this);

        int score = infoGame.getScore();
        int question = infoGame.getNumberQuestion();
        int percent = Math.round(((float) score / (float) question) * 100.0f);
        TextView modeText = findViewById(R.id.modeText);
        modeText.setText(infoGame.getMode());
        TextView scoreText = findViewById(R.id.scoreText);
        scoreText.setText(score + "");
        TextView questionText = findViewById(R.id.questionText);
        questionText.setText(question + "");
        TextView percentText = findViewById(R.id.scorePercent);
        percentText.setText(percent + "%");
        Button buttonBackHome = findViewById(R.id.buttonBackHome);
        buttonBackHome.setOnClickListener(this);
        try {
            playWavSound(this.getApplicationContext()
                    .getResources()
                    .getAssets()
                    .openFd("SSBU_ANNOUNCE/finalresults.wav"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * redirect to home
     * @param v
     */
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.buttonBackHome) {
            Intent intent = new Intent(StatsEndQuizActivity.this, HomeActivity.class);
            stopService(new Intent(StatsEndQuizActivity.this, MusicPlayerService.class));
            StatsEndQuizActivity.this.startActivity(intent);
        }
    }
    /**
     * redirect to home
     */
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(StatsEndQuizActivity.this, HomeActivity.class);
        stopService(new Intent(StatsEndQuizActivity.this, MusicPlayerService.class));
        StatsEndQuizActivity.this.startActivity(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        this.isContext = true;
    }

    @Override
    protected void onStop() {
        super.onStop();
        this.isContext = false;
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    private void onAppBackgrounded() {
        stopService(new Intent(StatsEndQuizActivity.this, MusicPlayerService.class));
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    private void onAppForegrounded() {
        if(this.isContext) {
            Intent intent = new Intent(StatsEndQuizActivity.this, MusicPlayerService.class);
            intent.putExtra("url", "http://www.feplanet.net/files/scripts/music.php?song=1599");
            startService(intent);
        }
    }

}
