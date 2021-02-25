package com.example.smash_card;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    private Question question;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Button startQuizButton = findViewById(R.id.startQuizButton);
        Button aboutButton = findViewById(R.id.aboutButton);
        Button charactersButton = findViewById(R.id.charactersButton);
        startQuizButton.setOnClickListener(this);
        aboutButton.setOnClickListener(this);
        charactersButton.setOnClickListener(this);

        InputStream datas = null;

        try {
            datas = this.getApplicationContext().getResources().getAssets().open("datas.json");
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.question = new Question(datas);
    }

    @Override
    public void onClick(View v) {
        Context context = v.getContext();

        switch (v.getId()){
            case R.id.startQuizButton:
                try {
                    this.dialogGameMode(context);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
//                Intent intent = new Intent(HomeActivity.this, MainActivity.class);
//                HomeActivity.this.startActivity(intent);
                break;
            case R.id.aboutButton:
                Intent intent = new Intent(HomeActivity.this, About.class);
                HomeActivity.this.startActivity(intent);
                break;
            case R.id.charactersButton:
                Intent charListIntent = new Intent(HomeActivity.this, CharacterListActivity.class);
                HomeActivity.this.startActivity(charListIntent);
                break;
        }
    }

    private void dialogGameMode(Context context) throws JSONException {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
        String[] mode = new String[] {"Noob","Pro", "VIP"};
        ArrayList<String> selectedItems = new ArrayList<>();
        selectedItems.add(Arrays.asList(mode).get(0));
        builder1.setCancelable(true)
                .setTitle("Choix de la difficulté")
                .setSingleChoiceItems(mode, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        selectedItems.clear();
                        selectedItems.add(Arrays.asList(mode).get(which));
                        Log.i("test", "onClick: " + selectedItems);
                    }
                })
                .setPositiveButton(
                "Prêt au combat !",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                        Intent intent = new Intent(context, MainActivity.class);
                        intent.putExtra("mode", selectedItems.get(0));
                        context.startActivity(intent);
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();
    }
}