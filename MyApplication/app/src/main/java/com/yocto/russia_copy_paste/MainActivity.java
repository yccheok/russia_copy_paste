package com.yocto.russia_copy_paste;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.util.Linkify;
import android.widget.EditText;

import com.google.android.material.bottomsheet.BottomSheetBehavior;


public class MainActivity extends AppCompatActivity {

    LinedEditText linedEditText;
    EditText editText;
    BodyTextWatcher bodyTextWatcher = new BodyTextWatcher();

    private class BodyTextWatcher implements TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        @Override
        public void afterTextChanged(Editable editable) {
            Linkify.addLinks(editable, Linkify.WEB_URLS | Linkify.EMAIL_ADDRESSES);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        linedEditText = findViewById(R.id.edit_text_1);
        editText = findViewById(R.id.edit_text_2);

        linedEditText.setLinksClickable(false);
        linedEditText.setAutoLinkMask(Linkify.WEB_URLS | Linkify.EMAIL_ADDRESSES);
        linedEditText.setMovementMethod(MyLinkMovementMethod.getInstance());
        linedEditText.addTextChangedListener(this.bodyTextWatcher);
    }
}