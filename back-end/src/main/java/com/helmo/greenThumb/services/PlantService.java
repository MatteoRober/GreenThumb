package com.helmo.greenThumb.services;
import com.helmo.greenThumb.infrastructures.PlantRepository;
import com.helmo.greenThumb.infrastructures.VarietyRepository;
import com.helmo.greenThumb.model.Plant;
import com.helmo.greenThumb.model.Variety;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PlantService {

    private final PlantRepository plantRepository;
    private final VarietyRepository varietyRepository;
    public PlantService(PlantRepository plantRepository, VarietyRepository varietyRepository) {
        this.plantRepository = plantRepository;
        this.varietyRepository = varietyRepository;
    }

    public List<Plant> getAllPlants(String uid) {
        return plantRepository.findAllByOwnerUid(uid);
    }

    public Plant addOrUpdatePlant(Plant plant) {

        // Récupère la plante existante si elle existe
        Optional<Plant> existingPlantOpt = plantRepository.findById(plant.getId());

        if (existingPlantOpt.isPresent()) {
            Plant existingPlant = existingPlantOpt.get();

            // Si le nom de la variété a changé, crée une nouvelle variété
            if (!existingPlant.getVariety().getName().equals(plant.getVariety().getName())) {
                Optional<Variety> existingVariety = varietyRepository.findByName(plant.getVariety().getName());

                if (existingVariety.isPresent()) {
                    // Utilise la variété existante si elle existe déjà
                    plant.setVariety(existingVariety.get());
                } else {
                    // Crée une nouvelle variété si elle n'existe pas
                    Variety newVariety = new Variety();
                    newVariety.setName(plant.getVariety().getName());
                    varietyRepository.save(newVariety);
                    plant.setVariety(newVariety);
                }
            } else {
                // Si la variété n'a pas changé, garde la même
                plant.setVariety(existingPlant.getVariety());
            }
        } else {
            // Lors de la création d'une nouvelle plante
            Optional<Variety> existingVariety = varietyRepository.findByName(plant.getVariety().getName());

            if (existingVariety.isPresent()) {
                plant.setVariety(existingVariety.get());
            } else {
                Variety newVariety = new Variety();
                newVariety.setName(plant.getVariety().getName());
                varietyRepository.save(newVariety);
                plant.setVariety(newVariety);
            }
        }

        // Sauvegarde la plante avec la nouvelle ou ancienne variété
        return plantRepository.save(plant);
    }


    public Optional<Plant> getPlantById(Long id) {
        return plantRepository.findById(id);
    }

    public void deletePlant(Long id) {
        plantRepository.deleteById(id);
    }
}
