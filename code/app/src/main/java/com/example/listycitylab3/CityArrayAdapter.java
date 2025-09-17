package com.example.listycitylab3;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class CityArrayAdapter extends ArrayAdapter<City> {
    private DialogFragment editBox;
    public CityArrayAdapter(Context context, ArrayList<City> cities) {
        super(context, 0, cities);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup
            parent) {
        View view;
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.content,
                    parent, false);
        } else {
            view = convertView;
        }
        view.setOnClickListener(v -> {
            View editView = LayoutInflater.from(getContext()).inflate(R.layout.fragment_add_city, null);
            EditText editCityName = editView.findViewById(R.id.edit_text_city_text);
            EditText editProvinceName = editView.findViewById(R.id.edit_text_province_text);
            TextView cityName = v.findViewById(R.id.city_text);
            TextView provinceName = v.findViewById(R.id.province_text);

            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            Dialog d = builder
                    .setView(editView)
                    .setTitle("Edit a city")
                    .setNegativeButton("Cancel", null)
                    .setPositiveButton("Edit", (dialog, which) -> {
                        cityName.setText(editCityName.getText().toString());
                        provinceName.setText(editProvinceName.getText().toString());
                    })
                    .create();
            d.show();
        });

        City city = getItem(position);
        TextView cityName = view.findViewById(R.id.city_text);
        TextView provinceName = view.findViewById(R.id.province_text);
        cityName.setText(city.getName());
        provinceName.setText(city.getProvince());
        return view;
    }
}