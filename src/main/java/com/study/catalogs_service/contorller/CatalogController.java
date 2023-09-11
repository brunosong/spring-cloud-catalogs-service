package com.study.catalogs_service.contorller;

import com.study.catalogs_service.jpa.Catalog;
import com.study.catalogs_service.service.CatalogService;
import com.study.catalogs_service.vo.ResponseCatalog;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/catalog-service")
public class CatalogController {

    private final Environment env;
    private final CatalogService catalogService;

    @GetMapping("/health_check")
    public String status() {
        return String.format("It's work PORT : %s" , env.getProperty("local.server.port"));
    }

    @GetMapping("/catalogs")
    public ResponseEntity<List<ResponseCatalog>> getCatalogs() {
        Iterable<Catalog> catalogList = catalogService.getAllCatalogs();

        List<ResponseCatalog> result = new ArrayList<>();

        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        catalogList.forEach( catalog -> {
            result.add(mapper.map(catalog,ResponseCatalog.class));
        });

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }



}
