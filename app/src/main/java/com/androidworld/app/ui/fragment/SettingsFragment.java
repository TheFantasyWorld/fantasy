package com.androidworld.app.ui.fragment;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.preference.PreferenceScreen;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;

import com.androidworld.app.R;
import com.androidworld.app.rxbus.RxEvent;
import com.androidworld.app.rxbus.RxEventBus;
import com.androidworld.app.ui.activity.HomeActivity;
import com.androidworld.app.ui.adapter.ColorsListAdapter;
import com.androidworld.app.util.DataCleanManager;
import com.androidworld.app.util.DialogUtils;
import com.androidworld.app.util.PreferenceUtils;
import com.androidworld.app.util.ThemeUtil;
import com.jenzz.materialpreference.Preference;
import com.jenzz.materialpreference.SwitchPreference;

import java.util.Arrays;
import java.util.List;

/**
 * <h3>设置偏好选项</h3>
 *
 * @author LQC
 *         当前时间：2016/7/19 21:24
 */
public class SettingsFragment extends PreferenceFragment implements Dialog.OnClickListener {

    public static final String PREFERENCE_FILE_NAME = "AndroidWorld.settings";
    private SwitchPreference mAutoUpdatePreference;
    private Preference mAppThemePreference;
    private Preference mCleanCachePreference;
    private Preference mCheckUpdatePreference;
    private boolean isAutoUpdate;

    public static SettingsFragment newInstance() {
        return new SettingsFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.prefs);
        getPreferenceManager().setSharedPreferencesName(PREFERENCE_FILE_NAME);
        findPreference();
        changeTheme();
        initCacheSize();
        mCheckUpdatePreference.setSummary("当前版本：v" + getVersion(getActivity()));
    }

    @Override
    public boolean onPreferenceTreeClick(PreferenceScreen preferenceScreen, android.preference.Preference preference) {

        if (isResumed() && preference == null) {
            return false;
        }

        String key = preference.getKey();
        if (TextUtils.equals(key, "right_hand_mode_key")) {

        }

        if (TextUtils.equals(key, "about_key")) {
            getActivity().startActivity(new Intent(getActivity(), HomeActivity.class));
        }

        if (TextUtils.equals(key, "app_theme")) {
            showThemeChooseDialog();
        }

        if (TextUtils.equals(key, "switch_account")) {
            AlertDialog.Builder builder = DialogUtils.makeDialogBuilderByTheme(getActivity());
            builder.setTitle("确认切换").setMessage("切换账号将会退出当前账号，跳转登录界面！").setPositiveButton("确定", this).setNegativeButton("取消", this);
            builder.show();
        }

        if (TextUtils.equals(key, "clean_cache")) {
            cacheClean();
        }

        if (TextUtils.equals(key, "auto_update")) {
            isAutoUpdate = !isAutoUpdate;
            PreferenceUtils.setPrefBoolean(getActivity(), "auto_update", isAutoUpdate);
        }

        if (TextUtils.equals(key, "feedback")) {

        }

        if (TextUtils.equals(key, "check_update")) {

        }

        return false;
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

    public void findPreference() {
        mAutoUpdatePreference = (SwitchPreference) findPreference(getString(R.string.auto_update));
        mAppThemePreference = (Preference) findPreference(getString(R.string.app_theme));
        mCleanCachePreference = (Preference) findPreference(getString(R.string.clean_cache));
        mCheckUpdatePreference = (Preference) findPreference(getString(R.string.check_update));
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
                changeTheme();
                RxEventBus.getInstance().send(new RxEvent(RxEvent.RESTART_WITH_NO_ANIMATION));
//                ((SettingsActivity) getActivity()).reload();
            }
        });
    }

    /**
     * 清除缓存
     */
    public void cacheClean() {
        DataCleanManager.clearAllCache(getActivity().getApplicationContext());
        initCacheSize();
    }

    /**
     * 计算缓存大小
     */
    private void initCacheSize() {
        try {
            mCleanCachePreference.setSummary("当前缓存:" + DataCleanManager.getTotalCacheSize(getActivity()));
        } catch (Exception e) {
            mCleanCachePreference.setSummary("当前缓存:未知");
        }
    }

    /**
     * 切换主题
     */
    public void changeTheme() {
        switch (PreferenceUtils.getPrefInt(getActivity(), "theme", ThemeUtil.Theme.BLUE.getIntValue())) {
            case 0:
                mAppThemePreference.setSummary("当前主题：魅惑红");
                break;
            case 1:
                mAppThemePreference.setSummary("当前主题：咖啡色");
                break;
            case 2:
                mAppThemePreference.setSummary("当前主题：天空蓝");
                break;
            case 3:
                mAppThemePreference.setSummary("当前主题：典雅色");
                break;
            case 4:
                mAppThemePreference.setSummary("当前主题：活力橙");
                break;
            case 5:
                mAppThemePreference.setSummary("当前主题：优雅紫");
                break;
            case 6:
                mAppThemePreference.setSummary("当前主题：可爱粉");
                break;
            case 7:
                mAppThemePreference.setSummary("当前主题：苹果绿");
                break;
            default:
                break;
        }

    }

    @Override
    public void onClick(DialogInterface dialog, int which) {

        switch (which) {
            case DialogInterface.BUTTON_POSITIVE:
                getActivity().startActivity(new Intent(getActivity(), HomeActivity.class));
                getActivity().finish();
                break;
        }
    }
}
