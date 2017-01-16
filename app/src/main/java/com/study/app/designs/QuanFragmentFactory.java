package com.study.app.designs;

import android.support.v4.app.Fragment;

import com.study.app.fragment.AttentionFragment;
import com.study.app.fragment.CourseFragment;
import com.study.app.fragment.HomeFragment;
import com.study.app.fragment.HotFragment;
import com.study.app.fragment.MineFragment;
import com.study.app.fragment.QuanFragment;
import com.study.app.fragment.TopicFragment;

import java.util.HashMap;

/**
 * Created by 芮靖林
 * on 2016/12/28.
 * 圈子Fragment的工厂
 */
public class QuanFragmentFactory {

    private static HashMap<Integer, Fragment> fragmentMap = new HashMap<>();

    public static Fragment getFragment(int position) {
        Fragment fragment = fragmentMap.get(position);
        if (fragment != null) {
            return fragment;
        }
        switch (position) {
            case 0:
                fragment = new TopicFragment();
                break;
            case 1:
                fragment = new HotFragment();
                break;
            case 2:
                fragment = new AttentionFragment();
                break;
        }
        fragmentMap.put(position, fragment);
        return fragment;
    }
}
