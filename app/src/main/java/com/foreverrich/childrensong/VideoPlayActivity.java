package com.foreverrich.childrensong;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.WindowManager;

import com.foreverrich.childrensong.bean.Video;
import com.foreverrich.childrensong.widget.UniversalMediaController;
import com.foreverrich.childrensong.widget.UniversalVideoView;

import java.util.List;

public class VideoPlayActivity extends AppCompatActivity  implements UniversalVideoView.VideoViewCallback {
    private String TAG = "VideoPlayActivity";
    UniversalVideoView mVideoView;
    UniversalMediaController mMediaController;
    List<Video> videos;
    private int current;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(R.layout.activity_video_play);

        videos = getIntent().getParcelableArrayListExtra("video_list");
        current = getIntent().getIntExtra("current",0);

        mVideoView = (UniversalVideoView) findViewById(R.id.videoView);
        mMediaController = (UniversalMediaController) findViewById(R.id.media_controller);
        mVideoView.setMediaController(mMediaController);
        mVideoView.setVideoViewCallback(this);

        mVideoView.setVideoPath(videos.get(current).url);
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
        current++;
        if(current >= videos.size()){
            current = 0;
        }
        mVideoView.setVideoPath(videos.get(current).url);
        mVideoView.requestFocus();
        mVideoView.start();
    }

    @Override
    public void onBufferingStart(MediaPlayer mediaPlayer) {

    }

    @Override
    public void onBufferingEnd(MediaPlayer mediaPlayer) {

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(mMediaController.dispatchKeyEvent(event)){
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
