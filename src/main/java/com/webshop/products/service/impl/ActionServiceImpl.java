package com.webshop.products.service.impl;

import com.webshop.products.domain.Action;
import com.webshop.products.domain.Product;
import com.webshop.products.exception.NotFoundException;
import com.webshop.products.repository.ActionRepository;
import com.webshop.products.service.ActionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class ActionServiceImpl implements ActionService {

    private final ActionRepository actionRepository;
    private final ProductServiceImpl productService;

    @Override
    public Page<Action> findAllActions(Pageable pageable) {
        return actionRepository.findAll(pageable);
    }

    @Override
    public Action findActionById(String id) {
        return actionRepository.findById(id).orElseThrow(() -> new NotFoundException(String.format(
                "Action with id: %s not found", id)));
    }

    @Override
    public Action saveAction(Action action) {
        action.setTotalCostWithoutAction(calculateCostOfAllActionsProducts(action.getProducts()));
        return actionRepository.insert(action);
    }

    @Override
    public Action updateAction(Action action, String id) {
        findActionById(id);
        action.setId(id);
        action.setTotalCostWithoutAction(calculateCostOfAllActionsProducts(action.getProducts()));
        return actionRepository.save(action);
    }

    @Override
    public void deleteActionById(String id) {
        actionRepository.deleteById(id);
    }

    private BigDecimal calculateCostOfAllActionsProducts(List<Product> products) {
        return products.stream()
                .map(Product::getId)
                .map(productService::findProductById)
                .map(Product::getPrice)
                .reduce(new BigDecimal(0), BigDecimal::add);
    }

}
