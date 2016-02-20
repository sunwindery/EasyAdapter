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
 * 多种item布局示例
 */
public class RecyclerAdapterTest2 extends AppCompatActivity {

    private EasyRecyclerAdapter<Bean> adapter;
    private List<Bean> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler2);
        list = new ArrayList<>();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
//        adapter = new MyRecyclerAdapter(list);
        adapter = new EasyRecyclerAdapter<Bean>(list, R.layout.left_layout, R.layout.right_layout) {
            @Override
            public void setDataForView(ViewHolder holder, int viewType, Bean item, int position) {
                if (0 == viewType) {//对应R.layout.left_layout布局
                    ((TextView) holder.getViewById(R.id.left_text)).setText(item.getContent());
                } else {//对应R.layout.right_layout布局
                    ((TextView) holder.getViewById(R.id.right_text)).setText(item.getContent());
                }
            }

            @Override
            public int getItemViewType(int position, List<Bean> mList) {
                //返回值与构造方法中布局文件依次对应（R.layout.left_layout对应0, R.layout.right_layout对应1）。
                // 在setDataForView（）中通过参数viewType，判断当前使用的是哪个布局的itemView，并做相应的操作。
                return mList.get(position).getValueType();
            }
        };
        recyclerView.setAdapter(adapter);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.left:
                Bean bean = new Bean();
                bean.setValueType(0);
                bean.setContent("您好！我是小红帽……");
                list.add(bean);
                break;
            case R.id.right:
                Bean bean2 = new Bean();
                bean2.setValueType(1);
                bean2.setContent("你好！");
                list.add(bean2);
                break;
        }
        adapter.notifyDataSetChanged();
    }
}
