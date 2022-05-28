package com.example.myquizz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.GridView;
import android.widget.Toolbar;

public class SetsActivity extends AppCompatActivity {
    private GridView sets_grid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sets);

        Toolbar toolbar=findViewById(R.id.sets_toolbar);
        getSupportActionBar(toolbar);

        String title =getIntent().getStringExtra("HOME");

        getSupportActionBar().setTitle(title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        sets_grid=findViewById(R.id.sets_grid);

        SetsAdapter adapter= new SetsAdapter(9);
        sets_grid.setAdapter(adapter);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()==android.R.id.home){
            SetsActivity.this.finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void getSupportActionBar(Toolbar toolbar) {
    }
}