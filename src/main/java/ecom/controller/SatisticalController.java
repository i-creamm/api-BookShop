package ecom.controller;


import ecom.dto.CategoryBestSeller;
import ecom.repository.SatisticalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/statistical")
public class SatisticalController {

    @Autowired
    SatisticalRepository satisticalRepository;

    @GetMapping("/get-category-seller")
    public ResponseEntity<List<CategoryBestSeller>> getCategoryBestSeller() {
        List<Object[]> list = satisticalRepository.getCategoryBestSeller();
        List<CategoryBestSeller> listCategoryBestSeller = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            CategoryBestSeller categoryBestSeller = new CategoryBestSeller(String.valueOf(list.get(i)[1]),
                    Integer.valueOf(String.valueOf(list.get(i)[0])), Double.valueOf(String.valueOf(list.get(i)[2])));
            listCategoryBestSeller.add(categoryBestSeller);
        }
        return ResponseEntity.ok(listCategoryBestSeller);
    }
}
