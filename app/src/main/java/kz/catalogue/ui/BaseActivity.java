package kz.catalogue.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.viewbinding.ViewBinding;

import kz.catalogue.viewmodels.BaseViewModel;

public abstract class BaseActivity<VB extends ViewBinding, VM extends BaseViewModel> extends AppCompatActivity {
    protected VB binding;
    protected VM viewModel;

    protected abstract VB createViewBinding(LayoutInflater layoutInflater);
    protected abstract VM createViewModel();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        EdgeToEdge.enable(this);
        super.onCreate(savedInstanceState);
        binding = createViewBinding(getLayoutInflater());
        viewModel = createViewModel();
    }

    @Override
    public void setContentView(View view) {
        super.setContentView(view);

        ViewCompat.setOnApplyWindowInsetsListener(view, (v, windowInsets) -> {
            Insets insets = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars());

            ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) v.getLayoutParams();
            layoutParams.topMargin = insets.top;
            layoutParams.leftMargin = insets.left;
            layoutParams.bottomMargin = insets.bottom;
            layoutParams.rightMargin = insets.right;
            v.setLayoutParams(layoutParams);

            return WindowInsetsCompat.CONSUMED;
        });
    }
}
