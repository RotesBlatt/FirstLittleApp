package de.slgaachen.firstlittleapp;

import android.content.Context;
import android.content.res.TypedArray;
import androidx.preference.DialogPreference;
import android.util.AttributeSet;

public class NumberPickerPreference extends DialogPreference {

    public static final int DEFAULT_VALUE = 10;
    private int mPickedNumber;

    public NumberPickerPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
        setDialogLayoutResource(R.layout.preference_dialog_numberpicker);

        setDialogTitle(R.string.preference_quotecount_dialog_title);
        setPositiveButtonText(R.string.preference_quotecount_dialog_positive_button_text);
        setNegativeButtonText(R.string.preference_quotecount_dialog_negative_button_text);
    }

    public int getValue() {
        return mPickedNumber;
    }

    public void setValue(int newPickedNumber) {
        mPickedNumber = newPickedNumber;

        // Speichern des Einstellungswerts in die SharedPreferences und Anpassen der Summary.
        persistInt(mPickedNumber);
        setSummary(mPickedNumber + " " + getContext().getString(R.string.preference_quotecount_summary));
    }

    @Override
    protected Object onGetDefaultValue(TypedArray a, int index) {
        // Auslesen des Standardwerts anhand des XML-Attributs.
        // Falls dies nicht möglich ist, wird die Konstante DEFAULT_VALUE verwendet.
        return a.getInt(index, DEFAULT_VALUE);
    }

    @Override
    protected void onSetInitialValue(boolean restorePersistedValue, Object defaultValue) {
        // Setzen des Einstellungswerts auf den in den SharedPreferences gespeicherten Wert
        // oder Verwenden des Standardwerts.
        if (restorePersistedValue) {
            setValue(getPersistedInt(mPickedNumber));
        } else {
            setValue((int) defaultValue);
        }
        setSummary(mPickedNumber + " " + getContext().getString(R.string.preference_quotecount_summary));
    }

}
