package traning.asal.com.mynotes;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import javax.security.auth.Subject;

import static android.support.v4.app.ActivityCompat.startActivity;

/**
 * Created by PalSoft on 08/04/2015.
 */
public class DatabaseHandler extends SQLiteOpenHelper {

    private static final Integer DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "SQLiteExample.db";
    protected static final String TABLE_Name = "Mynote";
    protected static final String COLUMN_ID = "id";
    protected static final String COLUMN_Text = "data";
    protected static final String COLUMN_Subject = "subject";


    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_NOTE_TABLE = ("Create Table " + TABLE_Name + " ( " + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_Text +" TEXT, "+ COLUMN_Subject + " Text )");
        db.execSQL(CREATE_NOTE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int old_versio, int new_version) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_Name);
        onCreate(db);
    }

    public Note createNote(Note objNote) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_Subject, objNote.getSubject());
        values.put(COLUMN_Text, objNote.getText());
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_Name, null, values);
        db.close();
        return objNote;

    }
   public void deleteSingleRow(long rowId)
    {
        SQLiteDatabase db = getWritableDatabase();
         db.delete(TABLE_Name, COLUMN_ID + "=" + rowId, null);
    }
    public void upDateByID(Note rowId)
    {
        ContentValues values = new ContentValues();
        values.put(COLUMN_Subject, rowId.getSubject());
        values.put(COLUMN_Text, rowId.getText());
        SQLiteDatabase db = getWritableDatabase();
        db.update(TABLE_Name, values,COLUMN_ID + "=" + rowId.getID(), null);
        db.close();
    }

}

