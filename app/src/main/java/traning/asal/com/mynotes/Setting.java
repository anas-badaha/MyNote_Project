package traning.asal.com.mynotes;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class Setting extends Activity {
    EditText oldpassword;
    EditText newpassword;
    EditText newpassword2;
    String oldpass;
    String newpass;
    String newpass2;
    String oldpass2;
    Context context=this;

    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        oldpassword = (EditText) findViewById(R.id.oldpass);
        newpassword = (EditText) findViewById(R.id.newpass);
        newpassword2 = (EditText) findViewById(R.id.newpass2);
        button = (Button) findViewById(R.id.save);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changPassword();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_setting, menu);
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

    public void changPassword(){
        oldpass=oldpassword.getText().toString();
        oldpass=oldpass.trim();
        newpass=newpassword.getText().toString();
        newpass=newpass.trim();
        newpass2=newpassword2.getText().toString();
        newpass2=newpass2.trim();
        DatabaseHandler openHelperClass = new DatabaseHandler(context);
        SQLiteDatabase sqliteDatabase = openHelperClass.getReadableDatabase();
        Cursor cursor = sqliteDatabase.query(DatabaseHandler.TABLE_Name2, null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                oldpass2=(cursor.getString(2));
            } while (cursor.moveToNext());
        }

        openHelperClass.close();
        if(oldpass.equals("")||newpass.equals("")||newpass2.equals("")){
            Toast.makeText(getBaseContext(), "Enter The Password", Toast.LENGTH_LONG).show();
        }else {
            if (oldpass.equals(oldpass2)) {
                if (newpass.equals(newpass2)) {
                    DatabaseHandler db = new DatabaseHandler(context);
                    db.upDatePassword(newpass);
                    Toast.makeText(getBaseContext(), "Password Changed ", Toast.LENGTH_LONG).show();
                    setContentView(R.layout.mainpage);
                    // startActivity(new Intent(Setting.this, MainPage.class));
                    this.finish();
                } else {
                    setContentView(R.layout.activity_setting);
                    oldpassword = (EditText) findViewById(R.id.oldpass);
                    oldpassword.setText(oldpass);
                    newpassword = (EditText) findViewById(R.id.newpass);
                    newpassword.setText("");
                    newpassword2 = (EditText) findViewById(R.id.newpass2);
                    newpassword2.setText("");
                    button = (Button) findViewById(R.id.save);
                    button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            changPassword();
                        }
                    });
                    Toast.makeText(getBaseContext(), "Passwords do not match", Toast.LENGTH_LONG).show();
                }
            } else {
                setContentView(R.layout.activity_setting);
                oldpassword = (EditText) findViewById(R.id.oldpass);
                oldpassword.setText("");
                newpassword = (EditText) findViewById(R.id.newpass);
                newpassword.setText(newpass);
                newpassword2 = (EditText) findViewById(R.id.newpass2);
                newpassword2.setText(newpass2);
                button = (Button) findViewById(R.id.save);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        changPassword();
                    }
                });
                Toast.makeText(getBaseContext(), "Old Password is Wrong", Toast.LENGTH_LONG).show();

            }
        }

    }

}
