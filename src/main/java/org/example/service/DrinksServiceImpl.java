package org.example.service;

import org.example.entity.Drinks;
import org.example.repo.DrinksRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class DrinksServiceImpl implements DrinksService{
    @Autowired
    DrinksRepo repo;
    @Override
    public List<Drinks> getAllDrinks() {
        return repo.findAll();
    }

    @Override
    public void addDrinks(Drinks drinks) {
        repo.save(drinks);
    }
}
