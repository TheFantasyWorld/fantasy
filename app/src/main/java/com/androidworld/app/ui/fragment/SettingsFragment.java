package com.androidworld.app.ui.fragment;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;

import com.androidworld.app.R;
import com.androidworld.app.ui.adapter.ColorsListAdapter;
import com.androidworld.app.util.DataCleanManager;
import com.androidworld.app.util.DialogUtils;
import com.androidworld.app.util.PreferenceUtils;
import com.androidworld.app.util.ThemeUtil;
import com.jenzz.materialpreference.Preference;
import com.jenzz.materialpreference.SwitchPreference;

import java.util.Arrays;
import java.util.List;

public class SettingsFragment extends PreferenceFragment {

    public static final String PREFERENCE_FILE_NAME = "AndroidWorld.settings";
    private SwitchPreference autoUpdatePreference;
    private Preference appThemePreference;
    private Preference cleanCachePreference;
    private Preference checkUpdatePreference;

    public static SettingsFragment newInstance() {
        SettingsFragment fragment = new SettingsFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.prefs);
        getPreferenceManager().setSharedPreferencesName(PREFERENCE_FILE_NAME);
        findPreference();
//        themeState();
//        initCacheSize();
//        checkUpdatePreference.setSummary("当前版本：v" + getVersion(getActivity()));
    }

    private String getVersion(Context ctx) {
        try {
            PackageManager pm = ctx.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(ctx.getPackageName(), PackageManager.GET_ACTIVITIES);
            return pi.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return "1.0.0";
    }

    /**
     * 获得程序当前版本号
     */
    public static int getVerCode(Context ctx) {
        try {
            PackageManager pm = ctx.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(ctx.getPackageName(), PackageManager.GET_ACTIVITIES);
            return pi.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return 1;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    public SettingsFragment() {
        super();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    public void findPreference() {
        autoUpdatePreference = (SwitchPreference) findPreference(getString(R.string.auto_update));
        appThemePreference = (Preference) findPreference(getString(R.string.app_theme));
        cleanCachePreference = (Preference) findPreference(getString(R.string.clean_cache));
        checkUpdatePreference = (Preference) findPreference(getString(R.string.check_update));
    }

    public void setAutoUpdatePreferenceChecked(boolean checked) {
        autoUpdatePreference.setChecked(checked);
    }

    public void initPreferenceListView(View view) {
        ListView listView = (ListView) view.findViewById(android.R.id.list);
        listView.setHorizontalScrollBarEnabled(false);
        listView.setVerticalScrollBarEnabled(false);
        listView.setDivider(new ColorDrawable(getResources().getColor(R.color.light_grey_1)));
        listView.setDividerHeight((int) getResources().getDimension(R.dimen.preference_divider_height));
        listView.setFooterDividersEnabled(false);
        listView.setHeaderDividersEnabled(false);
    }

    public void showThemeChooseDialog() {
        AlertDialog.Builder builder = DialogUtils.makeDialogBuilderByTheme(getActivity());
        builder.setTitle(R.string.change_theme);
        Integer[] res = new Integer[]{R.drawable.round_red, R.drawable.round_brown, R.drawable.round_blue,
                R.drawable.round_blue_grey, R.drawable.round_yellow, R.drawable.round_deep_purple,
                R.drawable.round_pink, R.drawable.round_green};
        List<Integer> list = Arrays.asList(res);
        ColorsListAdapter adapter = new ColorsListAdapter(getActivity(), list);
        adapter.setCheckItem(ThemeUtil.getCurrentTheme(getActivity()).getIntValue());
        GridView gridView = (GridView) LayoutInflater.from(getActivity()).inflate(R.layout.colors_panel_layout, null);
        gridView.setStretchMode(GridView.STRETCH_COLUMN_WIDTH);
        gridView.setCacheColorHint(0);
        gridView.setAdapter(adapter);
        builder.setView(gridView);
        final AlertDialog dialog = builder.show();
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                dialog.dismiss();
                int value = ThemeUtil.getCurrentTheme(getActivity()).getIntValue();
                if (value != position) {
                    PreferenceUtils.setPrefInt(getActivity(), "theme", position);
                }
                themeState();
            }
        });
    }

    public void cacheClean() {
        DataCleanManager.clearAllCache(getActivity().getApplicationContext());
        initCacheSize();
    }

    private void initCacheSize() {
        try {
            cleanCachePreference.setSummary("当前缓存:" + DataCleanManager.getTotalCacheSize(getActivity()));
        } catch (Exception e) {
            cleanCachePreference.setSummary("当前缓存:未知");
        }
    }

    public void themeState() {
        switch (PreferenceUtils.getPrefInt(getActivity(), "theme", 2)) {
            case 0:
                appThemePreference.setSummary("当前主题：魅惑红");
                break;
            case 1:
                appThemePreference.setSummary("当前主题：咖啡色");
                break;
            case 2:
                appThemePreference.setSummary("当前主题：天空蓝");
                break;
            case 3:
                appThemePreference.setSummary("当前主题：典雅色");
                break;
            case 4:
                appThemePreference.setSummary("当前主题：活力橙");
                break;
            case 5:
                appThemePreference.setSummary("当前主题：优雅紫");
                break;
            case 6:
                appThemePreference.setSummary("当前主题：可爱粉");
                break;
            case 7:
                appThemePreference.setSummary("当前主题：苹果绿");
                break;
            default:
                break;
        }
    }

}
