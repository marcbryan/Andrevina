package com.andrevina.et.andrevina;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class FameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fame);
        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        TextView textView = findViewById(R.id.textView);
        //final Dialog dialog = new Dialog(FameActivity.this);
        //dialog.setContentView(R.layout.custom_dialog);
        //dialog.setTitle("Title");
        //EditText edit=(EditText)dialog.findViewById(R.id.dialog_edit);
        //String text=edit.getText().toString();
        //dialog.dismiss();
        //name=text;
        textView.setText(message);
    }
}
