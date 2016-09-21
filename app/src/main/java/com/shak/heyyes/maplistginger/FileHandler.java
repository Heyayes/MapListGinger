package com.shak.heyyes.maplistginger;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by stshakun on 13.09.16.
 */
public class FileHandler {
    static File file;
    private static volatile FileHandler instance;

        public static FileHandler getInstance() {

            FileHandler localInstance = instance;
            if (localInstance == null) {
                synchronized (FileHandler.class) {
                    localInstance = instance;

                    if (localInstance == null) {
                        instance = localInstance = new FileHandler();
                    }
                }
            }
        return localInstance;
    }


    public ArrayAdapter<String> mapList(Context context){
        ArrayList<String> array = new ArrayList<String>();


//      try {

//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, array);
        return adapter;

    }

}

