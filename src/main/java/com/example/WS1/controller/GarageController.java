package com.example.WS1.controller;

import com.example.WS1.controller.request.CreateMotorcycleRequest;
import com.example.WS1.controller.request.DeleteMotorcycleRequest;
import com.example.WS1.controller.request.UpdateMotorcycleRequest;
import com.example.WS1.model.Motorcycle;
import com.example.WS1.service.GarageService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class GarageController {

    private GarageService garageService;

    public GarageController(GarageService garageService) {
        this.garageService = garageService;
    }


    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Motorcycle list"),
            @ApiResponse(code = 404, message = "Database empty")
    })
    @GetMapping("/motorcycles")
    public List<Motorcycle> getMotorcycles() {
        return garageService.getMotorcycles();
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Motorcycle found"),
            @ApiResponse(code = 404, message = "Motorcycle not found")
    })
    @GetMapping("/motorcycles/{id}")
    public Motorcycle getMortorcycle(@PathVariable("id") String id) throws Exception {
        return garageService.getMotorcycle(UUID.fromString(id));
    }



    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Motorcycle created"),
            @ApiResponse(code = 400, message = "Bad request body"),
            @ApiResponse(code = 404, message = "Motorcycle not created")
    })
    @PostMapping("/motorcycles")
    public ResponseEntity<Motorcycle> createMotorcycle(@RequestBody CreateMotorcycleRequest request) {
        return garageService.createMotorcycle(request.getMake(),request.getModel(),request.getYear());

    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Update a motorcycle"),
            @ApiResponse(code = 400, message = "Bad request body"),
            @ApiResponse(code = 404, message = "Motorcycle not found")
    })
    @PutMapping("/motorcycles/{id}")
    public Motorcycle updateMotorcycleInfo(
            @PathVariable("id") String id,
            @RequestBody UpdateMotorcycleRequest request
    ) throws Exception{
        try {
            return garageService.updateMotorcycle(UUID.fromString(id), request.getMake(), request.getModel(), request.getYear(), request.isNeedFixing());
        }catch(Exception ex){
            throw ex;
        }
    }

    @ApiResponses(value = {
            @ApiResponse(code = 204,message = "Motorcycle deleted"),
            @ApiResponse(code = 404, message = "Not found")
    })
    @DeleteMapping("/motorcycles/{id}")
    public ResponseEntity<Motorcycle> deleteMotorcycleInfo(
            @PathVariable("id") String id
    ) throws Exception{
        garageService.deleteMotorcycle(UUID.fromString(id));
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

}
