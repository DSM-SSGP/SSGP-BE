package project.ssgp.map.payload.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SearchRequest {

    private String categoryGroupCode;

    private String x;

    private String y;

    private Integer radius;

}
