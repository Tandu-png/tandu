package com.example.appmaytinh;
import android.graphics.Color;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.CharArrayWriter;
import java.util.Objects;
import java.util.Stack;

public class MainActivity extends AppCompatActivity {
    TextView Input,Output;
    StringBuilder input= new StringBuilder();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Input = findViewById(R.id.tvInput);
        Output = findViewById(R.id.tvOutput);
        int[] numberButton = {R.id.bt0, R.id.bt1, R.id.bt2, R.id.bt3, R.id.bt4,R.id.bt5, R.id.bt6, R.id.bt7, R.id.bt8, R.id.bt9, R.id.btcong, R.id.bttru, R.id.btnhan, R.id.btchia, R.id.btAC, R.id.btC, R.id.btEqual};
        View.OnClickListener numberList = v -> {
            Button b = (Button) v;
            input.append(b.getText().toString());
            Input.setText(input.toString());
        };
        for (int id : numberButton) {
            Button btn = findViewById(id);
            btn.setOnClickListener(numberList);
            btn.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#2196F3")));
            btn.setTextColor(Color.WHITE);
        }
        // AC và C
        int[] controlButtons = {R.id.btAC, R.id.btC};
        for (int id : controlButtons) {
            Button btn = findViewById(id);
            btn.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#FFFFFF")));
            btn.setTextColor(Color.BLACK);
        }

        // dấu =
        Button equalButton = findViewById(R.id.btEqual);
        equalButton.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#4CAF50")));
        equalButton.setTextColor(Color.WHITE);
        findViewById(R.id.btcong).setOnClickListener(v -> appendOperator("+"));
        findViewById(R.id.bttru).setOnClickListener(v -> appendOperator("-"));
        findViewById(R.id.btnhan).setOnClickListener(v -> appendOperator("x"));
        findViewById(R.id.btchia).setOnClickListener(v -> appendOperator("÷"));
        findViewById(R.id.btAC).setOnClickListener(v -> {
            input.setLength(0);
            Input.setText("Input");
            Output.setText("Output");
        });
        findViewById(R.id.btC).setOnClickListener(v -> {
            if (input.length() > 0) {
                input.deleteCharAt(input.length() - 1);
                Input.setText(input.toString());
            }
        });
        findViewById(R.id.btEqual).setOnClickListener(v -> {
            try {
                double result = evaluateExpression(input.toString());
                Output.setText(String.valueOf(result));
            } catch (Exception e) {
                Output.setText("Lỗi");
            }
        });
    }

    private void appendOperator(String op) {
        input.append(op);
        Input.setText(input.toString());
    }

    private double evaluateExpression(String expression) {
        expression = expression.replaceAll("÷", "/").replaceAll("x", "*");
        Stack<Double> numbers = new Stack<>();
        Stack<Character> operators = new Stack<>();

        StringBuilder number1 = new StringBuilder();

        for (int i = 0; i < expression.length(); i++) {
            char ch = expression.charAt(i);

            if (Character.isDigit(ch) || ch == '.') {
                number1.append(ch);
            } else if (ch == '+' || ch == '-' || ch == '*' || ch == '/') {
                if (number1.length() > 0) {
                    numbers.push(Double.parseDouble(number1.toString()));
                    number1.setLength(0);
                }

                while (!operators.isEmpty() && precedence(ch) <= precedence(operators.peek())) {
                    double b = numbers.pop();
                    double a = numbers.pop();
                    char op = operators.pop();
                    numbers.push(applyOperator(a, b, op));
                }

                operators.push(ch);
            }
        }

        if (number1.length() > 0) {
            numbers.push(Double.parseDouble(number1.toString()));
        }

        while (!operators.isEmpty()) {
            double b = numbers.pop();
            double a = numbers.pop();
            char op = operators.pop();
            numbers.push(applyOperator(a, b, op));
        }

        return numbers.pop();
    }

    private int precedence(char op) {
        if (op == '+' || op == '-') return 1;
        if (op == '*' || op == '/') return 2;
        return 0;
    }

    private double applyOperator(double a, double b, char op) {
        switch (op) {
            case '+': return a + b;
            case '-': return a - b;
            case '*': return a * b;
            case '/': return b == 0 ? 0 : a / b;
        }
        return 0;
    }
}