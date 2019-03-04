package com.google.codelabs.mdc.java.shrine;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.button.MaterialButton;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Fragment representing the login screen for Shrine.
 */
public class LoginFragment extends Fragment {

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.shr_login_fragment, container, false);
        final TextInputLayout passwordTextInput = view
                .findViewById(R.id.password_text_input);
        final TextInputEditText passwordEditText = view
                .findViewById(R.id.password_edit_text);
        MaterialButton nextButton = view
                .findViewById(R.id.next_button);

        nextButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(!isPasswordValid(passwordEditText.getText())){
                    passwordTextInput.setError(getString(R.string.shr_error_password));
                } else {
                    passwordTextInput.setError(null); //Clear the error
                    ((NavigationHost)getActivity()).navigateTo(new ProductGridFragment(), false);
                }
            }
        });

       //Clear the error once more 8 characters are typed
        passwordEditText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if(isPasswordValid(passwordEditText.getText())){
                    passwordTextInput.setError(null); //Clear the error
                }
                return false;
            }
        });

        return view;
    }

    private boolean isPasswordValid (@Nullable Editable text){
        return text!=null && text.length()>=getContext().getResources().getInteger(R.integer.shr_password_min_length);

    }

}
