package kz.catalogue.ui;

import android.os.Bundle;
import android.view.LayoutInflater;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import kz.catalogue.databinding.ActivityMainBinding;
import kz.catalogue.ui.fragments.FragmentGridList;
import kz.catalogue.ui.utils.UiUtils;
import kz.catalogue.ui.utils.AppBarAnimator;
import kz.catalogue.viewmodel.MainViewModel;

public class MainActivity extends BaseActivity<ActivityMainBinding, MainViewModel> {

    private AppBarAnimator appBarAnimator;
    private boolean isBackButtonVisible = false;

    @Override
    protected ActivityMainBinding createViewBinding(LayoutInflater inflater) {
        return ActivityMainBinding.inflate(inflater);
    }

    @Override
    protected MainViewModel createViewModel() {
        return new ViewModelProvider(this).get(MainViewModel.class);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());
        appBarAnimator = new AppBarAnimator(binding, this);

        setupNavigation();

        if (savedInstanceState == null) {
            UiUtils.loadFragment(getSupportFragmentManager(), new FragmentGridList(), false);
        }
    }

    private void setupNavigation() {
        binding.appBarBackButton.setOnClickListener(v -> getSupportFragmentManager().popBackStack());
        getSupportFragmentManager().addOnBackStackChangedListener(this::updateNavigationState);
        updateNavigationState();
    }

    private void updateNavigationState() {
        boolean hasBackStack = getSupportFragmentManager().getBackStackEntryCount() > 0;
        if (hasBackStack != isBackButtonVisible) {
            isBackButtonVisible = hasBackStack;
            appBarAnimator.animateBackButton(hasBackStack);
            appBarAnimator.animateTitle(hasBackStack);
        }
    }
}
