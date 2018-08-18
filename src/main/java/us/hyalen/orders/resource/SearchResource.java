package us.hyalen.orders.resource;

import us.hyalen.orders.domain.Order;
import us.hyalen.orders.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/search")
public class SearchResource {
    @Autowired
    private SearchService searchService;

    @GetMapping
    public ResponseEntity<List<Order>> listResults(@RequestParam(value="criteria", required=false) String search) {
        return ok(searchService.searchCriteria(search));
    }
}