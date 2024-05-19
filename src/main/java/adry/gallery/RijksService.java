package adry.gallery;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * https://data.rijksmuseum.nl/object-metadata/
 */
public interface RijksService {

    @GET("/api/en/collection")

    Single<ArtObjects> page(
            // pass parameters given on CollectionAPI (app id mandatory) to validate the call
            @Query("key") String key,
            @Query("p") int pageNum
    );

    @GET("/api/en/collection")

    Single<ArtObjects> query(
            @Query("key") String key,
            @Query("p") int pageNum,
            @Query("q")String query
    );

    @GET("/api/en/collection")

    Single<ArtObjects> artist(
            @Query("key") String key,
            @Query("p") int pageNum,
            @Query("involvedMaker")String artist
    );

}
