package com.webshop.products.repository;

import com.webshop.products.domain.Action;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ActionRepository extends MongoRepository<Action, String> {
}
