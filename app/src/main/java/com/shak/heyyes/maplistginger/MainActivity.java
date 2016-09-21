package com.shak.heyyes.maplistginger;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    final Context context = this;
    private static final int CM_DELETE_ID = 1;
    private static final int CM_OPEN_VIEW_1_ID = 2;
    private static final int CM_OPEN_VIEW_2_ID = 3;
    private ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        adapter = new Utility().mapList(context);
        final ListView lv = (ListView) findViewById(R.id.listView);

        //Make list from file of maps

        lv.setAdapter(adapter);
        registerForContextMenu(lv);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        //Все назначенное по кнопке
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {

                LayoutInflater li = LayoutInflater.from(context);
                View promptsView = li.inflate(R.layout.prompt, null);

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        context);

                alertDialogBuilder.setView(promptsView);
                final EditText userInput =
                        (EditText) promptsView.findViewById(R.id.editTextDialogUserInput);
                //define dialog function

                InputMethodManager m = (InputMethodManager)  getSystemService(Context.INPUT_METHOD_SERVICE);


                //TODO сменить ввод через окно на ввод непременно в самом lv

                alertDialogBuilder
                        .setCancelable(false)
                        .setPositiveButton(android.R.string.ok,
                                new DialogInterface.OnClickListener() {

                                    public void onClick(DialogInterface dialog,int id) {
                                        // get user input and set it to listView
                                        // edit text
//

                                        String name = userInput.getText().toString();
                                        new Utility().createMap(name, context);
                                        adapter.add(name);
                                        adapter.notifyDataSetChanged();

                                    }
                                })
                        .setOnKeyListener(
                                new DialogInterface.OnKeyListener(){
                                    @Override
                                    public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                                        if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                                                (keyCode == KeyEvent.KEYCODE_ENTER)) {

                                            String name = userInput.getText().toString();
                                            new Utility().createMap(name, context);
                                            adapter.add(name);
                                            adapter.notifyDataSetChanged();

                                            dialog.cancel();
                                        }
                                        return false;
                                    }
                                }
                        )

                        .setNegativeButton(android.R.string.cancel,
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,int id) {
                                        dialog.cancel();
                                    }
                                });
                //create dialog and show it
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();

            }
        });



        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String res = ((TextView)view).getText().toString();

                Intent intent = new Intent(context, MapMaker.class);
                intent.putExtra("fileName", res+".xml");
                startActivity(intent);

            }
        });


    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add(0, CM_OPEN_VIEW_1_ID, 0, "Открыть XML");
        menu.add(0, CM_OPEN_VIEW_2_ID, 0, "Открыть кнопку");
        menu.add(0, CM_DELETE_ID, 0, "Удалить запись");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo acmi = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        if (item.getItemId() == CM_DELETE_ID) {

            return true;
        } else if (item.getItemId() == CM_OPEN_VIEW_2_ID){

            //String res = ((TextView) findViewById(acmi.targetView)).getText().toString();
            String res = ((TextView)acmi.targetView).getText().toString();
            Intent intent = new Intent(context, MapMaker.class);
            intent.putExtra("fileName", res+".xml");
            startActivity(intent);
            return true;
        }   else if (item.getItemId() == CM_OPEN_VIEW_1_ID){

            String res = ((TextView)acmi.targetView).getText().toString();
            Intent intent = new Intent(context, ViewMap.class);
            intent.putExtra("fileName", res+".xml");
            startActivity(intent);
            return true;
        }


        return super.onContextItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
