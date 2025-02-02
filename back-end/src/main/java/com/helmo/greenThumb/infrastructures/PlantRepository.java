package com.helmo.greenThumb.infrastructures;


import com.helmo.greenThumb.model.Plant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlantRepository extends JpaRepository<Plant, Long> {
    List<Plant> findAllByOwnerUid(String uid);
}

