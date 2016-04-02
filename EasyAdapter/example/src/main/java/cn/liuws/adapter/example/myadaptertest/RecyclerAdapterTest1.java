package cn.liuws.adapter.example.myadaptertest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cn.liuws.adapter.EasyRecyclerAdapter;
import cn.liuws.adapter.example.bean.Bean;

/**
 * 单item布局示例
 */
public class RecyclerAdapterTest1 extends AppCompatActivity {

    private EasyRecyclerAdapter<Bean> adapter;
    private List<Bean> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler1);
        list = new ArrayList<>();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        adapter = new EasyRecyclerAdapter<Bean>(list, R.layout.left_layout) {
            @Override
            public void setDataForView(ViewHolder holder, Bean itemData, int position) {
                ((TextView) holder.getViewById(R.id.left_text)).setText(itemData.getContent() + position);
            }
        };
        recyclerView.setAdapter(adapter);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.add:
                Bean bean = new Bean();
                bean.setValueType(0);
                bean.setContent("EasyRecyclerAdapter");
                list.add(bean);
                break;
            case R.id.clear:
                list.clear();
                break;
        }
        adapter.notifyDataSetChanged();
    }
}
