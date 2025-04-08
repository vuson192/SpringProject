package com.sonvv.spring.controller;

import com.sonvv.spring.dto.WorkRequest;
import com.sonvv.spring.model.Work;
import com.sonvv.spring.service.WorkService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@SecurityRequirement(name = "basicAuth")
@RequestMapping("/transaction/")
@RestController
public class WorkController {

    @Autowired
    private WorkService workService;

    private final Logger logger = LogManager.getLogger(this.getClass());

    @Operation(summary = "Work Transaction Handler", description = "Handle all Work actions via POST")
    @RequestMapping(value = "out/inquiry", method = RequestMethod.POST)
    public ResponseEntity<?> handleWorkAction(@RequestBody WorkRequest request) {
        String action = request.getActionType();

        logger.info("Received action: " + action);

        switch (action.toUpperCase()) {
            case "CREATE":
                Work newWork = new Work(request.getName(), request.getDescription());
                return ResponseEntity.ok(workService.createWork(newWork));

            case "READ":
                Work found = workService.findWorkById(request.getId());
                return found != null ? ResponseEntity.ok(found) : ResponseEntity.notFound().build();

            case "UPDATE":
                Work toUpdate = new Work();
                toUpdate.setId(request.getId());
                toUpdate.setName(request.getName());
                toUpdate.setDescription(request.getDescription());
                return ResponseEntity.ok(workService.updateWork(toUpdate));

            case "DELETE":
                boolean deleted = workService.deleteWork(request.getId());
                return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();

            default:
                return ResponseEntity.badRequest().body("Invalid action type");
        }
    }
}