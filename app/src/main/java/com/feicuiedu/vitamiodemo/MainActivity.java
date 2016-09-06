package com.feicuiedu.vitamiodemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.feicuiedu.vitamiodemo.ademovideoview.VideoViewActivity;
import com.feicuiedu.vitamiodemo.bdemovideobuffer.VideoBufferActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnItemClick;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.listview) ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override public void onContentChanged() {
        super.onContentChanged();
        ButterKnife.bind(this);
        String[] datas = new String[]{
                "VideoView",
                "VideoBuffer"
        };
        ArrayAdapter adapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                datas);
        listView.setAdapter(adapter);
    }

    @OnItemClick(R.id.listview)
    public void onClickItem(int postion){
        Intent intent = null;
        switch (postion){
            case 0:
                intent = new Intent(this, VideoViewActivity.class);
                startActivity(intent);
                break;
            case 1:
                intent = new Intent(this, VideoBufferActivity.class);
                startActivity(intent);
                break;
            default:
                // TODO: 2016/9/6 0006  无效操作
                break;
        }
    }
}