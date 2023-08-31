/*
 * Copyright (C) 2020 Paranoid Android
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.lineageos.settings;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;

import androidx.preference.Preference;
import androidx.preference.PreferenceFragment;
import androidx.preference.SeekBarPreference;
import androidx.preference.SwitchPreference;
import androidx.preference.ListPreference;

import org.lineageos.settings.R;
import org.lineageos.settings.display.KcalSettingsActivity;
import org.lineageos.settings.display.LcdFeaturesPreferenceActivity;
import org.lineageos.settings.speaker.ClearSpeakerActivity;
import org.lineageos.settings.utils.VibrationUtils;

public class DeviceSettingsFragment extends PreferenceFragment implements
        Preference.OnPreferenceChangeListener {

    private static final String PREF_CLEAR_SPEAKER = "clear_speaker_settings";
    private static final String PREF_KCAL_SETTINGS = "kcal_settings";
    private static final String PREF_LCD_FEATURES = "lcd_features_settings";
    private static final String PREF_VIBRATION_STRENGTH = "vibration_strength";

    private Preference mKcalSettingsPref;
    private Preference mLcdFeaturesPref;
    private Preference mClearSpeakerPref;

    private SeekBarPreference mVibStrengthPref;


    private Vibrator mVibrator;

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.device_settings);

        mClearSpeakerPref = (Preference) findPreference(PREF_CLEAR_SPEAKER);
        mClearSpeakerPref.setOnPreferenceClickListener(preference -> {
            Intent intent = new Intent(getActivity().getApplicationContext(), ClearSpeakerActivity.class);
            startActivity(intent);
            return true;
        });

        mKcalSettingsPref = (Preference) findPreference(PREF_KCAL_SETTINGS);
        mKcalSettingsPref.setOnPreferenceClickListener(preference -> {
            Intent intent = new Intent(getActivity().getApplicationContext(), KcalSettingsActivity.class);
            startActivity(intent);
            return true;
        });

        mLcdFeaturesPref = (Preference) findPreference(PREF_LCD_FEATURES);
        mLcdFeaturesPref.setOnPreferenceClickListener(preference -> {
            Intent intent = new Intent(getActivity().getApplicationContext(), LcdFeaturesPreferenceActivity.class);
            startActivity(intent);
            return true;
        });

        mVibrator = (Vibrator) getActivity().getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);

        mVibStrengthPref = (SeekBarPreference) findPreference(PREF_VIBRATION_STRENGTH);

        if (VibrationUtils.isAvailable()) {
            mVibStrengthPref.setOnPreferenceChangeListener(this);
            mVibStrengthPref.setValue(VibrationUtils.getVibStrength());
            mVibStrengthPref.setSummary(Integer.toString(VibrationUtils.getVibStrength()) + "%");
            mVibStrengthPref.setMin(10);
        } else {
            mVibStrengthPref.setEnabled(false);
        }
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {
        switch (preference.getKey()) {
            case PREF_VIBRATION_STRENGTH:
                mVibStrengthPref.setSummary(String.valueOf(newValue) + "%");
                VibrationUtils.setVibStrength((int) newValue);
                if (mVibrator.hasVibrator()) {
                    mVibrator.vibrate(VibrationEffect.createOneShot(75, VibrationEffect.DEFAULT_AMPLITUDE));
                }
                return true;
            default:
                return false;                                                                                           
        }                                                                                                               
    }                                                                                                                   
}
