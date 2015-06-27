package com.gkhnl.slidingmenuinandroid;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.gkhnl.slidingmenuinandroid.pages.FacebookFragment;
import com.gkhnl.slidingmenuinandroid.pages.GoogleFragment;
import com.gkhnl.slidingmenuinandroid.pages.InstagramFragment;
import com.gkhnl.slidingmenuinandroid.pages.TwitterFragment;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity {

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;
    ListView lw_SlideMenu;
    SlideMenuAdapter adapter;

    List<SlideMenuItem> items;
    String[] titles;
    TypedArray icons;
    CharSequence actionBarTitle, appTitle;
    String fragment_name = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lw_SlideMenu = (ListView) findViewById(R.id.lw_Menu);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);

        // Menü başlıklarını kaynak dosyasından çekiyor
        titles = getResources().getStringArray(R.array.slidemenu_item);

        // icons dizimizi kaynak dosyadan çekiyoruz
        icons = getResources().obtainTypedArray(R.array.icons);
        // Ram i şişirmemek için resimleri yeniden yükler
        icons.recycle();

        items = new ArrayList<SlideMenuItem>();

        items.add(new SlideMenuItem(titles[0], icons.getResourceId(0, 0)));
        items.add(new SlideMenuItem(titles[1], icons.getResourceId(1, 0)));
        items.add(new SlideMenuItem(titles[2], icons.getResourceId(2, 0)));
        items.add(new SlideMenuItem(titles[3], icons.getResourceId(3,0)));

        // Açılışta uygulama ismini alıyor
        appTitle = getSupportActionBar().getTitle();
        actionBarTitle = items.get(0).getTitle();

        // Menüdeki her list item a click veriyor
        lw_SlideMenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                displayPage(i);

                // Her item a tıklanıldığında actionBar daki görünen başlığı değiştiriyor
                actionBarTitle = items.get(i).getTitle();

                // menü tıklamadan sonra kapanıyor
                drawerLayout.closeDrawer(lw_SlideMenu);
            }
        });

        adapter = new SlideMenuAdapter(items, getApplicationContext());
        lw_SlideMenu.setAdapter(adapter);

        // Toggle butonuna click veriyoruz, home butonu gibi davranmasını sağlıyor.
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // toggle nesnesi oluşturuyoruz.
        toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.opened, R.string.closed) {

            @Override
            public void onDrawerOpened(View drawerView) {
                getSupportActionBar().setTitle(appTitle);

            }

            @Override
            public void onDrawerClosed(View drawerView) {
                getSupportActionBar().setTitle(actionBarTitle);

            }
        };

        // toggle açılıp kapanmasına göre, toggle iconu değiştiriyor.
        toggle.syncState();
        // menü açılıp kapanmasını dinliyoruz.
        drawerLayout.setDrawerListener(toggle);

        // Açılışta facebook ekranı gelecek
        displayPage(0);
    }

    private void displayPage(int position) {

        Fragment fragment = null;

        switch (position)
        {
            case 0:
                fragment = new FacebookFragment();
                fragment_name = "FacebookFragment";
                break;
            case 1:
                fragment = new TwitterFragment();
                fragment_name = "TwitterFragment";
                break;
            case 2:
                fragment = new GoogleFragment();
                fragment_name = "GoogleFragment";
                break;
            case 3:
                fragment = new InstagramFragment();
                fragment_name = "InstagramFragment";
                default:
                    break;
        }

        if(fragment != null){

            // Fragment transaction nesnesi ile fragment ekranları arasında geçiş sağlıyor
            FragmentManager fragmentManager = getSupportFragmentManager();
            android.support.v4.app.FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.container, fragment).addToBackStack(fragment_name).commit();

            // Stack te bulunan fragment sayısını alıyor
            int count = getSupportFragmentManager().getBackStackEntryCount();

            if(count!=0) {
                // Son fragment alınıyor
                FragmentManager.BackStackEntry backStackEntry = getSupportFragmentManager().getBackStackEntryAt(count - 1);

                // Son fragment ile seçilen fragment aynı ise eski fragment siliniyor
                if (backStackEntry.getName().contains(fragment_name)) {
                    getSupportFragmentManager().popBackStack();
                }
            }
        }
    }

    @Override
    public void onBackPressed() {

        // fragment sayısı bir ise uygulamadan çıkıyor
        if(getSupportFragmentManager().getBackStackEntryCount() != 1)
            super.onBackPressed();


        else {
            finish();
            System.exit(0);
        }
    }

    @Override
    public void setTitle(CharSequence title) {

        getSupportActionBar().setTitle(title);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        // toggle icona tıklanıldığında menünün açılmasını sağlıyor
        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
