package kz.catalogue.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import kz.catalogue.R;
import kz.catalogue.databinding.ActivityMainBinding;
import kz.catalogue.ui.fragments.FragmentGridList;
import kz.catalogue.ui.utils.Utils;
import kz.catalogue.viewmodel.MainViewModel;

public class MainActivity extends BaseActivity<ActivityMainBinding, MainViewModel> {
    @Override
    protected ActivityMainBinding createViewBinding(LayoutInflater layoutInflater) {
        return ActivityMainBinding.inflate(layoutInflater);
    }

    @Override
    protected MainViewModel createViewModel() {
        if (viewModel == null)
            return new ViewModelProvider(this).get(MainViewModel.class);

        return viewModel;
    }

    private void showBackButton(boolean show) {
        float startX = Utils.dpToPx(getApplicationContext(), 60f);
        float endX = Utils.dpToPx(getApplicationContext(), 20f);

        if (show) {
            binding.appBarBackButton.animate()
                    .setInterpolator(new AccelerateDecelerateInterpolator())
                    .alpha(1)
                    .withStartAction(() -> binding.appBarBackButton.setVisibility(View.VISIBLE))
                    .setDuration(200);

            binding.appBarTitle.animate()
                    .setInterpolator(new AccelerateDecelerateInterpolator())
                    .translationX(startX)
                    .setDuration(200);
        } else {
            binding.appBarBackButton.animate()
                    .setInterpolator(new AccelerateDecelerateInterpolator())
                    .alpha(0)
                    .withEndAction(() -> binding.appBarBackButton.setVisibility(View.GONE))
                    .setDuration(200);
            binding.appBarTitle.animate()
                    .setInterpolator(new AccelerateDecelerateInterpolator())
                    .translationX(endX)
                    .setDuration(200);

        }
    }

    private void updateAppBar() {
        showBackButton(getSupportFragmentManager().getBackStackEntryCount() > 0);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());

        getSupportFragmentManager().addOnBackStackChangedListener(this::updateAppBar);
        binding.appBarBackButton.setOnClickListener(view -> getSupportFragmentManager().popBackStack());

        if (savedInstanceState == null)
            Utils.loadFragment(getSupportFragmentManager(), new FragmentGridList(), false);
    }
}
