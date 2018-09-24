package com.gtv.hanhee.fragmentlistfragment;

import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class FragmentDanhSach extends ListFragment{

    String[] arrayCity = {"Hai Phong","Ha Noi","Ho Chi Minh","Can Tho","Ha Nam","Nha Trang"};
    ArrayAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_danh_sach, container, false);

        adapter = new ArrayAdapter(getActivity(),android.R.layout.simple_list_item_1,arrayCity);
        setListAdapter(adapter);


        return view;
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {

        Toast.makeText(getActivity(), arrayCity[position], Toast.LENGTH_SHORT).show();
        super.onListItemClick(l, v, position, id);
    }
}
