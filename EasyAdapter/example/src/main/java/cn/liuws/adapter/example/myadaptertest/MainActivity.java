package cn.liuws.adapter.example.myadaptertest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;



public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_easy:
                startActivity(new Intent(this,EasyAdapterTest1.class));
                break;
            case R.id.btn_chat_easy:
                startActivity(new Intent(this,EasyAdapterTest2.class));
                break;
            case R.id.btn_recycler_easy:
                startActivity(new Intent(this,RecyclerAdapterTest1.class));
                break;
            case R.id.btn_chat_recycler_easy:
                startActivity(new Intent(this,RecyclerAdapterTest2.class));
                break;
            default:

        }
    }
}
