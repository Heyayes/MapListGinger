package com.shak.heyyes.maplistginger;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.shak.heyyes.maplistginger.BuilderMap;
import com.shak.heyyes.maplistginger.R;

import java.io.File;

public class MapMaker extends AppCompatActivity {


final Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_maker);

        String fileName = (String) getIntent().getSerializableExtra("fileName");
        File file = new File(fileName);
        //CharSequence str = new String(new Utility().readMap(file, context));

        BuilderMap bm = new BuilderMap(file, context);


        //Button button = (Button) findViewById(R.id.button1);

        //CharSequence str = new String(bm.toString());

        //button.setText(str);

    }
}
