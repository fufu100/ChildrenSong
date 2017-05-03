package com.foreverrich.childrensong.Api;

import com.foreverrich.childrensong.bean.VideoListResp;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by lyf on 2017/5/3.
 */

public interface SongService {

    @GET("1/getvideos.json")
    Observable<VideoListResp> getVideoList(@Query("subcateid") String id,
                                           @Query("p") int page,
                                           @Query("ps") int perpage);
}
