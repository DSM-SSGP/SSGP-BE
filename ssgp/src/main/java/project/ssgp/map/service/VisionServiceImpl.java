package project.ssgp.map.service;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import project.ssgp.map.VisionApi;
import project.ssgp.map.payload.response.DocumentListResponse;
import project.ssgp.map.payload.response.CategorySearchResponse;
import project.ssgp.map.payload.response.SearchResponse;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.util.ArrayList;
import java.util.List;

@Service
public class VisionServiceImpl implements VisionService {

    @Value("${kakao.rest.api.key}")
    private String authorizationKey;

    private VisionApi connection = new Retrofit.Builder()
            .baseUrl("https://dapi.kakao.com/v2/local/")
            .addConverterFactory(JacksonConverterFactory.create())
            .build()
            .create(VisionApi.class);

    @Override
    public List<SearchResponse> searchConvenienceStore(String x, String y) {

        List<DocumentListResponse> result = sendMapResponse(x, y).body()
                .getDocuments();

        List<SearchResponse> resultResponses = new ArrayList<>();

        for (DocumentListResponse element : result) {
            resultResponses.add(
                    SearchResponse.builder()
                            .addressName(element.getAddressName())
                            .categoryGroupName(element.getCategoryGroupName())
                            .categoryName(element.getCategoryName())
                            .phone(element.getPhone())
                            .placeName(element.getPlaceName())
                            .x(element.getX())
                            .y(element.getY())
                            .build()
            );
        }

        return resultResponses;
    }

    @SneakyThrows
    private Response<CategorySearchResponse> sendMapResponse(String x, String y) {

        return connection
                .mapApi(authorizationKey, "CS2", x, y, 2000)
                .execute();

    }

}