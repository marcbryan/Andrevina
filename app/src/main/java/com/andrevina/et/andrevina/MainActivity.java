package com.andrevina.et.andrevina;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Window;
import android.widget.Button;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private int intentos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        intentos = 0;
        final int numero = (int) (Math.random()*100)+1;
        System.out.println("Pista: "+numero);//pista
        final Button button = findViewById(R.id.button);
        final EditText eText = new EditText(this);
        final TextView tv = (TextView) findViewById(R.id.numIntro);
        tv.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN){
                    if (keyCode == KeyEvent.KEYCODE_ENTER){
                        System.out.println("click");
                        return true;
                    }

                }
                return false;
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String tvValue = tv.getText().toString();
                final Context context = getApplicationContext();
                CharSequence text = "";
                int duration = Toast.LENGTH_LONG;
                if (!tvValue.equals("")) {
                    int num1 = Integer.parseInt(tvValue);
                    if (num1 < numero){
                        text = "El numero es mes gran";
                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                        tv.setText("");
                        intentos++;
                    }
                    else if (num1 > numero){
                        text = "El numero es mes petit";
                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                        tv.setText("");
                        intentos++;
                    }
                    else if (num1 == numero) {
                        intentos++;
                        text = "Has encertat!";
                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                        final Dialog dialog = new Dialog(MainActivity.this);
                        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                        dialog.setContentView(R.layout.custom_dialog);
                        final TextView nombre = (TextView) dialog.findViewById(R.id.txt);
                        Button dialogButton = (Button) dialog.findViewById(R.id.dialogButtonOK);
                        dialogButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String playerName = nombre.getText().toString();
                                if (playerName.equals("")){
                                    dialog.dismiss();
                                    AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
                                    alertDialog.setCancelable(false);
                                    alertDialog.setTitle("Escriu un nom correcte");
                                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                                            new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialogInterface, int which) {
                                                    dialogInterface.dismiss();
                                                    dialog.show();
                                                }
                                            }
                                    );
                                    alertDialog.show();
                                }else {
                                    Intent intent = new Intent(getApplicationContext(), FameActivity.class);
                                    String nombreJugador = nombre.getText().toString();
                                    intent.putExtra("EXTRA_INTENTOS", intentos+"");
                                    intent.putExtra("EXTRA_NAME", nombreJugador);
                                    dialog.dismiss();
                                    startActivity(intent);
                                }
                            }
                        });
                        dialog.show();
                    }
                }
            }
        });
    }
    @Override
    public void onBackPressed(){}
}
