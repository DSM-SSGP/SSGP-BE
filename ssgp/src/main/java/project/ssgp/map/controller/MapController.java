package project.ssgp.map.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.ssgp.map.payload.response.SearchResponse;
import project.ssgp.map.service.VisionService;

import java.util.List;

@RestController
@RequestMapping("/map")
@RequiredArgsConstructor
public class MapController {

    private final VisionService visionService;

    @GetMapping
    public List<SearchResponse> searchConvenienceStore(@PathVariable String x,
                                                       @PathVariable String y) {
        return visionService.searchConvenienceStore(x, y);
    }

}
