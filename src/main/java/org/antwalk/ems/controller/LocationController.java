package org.antwalk.ems.controller;

import java.util.List;

import org.antwalk.ems.exception.ResourceNotFoundException;
import org.antwalk.ems.model.Location;
import org.antwalk.ems.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.constraints.Min;

@RestController
@RequestMapping("/location")
public class LocationController {

    @Autowired
    LocationRepository locationRepository;
    

    @PostMapping("/add")
    public ResponseEntity<Location> addLocation(@RequestBody(required = true) Location location){
        locationRepository.save(location);
        return ResponseEntity.ok().body(location);
    }

    @GetMapping("/listall")
    public ResponseEntity<List<Location>> listAllLocations(){
        return ResponseEntity.ok().body(locationRepository.findAll());
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<Location> getById(@PathVariable("id") @Min(1) Long id ) throws ResourceNotFoundException{
        Location location = locationRepository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException("No location with id " + id + " exists")
        );
        return ResponseEntity.ok().body(location);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Location> updateLocation(@PathVariable("id") @Min(1) Long id, Location suppliedLocation ) throws ResourceNotFoundException{
        Location location = locationRepository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException("No location with id " + id + " exists")
        );
        suppliedLocation.setLocId(location.getLocId());
        locationRepository.save(suppliedLocation);
        return ResponseEntity.ok().body(location);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Location> deleteLocation(@PathVariable("id") @Min(1) Long id, Location suppliedLocation ) throws ResourceNotFoundException{
        Location location = locationRepository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException("No location with id " + id + " exists")
        );
        locationRepository.delete(location);
        return ResponseEntity.ok().body(null);
    }
}
