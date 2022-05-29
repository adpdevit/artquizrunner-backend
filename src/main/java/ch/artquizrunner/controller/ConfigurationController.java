package ch.artquizrunner.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import ch.artquizrunner.api.ConfigurationApi;

@RestController
@CrossOrigin(exposedHeaders = "*")
public class ConfigurationController implements ConfigurationApi {

    @Override
    public ResponseEntity<Void> health() {
        return ResponseEntity.ok(null);
    }

}
