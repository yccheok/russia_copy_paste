package com.yocto.russia_copy_paste;

import static com.yocto.russia_copy_paste.Utils.lineSpacingWorkaroundRequired;
import static com.yocto.russia_copy_paste.Utils.setFallbackLineSpacingIfPossible;

import android.content.Context;
import android.util.AttributeSet;

/**
 * Created by yccheok on 24/3/2018.
 */

public class LinedEditText extends androidx.appcompat.widget.AppCompatEditText {
    private static final float DEFAULT_LINE_SPACING_EXTRA = 0.0f;
    private static final float DEFAULT_LINE_SPACING_MULTIPLIER = 1.4f;

    public LinedEditText(Context context) {
        super(context);
        setFallbackLineSpacingIfPossible(this, false);
    }

    public LinedEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        setFallbackLineSpacingIfPossible(this, false);
    }

    public LinedEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setFallbackLineSpacingIfPossible(this, false);
    }

    // protected void onDraw(Canvas canvas) {
    // Line drawing will be performed by LinedFrameLayout.

    // https://stackoverflow.com/questions/49467579/workaround-for-edittext-ignoring-linespacingmultiplier
    @Override
    protected void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter);

        if (lineSpacingWorkaroundRequired()) {
            if (lengthBefore != lengthAfter) {
                float add;
                float mul;

                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
                    add = getLineSpacingExtra();
                    mul = getLineSpacingMultiplier();
                } else {
                    add = DEFAULT_LINE_SPACING_EXTRA;
                    mul = DEFAULT_LINE_SPACING_MULTIPLIER;
                }

                setLineSpacing(0f, 1f);
                setLineSpacing(add, mul);
            }
        }
    }

}