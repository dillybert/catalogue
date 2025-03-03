package kz.catalogue.ui;

import android.os.Bundle;
import android.view.LayoutInflater;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import kz.catalogue.R;
import kz.catalogue.databinding.ActivityMainBinding;
import kz.catalogue.ui.fragments.FragmentGridList;
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

    private void loadFragment(Fragment fragment, boolean addToBackStack) {
        String fragmentTag = fragment.getClass().getSimpleName();

        Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.fragment_container_view_tag);
        if (currentFragment != null && currentFragment.getClass().getSimpleName().equals(fragmentTag)) {
            return;
        }

        androidx.fragment.app.FragmentTransaction transaction = getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container_view_tag, fragment, fragmentTag);

        if (addToBackStack) {
            transaction.addToBackStack(fragmentTag);
        }

        transaction.commit();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());

        if (savedInstanceState == null)
            loadFragment(new FragmentGridList(), false);
    }
}
