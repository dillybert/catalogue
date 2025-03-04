package kz.catalogue.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import kz.catalogue.databinding.FragmentCircularProgressViewBinding;

public class CircularProgressViewFragment extends BaseFragment<FragmentCircularProgressViewBinding> {
    @Override
    protected FragmentCircularProgressViewBinding createViewBinding(LayoutInflater inflater, ViewGroup container) {
        return FragmentCircularProgressViewBinding.inflate(inflater, container, false);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        return binding.getRoot();
    }
}
