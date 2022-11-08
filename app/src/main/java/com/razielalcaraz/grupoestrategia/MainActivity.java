package com.razielalcaraz.grupoestrategia;

import static java.security.AccessController.getContext;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;
import com.razielalcaraz.grupoestrategia.ui.login.LoginActivity;

import androidx.appcompat.app.ActionBar;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {
static String TAG="Main activity";
    private AppBarConfiguration mAppBarConfiguration;

    public static Activity vistaRoot = null;
    public static FeedReaderContract.FeedReaderDbHelper dbHelper;
    public static Boolean userIsLogged = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Levantar ticket de soporte", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                removeLoading();
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_boletos, R.id.nav_beneficios, R.id.nav_salud,
                R.id.nav_rrhh,R.id.nav_ideas,R.id.nav_salir)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
        ImageView imageView = (ImageView) findViewById(R.id.ImageLoad);

        Glide.with(this).load(R.drawable.loading).into(imageView);
        vistaRoot=this;
        removeLoading();
        if(!userIsLogged) {
Intent intent = new Intent(this, LoginActivity.class);
startActivity(intent);
        }
       // dbHelper = new FeedReaderContract.FeedReaderDbHelper(getContext());

    }

    public static void removeLoading(){

        ImageView loadingImage = (ImageView) vistaRoot.findViewById(R.id.ImageLoad);
        loadingImage.setVisibility(View.GONE);
        Log.d(TAG, "cerrado");


    }

    public static void escribirDatos(String title, String subtitle){
        //TODO: Definir estructura de las bases de datos
    // Gets the data repository in write mode
SQLiteDatabase db = dbHelper.getWritableDatabase();

// Create a new map of values, where column names are the keys
ContentValues values = new ContentValues();
values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE, title);
values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_SUBTITLE, subtitle);

// Insert the new row, returning the primary key value of the new row
long newRowId = db.insert(FeedReaderContract.FeedEntry.TABLE_NAME, null, values);

    }
public static void leerDatos(){
    //TODO: Definir estructura de las bases de datos
    SQLiteDatabase db = dbHelper.getReadableDatabase();

// Define a projection that specifies which columns from the database
// you will actually use after this query.
    String[] projection = {
            BaseColumns._ID,
            FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE,
            FeedReaderContract.FeedEntry.COLUMN_NAME_SUBTITLE
    };

// Filter results WHERE "title" = 'My Title'
    String selection = FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE + " = ?";
    String[] selectionArgs = { "My Title" };

// How you want the results sorted in the resulting Cursor
    String sortOrder =
            FeedReaderContract.FeedEntry.COLUMN_NAME_SUBTITLE + " DESC";

    Cursor cursor = db.query(
            FeedReaderContract.FeedEntry.TABLE_NAME,   // The table to query
            projection,             // The array of columns to return (pass null to get all)
            selection,              // The columns for the WHERE clause
            selectionArgs,          // The values for the WHERE clause
            null,                   // don't group the rows
            null,                   // don't filter by row groups
            sortOrder               // The sort order
    );


}

    public static void splashLoading(){

        ImageView loadingImage = (ImageView) vistaRoot.findViewById(R.id.ImageLoad);
        loadingImage.setVisibility(View.VISIBLE);
        Log.d(TAG, "cerrado");


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}