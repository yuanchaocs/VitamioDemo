package com.feicuiedu.vitamiodemo.bdemovideobuffer;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;

import com.feicuiedu.vitamiodemo.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.widget.MediaController;
import io.vov.vitamio.widget.VideoView;

// VideoView,处理一下缓冲
public class VideoBufferActivity extends AppCompatActivity implements
        MediaPlayer.OnPreparedListener,
        MediaPlayer.OnInfoListener {

    // 正常播放
    // 缓冲状态 - 更新UI(显示出一下个加载中)

    @Bind(R.id.videoView) VideoView videoView;
    @Bind(R.id.progressBar) ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_buffer);
        ButterKnife.bind(this);
        playVideo();
    }

    private void playVideo() {
        videoView.setVideoPath(getTestVideo());
        videoView.setMediaController(new MediaController(this));
        // 为了能监听出它的一个缓冲状态,我们要做一系列监听工作
        videoView.setOnPreparedListener(this); // 准备状态的监听 - 主要为了在准备好了后进行缓冲空间的设置
        videoView.setOnInfoListener(this); // 信息的监听，类似状态的一个监听 - 主要是为了知道什么时候开始及结束缓冲
    }

    public String getTestVideo() {
        return "http://devimages.apple.com/iphone/samples/bipbop/bipbopall.m3u8";
    }

    @Override public void onPrepared(MediaPlayer mp) {
        videoView.setBufferSize(1 * 1024 * 1024);
    }

    @Override public boolean onInfo(MediaPlayer mp, int what, int extra) {
        switch (what) {
            case MediaPlayer.MEDIA_INFO_BUFFERING_START:
                startBuffer();
                return true;
            case MediaPlayer.MEDIA_INFO_BUFFERING_END:
                endBuffer();
                return true;
        }
        return false;
    }

    // loading显示出来
    // videoview.pause()
    private void startBuffer() {
        if (videoView.isPlaying()) {
            videoView.pause();
        }
        // TODO: 2016/9/6 0006 UI的上更新
        progressBar.setVisibility(View.VISIBLE);
    }

    // loading隐藏起来
    // videoview.start()
    private void endBuffer() {
        videoView.start();
        // TODO: 2016/9/6 0006 UI上的更新
        progressBar.setVisibility(View.GONE);
    }

}
