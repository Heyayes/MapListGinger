package com.shak.heyyes.maplistginger;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

import java.io.File;

/**
 * Created by stshakun on 04.09.16.
 * This View show file is created by Utility.class
 */
public class ViewMap extends Activity {

final Context context = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.look_into_file);
        TextView textView = (TextView) findViewById(R.id.fileReaderView);
        String fileName = (String) getIntent().getSerializableExtra("fileName");
        File file = new File(fileName);
        CharSequence str = new String(new Utility().readMap(file, context));

        textView.setText(str);
    }
}