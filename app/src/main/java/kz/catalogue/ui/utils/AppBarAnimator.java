package kz.catalogue.ui.utils;

import android.content.Context;
import android.view.animation.AccelerateDecelerateInterpolator;

import kz.catalogue.databinding.ActivityMainBinding;

public class AppBarAnimator {

    private static final long ANIMATION_DURATION_MS = 200L;

    // Scale states for app_bar_back_button
    private static final float SCALE_VISIBLE = 1f;
    private static final float SCALE_HIDDEN = 0f;

    // Margins and transition values as dp
    private static final float BACK_BUTTON_INITIAL_MARGIN_DP = 10f;
    private static final float TITLE_INITIAL_MARGIN_DP = 20f;
    private static final float TITLE_SHIFT_WITH_BACK_DP = 60f;
    private static final float TITLE_SHIFT_NO_BACK_DP = 20f;

    private final ActivityMainBinding binding;
    private final Context context;

    public AppBarAnimator(ActivityMainBinding binding, Context context) {
        this.binding = binding;
        this.context = context;

        binding.appBarBackButton.setTranslationX(UiUtils.dpToPx(context, BACK_BUTTON_INITIAL_MARGIN_DP));
        binding.appBarTitle.setTranslationX(UiUtils.dpToPx(context, TITLE_INITIAL_MARGIN_DP));
    }

    public void animateBackButton(boolean isVisible) {
        binding.appBarTitle.clearAnimation();
        if (isVisible) {
            binding.appBarBackButton.setClickable(true);
            binding.appBarBackButton.animate()
                    .alpha(1)
                    .scaleX(SCALE_VISIBLE)
                    .scaleY(SCALE_VISIBLE)
                    .setInterpolator(new AccelerateDecelerateInterpolator())
                    .setDuration(ANIMATION_DURATION_MS);
        } else {
            binding.appBarBackButton.animate()
                    .alpha(0)
                    .scaleX(SCALE_HIDDEN)
                    .scaleY(SCALE_HIDDEN)
                    .setInterpolator(new AccelerateDecelerateInterpolator())
                    .setDuration(ANIMATION_DURATION_MS)
                    .withEndAction(() -> binding.appBarBackButton.setClickable(false));
        }
    }

    public void animateTitle(boolean hasBackStack) {
        float translationX = UiUtils.dpToPx(context, hasBackStack ? TITLE_SHIFT_WITH_BACK_DP : TITLE_SHIFT_NO_BACK_DP);
        binding.appBarTitle.clearAnimation();
        binding.appBarTitle.animate()
                .translationX(translationX)
                .setInterpolator(new AccelerateDecelerateInterpolator())
                .setDuration(ANIMATION_DURATION_MS);
    }
}
