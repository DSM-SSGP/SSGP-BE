package project.ssgp.map;

import project.ssgp.map.payload.response.CategorySearchResponse;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface VisionApi {

    @GET("/v2/local/search/category.json")
    Call<CategorySearchResponse> visionApi(
            @Header("Authorization")
            String token,
            @Field("categoryGroupCode") String category_group_code,
            @Field("x") String x,
            @Field("y") String y,
            @Field("radius") Integer radius
    );

}
