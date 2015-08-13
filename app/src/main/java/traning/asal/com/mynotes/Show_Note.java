package traning.asal.com.mynotes;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class Show_Note extends Activity {

    private List<String> lstSubjects;
    private ArrayList<Note> lstNotes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_note);
        ListView listContent = (ListView) findViewById(R.id.notes_list);
        DatabaseHandler openHelperClass = new DatabaseHandler(this);
        SQLiteDatabase sqliteDatabase = openHelperClass.getReadableDatabase();

        Cursor cursor = sqliteDatabase.query(DatabaseHandler.TABLE_Name, null, null, null, null, null, null);
        lstSubjects = new ArrayList<String>();
        lstNotes = new ArrayList<Note>();
        Note objNote;
        if(cursor.moveToFirst()){
            do{
                objNote = new Note();
                objNote.setSubject(cursor.getString(2));
                objNote.setText(cursor.getString(1));
                if(objNote.getText().equals("null")){
                    Toast.makeText(getBaseContext(), "Write Your Note", Toast.LENGTH_LONG).show();

                }
                objNote.setID(Integer.parseInt(cursor.getString(0)));
                lstNotes.add(objNote);
                lstSubjects.add(cursor.getString(2));
            }while(cursor.moveToNext());
        }
        openHelperClass.close();

        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                lstSubjects );

        listContent.setAdapter(arrayAdapter);

         // list = (ListView) findViewById(R.id.notes_list);
        // listContent.setOnItemClickListener(LstListener);
       final ListView list = (ListView) findViewById(R.id.notes_list);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent , View view, int position, long id) {
                @SuppressWarnings("unchecked")
               // Object ob= listContent.getItemAtPosition(position)
               //Log.i(Tag, "DisplayID:" + ob.toString());
                //calling the next activity
               Intent intent = new Intent(Show_Note.this, Data_Activity.class);
               /*intent.putExtra("info", Note.setText().toString());*/
                Note objNote = lstNotes.get(position);
                String content = objNote.getText();
                String subject = objNote.getSubject();
                intent.putExtra("subject",subject);
                intent.putExtra("content", content);
               // intent.putIntegerArrayListExtra(String "LstNotes", ArrayList<Integer> value);
                intent.putExtra("ID",objNote.getID());
                startActivity(intent);
                //txtView.setText(listContent[position]);
               // txtView.setText(info[3]);

           }
            });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_show__note, menu);
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
