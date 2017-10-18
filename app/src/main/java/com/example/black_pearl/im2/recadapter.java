package com.example.black_pearl.im2;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;



public class recadapter  extends RecyclerView.Adapter<recadapter.myviewholder> {

    private LayoutInflater inflater;
    ArrayList <serviceinfo> listdata= new ArrayList<serviceinfo>();
    Context context;


    public recadapter(ArrayList<serviceinfo> data,Context context) {
        inflater= LayoutInflater.from(context);
        this.listdata=data;
        this.context=context;
    }


    @Override
    public myviewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.custom_row_logged_in_user,parent,false);

        myviewholder holder=new myviewholder(view,context,listdata);

        return holder;
    }

    @Override
    public void onBindViewHolder(final myviewholder holder, int position) {
        final serviceinfo current=listdata.get(position);

        holder.name.setText(current.text);
        holder.image.setImageResource(current.iconid);
    }

    @Override
    public int getItemCount() {
        return listdata.size();
    }


    class myviewholder extends RecyclerView.ViewHolder {
        serviceinfo item;
        TextView name;
        ImageView image;
        Context ctx;

        ArrayList<serviceinfo> qdinf=new ArrayList<serviceinfo>();

        public myviewholder(View itemView, Context ctx, ArrayList<serviceinfo> qdinf) {
            super(itemView);

            this.ctx=ctx;
            this.qdinf=qdinf;
            name= (TextView) itemView.findViewById(R.id.servctext);
            image=(ImageView)itemView.findViewById(R.id.servcimage);
        }



    }
}
