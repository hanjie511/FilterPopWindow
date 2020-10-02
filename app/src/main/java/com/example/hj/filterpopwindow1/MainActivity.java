package com.example.hj.filterpopwindow1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;

import com.example.hj.myfilterpopwindow.HJFilterPopWindow;
import com.example.hj.myfilterpopwindow.MyFilter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    List<Map<String,Object>> list=new ArrayList<>();
    Map resultMap=new HashMap();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
    }
    private void initData(){
        Map<String,Object> m1=new HashMap<>();
        m1.put(HJFilterPopWindow.isShowMore,"false");
        m1.put(HJFilterPopWindow.tagName,"姓名");
        List<MyFilter> list1=new ArrayList<>();
        MyFilter myFilter=null;
        for(int i=0;i<10;i++){
            myFilter=new MyFilter();
            myFilter.setName("张三"+i);
            myFilter.setSelected(false);
            list1.add(myFilter);
        }
        m1.put(HJFilterPopWindow.dataSource,list1);
        list.add(m1);
        Map<String,Object> m2=new HashMap<>();
        m2.put(HJFilterPopWindow.isShowMore,"false");
        m2.put(HJFilterPopWindow.tagName,"颜色");
        List<MyFilter> list2=new ArrayList<>();
        for(int i=0;i<5;i++){
            myFilter=new MyFilter();
            myFilter.setName("颜色"+i);
            myFilter.setSelected(false);
            list2.add(myFilter);
        }
        m2.put(HJFilterPopWindow.dataSource,list2);
        list.add(m2);

        Map<String,Object> m3=new HashMap<>();
        m3.put(HJFilterPopWindow.isShowMore,"false");
        m3.put(HJFilterPopWindow.tagName,"品牌");
        List<MyFilter> list3=new ArrayList<>();
        for(int i=0;i<3;i++){
            myFilter=new MyFilter();
            myFilter.setName("品牌"+i);
            myFilter.setSelected(false);
            list3.add(myFilter);
        }
        m3.put(HJFilterPopWindow.dataSource,list3);
        list.add(m3);
    }
    public void  showFilterPopwindow(View v){
        HJFilterPopWindow hj=new HJFilterPopWindow(MainActivity.this);
        hj.setDataSource(list);
        LinearLayout mainLinear=findViewById(R.id.mainLinear);
        hj.showAtLocation(mainLinear, Gravity.RIGHT,0,0);
        hj.setHJFilterPopwindowDismisListener(new HJFilterPopWindow.HJFilterPopwindowDismisListener() {
            @Override
            public void getFilterResult(List<Map<String, Object>> list1, Map m) {
                list=list1;
                resultMap=m;
                String str="姓名："+m.get("姓名")+"\n";
                str=str+"颜色："+m.get("颜色")+"\n";
                str=str+"品牌："+m.get("品牌")+"\n";
                AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("你选择的内容为：");
                builder.setMessage(str);
                builder.create().show();
            }
        });
    }
}