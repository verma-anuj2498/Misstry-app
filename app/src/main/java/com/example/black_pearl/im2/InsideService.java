package com.example.black_pearl.im2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class InsideService extends AppCompatActivity {

    CardView scheduleserviceCard, bookserviceCard;
    ImageView   icon;
    TextView    name;
        int pos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inside_service);
        setTitle("Indian Mistrry");

        scheduleserviceCard=(CardView)findViewById(R.id.scheduleservicecard);
        bookserviceCard    =(CardView)findViewById(R.id.bookservicenowCard);
        icon               =(ImageView)findViewById(R.id.iconinsideservice);
        name               =(TextView)findViewById(R.id.servicenameinsideservice);

        pos=getIntent().getIntExtra("position",0);

        if(pos==0){
            icon.setImageResource(R.drawable.electrician);
            name.setText("Electrician");
        }

        if(pos==1){
            icon.setImageResource(R.drawable.plumber);
            name.setText("Plumber");
        }

        if(pos==2){
            icon.setImageResource(R.drawable.carservice);
            name.setText("Car Service");
        }

        if(pos==3){
            icon.setImageResource(R.drawable.bikeservice);
            name.setText("Bike Service");
        }

        if(pos==4){
            icon.setImageResource(R.drawable.cleaning);
            name.setText("Cleaning");
        }

        if(pos==5){
            icon.setImageResource(R.drawable.acfridge);
            name.setText("AC fridge &\nOther");
        }

        if(pos==6){
            icon.setImageResource(R.drawable.photographer);
            name.setText("Photographer");
        }

        if(pos==7){
            icon.setImageResource(R.drawable.dj);
            name.setText("DJ Booking");
        }




        scheduleserviceCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent in=new Intent(InsideService.this,Scheduleservice.class);
                startActivity(in);
            }
        });


        bookserviceCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent in=new Intent(InsideService.this,BookServiceNow.class);
                startActivity(in);

            }
        });

    }
}
