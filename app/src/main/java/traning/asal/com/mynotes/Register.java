package traning.asal.com.mynotes;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class Register extends Activity {
    private DatabaseHandler dbHelper;
    SQLiteDatabase sqLiteDatabase;
    EditText FirstName;
    EditText LastName;
    EditText UserName;
    EditText Password;
    Context context=this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        FirstName = (EditText) findViewById(R.id.FirNtname);
        LastName = (EditText) findViewById(R.id.LastName);
        UserName = (EditText) findViewById(R.id.username);
        Password = (EditText) findViewById(R.id.password);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_register, menu);
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
    public void Registeration(View view){
        String First=FirstName.getText().toString();
        First=First.trim();
        String Last= LastName.getText().toString();
        Last=Last.trim();
        String User=UserName.getText().toString();
        User=User.trim();
        String Pass= Password.getText().toString();
        Pass=Pass.trim();
        User objuser = new User();
        objuser.setFirstName(First);
        objuser.setLastName(Last);
        objuser.setUserName(User);
        objuser.setPassword(Pass);
        if(First.equals("")||Last.equals("")||User.equals("")||Pass.equals("")){
            Toast.makeText(getBaseContext(),"Plese Write All Informations ",Toast.LENGTH_LONG).show();
        }else {
            dbHelper = new DatabaseHandler(context);
            //sqLiteDatabase=dbHelper.getWritableDatabase();
            dbHelper.createUser(objuser);
            Toast.makeText(getBaseContext(), "successfully registered", Toast.LENGTH_LONG).show();
            startActivity(new Intent(Register.this, LogIn.class));
            dbHelper.close();
            this.finish();
        }
    }
}
