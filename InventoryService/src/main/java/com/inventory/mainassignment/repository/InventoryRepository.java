package com.inventory.mainassignment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inventory.mainassignment.models.Inventory;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {

}
