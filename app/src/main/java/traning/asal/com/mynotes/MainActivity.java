package traning.asal.com.mynotes;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends Activity {
    private DatabaseHandler dbHelper;
    SQLiteDatabase sqLiteDatabase;
    private Context objContext;
    EditText Subject;
    EditText Text;
    Context context=this;
    //public MainActivity() {
    //  this.objContext = this.getBaseContext();
    //}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
            Subject = (EditText) findViewById(R.id.Subject);
            Text = (EditText) findViewById(R.id.Text);


//            Subject.setText(subEdit);
//            Text.setText(contEdit);
//            final Button button = (Button) findViewById(R.id.button);
//            button.setOnClickListener(new View.OnClickListener() {
//                public void onClick(View v) {
//                    SQLiteDatabase db = getWritableDatabase();
//                    new DatabaseHandler(context).update(DatabaseHandler.TABLE_Name, Subject,Text, "DatabaseHandler.COLUMN_ID " + "=" + ID, null);                }
//            });
        }


    public void addnote(View view)
    {
        String subject=Subject.getText().toString();
        String text= Text.getText().toString();
        Note objNote = new Note();
        objNote.setSubject(subject);
        objNote.setText(text);
        dbHelper=new DatabaseHandler(context);
        //sqLiteDatabase=dbHelper.getWritableDatabase();
        dbHelper.createNote(objNote);
        Toast.makeText(getBaseContext(),"Data saved",Toast.LENGTH_LONG).show();
        startActivity(new Intent(MainActivity.this, MainPage.class));
        dbHelper.close();
        this.finish();
    }

   // public void newnote(View view) {
     //   DatabaseHandler dbHandler = new DatabaseHandler( null);


//       Note objnote = new Note();

  //      dbHandler.createNote(objnote);
    //    Subject.setText("");
      //  Text.setText("");
        //Toast.makeText(getApplicationContext(), objnote.getSubject().toString() + " has been saved",
          //          Toast.LENGTH_SHORT).show();
    //}
}

      //  Button button = (Button) findViewById(R.id.button);
        //    button.setOnClickListener(new View.OnClickListener() {
          //      @Override
            //    public void onClick(View view) {
              //      Note objNote = new Note();//dbHelper.(), String.valueOf(txtName.getText()), String.valueOf(txtPhone.getText()), String.valueOf(txtEmail.getText()), String.valueOf(txtAddress.getText()), imageURI);
                //    objNote.setSubject(Subject);
                  //  objNote.setText("badaha");
                   // dbHelper = new DatabaseHandler(objContext);
                   // dbHelper.createNote(objNote);
                   // ContactsContract.Contacts.add(objNote);
                  //  Toast.makeText(getApplicationContext(), objNote.getSubject().toString() + " has been saved",
                      //      Toast.LENGTH_SHORT).show();
                //}

        //});

        //dbHelper = new ExampleDBHelper(this);



//    }

//    }