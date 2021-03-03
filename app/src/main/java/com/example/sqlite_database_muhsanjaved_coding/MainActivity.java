package com.example.sqlite_database_muhsanjaved_coding;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btnInsert,btnDelete,btnView,btnUpDate;
    EditText edName,edUserName,edPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findId();

        myDatabase database = new myDatabase(this);
//        SQLiteDatabase db = database.getReadableDatabase();


        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name1 = edName.getText().toString();
                String username1 = edUserName.getText().toString();
                String password1 = edPassword.getText().toString();

                if (name1.equals("") || username1.equals("") || password1.equals("")){
                    Toast.makeText(MainActivity.this,"Enter all the Fields",Toast.LENGTH_SHORT).show();
                }
                else {
                    Boolean i = database.insert_data(name1,username1,password1);
                    if (i==true){
                        Toast.makeText(MainActivity.this,"Successful",Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(MainActivity.this,"Not Successful",Toast.LENGTH_SHORT).show();
                    }
                }
                edName.setText("");
                edUserName.setText("");
                edPassword.setText("");

            }
        });

        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor cursor = database.get_Data_View();
                if (cursor.getCount() == 0){
                    Toast.makeText(MainActivity.this,"No data found", Toast.LENGTH_SHORT).show();
                }
                StringBuffer buffer = new StringBuffer();
                while (cursor.moveToNext()){
                    buffer.append("Name:: "+ cursor.getString(1)+"\n");
                    buffer.append("UserName:: "+ cursor.getString(2)+"\n");
                    buffer.append("Password:: "+ cursor.getString(3)+"\n"+"\n"+"\n");

                }
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setCancelable(true);
                builder.setTitle("SignUp Users Data");
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = edUserName.getText().toString();
                boolean i = database.Delete_userName(userName);
                if (i == true){
                    Toast.makeText(MainActivity.this,"Successful",Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(MainActivity.this,"Not Successful",Toast.LENGTH_SHORT).show();
                }

            }
        });


    }

    private void findId() {
        edName =findViewById(R.id.edName);
        edUserName =findViewById(R.id.edUserName);
        edPassword =findViewById(R.id.edPassword);

        btnInsert = findViewById(R.id.btnInsert);
        btnDelete = findViewById(R.id.btnDelete);
        btnView = findViewById(R.id.btnView);
        btnUpDate = findViewById(R.id.btnUpDate);
    }
}