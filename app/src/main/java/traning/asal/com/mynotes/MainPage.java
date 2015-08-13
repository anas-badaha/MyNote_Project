package traning.asal.com.mynotes;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by PalSoft on 08/05/2015.
 */
public class MainPage extends Activity {
    String name;
    String data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainpage);


    }
    public void secondactivity(View view){
        startActivity(new Intent(MainPage.this, MainActivity.class));

    }

    public void ShowNote(View v) {
        startActivity(new Intent(MainPage.this, Show_Note.class));
/*        ListView listContent = (ListView) findViewById(R.id.notes_list);
        DatabaseHandler openHelperClass = new DatabaseHandler(this);
        SQLiteDatabase sqliteDatabase = openHelperClass.getReadableDatabase();

        Cursor cursor = sqliteDatabase.query(DatabaseHandler.TABLE_Name, null, null, null, null, null, null);
        List<String> lstSubjects = new ArrayList<String>();

        if(cursor.moveToFirst()){
            do{
                String[] from = new String[]{cursor.getString(0),cursor.getString(1),cursor.getString(2)};
                lstSubjects.add(cursor.getString(1));
                //name = cursor.getString(0).toString();
                //data = cursor.getString(1);

                //uGraduateListAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, populateList());


            }while(cursor.moveToNext());
        }
        openHelperClass.close();

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                lstSubjects );

        listContent.setAdapter(arrayAdapter);*/

        //String[] codeLearnChapters = new String[]{name,data};
//        String[] codeLearnChapters = new String[] { "Android Introduction","Android Setup/Installation","Android Hello World","Android Layouts/Viewgroups","Android Activity & Lifecycle","Intents in Android"};
//        ArrayAdapter<String> codeLearnArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,codeLearnChapters);
//        ListView codeLearnLessons = (ListView)findViewById(R.id.listviewinfo);
//        codeLearnLessons.setAdapter(codeLearnArrayAdapter);
//        AlertDialog.Builder loadData = new AlertDialog.Builder(this);
//        loadData.setTitle("Info");
//        loadData.setMessage("Subject: "+name+" yourtext: "+data);
//        loadData.setNegativeButton("OK", new DialogInterface.OnClickListener() {
//
//            @Override
//            public void onClick(DialogInterface arg0, int value) {
//                // TODO Auto-generated method stub
//                arg0.dismiss();
//            }
//        });
//        loadData.show();

        //loadSuccess();
        //Toast.makeText(getBaseContext(), "Name: "+getName+", Email: "+getEmail, Toast.LENGTH_LONG).show();
    }
}
