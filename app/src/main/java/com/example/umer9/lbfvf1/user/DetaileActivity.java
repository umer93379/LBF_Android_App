package com.example.umer9.lbfvf1.user;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.TextView;

import com.example.umer9.lbfvf1.R;

public class DetaileActivity extends AppCompatActivity {

    TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detaile);

        Post book = (Post)getIntent().getSerializableExtra("book");
            title=(TextView)findViewById(R.id.titleText);
            if (book !=null){
                title.setText(book.b_title);
            }

    }
}
