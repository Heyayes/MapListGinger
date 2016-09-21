package com.shak.heyyes.maplistginger;

import android.content.Context;
import android.util.Log;
import android.widget.ArrayAdapter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

/**
 * Created by stshakun on 29.08.16.
 */
public class Utility {

    String mapListFile = "mapListFile";

    public void createMap(String name, Context ctx) {

        String xmlHeader = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<NameOfMap name=\"" + name + "\"/>\n</xml>";

        try {
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
                    ctx.openFileOutput(name + ".xml", ctx.MODE_PRIVATE)));
            bw.write(xmlHeader);
            bw.close();

            bw = new BufferedWriter(new OutputStreamWriter(
                    ctx.openFileOutput(mapListFile, ctx.MODE_APPEND)));
            bw.write(name + "\n");
            bw.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String readMap(File file, Context ctx){
        //ArrayList<String> inFile = new ArrayList<>();
       StringBuilder inFile = new StringBuilder();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    ctx.openFileInput(file.getName())));
            String line = "";
            while ((line = br.readLine()) != null){
                inFile.append(line + "\n");
            }
//            inFile.append("\n");
//            inFile.append(file.getAbsolutePath());
//            inFile.append("\n");
//            inFile.append(file.getCanonicalPath());
            br.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return inFile.toString();
    }

    public ArrayAdapter<String> mapList(Context context){

        ArrayList<String> array = new ArrayList<String>();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    context.openFileInput(mapListFile)));
            String str = "";
            while ((str = br.readLine()) != null){
                array.add(str);
                Log.d("myLogUtil", "array size:" + array.size() + " file read:" + str);
            }
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, array);
        return adapter;
    }

    public void openMap(Context context){
    }

}