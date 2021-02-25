package com.example.smash_card;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import java.util.List;

public class CharacterListActivity extends AppCompatActivity {

    public static final String TAG = "CharacterListActivity";
    private List<Characters> characters;
    private CharacterAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_list);
        Intent srcIntent = getIntent();


        characters =  srcIntent.getParcelableArrayListExtra("characters");

        adapter = new CharacterAdapter(characters);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }


}