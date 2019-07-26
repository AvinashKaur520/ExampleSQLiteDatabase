package com.example.databaseexample;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
{
    EditText name,location,description;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.editText);
        location = findViewById(R.id.editText2);
        description = findViewById(R.id.editText3);
        btn = findViewById(R.id.button);


        btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if(name.length()==0||location.length()==0||description.length()==0)
                {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setMessage("Please Enter Details");
                    builder.setCancelable(true);

                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.cancel();
                        }
                    });

                    AlertDialog alert = builder.create();
                    alert.show();
                }
                else
                {
                    String username = name.getText().toString();
                    String userloc = location.getText().toString();
                    String userdes = description.getText().toString();

                    DBHandler database = new DBHandler(MainActivity.this);
                    database.insertData(username,userloc,userdes);

                    Intent i = new Intent(MainActivity.this,Details.class);
                    startActivity(i);

//                Toast.makeText(MainActivity.this, "Details Successfully Saved"+R.drawable.happy, Toast.LENGTH_SHORT).show();

                    Toast ImageToast = new Toast(getBaseContext());
                    LinearLayout toastLayout = new LinearLayout(getBaseContext());
                    toastLayout.setOrientation(LinearLayout.HORIZONTAL);

                    ImageView image = new ImageView(getBaseContext());
                    TextView text = new TextView(getBaseContext());

                    if(username.isEmpty()||userloc.isEmpty()||userdes.isEmpty())
                    {
                        text.setText("Details Failed To Save!");
                        image.setImageResource(R.drawable.sad);
                    }
                    else
                    {
                        text.setText("Details Successfully Saved!");
                        image.setImageResource(R.drawable.happy);
                        name.setText("");
                        location.setText("");
                        description.setText("");
                    }

                    toastLayout.addView(text);
                    toastLayout.addView(image);

                    ImageToast.setView(toastLayout);
                    ImageToast.setDuration(Toast.LENGTH_LONG);
                    ImageToast.show();
                }
            }
        });
    }
}
