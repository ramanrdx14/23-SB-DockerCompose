package org.example.service;

import org.example.entity.Drinks;

import java.util.List;

public interface DrinksService {
    public List<Drinks> getAllDrinks();
    public void addDrinks(Drinks drinks);
}
