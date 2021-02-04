package de.slgaachen.firstlittleapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    Button button;
    TextView textView;
    ImageView imageView;

    int[] bilder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = findViewById(R.id.name_input_EditText);
        button = findViewById(R.id.input_Button);
        textView = findViewById(R.id.centered_bottom_TextView);
        imageView = findViewById(R.id.randomImage_ImageView);

        imageView.setVisibility(View.INVISIBLE);
        bilder = new int[]{R.drawable.handofbloodchillen, R.drawable.handofbloodimpool, R.drawable.handofblood, R.drawable.handofbloodamsteuer, R.drawable.handofbloodstudent};

        initAppBar();
    }

    public void onClick(View view) {
        imageView.setVisibility(View.VISIBLE);
        if (!editText.getText().toString().isEmpty()) {
            textView.setText("Hallo " + editText.getText());
        } else {
            textView.setText(R.string.missingName_text);
        }
        imageView.setImageResource(bilder[randomNumber(bilder.length, 0)]);
    }

    public void changeActivityListView(View view) {
        Intent intent = new Intent(this, ListviewActivity.class);
        intent.putExtra("name", editText.getText().toString());
        startActivity(intent);
    }

    public void initAppBar(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_mainactivity);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_activity_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.action_settings:
                startActivity(new Intent(this, SettingsActivity.class));
                return true;
            /*case R.id.action_about:
                startActivity(new Intent(this, AboutActivity.class));
                return true;*/
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public int randomNumber(int max, int min) {
        return (int) (Math.random() * max) + min;
    }
}