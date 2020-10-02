package com.example.hj.myfilterpopwindow;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class MyListAdapter extends ArrayAdapter<Map<String,Object>> implements MyGridViewAdapter.MyGridViewDataChangedListener {
    private List<Map<String,Object>> list;
    private MyGridViewAdapter myGridViewAdapter;
    public MyListAdapter(@NonNull Context context, int resource, @NonNull List<Map<String, Object>> objects) {
        super(context, resource, objects);
        this.list=objects;
    }
    public List<Map<String, Object>> getList() {
        return list;
    }

    public void setList(List<Map<String, Object>> list) {
        this.list = list;
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Nullable
    @Override
    public Map<String, Object> getItem(int position) {
        return list.get(position);
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder holder=null;
        Map m=list.get(position);
        if(convertView==null){
            holder=new ViewHolder();
            convertView= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_listview_hj_popwindow_layout,null,false);
            holder.selectedTag_text_item_listview_hj_popwindow_layout=convertView.findViewById(R.id.selectedTag_text_item_listview_hj_popwindow_layout);
            holder.tagName_text_item_listview_hj_popwindow_layout=convertView.findViewById(R.id.tagName_text_item_listview_hj_popwindow_layout);
            holder.showLess_imageView_item_listview_hj_popwindow_layout=convertView.findViewById(R.id.showLess_imageView_item_listview_hj_popwindow_layout);
            holder.showMore_imageView_item_listview_hj_popwindow_layout=convertView.findViewById(R.id.showMore_imageView_item_listview_hj_popwindow_layout);
            holder.gridView_item_listview_hj_popwindow_layout=convertView.findViewById(R.id.gridView_item_listview_hj_popwindow_layout);
            convertView.setTag(holder);
        }else{
            holder= (ViewHolder) convertView.getTag();
        }
        holder.tagName_text_item_listview_hj_popwindow_layout.setText((String)m.get(HJFilterPopWindow.tagName));
        myGridViewAdapter=new MyGridViewAdapter(parent.getContext(),R.layout.item_gridview_hj_popwindow_layout, (List<MyFilter>) m.get(HJFilterPopWindow.dataSource));
        myGridViewAdapter.setShowSelectedTextView(holder.selectedTag_text_item_listview_hj_popwindow_layout);
        myGridViewAdapter.setTagName((String)m.get(HJFilterPopWindow.tagName));
        myGridViewAdapter.setMyGridViewDataChangedListener(this);
        holder.gridView_item_listview_hj_popwindow_layout.setAdapter(myGridViewAdapter);
        if(m.containsKey(HJFilterPopWindow.isShowMore)){//有显示更多属性
            myGridViewAdapter.setIsShowMore((String)m.get(HJFilterPopWindow.isShowMore));
            if("true".equals((String)m.get(HJFilterPopWindow.isShowMore))){//是显示更多
                holder.showMore_imageView_item_listview_hj_popwindow_layout.setVisibility(View.GONE);
                holder.showLess_imageView_item_listview_hj_popwindow_layout.setVisibility(View.VISIBLE);
            }else{//不是显示更多
                holder.showMore_imageView_item_listview_hj_popwindow_layout.setVisibility(View.VISIBLE);
                holder.showLess_imageView_item_listview_hj_popwindow_layout.setVisibility(View.GONE);
            }
        }else{//不含有显示更多属性
            myGridViewAdapter.setIsShowMore("false");
            holder.showMore_imageView_item_listview_hj_popwindow_layout.setVisibility(View.VISIBLE);
            holder.showLess_imageView_item_listview_hj_popwindow_layout.setVisibility(View.GONE);
        }
        holder.showMore_imageView_item_listview_hj_popwindow_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map m=list.get(position);
                m.put(HJFilterPopWindow.isShowMore,"true");
                list.set(position,m);
                myGridViewAdapter.setIsShowMore("true");
                myGridViewAdapter.notifyDataSetChanged();
                notifyDataSetChanged();
            }
        });
        holder.showLess_imageView_item_listview_hj_popwindow_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map m=list.get(position);
                m.put(HJFilterPopWindow.isShowMore,"false");
                list.set(position,m);
                myGridViewAdapter.setIsShowMore("false");
                myGridViewAdapter.notifyDataSetChanged();
                notifyDataSetChanged();
            }
        });
        return convertView;
    }

    @Override
    public void setData(List<MyFilter> list1,String tagName) {
        int i=0;
        for(Map m:list){
            if(tagName.equals(m.get(HJFilterPopWindow.tagName))){
                Map m1=list.get(i);
                m1.put(HJFilterPopWindow.dataSource,list1);
                list.set(i,m1);
            }
            i++;
        }
    }

    class ViewHolder{
        TextView tagName_text_item_listview_hj_popwindow_layout;
        TextView selectedTag_text_item_listview_hj_popwindow_layout;
        ImageView showMore_imageView_item_listview_hj_popwindow_layout;
        ImageView showLess_imageView_item_listview_hj_popwindow_layout;
        HJGridView gridView_item_listview_hj_popwindow_layout;
    }
}
