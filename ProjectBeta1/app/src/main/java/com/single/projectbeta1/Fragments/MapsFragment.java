package com.single.projectbeta1.Fragments;

/**
 * Created by singl on 10/5/2016.
 */

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.single.projectbeta1.R;

/**
 * Created by Admin on 11-12-2015.
 */
public class MapsFragment extends Fragment {
    public MapsFragment()
    {
    // constructor
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_maps, container, false);
    }
}