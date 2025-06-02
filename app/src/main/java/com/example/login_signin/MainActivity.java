package com.example.login_signin;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button loginButton;
    private TextView welcomeTextView;
    private TextView titleTextView; // Thêm dòng này để xử lý Ajheryuk

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Tìm TextView có ID là textView5 để đổi màu một phần chữ
        titleTextView = findViewById(R.id.textView5);
        SpannableString styledTitle = new SpannableString("Ajheryuk");

        // Tô màu đỏ cho "Ajhe" (từ ký tự 0 đến 4)
        ForegroundColorSpan redSpan = new ForegroundColorSpan(Color.parseColor("#FD6C6C"));
        styledTitle.setSpan(redSpan, 0, 4, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        titleTextView.setText(styledTitle);

        // Xử lý nút GET STARTED
        loginButton = findViewById(R.id.loginButton);
        loginButton.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, Login.class));
            finish();
        });
    }
}
