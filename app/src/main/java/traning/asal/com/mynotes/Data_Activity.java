package traning.asal.com.mynotes;

import android.app.Activity;
import android.app.TaskStackBuilder;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;


public class Data_Activity extends Activity {
   //ArrayList<String> lstNotes = new ArrayList<>();
    long ID;
    String sub = null;
    String cont=null;
    //String sub;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_);



        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                sub= ("There is no data to show ");
                cont= ("There is no data to show ");

            } else {
               sub =extras.getString("subject");
               cont=extras.getString("content");
                ID=extras.getLong("ID");
               // lstNotes=extras.getSerializableExtra("lstNotes");
            }
        }
        ((TextView)findViewById(R.id.txtVo)).setText(sub);
        ((TextView)findViewById(R.id.txtV1)).setText(cont);

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_data_, menu);
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
    public void Delete(View view){
        new  DatabaseHandler(this).deleteSingleRow(ID);
        startActivity(new Intent(Data_Activity.this, Show_Note.class));
        this.finish();

    }

    public void UpDate(View view){
        Intent intent = new Intent(Data_Activity.this, Edit.class);
        intent.putExtra("subject",sub);
        intent.putExtra("content", cont);
        intent.putExtra("ID",ID);

        startActivity(intent);
        this.finish();
    }

}
