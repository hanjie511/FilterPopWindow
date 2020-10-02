package com.example.hj.myfilterpopwindow;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class HJFilterPopWindow extends PopupWindow {
    private List<Map<String, Object>> list;
    public static final String isShowMore="false";
    public static final String dataSource="dataSource";
    public static final String tagName="tagName";
    private ListView listView_hj_popwindow_layout;
    private MyListAdapter myListAdapter;
    private Button cancel_btn_hj_popwindow;
    private Button confirm_btn_hj_popwindow;
    private Context context;
    private HJFilterPopwindowDismisListener hjFilterPopwindowDismisListener;
    public HJFilterPopWindow(Context context) {
        super(context);
        this.context=context;
    }
    public void setDataSource(List<Map<String, Object>> list){
        this.list=list;
        View view= LayoutInflater.from(context).inflate(R.layout.hj_popwindow_layout,null,false);
        setContentView(view);
        DisplayMetrics dm = new DisplayMetrics();
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        wm.getDefaultDisplay().getMetrics(dm);
        int screenWidth = dm.widthPixels;
        int screenHeight = dm.heightPixels;
        int width=(int)(screenWidth*0.8);
        setWidth(width);
        setHeight(screenHeight);
        setOutsideTouchable(true);
    }
    public HJFilterPopWindow(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context=context;
    }
    public void setHJFilterPopwindowDismisListener(HJFilterPopwindowDismisListener hjFilterPopwindowDismisListener){
        this.hjFilterPopwindowDismisListener=hjFilterPopwindowDismisListener;
    }
    @Override
    public void setContentView(View contentView) {
        super.setContentView(contentView);
        listView_hj_popwindow_layout=contentView.findViewById(R.id.listView_hj_popwindow_layout);
        cancel_btn_hj_popwindow=contentView.findViewById(R.id.cancel_btn_hj_popwindow);
        confirm_btn_hj_popwindow=contentView.findViewById(R.id.confirm_btn_hj_popwindow);
        myListAdapter=new MyListAdapter(context,R.layout.item_listview_hj_popwindow_layout,list);
        listView_hj_popwindow_layout.setAdapter(myListAdapter);
        confirm_btn_hj_popwindow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list=myListAdapter.getList();
                Map cmap=new HashMap();
                for(Map<String,Object> m:list){
                    String str="";
                    int position=0;
                    for(MyFilter filter:(List<MyFilter>)m.get(HJFilterPopWindow.dataSource)){
                        if(filter.isSelected()){
                            str=str+filter.getName()+",";
                        }
                        position++;
                    }
                    cmap.put(m.get(HJFilterPopWindow.tagName),str);
                }
                hjFilterPopwindowDismisListener.getFilterResult(list,cmap);
                dismiss();
            }
        });
        cancel_btn_hj_popwindow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list=myListAdapter.getList();
                Map cmap=new HashMap();
                for(Map<String,Object> m:list){
                    String str="";
                    int position=0;
                    for(MyFilter filter:(List<MyFilter>)m.get(HJFilterPopWindow.dataSource)){
                        if(filter.isSelected()){
                            str=str+filter.getName()+",";
                        }
                        position++;
                    }
                    cmap.put(m.get(HJFilterPopWindow.tagName),str);
                }
                hjFilterPopwindowDismisListener.getFilterResult(list,cmap);
                dismiss();
            }
        });
    }
    public interface HJFilterPopwindowDismisListener{
        void getFilterResult(List<Map<String, Object>> list,Map m);
    }
}
