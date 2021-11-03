package project.ssgp.map.payload.response;

import lombok.Getter;

import java.util.List;

@Getter
public class CategorySearchResponse {

    private List<DocumentListResponse> documents;

    private MetaResponse metas;

    private SameNameResponse sameNames;

}