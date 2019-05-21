package com.webshop.products.service;

import com.webshop.products.domain.Action;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ActionService {

    Page<Action> findAllActions(Pageable pageable);

    Action findActionById(String id);

    Action saveAction(Action action);

    Action updateAction(Action action, String id);

    void deleteActionById(String id);

}

