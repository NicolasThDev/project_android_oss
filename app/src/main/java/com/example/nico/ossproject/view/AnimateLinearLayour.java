package com.example.nico.ossproject.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * Created by nico on 01/09/2017.
 */

public class AnimateLinearLayour extends LinearLayout {


    public AnimateLinearLayour(Context context) {
        super(context);
    }

    public AnimateLinearLayour(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public AnimateLinearLayour(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public AnimateLinearLayour(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }



    @Override
    public void setVisibility(int visibility)
    {
        if (getVisibility() != visibility)
        {
            if (visibility == VISIBLE)
            {
                animate()
                        .translationY(0)
                        .setListener(new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationEnd(Animator animation) {
                                super.onAnimationEnd(animation);
                            }
                        });
            }
            else if ((visibility == INVISIBLE) || (visibility == GONE))
            {
                animate()
                        .translationY(getHeight())
                        .setListener(new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationStart(Animator animation) {
                                super.onAnimationStart(animation);

                            }
                        });
            }
        }

        super.setVisibility(visibility);
    }
}
