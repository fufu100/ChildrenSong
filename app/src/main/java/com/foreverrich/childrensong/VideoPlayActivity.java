package com.foreverrich.childrensong;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.WindowManager;

import com.foreverrich.childrensong.bean.Video;
import com.foreverrich.childrensong.widget.UniversalMediaController;
import com.foreverrich.childrensong.widget.UniversalVideoView;

public class VideoPlayActivity extends AppCompatActivity  implements UniversalVideoView.VideoViewCallback {
    private String TAG = "VideoPlayActivity";
    UniversalVideoView mVideoView;
    UniversalMediaController mMediaController;
    Video info;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(R.layout.activity_video_play);

        info = getIntent().getParcelableExtra("video");

        mVideoView = (UniversalVideoView) findViewById(R.id.videoView);
        mMediaController = (UniversalMediaController) findViewById(R.id.media_controller);
        mVideoView.setMediaController(mMediaController);
        mVideoView.setVideoViewCallback(this);

        mVideoView.setVideoPath("http://res1.ubestkid.com/blk/m/054_wawdyey.mp4");
        mVideoView.requestFocus();
        mVideoView.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.v(TAG, "onDestroy");
    }

    @Override
    public void onScaleChange(boolean isFullscreen) {

    }

    @Override
    public void onPause(MediaPlayer mediaPlayer) {

    }

    @Override
    public void onStart(MediaPlayer mediaPlayer) {

    }

    @Override
    public void stop() {

    }

    @Override
    public void onBufferingStart(MediaPlayer mediaPlayer) {

    }

    @Override
    public void onBufferingEnd(MediaPlayer mediaPlayer) {

    }
}
