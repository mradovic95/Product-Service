package com.webshop.products.controller;

import com.webshop.products.domain.Action;
import com.webshop.products.secutiry.CheckSecurity;
import com.webshop.products.service.ActionService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/actions")
@RequiredArgsConstructor
public class ActionController {

    private final ActionService actionService;

    @ApiOperation(value = "Get all actions")
    @GetMapping
    @CheckSecurity(roles = {"ROLE_ADMIN", "ROLE_USER"})
    public ResponseEntity<Page<Action>> getAllActions(@RequestHeader("Authorization") String authorization, Pageable pageable) {
        return new ResponseEntity<>(actionService.findAllActions(pageable), HttpStatus.OK);
    }

    @ApiOperation(value = "Get action by id")
    @GetMapping("/{id}")
    @CheckSecurity(roles = {"ROLE_ADMIN", "ROLE_USER"})
    public ResponseEntity<Action> getAllActions(@RequestHeader("Authorization") String authorization, @PathVariable String id) {
        return new ResponseEntity<>(actionService.findActionById(id), HttpStatus.OK);
    }

    @ApiOperation(value = "Save action")
    @PostMapping
    @CheckSecurity(roles = {"ROLE_ADMIN"})
    public ResponseEntity<Action> saveAction(@RequestHeader("Authorization") String authorization, @RequestBody @Valid Action action) {
        return new ResponseEntity<>(actionService.saveAction(action), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Update action")
    @PutMapping
    @CheckSecurity(roles = {"ROLE_ADMIN"})
    public ResponseEntity<Action> updateAction(@RequestHeader("Authorization") String authorization, @RequestBody @Valid Action action, @PathVariable String id) {
        return new ResponseEntity<>(actionService.updateAction(action, id), HttpStatus.OK);
    }

    @ApiOperation(value = "Delete action")
    @DeleteMapping("/{id}")
    @CheckSecurity(roles = {"ROLE_ADMIN"})
    public ResponseEntity deleteAction(@RequestHeader("Authorization") String authorization, @PathVariable String id) {
        actionService.deleteActionById(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

}
