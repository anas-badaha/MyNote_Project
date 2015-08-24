package traning.asal.com.mynotes;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.changpass:
                startActivity(new Intent(MainPage.this, Setting.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void secondactivity(View view) {
        startActivity(new Intent(MainPage.this, MainActivity.class));
    }

    public void ShowNote(View v) {
        startActivity(new Intent(MainPage.this, ShowNote.class));
    }
}

