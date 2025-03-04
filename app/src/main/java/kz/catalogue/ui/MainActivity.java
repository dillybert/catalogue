package kz.catalogue.ui;

import android.os.Bundle;
import android.view.LayoutInflater;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import kz.catalogue.databinding.ActivityMainBinding;
import kz.catalogue.ui.fragments.GridListFragment;
import kz.catalogue.ui.utils.UiUtils;
import kz.catalogue.ui.utils.AppBarAnimator;
import kz.catalogue.viewmodels.MainViewModel;

public class MainActivity extends BaseActivity<ActivityMainBinding, MainViewModel> {

    private AppBarAnimator appBarAnimator;
    private boolean isNavigationBackButtonVisible = false;

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

        initNavigationListeners();

        if (savedInstanceState == null) {
            UiUtils.navigateToFragment(getSupportFragmentManager(), new GridListFragment(), false);
        }
    }

    private void initNavigationListeners() {
        binding.appBarBackButton.setOnClickListener(v -> getSupportFragmentManager().popBackStack());
        getSupportFragmentManager().addOnBackStackChangedListener(this::refreshNavigationState);
        refreshNavigationState();
    }

    private void refreshNavigationState() {
        boolean hasBackStack = getSupportFragmentManager().getBackStackEntryCount() > 0;
        if (hasBackStack != isNavigationBackButtonVisible) {
            isNavigationBackButtonVisible = hasBackStack;
            appBarAnimator.animateBackButton(hasBackStack);
            appBarAnimator.animateTitle(hasBackStack);
        }
    }
}
