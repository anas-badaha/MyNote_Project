package traning.asal.com.mynotes;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.DatabaseUtils;
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
    protected static final String TABLE_Name2 = "User";
    protected static final String COLUMN_ID = "id";
    protected static final String COLUMN_Text = "data";
    protected static final String COLUMN_Subject = "subject";
    protected static final  String COLUMN_User="User";
    protected static final String COLUMN_password="pass";
    protected static final String COLUMN_FirstName="firstname";
    protected static final String COLUMN_LastName="lastname";


    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_NOTE_TABLE = ("Create Table " + TABLE_Name + " ( " + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_Text +" TEXT, "+ COLUMN_Subject + " Text )");
        db.execSQL(CREATE_NOTE_TABLE);
        String CREATE_User_TABLE = ("Create Table " + TABLE_Name2 + " ( " + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_User+" TEXT, "+ COLUMN_password + " Text, "+ COLUMN_FirstName + " Text, " + COLUMN_LastName + " TEXT )");
        db.execSQL(CREATE_User_TABLE);
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
    public long createUser(User objuser) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_FirstName, objuser.getFirstName());
        values.put(COLUMN_LastName, objuser.getLastName());
        values.put(COLUMN_User, objuser.getUserName());
        values.put(COLUMN_password, objuser.getPassword());
        SQLiteDatabase db = getWritableDatabase();
        long id = db.insert(TABLE_Name2, null, values);
        db.close();

        return id;
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
    public void upDatePassword(String newpass){
        ContentValues values = new ContentValues();
        values.put(COLUMN_password, newpass);
        SQLiteDatabase db = getWritableDatabase();
        db.update(TABLE_Name2, values,COLUMN_password, null);
        db.close();
    }
//    public boolean check(Note objnote){
//        if (objnote.getUserName()==COLUMN_User && objnote.getPassword()==COLUMN_password){
//        }
//        return true;
    //}
//    public long NumRow(){
//        return DatabaseUtils.queryNumEntries(db, "TABLE_Name2");
//
//    }

}

