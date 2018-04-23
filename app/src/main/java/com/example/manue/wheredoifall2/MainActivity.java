package com.example.manue.wheredoifall2;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.github.chrisbanes.photoview.PhotoView;

import java.util.Random;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    PhotoView photoView;
    String[] zonas = {"anarchyacres","dustydepot","fatalfields","flushfactory","greasygrove",
            "hauntedhills","junkjunction","lonelylodge","lootlake","moistymire","pleasantpark",
            "retailrow","saltysprings","shiftyshafts","snobbyshores","tiltedtowers","tomatotown",
            "wailingwoods"};
    CircleImageView circleImageView;
    Toast toast = null;
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        photoView = (PhotoView) findViewById(R.id.mapa);
        photoView.setImageResource(R.drawable.mapa);

        circleImageView = findViewById(R.id.mapaZona);
        circleImageView.setVisibility(View.GONE);

        mediaPlayer = MediaPlayer.create(getApplicationContext() , R.raw.drop);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void generateMethod(View view) {
        Random r = new Random();
        int valor = r.nextInt(17)+1;  // Entre 0 y 17, más 1.

        int id = getResources().getIdentifier(getPackageName()+":drawable/" + zonas[valor], null, null);

        circleImageView.setImageResource(id);
        circleImageView.setVisibility(View.VISIBLE);
        verToast(zonas[valor],this);
        reproducirAudio();
    }

    private void verToast(String texto, Context context) {                  //muestro toast solo en el caso de que no esto otro abierto
        if (toast != null) {
            toast.cancel();
        }
        toast = Toast.makeText(context, texto, Toast.LENGTH_SHORT);
        toast.show();                                                       //si hay otro abierto lo cancelo y muestro el siguiente toast
    }

    private void reproducirAudio() {
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.seekTo(0);
            mediaPlayer.start();
        }else{
            mediaPlayer.start();
        }
    }
}
