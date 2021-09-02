package com.project.json.service;

import com.project.json.dto.ComponentDto;
import com.project.json.dto.ScreenDto;
import com.project.json.repository.ComponentRepository;
import com.project.json.repository.ScreenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class DataService {

    @Autowired
    private ScreenRepository screenRepository;
    @Autowired
    private ComponentRepository componentRepository;

    public List<ScreenDto> getAllScreens(){
        return screenRepository.findAll();
    }

    public List<ComponentDto> getAllComponents(){
        return componentRepository.findAll();
    }

    public void saveScreen(ScreenDto screenDto){
        screenRepository.save(screenDto);
    }

    public void saveComponent(ComponentDto componentDto){
        componentRepository.save(componentDto);
    }

    public ScreenDto getScreen(String id){
        return screenRepository.findById(id).orElse(null);
    }

    public ComponentDto getComponent(String id){
        return componentRepository.findById(id).orElse(null);
    }

    public void deleteScreen(String id){
        screenRepository.deleteById(id);
    }

    public void deleteComponent(String id){
        componentRepository.deleteById(id);
    }
}
