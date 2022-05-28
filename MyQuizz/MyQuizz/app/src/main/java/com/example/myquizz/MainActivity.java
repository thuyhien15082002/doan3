package com.example.myquizz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.GridView;
import android.widget.TabHost;
import android.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    TabHost tabHost;
    private GridView catGrid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControll();

        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Chọn môn kiểm tra ");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        catGrid =findViewById(R.id.catGird);
        List<String> catList=new ArrayList<>();
        catList.add("LỊCH SỬ");
        catList.add("ĐỊA LÝ");
        catList.add("GDCD");
        catList.add("TỔ HỢP");

        CatGridAdapter adapter=new CatGridAdapter(catList);
        catGrid.setAdapter(adapter);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()== android.R.id.home) {
            MainActivity.this.finish();
        }
        return super.onOptionsItemSelected(item);





    }

    private void addControll() {
        tabHost=findViewById(R.id.tabhost);
        tabHost.setup();
        createTab();
    }

    private void createTab() {
        //tab1
        TabHost.TabSpec tabSpec1;
        tabSpec1 =tabHost.newTabSpec("tab1");
        tabSpec1.setContent(R.id.tab1);
        tabSpec1.setIndicator("", getResources().getDrawable(R.drawable.ic_home_24));
        tabHost.addTab(tabSpec1);
        //tab2
        TabHost.TabSpec tabSpec2;
        tabSpec2=tabHost.newTabSpec("tab2");
        tabSpec2.setContent(R.id.tab2);
        tabSpec2.setIndicator("", getResources().getDrawable( R.drawable.ic_history_24));
        tabHost.addTab(tabSpec2);
    }
    private void setSupportActionBar(Toolbar toolbar) {
    }
}