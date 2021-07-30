package com.bytework.repository;

import com.bytework.model.ServiceProvider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceProviderRepo extends JpaRepository<ServiceProvider,Long> {

    List<ServiceProvider> findByCity(String city);



}
