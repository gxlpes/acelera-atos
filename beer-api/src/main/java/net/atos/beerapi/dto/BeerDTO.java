package net.atos.beerapi.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import net.atos.beerapi.enums.BeerType;
import net.atos.beerapi.model.Beer;

public class BeerDTO {

    private Long id;

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Type is required")
    @Enumerated(EnumType.STRING)
    private BeerType type;

    @Positive(message = "Alcohol content must be positive")
    @NotNull(message = "Alcohol content is required")
    private double alcoholContent;

    public BeerDTO(Beer beer) {
        this.id = beer.getId();
        this.name = beer.getName();
        this.type = beer.getType();
        this.alcoholContent = beer.getAlcoholContent();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BeerType getType() {
        return type;
    }

    public void setType(BeerType type) {
        this.type = type;
    }

    public double getAlcoholContent() {
        return alcoholContent;
    }

    public void setAlcoholContent(double alcoholContent) {
        this.alcoholContent = alcoholContent;
    }

    // toString() method
    @Override
    public String toString() {
        return "BeerDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", alcoholContent=" + alcoholContent +
                '}';
    }
}
