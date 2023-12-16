package edu.miu.cs.cs544.controller;

import edu.miu.cs.cs544.DTO.StateDTO;
import edu.miu.cs.cs544.service.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/states")
public class StateController {

    @Autowired
    private StateService stateService;

    @GetMapping("/{id}")
    public ResponseEntity<StateDTO> getStateById(@PathVariable Long id) {
        StateDTO stateDTO = stateService.getStateById(id);
        return ResponseEntity.ok(stateDTO);
    }

    @PostMapping("/create")
    public ResponseEntity<StateDTO> createState(@RequestBody StateDTO stateDTO) {
        StateDTO createdState = stateService.createState(stateDTO);
        return new ResponseEntity<>(createdState, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StateDTO> updateState(@PathVariable Long id, @RequestBody StateDTO stateDTO) {
        StateDTO updatedState = stateService.updateState(id, stateDTO);
        if (updatedState != null) {
            return ResponseEntity.ok(updatedState);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteState(@PathVariable Long id) {
        boolean isDeleted = stateService.deleteState(id);
        if (isDeleted) {
            return ResponseEntity.ok("State with ID: " + id + " has been deleted.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("State with ID: " + id + " not found.");
        }
    }
}