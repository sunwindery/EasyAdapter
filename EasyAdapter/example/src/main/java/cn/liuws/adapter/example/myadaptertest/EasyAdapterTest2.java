package cn.liuws.adapter.example.myadaptertest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cn.liuws.adapter.EasyAdapter;
import cn.liuws.adapter.example.bean.Bean;

/**
 * 多种item布局示例
 */
public class EasyAdapterTest2 extends AppCompatActivity {

    private ArrayList<Bean> list;
    private EasyAdapter<Bean> myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_easy_adapter2);
        ListView listView = (ListView) findViewById(R.id.list_view);
        list = new ArrayList<Bean>();
        myAdapter = new EasyAdapter<Bean>(list, R.layout.left_layout, R.layout.right_layout) {
            @Override
            public void setDataForView(ViewHolder holder, int viewType, Bean item, int position) {
                switch (getItemViewType(position)) {
                    case 0://对应R.layout.left_layout布局
                        ((TextView) holder.getViewById(R.id.left_text)).setText(item.getContent() + position);
                        break;
                    case 1://对应R.layout.right_layout布局
                        ((TextView) holder.getViewById(R.id.right_text)).setText(item.getContent() + position);
                        break;
                }
            }

            @Override
            public int getItemViewType(int position, List<Bean> mList) {
                //返回值与构造方法中布局文件依次对应（R.layout.left_layout对应0, R.layout.right_layout对应1）。
                // 在setDataForView（）中通过参数viewType，判断当前使用的是哪个布局的itemView，并做相应的操作。
                return mList.get(position).getValueType();
            }
        };
        listView.setAdapter(myAdapter);
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
        myAdapter.notifyDataSetChanged();
    }

}
