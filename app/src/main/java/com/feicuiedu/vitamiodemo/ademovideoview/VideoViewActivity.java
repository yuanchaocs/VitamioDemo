package com.feicuiedu.vitamiodemo.ademovideoview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.feicuiedu.vitamiodemo.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.vov.vitamio.Vitamio;
import io.vov.vitamio.widget.MediaController;
import io.vov.vitamio.widget.VideoView;

public class VideoViewActivity extends AppCompatActivity {

    @Bind(R.id.videoView) VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_view);
        ButterKnife.bind(this);
        Vitamio.isInitialized(this);
        videoView.setVideoPath(getTestVideo());
        videoView.setMediaController(new MediaController(this));
        videoView.start();
    }

    public String getTestVideo() {
        return "http://devimages.apple.com/iphone/samples/bipbop/bipbopall.m3u8";
    }
}
