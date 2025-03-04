package kz.catalogue.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import kz.catalogue.databinding.FragmentGridListBinding;
import kz.catalogue.ui.utils.UiUtils;

public class GridListFragment extends BaseFragment<FragmentGridListBinding> {
    @Override
    protected FragmentGridListBinding createViewBinding(LayoutInflater inflater, ViewGroup container) {
        return FragmentGridListBinding.inflate(inflater, container, false);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        binding.test.setOnClickListener(view1 -> {
            UiUtils.navigateToFragment(getParentFragmentManager(), new CircularProgressViewFragment(), true);
        });

        return binding.getRoot();
    }
}
