package project.ssgp.map.payload.response;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class DocumentListResponse {

    private String addressName;

    private String categoryGroupCode;

    private String categoryGroupName;

    private String categoryName;

    private String distance;

    private String id;

    private String phone;

    private String placeName;

    private String placeUrl;

    private String rodaAddressName;

    private String x;

    private String y;

}
