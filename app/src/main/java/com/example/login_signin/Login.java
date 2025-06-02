package com.example.login_signin;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import androidx.appcompat.app.AppCompatActivity;

public class Login extends AppCompatActivity {

    private TextInputEditText emailEditText, passwordEditText;
    private MaterialButton loginButton;
    private TextView forgotPassword, signUpText;
    private LinearLayout facebookLoginLayout, googleLoginLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        loginButton = findViewById(R.id.loginButton);
        forgotPassword = findViewById(R.id.forgotPassword);
        signUpText = findViewById(R.id.signUpText);

        LinearLayout googleFacebookContainer = findViewById(R.id.googleFacebookLayout);
        facebookLoginLayout = (LinearLayout) googleFacebookContainer.getChildAt(0);
        googleLoginLayout = (LinearLayout) googleFacebookContainer.getChildAt(1);

        loginButton.setOnClickListener(v -> {
            String email = emailEditText.getText().toString().trim();
            String password = passwordEditText.getText().toString().trim();

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Điền vào khoảng trống", Toast.LENGTH_SHORT).show();
                return;
            }

            // Lấy dữ liệu đăng ký đã lưu
            SharedPreferences prefs = getSharedPreferences("UserData", MODE_PRIVATE);
            String registeredEmail = prefs.getString("email", null);
            String registeredPassword = prefs.getString("password", null);

            if (registeredEmail == null || registeredPassword == null) {
                Toast.makeText(this, "Chưa đăng kí tài khoản", Toast.LENGTH_SHORT).show();
            } else if (email.equals(registeredEmail) && password.equals(registeredPassword)) {
                Toast.makeText(this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();

                // Chuyển sang MainActivity (màn hình chính của ứng dụng)
                Intent intent = new Intent(Login.this, MainActivity.class);
                startActivity(intent);
                finish(); // Kết thúc Login để không quay lại màn hình này khi bấm back
            } else {
                Toast.makeText(this, "Email hoặc mật khẩu không đúng", Toast.LENGTH_SHORT).show();
            }
        });

        forgotPassword.setOnClickListener(v -> {
            Toast.makeText(this, "Chuyển sang lấy lại mật khẩu", Toast.LENGTH_SHORT).show();
            // TODO: Tạo màn hình lấy lại mật khẩu nếu muốn
        });

        facebookLoginLayout.setOnClickListener(v -> {
            Toast.makeText(this, "Đăng nhập bằng Facebook", Toast.LENGTH_SHORT).show();
            // TODO: Tích hợp SDK Facebook Login ở đây nếu cần
        });

        googleLoginLayout.setOnClickListener(v -> {
            Toast.makeText(this, "Đăng nhập bằng Google", Toast.LENGTH_SHORT).show();
            // TODO: Tích hợp Google Sign-In SDK nếu cần
        });

        signUpText.setOnClickListener(v -> {
            Intent intent = new Intent(Login.this, SiginUp.class);
            startActivity(intent);
        });
    }
}
