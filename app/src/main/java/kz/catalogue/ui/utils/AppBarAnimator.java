package kz.catalogue.ui.utils;

import android.content.Context;
import android.view.animation.AccelerateDecelerateInterpolator;

import kz.catalogue.databinding.ActivityMainBinding;

public class AppBarAnimator {

    private static final long ANIMATION_DURATION_MS = 200L;
    private static final float BACK_BUTTON_VISIBLE_SCALE = 1f;
    private static final float BACK_BUTTON_HIDDEN_SCALE = 0f;
    private static final float BACK_BUTTON_INITIAL_MARGIN = 10f;
    private static final float TITLE_SHIFT_WITH_BACK = 60f;
    private static final float TITLE_SHIFT_NO_BACK = 20f;
    private static final float TITLE_INITIAL_MARGIN = 20f;

    private final ActivityMainBinding binding;
    private final Context context;

    public AppBarAnimator(ActivityMainBinding binding, Context context) {
        this.binding = binding;
        this.context = context;

        binding.appBarBackButton.setTranslationX(UiUtils.dpToPx(context, BACK_BUTTON_INITIAL_MARGIN));
        binding.appBarTitle.setTranslationX(UiUtils.dpToPx(context, TITLE_INITIAL_MARGIN));
    }

    public void animateBackButton(boolean isVisible) {
        binding.appBarTitle.clearAnimation();
        if (isVisible) {
            binding.appBarBackButton.setClickable(true);
            binding.appBarBackButton.animate()
                    .alpha(1)
                    .scaleX(BACK_BUTTON_VISIBLE_SCALE)
                    .scaleY(BACK_BUTTON_VISIBLE_SCALE)
                    .setInterpolator(new AccelerateDecelerateInterpolator())
                    .setDuration(ANIMATION_DURATION_MS);
        } else {
            binding.appBarBackButton.animate()
                    .alpha(0)
                    .scaleX(BACK_BUTTON_HIDDEN_SCALE)
                    .scaleY(BACK_BUTTON_HIDDEN_SCALE)
                    .setInterpolator(new AccelerateDecelerateInterpolator())
                    .setDuration(ANIMATION_DURATION_MS)
                    .withEndAction(() -> binding.appBarBackButton.setClickable(false));
        }
    }

    public void animateTitle(boolean hasBackStack) {
        float translationX = UiUtils.dpToPx(context, hasBackStack ? TITLE_SHIFT_WITH_BACK : TITLE_SHIFT_NO_BACK);
        binding.appBarTitle.clearAnimation();
        binding.appBarTitle.animate()
                .translationX(translationX)
                .setInterpolator(new AccelerateDecelerateInterpolator())
                .setDuration(ANIMATION_DURATION_MS);
    }
}
