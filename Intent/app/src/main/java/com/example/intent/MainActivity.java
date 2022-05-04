package com.example.intent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bt = findViewById(R.id.bt);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent t = new Intent(MainActivity.this, SecondActivity.class);
                String name = "To An An";
                t.putExtra("name", name);
                int age = 22;
                t.putExtra("age", age);
                String[] subjects = {"LTHDT", "LT Web", "LT TB DD"};
                t.putExtra("subjects", subjects);

                Student s = new Student(R.drawable.female, "Vu Thi Huong", 40);
                t.putExtra("st", s);

                startActivity(t);
            }
        });
    }
}