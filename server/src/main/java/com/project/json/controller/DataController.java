package com.project.json.controller;

import com.project.json.dto.ComponentDto;
import com.project.json.dto.ScreenDto;
import com.project.json.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/db")
@CrossOrigin
public class DataController {

    @Autowired
    private DataService dataService;

    @GetMapping("/allScreens")
    public ResponseEntity<List<ScreenDto>> getScreens(){
        return ResponseEntity.ok(dataService.getAllScreens());
    }

    @GetMapping("/allComponents")
    public ResponseEntity<List<ComponentDto>> getComponents(){
        return ResponseEntity.ok(dataService.getAllComponents());
    }

    @GetMapping("/screen/{id}")
    public ResponseEntity<ScreenDto> getScreen(@PathVariable String id){
        return ResponseEntity.ok(dataService.getScreen(id));
    }

    @GetMapping("/component/{id}")
    public ResponseEntity<ComponentDto> getComponent(@PathVariable String id){
        return ResponseEntity.ok(dataService.getComponent(id));
    }

    @PostMapping("/screen")
    public void saveScreen(@RequestBody ScreenDto screenDto){
        System.out.println(screenDto);
        dataService.saveScreen(screenDto);
    }


    @PostMapping("/component")
    public void saveComponent(@RequestBody ComponentDto componentDto){
        dataService.saveComponent(componentDto);
    }

    @DeleteMapping("/screen/{id}")
    public void deleteScreen(@PathVariable String id){
        dataService.deleteScreen(id);
    }

    @DeleteMapping("/component/{id}")
    public void deleteComponent(@PathVariable String id){
        dataService.deleteComponent(id);
    }
}
