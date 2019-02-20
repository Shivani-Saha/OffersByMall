package com.app.adarsh.offersbymall;

import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;

import com.example.adarsh.offersbymall.R;

/**
 * A {@link PreferenceActivity} that presents a set of application settings.
 * <p>
 * See <a href="http://developer.android.com/design/patterns/settings.html">
 * Android Design: Settings</a> for design guidelines and the <a
 * href="http://developer.android.com/guide/topics/ui/settings.html">Settings
 * API Guide</a> for more information on developing a Settings UI.
 */
public class VListSettingsActivity extends PreferenceActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getFragmentManager().beginTransaction().replace(android.R.id.content, new SettingsPreferenceFragment()).commit();
    }

public static  class SettingsPreferenceFragment extends PreferenceFragment implements Preference.OnPreferenceChangeListener {
    public SettingsPreferenceFragment() {

    }
    @Override
    public void onCreate(final Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.v_list_pref_general);
        ListPreference city=(ListPreference)findPreference("City");
        city.setEntries(R.array.Malls_name);
        city.setEntryValues(R.array.Malls_name);
        ListPreference category=(ListPreference)findPreference("Category");
        category.setEntries(R.array.Category_Shops);
        category.setEntryValues(R.array.Category_Shops);
        bindCityPreferenceSummaryToValue(findPreference(getString(R.string.city)));
        bindPreferenceSummaryToValue(findPreference(getString(R.string.malls)));
        bindPreferenceSummaryToValue(findPreference(getString(R.string.category)));

    }
    private void bindCityPreferenceSummaryToValue(Preference preference) {
        // Set the listener to watch for value changes.
        preference.setOnPreferenceChangeListener(this);

        // Trigger the listener immediately with the preference's
        // current value.
        String cityValue=PreferenceManager
                .getDefaultSharedPreferences(preference.getContext())
                .getString(preference.getKey(), "");
        onPreferenceChange(preference,cityValue);
        if (cityValue.equalsIgnoreCase("NEW DELHI")) {
            ListPreference malls=(ListPreference) findPreference("Malls");
            malls.setEntries(R.array.newdelhi_malls);
            malls.setEntryValues(R.array.newdelhi_malls);
        }
        if (cityValue.equalsIgnoreCase("GHAZIABAD")) {
            ListPreference malls=(ListPreference) findPreference("Malls");
            malls.setEntries(R.array.ghaziabad_malls);
            malls.setEntryValues(R.array.ghaziabad_malls);
        }
        if (cityValue.equalsIgnoreCase("GURGAON")) {
            ListPreference malls=(ListPreference) findPreference("Malls");
            malls.setEntries(R.array.gurgaon_mall);
            malls.setEntryValues(R.array.gurgaon_mall);
        }
        if (cityValue.equalsIgnoreCase("NOIDA")) {
            ListPreference malls=(ListPreference) findPreference("Malls");
            malls.setEntries(R.array.noida_malls);
            malls.setEntryValues(R.array.noida_malls);
        }
        if (cityValue.equalsIgnoreCase("FARIDABAD")) {
            ListPreference malls=(ListPreference) findPreference("Malls");
            malls.setEntries(R.array.faridabad_malls);
            malls.setEntryValues(R.array.faridabad_malls);
        }
        if (cityValue.equalsIgnoreCase("MEERUT")) {
            ListPreference malls=(ListPreference) findPreference("Malls");
            malls.setEntries(R.array.meerut_malls);
            malls.setEntryValues(R.array.meerut_malls);}

    }

    private void bindPreferenceSummaryToValue(Preference preference) {
        // Set the listener to watch for value changes.
        preference.setOnPreferenceChangeListener(this);

        // Trigger the listener immediately with the preference's
        // current value.
        onPreferenceChange(preference,
                PreferenceManager
                        .getDefaultSharedPreferences(preference.getContext())
                        .getString(preference.getKey(), ""));

    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object value) {
        String stringValue = value.toString();

        if (preference instanceof ListPreference) {
            // For list preferences, look up the correct display value in
            // the preference's 'entries' list (since they have separate labels/values).
            ListPreference listPreference = (ListPreference) preference;
            int prefIndex = listPreference.findIndexOfValue(stringValue);
            if (prefIndex >= 0) {
                preference.setSummary(listPreference.getEntries()[prefIndex]);
            }
        } else {
            // For other preferences, set the summary to the value's simple string representation.
            preference.setSummary(stringValue);
        }

        return true;
    }

}
}