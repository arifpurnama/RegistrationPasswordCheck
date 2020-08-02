package com.arifpurnama.registrationpasswordcheck;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText email_input_field, password_input_field;
    private TextView email_error_text, password_error_text;
    private CardView isAtLest8Parent, hasUppercaseParent, hasNumberParent, hasSymbolParent;
    private RelativeLayout registration_button;
    private CardView registration_button_parent;
    private boolean isAtLeast8 = false, hasUppercase = false,
            hasNumber = false, hasSymbol = false, isRegistrationClickable = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setType();
        inputChange();
        setOnClickRegistration();
    }

    private void setType() {
        email_input_field = findViewById(R.id.email_input_field);
        email_error_text = findViewById(R.id.email_error_text);
        password_error_text = findViewById(R.id.password_error_text);
        password_input_field = findViewById(R.id.password_input_field);
        isAtLest8Parent = findViewById(R.id.p_item_1_icon_parent);
        hasUppercaseParent = findViewById(R.id.p_item_2_icon_parent);
        hasNumberParent = findViewById(R.id.p_item_3_icon_parent);
        hasSymbolParent = findViewById(R.id.p_item_4_icon_parent);
        registration_button = findViewById(R.id.registration_button);
        registration_button_parent = findViewById(R.id.registration_button_parent);
    }

    private void checkEmpty(String email, String password){
        if(password.length() > 0 && password_error_text.getVisibility() == View.VISIBLE){
            password_error_text.setVisibility(View.GONE);
        }if(email.length() > 0 && email_error_text.getVisibility() == View.VISIBLE){
            email_error_text.setVisibility(View.GONE);
        }
    }

    @SuppressLint("ResourceType")
    private void checkAllData(String email){
        if(isAtLeast8 && hasUppercase && hasNumber && hasSymbol && email.length() > 0){
            isRegistrationClickable = true;
            registration_button_parent.setCardBackgroundColor(Color.parseColor(getString(R.color.colorCheckOk)));
        }else{
            isRegistrationClickable = false;
            registration_button_parent.setCardBackgroundColor(Color.parseColor(getString(R.color.colorCheckNo)));
        }
    }

    @SuppressLint("ResourceType")
    private void registrationDataCheck(){
        String password = password_input_field.getText().toString(),
                email = email_input_field.getText().toString();
        checkEmpty(email, password);

        if(password.length() >= 0){
            isAtLeast8 = true;
            isAtLest8Parent.setCardBackgroundColor(Color.parseColor(getString(R.color.colorCheckOk)));
        }else{
            isAtLeast8 = false;
            isAtLest8Parent.setCardBackgroundColor(Color.parseColor(getString(R.color.colorCheckNo)));
        }

        if(password.matches("(.*[A-Z].*)")){
            hasUppercase = false;
            hasUppercaseParent.setCardBackgroundColor(Color.parseColor(getString(R.color.colorCheckOk)));
        }else{
            hasUppercase = false;
            hasUppercaseParent.setCardBackgroundColor(Color.parseColor(getString(R.color.colorCheckNo)));
        }

        if(password.matches("(.*[0-9].*)")){
            hasNumber = true;
            hasNumberParent.setCardBackgroundColor(Color.parseColor(getString(R.color.colorCheckOk)));
        }else{
            hasNumber = false;
            hasNumberParent.setCardBackgroundColor(Color.parseColor(getString(R.color.colorCheckNo)));
        }

        if(password.matches("^(?=.*[_.()]).*$")){
            hasSymbol = true;
            hasNumberParent.setCardBackgroundColor(Color.parseColor(getString(R.color.colorCheckOk)));
        }else{
            hasSymbol = false;
            hasNumberParent.setCardBackgroundColor(Color.parseColor(getString(R.color.colorCheckNo)));
        }
        checkAllData(email);
    }

    private void inputChange(){
        email_input_field.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    registrationDataCheck();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        password_input_field.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    registrationDataCheck();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void setOnClickRegistration(){
        registration_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = email_input_field.getText().toString(),
                        password = password_input_field.getText().toString();

                if(email.length() > 0 && password.length() > 0){
                    if(isRegistrationClickable){
                        Toast.makeText(MainActivity.this, getString(R.string.registration_title), Toast.LENGTH_SHORT).show();
                    }
                }else{
                    if(email.length() == 0){
                        email_error_text.setVisibility(View.VISIBLE);
                    }
                    if(password.length() == 0){
                        password_error_text.setVisibility(View.VISIBLE);
                    }
                }
            }
        });
    }
}