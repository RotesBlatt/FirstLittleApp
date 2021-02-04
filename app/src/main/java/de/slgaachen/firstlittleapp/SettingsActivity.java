package de.slgaachen.firstlittleapp;

import android.os.Bundle;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // View-Elemente aus XML-Layout Datei erzeugen lassen
        setContentView(R.layout.activity_settings);

        // Initialisieren der App Bar und Aktivieren des Up-Buttons
        initAppBar();

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle(getString(R.string.action_settings));

        //Toast.makeText(this, "Schritt 1: Die SettingsActivity wurde gestartet.", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void initAppBar(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_settings_activity);
        setSupportActionBar(toolbar);
    }
}