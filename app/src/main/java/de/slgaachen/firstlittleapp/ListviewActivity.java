package de.slgaachen.firstlittleapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;
import androidx.preference.PreferenceManager;

import java.util.ArrayList;
import java.util.List;

public class ListviewActivity extends AppCompatActivity {

    private List<RandomName> mRandomNameList = new ArrayList<>();

    private EditText etBaseName;
    private EditText etNumber;
    private String[] randomNames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);

        etBaseName = findViewById(R.id.randomName_EditText);
        etNumber = findViewById(R.id.randomNameNumber_EditText);

        initAppBar();
        retrieveIntent();

    }


    public void createRandomNameList(View view) {
        if (etNumber.getText().toString().isEmpty()) {
            Toast.makeText(this, R.string.noNumberGiven_text, Toast.LENGTH_LONG).show();
        } else {

            if (etBaseName.length() < 8) {
                Toast.makeText(this, R.string.nameToShort_text, Toast.LENGTH_LONG).show();
            } else {

                int lenght = Integer.parseInt(etNumber.getText().toString());
                randomNames = new String[lenght];
                mRandomNameList.clear();

                String tempString = etBaseName.getText().toString();

                for (int i = 0; i < lenght; i++) {
                    randomNames[i] = shuffle(tempString);
                }

                fillRandomNameList();
                bindAdapterToListView();
            }
        }
    }

    public void fillRandomNameList() {
        String[] images = getResources().getStringArray(R.array.randomName_array);
        //Auslesen der SharedPreferences um zu überprüfen, ob Random Images benutzt werden sollen
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String prefRandomImageKey= getString(R.string.preference_randomimage_key);
        boolean isRandomImageOn = sharedPreferences.getBoolean(prefRandomImageKey, false);

        if(isRandomImageOn){
            for (int i = 0; i < randomNames.length; i++) {
                mRandomNameList.add(new RandomName(randomNames[i], images[randomNumber(images.length, 0)]));
            }
        } else {
            for (int i = 0; i < randomNames.length; i++) {
                mRandomNameList.add(new RandomName(randomNames[i], images[2]));
            }
        }
    }

    public void bindAdapterToListView() {
        RandomNameArrayAdapter randomNameArrayAdapter = new RandomNameArrayAdapter(this, mRandomNameList);
        ListView randomNameListView = findViewById(R.id.listview_activity_listview);
        randomNameListView.setAdapter(randomNameArrayAdapter);
    }

    public void changeActivityMainActivity(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void retrieveIntent(){
        Intent intent = getIntent();
        etBaseName.setText(intent.getStringExtra("name"));
    }

    public int randomNumber(int max, int min) {
        return (int) (Math.random() * max) + min;
    }

    public String shuffle(String input) {
        List<Character> characters = new ArrayList<>();
        for (char c : input.toCharArray()) {
            characters.add(c);
        }
        StringBuilder output = new StringBuilder(input.length());
        while (characters.size() != 0) {
            int randPicker = (int) (Math.random() * characters.size());
            output.append(characters.remove(randPicker));
        }

        return output.toString();
    }

    public void clearListView(){
        if(!mRandomNameList.isEmpty()) {
            mRandomNameList.clear();
            bindAdapterToListView();
        }
    }
    public void initAppBar(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_listview);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_activity_listview, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.action_delete_all:
                clearListView();
                return true;

            case R.id.action_settings:
                startActivity(new Intent(this, SettingsActivity.class));
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

}