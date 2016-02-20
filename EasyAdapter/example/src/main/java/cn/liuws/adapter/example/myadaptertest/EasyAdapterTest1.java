package cn.liuws.adapter.example.myadaptertest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import cn.liuws.adapter.EasyAdapter;
import cn.liuws.adapter.example.bean.Bean;

/**
 * 单tem布局示例
 */
public class EasyAdapterTest1 extends AppCompatActivity {

    private ArrayList<Bean> list;
    private EasyAdapter<Bean> myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_easy_adapter1);
        ListView listView = (ListView) findViewById(R.id.list_view);
        list = new ArrayList<Bean>();
        myAdapter = new EasyAdapter<Bean>(list, R.layout.left_layout) {
            @Override
            public void setDataForView(ViewHolder holder, int viewType, Bean item, int position) {
                ((TextView) holder.getViewById(R.id.left_text)).setText(item.getContent() + position);
            }
        };
        listView.setAdapter(myAdapter);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.add:
                Bean bean = new Bean();
//                bean.setValueType(0);
                bean.setContent("EasyAdapter");
                list.add(bean);
                break;
            case R.id.clear:
                list.clear();
                break;
        }
        myAdapter.notifyDataSetChanged();
    }

}
