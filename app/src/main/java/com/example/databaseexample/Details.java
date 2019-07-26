package com.example.databaseexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class Details extends AppCompatActivity
{
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        DBHandler handler = new DBHandler(this);

        ArrayList<HashMap<String,String>> myArray = handler.GetData();

        ListView lv = findViewById(R.id.user_list);

        ListAdapter adapter = new SimpleAdapter(Details.this, myArray, R.layout.layout,new String[]{"name","location","description"}, new int[]{R.id.name, R.id.location , R.id.description});

        lv.setAdapter(adapter);

        Button btn = findViewById(R.id.btnBack);

        btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent i = new Intent(Details.this,MainActivity.class);
                startActivity(i);
            }
        });

    }
}
