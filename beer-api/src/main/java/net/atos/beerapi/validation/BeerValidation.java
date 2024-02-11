package net.atos.beerapi.validation;

import net.atos.beerapi.dto.BeerDTO;

public interface BeerValidation {
    boolean validate(BeerDTO beerDTO);
}
