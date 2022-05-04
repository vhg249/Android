package com.example.intent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.Arrays;

public class SecondActivity extends AppCompatActivity {
    private Button bt;
    private TextView tvName, tvSub, tvST;
    private ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        bt = findViewById(R.id.bt);
        tvName = findViewById(R.id.tvName);
        tvSub = findViewById(R.id.tvSub);
        img = findViewById(R.id.img);
        tvST = findViewById(R.id.tvST);
        Intent t = getIntent();
        String name = t.getStringExtra("name");
        int age = t.getIntExtra("age", 20);
        String[] sub = t.getStringArrayExtra("subjects");
        tvSub.setText(Arrays.toString(sub));
        tvName.setText(name+","+age);

        Student s = (Student) t.getSerializableExtra("st");
        img.setImageResource(s.getImg());
        tvST.setText("name: "+s.getName()+ " Age: " + s.getAge());

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}