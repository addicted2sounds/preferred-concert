package addicted.edu.preferedconcert;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import addicted.edu.preferedconcert.entity.Song;
import addicted.edu.preferedconcert.entity.User;

public class MainActivity extends AppCompatActivity {
    private static User currentUser;
    private static ArrayList<String> songList = new ArrayList<String>(
            Arrays.asList("Katyusha", "Jango", "Zabula")
    );
    private ArrayAdapter<String> adapter;

    public static User getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(User currentUser) {
        MainActivity.currentUser = currentUser;
    }

    public void logout(View v) {
        currentUser = null;
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    public void addSong(View v) {
        EditText edit = (EditText) findViewById(R.id.editText);
        songList.add(edit.getText().toString());
        edit.setText("");
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TextView textView = (TextView) findViewById(R.id.textView);
        textView.setText("Logged in as " + currentUser.getEmail());
        final ListView listView = (ListView) findViewById(R.id.listView);
        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_multiple_choice, songList);
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        listView.setAdapter(adapter);
        for (String selectedSong : currentUser.getFavorites()) {
            listView.setItemChecked(songList.indexOf(selectedSong), true);
        }
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("concert", "" + songList.get(position));
                if (listView.isItemChecked(position)) {
                    Log.d("concert", "added to fav");
                    currentUser.addFavorite(songList.get(position));
                } else {
                    currentUser.removeFavorite(songList.get(position));
                }
            }
        });
//        Button addSongButton = (Button) findViewById(R.id.addSongButton);
//        addSongButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
//        Button signoutButton = (Button) findViewById(R.id.buttonExit);
//        signoutButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                logout();
//            }
//        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        if (currentUser.isAdmin()) {
            getMenuInflater().inflate(R.menu.menu_admin_main, menu);
        } else {
            getMenuInflater().inflate(R.menu.menu_main, menu);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id) {
            case R.id.action_logout:
                currentUser = null;
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                return true;
            case R.id.action_admin:
                intent = new Intent(this, AdminActivity.class);
                startActivity(intent);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
