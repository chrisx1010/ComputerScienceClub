package com.example.chrismunoz.computerscienceclub;

//Importing the widgets that I need. It will auto-import.
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.SeekBar.OnSeekBarChangeListener;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    //To edit a ListView you need a Array list and an Array adapter.
    ArrayList<String> items;
    ArrayAdapter<String> itemsAdapter;
    ListView list;

    //Slid ing bar. Min 0 Max 100 (you can customize it)
    SeekBar sBar;
    int value = 0;

    //name the button outside the onCreate or it has to be final
    private Button red;

    //I failed at this
    boolean secretCode1 = false;
    boolean secretCode2 = false;
    boolean secretCode3 = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //This is a way of final. I prefer this way.
        final TextView title1 = (TextView) findViewById(R.id.txtBtn);

        //Buttons: name them, findViewById button id(what you called it in activity main.
        Button blue = (Button) findViewById(R.id.btnBlue);
        Button next = (Button) findViewById(R.id.btnNext);

        //named Button red outside the onCreate.
        red = (Button) findViewById(R.id.btnRed);

        //setting list to the correct widget id.
        list = (ListView) findViewById(R.id.listView);
        //making new ArrayList of type string.
        items = new ArrayList<String>();
        ///making a Array Adapter of type string.     (simple_list_item_1) default name for fist ListView in xml file
        itemsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);
        list.setAdapter(itemsAdapter);
        //Adding initial items to te list.
        items.add("Remove Me");
        items.add("Remove Me Too");
        setupListViewListener();

        //Title
        final TextView finish = (TextView) findViewById(R.id.txtFinish);
        //Changing Visibility (Visible,Invisible,Gone)
        finish.setVisibility(View.INVISIBLE);

        //One way to check if a button
        blue.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Changing the text Color other ways(Color.parse());
                title1.setTextColor(Color.rgb(0, 0, 200));

            }
        });
        red.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                title1.setTextColor(Color.rgb(200, 0, 0));
                //ignore
                if (title1.getCurrentTextColor() == Color.rgb(200, 0, 0)) {
                    secretCode1 = true;
                }
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //changes screen
                setContentView(R.layout.activity_main2);

            }
        });
        //sets sBar to the correct id
        sBar = (SeekBar) findViewById(R.id.seekBar);
        // progress bar min 0, max 100 (default)
        final ProgressBar pBar = (ProgressBar) findViewById(R.id.pBar);
        //shows progress
        final TextView txtProgress = (TextView) findViewById(R.id.txtProgress);

        sBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                //You need this
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                //You need this
            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                //progress set to value
                value = progress;
                pBar.setProgress(value);
                //changing text
                txtProgress.setText(value + "%");
                //ignore
                if (value == 75) {
                    secretCode2 = true;
                }
            }
        });


        if (secretCode1 == true && secretCode2 == true && secretCode3 == true){
            finish.setVisibility(View.VISIBLE);
        }



    }
    private void setupListViewListener() {
        list.setOnItemLongClickListener(
                new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> adapter, View view, int position, long id) {
                        //removing items if long click
                        items.remove(position);
                        //This should get the length of the list View
                        int x = list.getAdapter().getCount();
                        //updates
                        itemsAdapter.notifyDataSetChanged();
                        if (x == 0) {
                            secretCode3 = true;
                        }
                        return true;
                    }
                });

    }
    //another way for a button click
    public void onAddItem(View V) {
        EditText enter = (EditText)findViewById(R.id.txtEnter);
        //gets string of entered string
        String newItem = enter.getText().toString();
        //adds item
        itemsAdapter.add(newItem);
        //resets text
        enter.setText("");

    }



}
