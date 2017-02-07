package com.study.app.designs;

import android.support.v4.app.Fragment;

import com.study.app.fragment.CatalogFragment;
import com.study.app.fragment.CommentFragment;
import com.study.app.fragment.ParticularsFragment;

import java.util.HashMap;

/**
 * Created by 芮靖林
 * on 2016/12/28.
 * 圈子Fragment的工厂
 */
public class CourseInfoFragmentFactory {

    private static HashMap<Integer, Fragment> fragmentMap = new HashMap<>();

    public static Fragment getFragment(int position) {
        Fragment fragment = fragmentMap.get(position);
        if (fragment != null) {
            return fragment;
        }
        switch (position) {
            case 0:
                fragment = new ParticularsFragment();
                break;
            case 1:
                fragment = new CatalogFragment();
                break;
            case 2:
                fragment = new CommentFragment();
                break;
        }
        fragmentMap.put(position, fragment);
        return fragment;
    }
}
