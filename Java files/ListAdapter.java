package com.example.landmapper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ListAdapter extends BaseAdapter {
    Context context;
    ArrayList<String> ID;
    ArrayList<String> Name;
    ArrayList<String> PhoneNumber;
    ArrayList<String> District;
    ArrayList<String> PlotNumber;
    ArrayList<String> Latitude;
    ArrayList<String> Longitude;




    public ListAdapter(
            Context context2,
            ArrayList<String> id,
            ArrayList<String> name,
            ArrayList<String> phone,
            ArrayList<String> mdistrict,
            ArrayList<String> pnumber,
            ArrayList<String> latitude,
            ArrayList<String> longitude



    )
    {

        this.context = context2;
        this.ID = id;
        this.Name = name;
        this.PhoneNumber = phone;
        this.District = mdistrict;
        this.PlotNumber = pnumber;
        this.Latitude = latitude;
        this.Longitude = longitude;



    }

    public int getCount() {
        // TODO Auto-generated method stub
        return ID.size();
    }

    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return null;
    }

    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }

    public View getView(int position, View child, ViewGroup parent) {

        Holder holder;

        LayoutInflater layoutInflater;

        if (child == null) {
            layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            child = layoutInflater.inflate(R.layout.data, null);

            holder = new Holder();

            holder.ID_TextView = (TextView) child.findViewById(R.id.textViewID);
            holder.Name_TextView = (TextView) child.findViewById(R.id.textViewNAME);
            holder.PhoneNumberTextView = (TextView) child.findViewById(R.id.textViewPHONE_NUMBER);
            holder.DistrictTextView = (TextView)child.findViewById(R.id.textViewDISTRICT);
            holder.PlotNumberTextView = (TextView)child.findViewById(R.id.textViewPLOT_NUMBER);
            holder.LatitudeTextView = (TextView)child.findViewById(R.id.textViewLATITUDE);
            holder.LongitudeTextView = (TextView)child.findViewById(R.id.textViewLONGITUDE);







            child.setTag(holder);

        } else {

            holder = (Holder) child.getTag();
        }
        holder.ID_TextView.setText(ID.get(position));
        holder.Name_TextView.setText(Name.get(position));
        holder.PhoneNumberTextView.setText(PhoneNumber.get(position));
        holder.DistrictTextView.setText(District.get(position));
        holder.PlotNumberTextView.setText(PlotNumber.get(position));
        holder.LatitudeTextView.setText(Latitude.get(position));
        holder.LongitudeTextView.setText(Longitude.get(position));



        return child;
    }

    public class Holder {

        TextView ID_TextView;
        TextView Name_TextView;
        TextView PhoneNumberTextView;
        TextView DistrictTextView;
        TextView PlotNumberTextView;
        TextView LatitudeTextView;
        TextView LongitudeTextView;


    }

}
