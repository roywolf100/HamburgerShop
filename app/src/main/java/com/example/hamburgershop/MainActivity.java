package com.example.hamburgershop;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    int price = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button btnOrder = findViewById(R.id.btnOrder);
        RadioGroup rgSize = findViewById(R.id.rgSize);
        RadioGroup rgDoneness = findViewById(R.id.doneness);
        Button btnClear = findViewById(R.id.btnClear);
        btnClear.setOnClickListener(v -> {
            rgSize.check(R.id.rbMedium);
            rgDoneness.clearCheck();
            btnClear.setVisibility(View.VISIBLE);
        });

        rgDoneness.setOnCheckedChangeListener((group, checkedId) -> {
            btnClear.setVisibility(View.VISIBLE);
        });

        rgSize.setOnCheckedChangeListener((group, checkedId) -> {
            btnClear.setVisibility(View.VISIBLE);
        });


        btnOrder.setOnClickListener(v -> {
            int checkedId = rgSize.getCheckedRadioButtonId();

            String size;
            if (checkedId == R.id.rbSmall) {
                size = "100g";
                price = 40;
            } else if (checkedId == R.id.rbMedium) {
                size = "200g";
                price = 60;
            } else {
                size = "400g";
                price = 80;
            }

            int donenessId = rgDoneness.getCheckedRadioButtonId();
            String doneness;
            if (donenessId == -1) {
                Toast.makeText(MainActivity.this, "בחרו סוג בצק לפני ההזמנה", Toast.LENGTH_SHORT).show();
                return;
            }
            else if (donenessId == R.id.dR) {
                doneness = "רייר";
            }
            else if (donenessId == R.id.dMR) {
                doneness = " מדיום רייר";
            }

            else if (donenessId == R.id.dM) {
                doneness = " מדיום";
            }

            else if (donenessId == R.id.dMW) {
                doneness = " מדיום וול";
            }

            else {
                doneness = " וול דאן";
            }


            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("אישור הזמנה");
            builder.setMessage("להזמין המבורגר " + size + " במידת עשייה: " + doneness + "במחיר של "+ price + "שקל "+  "?");

            builder.setPositiveButton("אשר", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(MainActivity.this, "ההזמנה התקבלה!", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(MainActivity.this, SummaryActivity.class);
                    intent.putExtra("size", size);
                    intent.putExtra("doneness", doneness);
                    intent.putExtra("price", price);
                    startActivity(intent);
                }
            });

            builder.setNegativeButton("ביטול", null);

            builder.show();        });

    }
}