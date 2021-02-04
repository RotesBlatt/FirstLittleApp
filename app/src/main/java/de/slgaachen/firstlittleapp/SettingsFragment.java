package de.slgaachen.firstlittleapp;

import android.os.Bundle;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import android.widget.Toast;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import androidx.fragment.app.DialogFragment;

public class SettingsFragment extends PreferenceFragmentCompat implements
        Preference.OnPreferenceChangeListener {

    @Override
    public void onCreatePreferences(Bundle bundle, String s) {

        //Toast.makeText(getContext(), "Schritt 2: Das SettingsFragment wurde gestartet.", Toast.LENGTH_SHORT).show();
        //Test
        // Erzeugen der App-Einstellungen aus den Preference-Elementen der XML-Datei
        addPreferencesFromResource(R.xml.preferences);

        // Den Listener für die SwitchPreference-Einstellung registrieren
        Preference preference = findPreference(getString(R.string.preference_randomimage_key));
        preference.setOnPreferenceChangeListener(this);

        // Auslesen des Einstellungswerts aus den SharedPreferences und Auslösen des Listeners
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        boolean preferenceValue = sharedPrefs.getBoolean(preference.getKey(), false);
        onPreferenceChange(preference, preferenceValue);

    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {

        String preferenceKey = preference.getKey();
        if (preferenceKey.equals(getString(R.string.preference_randomimage_key))) {
            boolean isRandomImageOn = (boolean) newValue;
            if (isRandomImageOn) {
                preference.setSummary(getString(R.string.preference_randomimage_summary_on));
            } else {
                preference.setSummary(getString(R.string.preference_randomimage_summary_off));
            }
        }

        return true;
    }

    @Override
    public void onDisplayPreferenceDialog(Preference preference) {
        // Prüfen, ob die angeklickte Einstellung unsere Custom Preference ist
        if (preference instanceof NumberPickerPreference) {
            // Erzeugen einer neuen Instanz der NumberPickerPreferenceDialogFragment-Klasse,
            // die den Schlüssel der zugehörigen Preference (NumberPickerPreference) enthält.
            String dialogFragmentTag = "androidx.preference.PreferenceFragmentCompat.DIALOG";
            DialogFragment dialogFragment = NumberPickerPreferenceDialogFragment.newInstance(preference.getKey());
            dialogFragment.setTargetFragment(this, 0);
            dialogFragment.show(getParentFragmentManager(), dialogFragmentTag);
        } else {
            // Wurde eine andere Einstellung angeklickt, lassen wir die Super-Klasse die Aufgabe erledigen.
            super.onDisplayPreferenceDialog(preference);
        }
    }
}