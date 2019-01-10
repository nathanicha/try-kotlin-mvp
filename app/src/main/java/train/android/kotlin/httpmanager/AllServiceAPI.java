package train.android.kotlin.httpmanager;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import train.android.kotlin.model.AlbumModel;
import train.android.kotlin.model.PhotoAlbumModel;
import train.android.kotlin.model.UsersModel;
import train.android.kotlin.module.todo.Usercheck;
import train.android.kotlin.module.topost.CmObj;
import train.android.kotlin.module.topost.PostObj;

/**
 * Created by nack_ on 16/1/2561.
 */

public interface AllServiceAPI {

    @GET("users")
    Call<List<UsersModel>> getPosts();

    @GET("albums")
    Call<List<AlbumModel>> getAlbums(@Query("userId") int id);

    @GET("photos")
    Call<List<PhotoAlbumModel>> getPhotoAlbums(@Query("albumId") int id);

    @GET("todos")
    Call<List<Usercheck>> getTodo(@Query("userId") int id);

    @GET("posts")
    Call<List<PostObj>> getToPost(@Query("userId") int id);

    @GET("comments")
    Call<List<CmObj>> getToComment(@Query("postId") int id);


}


