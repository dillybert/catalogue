package kz.catalogue.ui.utils;

import android.content.Context;
import android.util.TypedValue;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import kz.catalogue.R;

public class UiUtils {
    public static float dpToPx(Context context, float dp) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, context.getResources().getDisplayMetrics());
    }

    public static void navigateToFragment(FragmentManager fragmentManager, Fragment fragment, boolean addToBackStack) {
        final String fragmentTag = fragment.getClass().getSimpleName();

        Fragment currentFragment = fragmentManager.findFragmentById(R.id.fragment_container_view_tag);
        if (currentFragment != null &&
                currentFragment.getClass().getSimpleName().equals(fragmentTag)) {
            return;
        }

        FragmentTransaction transaction = fragmentManager.beginTransaction();

        transaction.setCustomAnimations(
                R.anim.scale_in,
                R.anim.scale_out,
                R.anim.scale_in,
                R.anim.scale_out
        );

        transaction.replace(R.id.fragment_container_view_tag, fragment, fragmentTag);

        if (addToBackStack) {
            transaction.addToBackStack(fragmentTag);
        }

        transaction.commit();
    }
}
