package com.study.app.factory;

import android.support.v4.app.Fragment;


import com.study.app.fragment.CourseFragment;
import com.study.app.fragment.HomeFragment;
import com.study.app.fragment.MineFragment;
import com.study.app.fragment.QuanFragment;

import java.util.HashMap;

/**
 * Created by 芮靖林
 * on 2016/12/28.
 * Fragment的工厂
 */
public class FragmentFactory {

    private static HashMap<Integer, Fragment> fragmentMap = new HashMap<>();

    public static Fragment getFragment(int position) {
        Fragment fragment = fragmentMap.get(position);
        if (fragment != null) {
            return fragment;
        }
        switch (position) {
            case 0:
                fragment = new HomeFragment();
                break;
            case 1:
                fragment = new CourseFragment();
                break;
            case 2:
                fragment = new QuanFragment();
                break;
            case 3:
                fragment = new MineFragment();
                break;
        }
        fragmentMap.put(position, fragment);
        return fragment;
    }
}
