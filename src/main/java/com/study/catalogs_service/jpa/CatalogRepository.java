package com.study.catalogs_service.jpa;

import org.springframework.data.repository.CrudRepository;

public interface CatalogRepository extends CrudRepository<Catalog,Long> {
    Catalog findByProductId(String productId);
}
