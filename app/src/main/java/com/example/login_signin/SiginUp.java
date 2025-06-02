package com.example.login_signin;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.StyleSpan;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SiginUp extends AppCompatActivity {

    private EditText emailInput, passwordInput, fullNameEditText;
    private Button signUpButton;
    private TextView gotoLoginText, termsTextView;
    private LinearLayout facebookLoginLayout, googleLoginLayout;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.siginup);

        fullNameEditText = findViewById(R.id.fullNameEditText);
        emailInput = findViewById(R.id.emailInput);
        passwordInput = findViewById(R.id.passwordInput);
        signUpButton = findViewById(R.id.signUpButton);
        gotoLoginText = findViewById(R.id.gotoLoginText);
        facebookLoginLayout = findViewById(R.id.facebookLoginLayout);
        googleLoginLayout = findViewById(R.id.googleLoginLayout);
        termsTextView = findViewById(R.id.termsTextView);

        // Tạo đoạn text với 2 phần in đậm
        String text = "By signing up you accept the Terms of Service and Privacy Policy";
        SpannableString spannableString = new SpannableString(text);

        int startTerms = text.indexOf("Terms of Service");
        int endTerms = startTerms + "Terms of Service".length();

        int startPrivacy = text.indexOf("Privacy Policy");
        int endPrivacy = startPrivacy + "Privacy Policy".length();

        spannableString.setSpan(new StyleSpan(Typeface.BOLD), startTerms, endTerms, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new StyleSpan(Typeface.BOLD), startPrivacy, endPrivacy, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        termsTextView.setText(spannableString);

        signUpButton.setOnClickListener(v -> {
            String email = emailInput.getText().toString().trim();
            String password = passwordInput.getText().toString().trim();
            String fullName = fullNameEditText.getText().toString().trim();

            if (email.isEmpty() || password.isEmpty() || fullName.isEmpty()) {
                Toast.makeText(this, "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                return;
            }

            SharedPreferences prefs = getSharedPreferences("UserData", MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();
            editor.putString("email", email);
            editor.putString("password", password);
            editor.putString("fullName", fullName);
            editor.apply();

            Toast.makeText(this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();

            startActivity(new Intent(SiginUp.this, Login.class));
            finish();
        });

        gotoLoginText.setOnClickListener(v -> {
            startActivity(new Intent(SiginUp.this, Login.class));
            finish();
        });

        facebookLoginLayout.setOnClickListener(v -> {
            Toast.makeText(this, "Đăng nhập bằng Facebook", Toast.LENGTH_SHORT).show();
        });

        googleLoginLayout.setOnClickListener(v -> {
            Toast.makeText(this, "Đăng nhập bằng Google", Toast.LENGTH_SHORT).show();
        });
    }
}
