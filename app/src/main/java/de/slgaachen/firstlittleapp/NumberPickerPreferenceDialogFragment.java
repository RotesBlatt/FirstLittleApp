package de.slgaachen.firstlittleapp;

import android.os.Bundle;
import androidx.preference.PreferenceDialogFragmentCompat;
import android.view.View;
import android.widget.NumberPicker;

public class NumberPickerPreferenceDialogFragment extends PreferenceDialogFragmentCompat {

    private NumberPicker mNumberPicker;

    public static NumberPickerPreferenceDialogFragment newInstance(String preferenceKey) {
        NumberPickerPreferenceDialogFragment fragment = new NumberPickerPreferenceDialogFragment();
        Bundle bundle = new Bundle(1);
        bundle.putString(ARG_KEY, preferenceKey);
        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    protected void onBindDialogView(View view) {
        super.onBindDialogView(view);

        mNumberPicker = (NumberPicker) view.findViewById(R.id.number_picker);
        mNumberPicker.setMinValue(5);
        mNumberPicker.setMaxValue(25);
        mNumberPicker.setWrapSelectorWheel(false);
        mNumberPicker.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);

        // Den Wert des NumberPicker-Widgets auf den aktuellen Einstellungswert setzen
        NumberPickerPreference numberPickerPreference = (NumberPickerPreference) getPreference();
        mNumberPicker.setValue(numberPickerPreference.getValue());
    }

    @Override
    public void onDialogClosed(boolean positiveResult) {
        if (positiveResult) {
            int selectedValue = mNumberPicker.getValue();

            // Den Einstellungswert auf den im NumberPicker-Widget ausgewählten Wert setzen
            NumberPickerPreference numberPickerPreference = (NumberPickerPreference) getPreference();
            if (numberPickerPreference.callChangeListener(selectedValue)) {
                numberPickerPreference.setValue(selectedValue);
            }
        }
    }

}
