package traning.asal.com.mynotes;

import android.app.Activity;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.widget.CompoundButton;

import java.util.ArrayList;


public class LogIn extends Activity {
    private DatabaseHandler dbHelper;
    SQLiteDatabase sqLiteDatabase;
    EditText UserName;
    EditText Password;
    CheckBox mCbShowPwd;
    Button register;
    long count ;
    String user;
    String pass;
    User objuser;
    Context context=this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        count =NumRow();
        UserName = (EditText) findViewById(R.id.username);
        register = (Button) findViewById(R.id.register);
        Password = (EditText) findViewById(R.id.password);
        if (count >= 1) {
            register.setEnabled(true);
            register.setVisibility(View.INVISIBLE);
        }
        Password.setTransformationMethod(PasswordTransformationMethod.getInstance());
        mCbShowPwd = (CheckBox) findViewById(R.id.cbShowPwd);
        mCbShowPwd.setOnCheckedChangeListener(new OnCheckedChangeListener() {

            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // checkbox status is changed from uncheck to checked.
                if (!isChecked) {
                    // show password
                    Password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                } else {
                    // hide password
                    Password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_log_in, menu);
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

   public void Register(View view) {
       startActivity(new Intent(LogIn.this, Register.class));
   }
    public void Login(View view) {
        user = UserName.getText().toString();
        pass = Password.getText().toString();
        if (user.equals("") || pass.equals("")) {
            Toast.makeText(getBaseContext(), " Enter password and user name", Toast.LENGTH_LONG).show();
        }
        else {
            DatabaseHandler openHelperClass = new DatabaseHandler(context);
            SQLiteDatabase sqliteDatabase = openHelperClass.getReadableDatabase();
            Cursor cursor = sqliteDatabase.query(DatabaseHandler.TABLE_Name2, null, null, null, null, null, null);
            if (cursor.moveToFirst()) {
                do {
                    objuser = new User();
                    objuser.setPassword(cursor.getString(2));
                    objuser.setUserName(cursor.getString(1));
                    count = cursor.getCount();
                } while (cursor.moveToNext());
                check();
            }

            openHelperClass.close();
//        Toast.makeText(getBaseContext(), "You Are Not a Member", Toast.LENGTH_LONG).show();
//        startActivity(new Intent(LogIn.this, LogIn.class));

        }
    }
    public void check() {
            if (objuser.getUserName().equals(user) && objuser.getPassword().equals(pass) && count <= 1) {
                Toast.makeText(getBaseContext(), "Successfully LogIn", Toast.LENGTH_LONG).show();
                startActivity(new Intent(LogIn.this, MainPage.class));
                this.finish();
            } else {

                Toast.makeText(getBaseContext(), "You are not User", Toast.LENGTH_LONG).show();

            }
    }
    public long NumRow() {
        DatabaseHandler openHelperClass = new DatabaseHandler(context);
        SQLiteDatabase sqliteDatabase = openHelperClass.getReadableDatabase();
        Cursor cursor = sqliteDatabase.query(DatabaseHandler.TABLE_Name2, null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                count = cursor.getCount();
            } while (cursor.moveToNext());
        }
        return count;
    }
}
