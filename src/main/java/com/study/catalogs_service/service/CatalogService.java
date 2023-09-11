package com.study.catalogs_service.service;

import com.study.catalogs_service.jpa.Catalog;

public interface CatalogService {
    Iterable<Catalog> getAllCatalogs();
}
