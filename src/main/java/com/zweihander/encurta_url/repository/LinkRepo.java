package com.zweihander.encurta_url.repository;

import com.zweihander.encurta_url.model.LinkModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LinkRepo extends JpaRepository<LinkModel, Integer> {
    Optional<LinkModel> findByCodigoUrl(String codigo);
    Optional<LinkModel>findByUrlOriginal(String original);
}
