package com.example.hamburgershop;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class PaymentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_payment);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button btnCard = findViewById(R.id.bCard);
        Button btnCash= findViewById(R.id.bCash);

        btnCard.setOnClickListener(v -> {
            Toast.makeText(PaymentActivity.this, "שולם באשראי, תודה!", Toast.LENGTH_LONG).show();
            finish();
        });

        btnCash.setOnClickListener(v -> {
            Toast.makeText(PaymentActivity.this, "שולם במזומן, תודה!", Toast.LENGTH_LONG).show();
            finish();
        });
    }
}
