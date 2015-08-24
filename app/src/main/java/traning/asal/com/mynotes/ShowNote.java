package traning.asal.com.mynotes;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


public class ShowNote extends Activity {

    private List<String> lstSubjects;
    private ArrayList<Note> lstNotes;
    ListView listContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_note);

            listView();
         // list = (ListView) findViewById(R.id.notes_list);
        // listContent.setOnItemClickListener(LstListener);
       final ListView list = (ListView) findViewById(R.id.notes_list);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                lisview(position);
            }
        });
        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            public boolean onItemLongClick(AdapterView<?> arg0, View v,
                                           int index, long arg3) {
                // TODO Auto-generated method stub
                Dialog(index);
                return true;
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
    public void lisview(int position){
        Intent intent = new Intent(ShowNote.this, DataActivity.class);
        Note objNote = lstNotes.get(position);
        String content = objNote.getText();
        String subject = objNote.getSubject();
        intent.putExtra("subject",subject);
        intent.putExtra("content", content);
        intent.putExtra("ID",objNote.getID());
        startActivity(intent);
       // this.finish();
    }

    public void Dialog(final int position){
        new AlertDialog.Builder(this)
                .setTitle("Delete/Edit entry")
                //.setMessage("Are you sure you want to delete this entry?")
                .setNeutralButton("Delete", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // continue with delete
                    confirm(position);
                    }
                })
                .setNegativeButton("Edit", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // do nothing
                        Note objNote = lstNotes.get(position);
                        String content = objNote.getText();
                        String subject = objNote.getSubject();
                        long ID = objNote.getID();
                        Intent intent = new Intent(ShowNote.this, Edit.class);
                        intent.putExtra("subject",subject);
                        intent.putExtra("content", content);
                        intent.putExtra("ID",ID);
                        startActivity(intent);
                    }
    })
            .setIcon(android.R.drawable.ic_dialog_alert)
    .show();
    }
    public void confirm(final int position){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Confirm");
        builder.setMessage("Are you sure?");

        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                // Do nothing but close the dialog
                Note objNote = lstNotes.get(position);
                long ID = objNote.getID();
                new  DatabaseHandler(ShowNote.this).deleteSingleRow(ID);
                listView();
                //startActivity(new Intent(ShowNote.this, ShowNote.class));
               // dialog.dismiss();
            }
        });

        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Do nothing
                dialog.dismiss();
            }
        });

        AlertDialog alert = builder.create();
        alert.show();
    }
    public void listView(){
        listContent = (ListView) findViewById(R.id.notes_list);
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
    }
}

