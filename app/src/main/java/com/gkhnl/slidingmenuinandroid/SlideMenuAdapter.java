package com.gkhnl.slidingmenuinandroid;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by gkhnnl on 11/06/15.
 */
public class SlideMenuAdapter extends BaseAdapter {

    private List<SlideMenuItem> items;
    Context ctx;
    TextView txt_title;

    public SlideMenuAdapter(List<SlideMenuItem> items, Context context){
        this.items = items;
        this.ctx = context;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        View v = LayoutInflater.from(ctx).inflate(R.layout.slidemenu_item, null);

        txt_title = (TextView)v.findViewById(R.id.txt_title);

        txt_title.setText(items.get(position).getTitle());

        return v;
    }
}
