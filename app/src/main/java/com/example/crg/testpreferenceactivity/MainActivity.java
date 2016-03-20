package com.example.crg.testpreferenceactivity;

import android.content.SharedPreferences;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceScreen;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.prefs.Preferences;

public class MainActivity extends PreferenceActivity implements Preference.OnPreferenceChangeListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        addPreferencesFromResource(R.xml.preference_setting);
        Preference namePreference = findPreference("name");
        SharedPreferences sharedPreferences = namePreference.getSharedPreferences();
        namePreference.setSummary(sharedPreferences.getString("name", ""));
        if (sharedPreferences.getBoolean("is_save_personinfo", false)){
            namePreference.setEnabled(true);
        } else{
            namePreference.setEnabled(false);
        }
        Preference phoneNum = findPreference("phone_num");
        phoneNum.setSummary(sharedPreferences.getString("phone_num", ""));
        phoneNum.setOnPreferenceChangeListener(this);
        namePreference.setOnPreferenceChangeListener(this);
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {
            preference.setSummary(String.valueOf(newValue));
        return true;
    }

    @Override
    public boolean onPreferenceTreeClick(PreferenceScreen preferenceScreen, Preference preference) {
        if ("is_save_personinfo".equals(preference.getKey())){
            findPreference("name").setEnabled(!findPreference("name").isEnabled());
        }
        return super.onPreferenceTreeClick(preferenceScreen, preference);
    }
}
