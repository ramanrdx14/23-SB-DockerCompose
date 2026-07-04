package org.example.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Drinks {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer drinkId;
    String drinkName;
    Double drinkCost;
    String drinkQuantity;

    public Integer getDrinkId() {
        return drinkId;
    }

    public void setDrinkId(Integer drinkId) {
        this.drinkId = drinkId;
    }

    public String getDrinkName() {
        return drinkName;
    }

    public void setDrinkName(String drinkName) {
        this.drinkName = drinkName;
    }

    public Double getDrinkCost() {
        return drinkCost;
    }

    public void setDrinkCost(Double drinkCost) {
        this.drinkCost = drinkCost;
    }

    public String getDrinkQuantity() {
        return drinkQuantity;
    }

    public void setDrinkQuantity(String drinkQuantity) {
        this.drinkQuantity = drinkQuantity;
    }
}
