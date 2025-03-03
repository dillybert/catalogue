package kz.catalogue.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import kz.catalogue.databinding.ActivityMainBinding;
import kz.catalogue.ui.fragments.FragmentGridList;
import kz.catalogue.ui.utils.Utils;
import kz.catalogue.viewmodel.MainViewModel;

public class MainActivity extends BaseActivity<ActivityMainBinding, MainViewModel> {

    private static final long ANIMATION_DURATION = 200L;
    private static final float BACK_BUTTON_VISIBLE_SCALE = 1f;
    private static final float BACK_BUTTON_HIDDEN_SCALE = 0f;

    @Override
    protected ActivityMainBinding createViewBinding(LayoutInflater layoutInflater) {
        return ActivityMainBinding.inflate(layoutInflater);
    }

    @Override
    protected MainViewModel createViewModel() {
        return new ViewModelProvider(this).get(MainViewModel.class);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(binding.getRoot());

        setupAppBar();
        if (savedInstanceState == null) {
            Utils.loadFragment(getSupportFragmentManager(), new FragmentGridList(), false);
        }
    }

    private void setupAppBar() {
        binding.appBarBackButton.setOnClickListener(view -> getSupportFragmentManager().popBackStack());
        getSupportFragmentManager().addOnBackStackChangedListener(this::updateAppBar);
        updateAppBar(); // Ensure correct state on launch
    }

    private void updateAppBar() {
        boolean hasBackStack = getSupportFragmentManager().getBackStackEntryCount() > 0;
        animateBackButton(hasBackStack);
        animateTitle(hasBackStack);
    }

    private void animateBackButton(boolean show) {
        if (show) {
            binding.appBarBackButton.setVisibility(View.VISIBLE);
            binding.appBarBackButton.animate()
                    .alpha(1)
                    .scaleX(BACK_BUTTON_VISIBLE_SCALE)
                    .scaleY(BACK_BUTTON_VISIBLE_SCALE)
                    .setInterpolator(new AccelerateDecelerateInterpolator())
                    .setDuration(ANIMATION_DURATION);
        } else {
            binding.appBarBackButton.animate()
                    .alpha(0)
                    .scaleX(BACK_BUTTON_HIDDEN_SCALE)
                    .scaleY(BACK_BUTTON_HIDDEN_SCALE)
                    .setInterpolator(new AccelerateDecelerateInterpolator())
                    .setDuration(ANIMATION_DURATION)
                    .withEndAction(() -> binding.appBarBackButton.setVisibility(View.GONE));
        }
    }

    private void animateTitle(boolean hasBackStack) {
        float translationX = hasBackStack ? Utils.dpToPx(this, 60f) : Utils.dpToPx(this, 20f);
        binding.appBarTitle.animate()
                .translationX(translationX)
                .setInterpolator(new AccelerateDecelerateInterpolator())
                .setDuration(ANIMATION_DURATION);
    }
}
