package com.qianfeng.encode;

import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private FragmentManager manager;
    private NavigationView nav;
    private MD5Fragment md5Fragment;
    private Base64Fragment base64Fragment;
    private DESFragment desFragment;
    private DrawerLayout drawer;
    private ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawer = ((DrawerLayout) findViewById(R.id.drawer));
        nav = ((NavigationView) findViewById(R.id.navigation_view));
        nav.setNavigationItemSelectedListener(this);
        manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();

        /**
         * 设置左上角的图标
         */
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        /**
         * 绑定抽屉和图标的事件
         *
         */
        toggle = new ActionBarDrawerToggle(this,drawer,0,0);
        drawer.setDrawerListener(toggle);
        /**
         * 状态的同步
         */
        toggle.syncState();


        md5Fragment = new MD5Fragment();
        base64Fragment = new Base64Fragment();
        desFragment = new DESFragment();

        transaction.add(R.id.content,md5Fragment)
                .add(R.id.content,base64Fragment)
                .add(R.id.content,desFragment)
                .attach(md5Fragment)
                .detach(base64Fragment)
                .detach(desFragment).commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item)|| toggle.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        FragmentTransaction transaction = manager.beginTransaction();
        switch (item.getItemId()) {
            case R.id.action_md5 :
                transaction.attach(md5Fragment).detach(base64Fragment).detach(desFragment);
                break;
            case R.id.action_base64 :
                transaction.attach(base64Fragment).detach(md5Fragment).detach(desFragment);
                break;
            case R.id.action_des :
                transaction.attach(desFragment).detach(md5Fragment).detach(base64Fragment);
                break;

        }
        drawer.closeDrawer(Gravity.LEFT);
        transaction.commit();
        return true;
    }
}
