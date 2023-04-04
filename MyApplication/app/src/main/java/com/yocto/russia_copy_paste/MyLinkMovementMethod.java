package com.yocto.russia_copy_paste;


import android.text.Layout;
import android.text.Selection;
import android.text.Spannable;
import android.text.method.ArrowKeyMovementMethod;
import android.text.style.URLSpan;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.TextView;

// https://stackoverflow.com/questions/10572389/clickable-links-and-copy-paste-menu-in-editview-in-android/18541955#18541955
public class MyLinkMovementMethod extends ArrowKeyMovementMethod {
    private static final String TAG = "MyLinkMovementMethod";

    private static final String LINK_ACTION_DIALOG_FRAGMENT = "LINK_ACTION_DIALOG_FRAGMENT";

    private static class Ref {
        static final MyLinkMovementMethod singleton = new MyLinkMovementMethod();
    }

    public static MyLinkMovementMethod getInstance() {
        return Ref.singleton;
    }

    @Override
    public boolean onTouchEvent(TextView widget, Spannable buffer, MotionEvent event) {
        int action = event.getAction();

        if (action == MotionEvent.ACTION_UP ||
                action == MotionEvent.ACTION_DOWN) {
            int x = (int) event.getX();
            int y = (int) event.getY();

            x -= widget.getTotalPaddingLeft();
            y -= widget.getTotalPaddingTop();

            x += widget.getScrollX();
            y += widget.getScrollY();

            Layout layout = widget.getLayout();
            int line = layout.getLineForVertical(y);
            int off = layout.getOffsetForHorizontal(line, x);

            URLSpan[] link = buffer.getSpans(off, off, URLSpan.class);

            if (link.length != 0) {
                if (action == MotionEvent.ACTION_UP) {
                    showAlertDialog(widget, link[0]);
                } else {
                    Selection.setSelection(buffer, buffer.getSpanStart(link[0]), buffer.getSpanEnd(link[0]));
                }

                return true;
            }
            /*else {
                that's the line we need to remove
                Selection.removeSelection(buffer);
            }*/
        }

        try {
            return super.onTouchEvent(widget, buffer, event);
        } catch (java.lang.IndexOutOfBoundsException e) {
            Log.e(TAG, "", e);
            return true;
        }
    }

    private static void showAlertDialog(TextView textView, URLSpan urlSpan) {
    }
}

