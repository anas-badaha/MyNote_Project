package traning.asal.com.mynotes;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;


public class DataActivity extends Activity {
   //ArrayList<String> lstNotes = new ArrayList<>();
    long ID;
    String sub = null;
    String cont=null;
    ListView listContent;

    //String sub;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_);



        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                sub =extras.getString("subject");
                cont=extras.getString("content");

            } else {
               sub =extras.getString("subject");
               cont=extras.getString("content");
                ID=extras.getLong("ID");
               // lstNotes=extras.getSerializableExtra("lstNotes");
        }
        }
        ((TextView)findViewById(R.id.txtVo)).setText(sub);
        ((TextView)findViewById(R.id.txtV1)).setText(cont);
       // Button button= (Button) findViewById(R.id.delete);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Delete();
//            }
//        });
//        Button button2= (Button) findViewById(R.id.edit);
//        button2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                UpDate();
//            }
//        });
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
    public void Delete(){
        new  DatabaseHandler(this).deleteSingleRow(ID);
        startActivity(new Intent(DataActivity.this,ShowNote.class));
        this.finish();
    }

    public void UpDate(){
        Intent intent = new Intent(DataActivity.this, Edit.class);
        intent.putExtra("subject",sub);
        intent.putExtra("content", cont);
        intent.putExtra("ID",ID);

        startActivity(intent);
        this.finish();
    }

}
