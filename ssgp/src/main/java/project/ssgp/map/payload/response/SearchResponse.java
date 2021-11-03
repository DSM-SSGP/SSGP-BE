package project.ssgp.map.payload.response;

import lombok.Builder;

@Builder
public class SearchResponse {

    private String addressName;

    private String categoryGroupName;

    private String categoryName;

    private String phone;

    private String placeName;

    private String x;

    private String y;

}