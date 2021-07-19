package com.example.coffeehouse.repositories;

import com.example.coffeehouse.models.converters.CupCoefficients;
import org.springframework.data.repository.CrudRepository;

public interface CupCoefficientsRepository extends CrudRepository<CupCoefficients, String> {
    public CupCoefficients findByKind(String kind);
}
