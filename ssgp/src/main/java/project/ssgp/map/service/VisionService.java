package project.ssgp.map.service;

import project.ssgp.map.payload.response.SearchResponse;

import java.util.List;

public interface VisionService {

    List<SearchResponse> searchConvenienceStore(String x, String y);

}
