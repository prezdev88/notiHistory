package org.prezdev.notihistory.service;

import android.support.v4.app.Fragment;

public interface FragmentService {
    void load(Fragment fragment);

    Fragment getVisibleFragment();

    boolean isFragmentVisible(Fragment fragment);
}
