package net.atos.beerapi.model;

import jakarta.persistence.*;
import lombok.Data;
import net.atos.beerapi.dto.BeerDTO;
import net.atos.beerapi.enums.BeerType;

@Data
@Entity
public class Beer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private BeerType type;

    private double alcoholContent;

    public Beer(BeerDTO beerDTO) {
        this.name = beerDTO.getName();
        this.type = beerDTO.getType();
        this.alcoholContent = beerDTO.getAlcoholContent();
    }
}
