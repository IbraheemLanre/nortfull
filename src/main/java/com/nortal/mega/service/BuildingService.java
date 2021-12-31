package com.nortal.mega.service;

import com.nortal.mega.persistence.BuildingDboMapper;
import com.nortal.mega.persistence.BuildingRepository;
import com.nortal.mega.rest.BuildingDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BuildingService{


    private final BuildingRepository buildingRepository;
    private final BuildingDboMapper buildingDboMapper;


    public List<Building> findAll() {
        return buildingRepository.findAll().stream().map(buildingDboMapper::map).collect(Collectors.toList());
    }

    public Building findBuildingById(Long id) {
        return buildingDboMapper.map(buildingRepository.findById(id).orElseThrow());
    }

    public Building save(Building building) {

        Building newBuilding = new Building();

        if (!newBuilding.getIndex().startsWith("NO")){
            System.out.println(newBuilding.toString());
            System.out.println("Index has to start with NO");
        }

        if(newBuilding.getEnergyUnits()>50){
            System.out.println("this building can stake only a maximum of 50 units");
        }

        buildingRepository.save(buildingDboMapper.map(newBuilding));

        return newBuilding;
    }

    public void update(Building building, Long id){
        Building newBuilding = buildingDboMapper.map(buildingRepository.findById(id).orElseThrow());

        if (!newBuilding.getIndex().startsWith("NO")){
            System.out.println(newBuilding.toString());
            System.out.println("Index has to start with NO");
        }

        if(newBuilding.getEnergyUnits()>50){
            System.out.println("this building can stake only a maximum of 50 units");
        }

        newBuilding.setName(building.getName());
        newBuilding.setAddress(building.getAddress());
        newBuilding.setEnergyUnits(building.getEnergyUnits());
        newBuilding.setIndex(building.getIndex());

        buildingRepository.save(buildingDboMapper.map(newBuilding));

    }




}
