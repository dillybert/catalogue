package kz.catalogue.ui.utils;

import android.content.Context;
import android.util.TypedValue;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import kz.catalogue.R;

public class UiUtils {
    public static float dpToPx(Context context, float dp) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, context.getResources().getDisplayMetrics());
    }

    public static void loadFragment(FragmentManager manager, Fragment fragment, boolean addToBackStack) {
        String fragmentTag = fragment.getClass().getSimpleName();

        Fragment currentFragment = manager.findFragmentById(R.id.fragment_container_view_tag);
        if (currentFragment != null && currentFragment.getClass().getSimpleName().equals(fragmentTag)) {
            return;
        }

        androidx.fragment.app.FragmentTransaction transaction = manager.beginTransaction()
                .replace(R.id.fragment_container_view_tag, fragment, fragmentTag);

        if (addToBackStack) {
            transaction.addToBackStack(fragmentTag);
        }

        transaction.commit();
    }
}
