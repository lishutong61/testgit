package com.example.administrator.face;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends Activity {
    private EditText usernameEdit;
    private EditText passwordEdit;
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private Button login;
    private CheckBox remeberPass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pref = PreferenceManager.getDefaultSharedPreferences(this);
        remeberPass=(CheckBox) findViewById(R.id.remember_pass);
        login=(Button) findViewById(R.id.button_1);
        usernameEdit=(EditText) findViewById(R.id.editText);
        passwordEdit=(EditText) findViewById(R.id.editText2);
        boolean isRemeber=pref.getBoolean("remember_password",false);

        if(isRemeber){
            //将账号和密码都设置到文本框中
            String username=pref.getString("username","");
            String password=pref.getString("password","");
            usernameEdit.setText(username);
            passwordEdit.setText(password);
            remeberPass.setChecked(true);
        }
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.button_1:
                        String username1 = usernameEdit.getText().toString();
                        String password1 = passwordEdit.getText().toString();
                        if (username1.equals("hhh") && password1.equals("321")) {
                            editor=pref.edit();

                            if(remeberPass.isChecked()){
                                editor.putBoolean("remember_password",true);
                                editor.putString("username",username1);
                                editor.putString("password",password1);
                            }else{
                                editor.clear();
                            }
                            editor.apply();
                            Toast.makeText(MainActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(MainActivity.this, "登录失败", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    default:
                        break;
                }
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
