package com.example.black_pearl.im2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;

public class LoggedinUser extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private RecyclerView recyclerview;
    recadapter adapter;
    private int[] icons={R.drawable.electrician,R.drawable.plumber,R.drawable.carservice,R.drawable.bikeservice,R.drawable.cleaning,R.drawable.acfridge,R.drawable.photographer,R.drawable.dj};
    private String[] titles;
    ArrayList<serviceinfo> data=new ArrayList<serviceinfo>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loggedin_user);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);






        titles=this.getResources().getStringArray(R.array.services);
        for(int i=0;i<1;i++) {
            for (int j = 0; j < titles.length; j++) {
                serviceinfo item=new serviceinfo();
                item.setIconid(icons[j]);
                item.setText(titles[j]);
                data.add(item);
            }
        }
        recyclerview=(RecyclerView)findViewById(R.id.recyc);
        recyclerview.setLayoutManager(new GridLayoutManager(this,2));
        adapter=new recadapter(data,this);
        recyclerview.setAdapter(adapter);


        recyclerview.addOnItemTouchListener(new recyclertouchlistener(LoggedinUser.this, recyclerview, new ClickListener() {


            @Override
            public void onClick(View view, int position) {

                Intent in=new Intent(LoggedinUser.this,InsideService.class);
                in.putExtra("position",position%8);
                startActivity(in);



                Toast.makeText(LoggedinUser.this,"onClick"+position%8,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongClick(View view, int position) {
                Toast.makeText(LoggedinUser.this,"onLongClick"+position%8,Toast.LENGTH_SHORT).show();
            }
        }));


    }

    static class recyclertouchlistener implements RecyclerView.OnItemTouchListener{

        private GestureDetector gestureDetector;
        private ClickListener clickListener;




//constructor

        public recyclertouchlistener(Context context, final RecyclerView recyclerView, final ClickListener clickListener) {

            this.clickListener=clickListener;

            gestureDetector=new GestureDetector(context,new GestureDetector.SimpleOnGestureListener(){

                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

                @Override
                public void onLongPress(MotionEvent e) {

                    View child=recyclerView.findChildViewUnder(e.getX(),e.getY());
                    if(child!=null && clickListener!=null ){

                        clickListener.onLongClick(child,recyclerView.getChildPosition(child));
                    }


                    super.onLongPress(e);
                }
            });
        }





        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {



            View child=rv.findChildViewUnder(e.getX(),e.getY());
            if(child!=null && clickListener!=null && gestureDetector.onTouchEvent(e)){

                clickListener.onClick(child,rv.getChildPosition(child));
            }

            //   gestureDetector.onTouchEvent(e);

            return false;
        }


        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {

        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        }

    }



    public static interface ClickListener{
        public void onClick(View view,int position);

        public void onLongClick(View view,int position);
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
        getMenuInflater().inflate(R.menu.loggedin_user, menu);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.profile) {

            Intent in = new Intent(LoggedinUser.this,profile.class);
            startActivity(in);

        } else if (id == R.id.Bookings) {

            Toast.makeText(LoggedinUser.this,"Bookings",Toast.LENGTH_SHORT).show();

        } else if (id == R.id.Services) {
            Toast.makeText(LoggedinUser.this,"Services",Toast.LENGTH_SHORT).show();


        } else if (id == R.id.Paytm_Wallet) {
            Toast.makeText(LoggedinUser.this,"Paytm",Toast.LENGTH_SHORT).show();


        } else if (id == R.id.InE) {
            Toast.makeText(LoggedinUser.this,"Invite and earn",Toast.LENGTH_SHORT).show();


        } else if (id == R.id.Call) {
            Toast.makeText(LoggedinUser.this,"Call us",Toast.LENGTH_SHORT).show();


        } else if (id == R.id.Rate) {
            Toast.makeText(LoggedinUser.this,"Rate",Toast.LENGTH_SHORT).show();


        } else if (id == R.id.Privacy) {
            Toast.makeText(LoggedinUser.this,"Privacy",Toast.LENGTH_SHORT).show();


        } else if (id == R.id.TnC) {
            Toast.makeText(LoggedinUser.this,"terms and conditions",Toast.LENGTH_SHORT).show();


        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
