package traning.asal.com.mynotes;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class Edit extends Activity {
    long ID;
    String subEdit = null;
    String contEdit=null;
    EditText Subject;
    EditText Text;
    Button button;
    Context context=this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                subEdit = ("There is no data to show ");
                contEdit = ("There is no data to show ");

            } else {
                subEdit = extras.getString("subject");
                contEdit = extras.getString("content");
                ID = extras.getLong("ID");
            }

        }

        Subject = (EditText) findViewById(R.id.Subject);
        Text = (EditText) findViewById(R.id.Text);
        button=(Button) findViewById(R.id.button);
        Subject.setText(subEdit);
        Text.setText(contEdit);
        final Note objNote=new Note();
        objNote.setID(ID);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_edit, menu);
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
       public void save (){
        final Note objNote=new Note();
           objNote.setID(ID);
           objNote.setSubject(Subject.getText().toString());
           objNote.setText(Text.getText().toString());
           if((Text.getText().toString()).equals("null")){
               Toast.makeText(getBaseContext(), "Write Your Note", Toast.LENGTH_LONG).show();
           }else {
               Intent intent = new Intent(Edit.this, DataActivity.class);
               String sub=Subject.getText().toString();
               String cont=Text.getText().toString();
               intent.putExtra("subject",sub);
               intent.putExtra("content", cont);
               DatabaseHandler db = new DatabaseHandler(context);
               db.upDateByID(objNote);
               Toast.makeText(getBaseContext(), "Data upadted", Toast.LENGTH_LONG).show();
               startActivity(intent);

               this.finish();
           }
    }
}
