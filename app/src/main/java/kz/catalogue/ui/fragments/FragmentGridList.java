package kz.catalogue.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import kz.catalogue.R;
import kz.catalogue.ui.utils.Utils;

public class FragmentGridList extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_grid_list, container, false);

        view.findViewById(R.id.test).setOnClickListener(view1 -> {
            Utils.loadFragment(getParentFragmentManager(), new FragmentCircularProgressView(), true);
        });

        return view;
    }
}
