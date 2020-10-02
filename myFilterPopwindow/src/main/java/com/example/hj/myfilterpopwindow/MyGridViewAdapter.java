package com.example.hj.myfilterpopwindow;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class MyGridViewAdapter extends ArrayAdapter<MyFilter> {
    private List<MyFilter> list;
    private boolean isShowMore;
    private TextView selectedText;
    private MyGridViewDataChangedListener myGridViewDataChangedListener;
    private String tagName;
    public MyGridViewAdapter(@NonNull Context context, int resource, @NonNull List<MyFilter> list) {
        super(context, resource, list);
        this.list=list;
    }
    public void setIsShowMore(String isShowMore1){
        if("true".equals(isShowMore1)){
           isShowMore=true;
        }else{
            isShowMore=false;
        }
    }
    public void setTagName(String tagName){
        this.tagName=tagName;
    }
    public void setMyGridViewDataChangedListener(MyGridViewDataChangedListener myGridViewDataChangedListener){
        this.myGridViewDataChangedListener=myGridViewDataChangedListener;
    }
    public void setShowSelectedTextView(TextView textView){
        this.selectedText=textView;
    }
    @Override
    public int getCount() {
        if(isShowMore){
            return list.size();
        }else{
            if(list.size()>=4){
                return 4;
            }else{
                return list.size();
            }
        }
    }

    @Nullable
    @Override
    public MyFilter getItem(int position) {
        return list.get(position);
    }
    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder holder=null;
        final MyFilter myFilter=list.get(position);
        if(convertView==null){
            holder=new ViewHolder();
            convertView= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_gridview_hj_popwindow_layout,null,false);
            holder.checkBox=convertView.findViewById(R.id.checkbox_item_gridview_hj_popwindow);
            convertView.setTag(holder);
        }else{
            holder= (ViewHolder) convertView.getTag();
        }
        holder.checkBox.setText(myFilter.getName());
        holder.checkBox.setTag(position);
        if(myFilter.isSelected()){
            holder.checkBox.setChecked(true);
        }else{
            holder.checkBox.setChecked(false);
        }
        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    MyFilter myFilter1=list.get(position);
                    myFilter1.setSelected(true);
                    list.set(position,myFilter1);
                }else{
                    MyFilter myFilter1=list.get(position);
                    myFilter1.setSelected(false);
                    list.set(position,myFilter1);
                }
                String str="";
                for(MyFilter my:list){
                    if(my.isSelected()){
                        str=str+my.getName()+",";
                    }
                }
                myGridViewDataChangedListener.setData(list,tagName);
                selectedText.setText(str.substring(0,str.length()-1));
                notifyDataSetChanged();
            }
        });
        return convertView;
    }
    public interface MyGridViewDataChangedListener{
        void setData(List<MyFilter> list,String tagName);
    }
    class ViewHolder {
        CheckBox checkBox;
    }

}
