package kz.catalogue.ui.custom;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.DecelerateInterpolator;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import kz.catalogue.R;

public class CircularProgressView extends View {
    private int progress = 0;
    private int maxProgress = 100;
    private int progressColor;
    private int backgroundColor;
    private float strokeWidth;
    private boolean animateProgress;
    private int startAngle = 135; // -90 for full circular progress

    private Paint progressPaint;
    private Paint backgroundPaint;
    private RectF rectF;
    private float animatedProgress = 0;

    private OnProgressChangeListener progressChangeListener;
    private ValueAnimator animator;

    public CircularProgressView(Context context) {
        super(context);
        init(null);
    }

    public CircularProgressView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public CircularProgressView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(@Nullable AttributeSet attributeSet) {
        TypedArray a = getContext().obtainStyledAttributes(attributeSet, R.styleable.CircularProgressView);

        progress = a.getInt(R.styleable.CircularProgressView_progress, 0);
        maxProgress = a.getInt(R.styleable.CircularProgressView_maxProgress, 100);
        progressColor = a.getColor(R.styleable.CircularProgressView_progressColor, 0xFF00FF00);
        backgroundColor = a.getColor(R.styleable.CircularProgressView_backgroundColor, 0xFFDDDDDD);
        strokeWidth = a.getDimension(R.styleable.CircularProgressView_strokeWidth, 20f);
        animateProgress = a.getBoolean(R.styleable.CircularProgressView_animateProgress, true);

        a.recycle();

        progressPaint = createPaint(progressColor);
        backgroundPaint = createPaint(backgroundColor);

        rectF = new RectF();

        if (animateProgress) {
            startAnimation();
        } else {
            animatedProgress = progress;
            notifyProgressChange();
        }
    }

    private Paint createPaint(int color) {
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeWidth(strokeWidth);
        paint.setColor(color);
        return paint;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        float padding = strokeWidth / 2;
        rectF.set(padding, padding, w - padding, h - padding);
    }

    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        super.onDraw(canvas);
        //canvas.drawOval(rectF, backgroundPaint); uncom this for full circular progress
        canvas.drawArc(rectF, startAngle, 270, false, backgroundPaint);
        float sweepAngle = (animatedProgress / maxProgress) * 270; // 360 for full
        canvas.drawArc(rectF, startAngle, sweepAngle, false, progressPaint);
    }

    public void setProgress(int progress) {
        int clampedProgress = Math.min(Math.max(progress, 0), maxProgress);
        if (this.progress == clampedProgress) return;

        this.progress = clampedProgress;

        if (animateProgress) {
            startAnimation();
        } else {
            animatedProgress = this.progress;
            notifyProgressChange();
            invalidate();
        }
    }

    public int getProgress() {
        return Math.round(animatedProgress);
    }

    public void setOnProgressChangeListener(OnProgressChangeListener listener) {
        this.progressChangeListener = listener;
    }

    private void notifyProgressChange() {
        if (progressChangeListener != null) {
            progressChangeListener.onProgressChanged(getProgress());
        }
    }

    private void startAnimation() {
        if (animator != null && animator.isRunning()) {
            animator.cancel();
        }

        animator = ValueAnimator.ofFloat(animatedProgress, progress);
        animator.setDuration(500);
        animator.setInterpolator(new DecelerateInterpolator());
        animator.addUpdateListener(valueAnimator -> {
            animatedProgress = (float) valueAnimator.getAnimatedValue();
            notifyProgressChange();
            invalidate();
        });

        animator.start();
    }

    public void setProgressColor(int color) {
        this.progressColor = color;
        progressPaint.setColor(color);
        invalidate();
    }

    public void setBackgroundColor(int color) {
        this.backgroundColor = color;
        backgroundPaint.setColor(color);
        invalidate();
    }

    public void setStrokeWidth(float strokeWidth) {
        this.strokeWidth = strokeWidth;
        progressPaint.setStrokeWidth(strokeWidth);
        backgroundPaint.setStrokeWidth(strokeWidth);
        invalidate();
    }

    public void setStartAngle(int startAngle) {
        this.startAngle = startAngle;
        invalidate();
    }
}
