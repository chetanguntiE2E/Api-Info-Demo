package com.example.application.data.repository;
import com.example.application.data.entity.Apitype;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApitypeRepository extends JpaRepository<Apitype, Integer> {

}