package net.atos.beerapi.validation.types;

import net.atos.beerapi.dto.BeerDTO;
import net.atos.beerapi.enums.BeerType;
import net.atos.beerapi.exception.ValidacaoException;
import net.atos.beerapi.validation.BeerValidation;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class PossibleBeerTypes implements BeerValidation {

    private static final Set<BeerType> VALID_BEER_TYPES = new HashSet<>(
            Arrays.asList(BeerType.values()));

    @Override
    public boolean validate(BeerDTO beerDTO) {
        try {
            BeerType beerType = BeerType.valueOf(beerDTO.getType().toUpperCase());
            if (!VALID_BEER_TYPES.contains(beerType)) {
                throw new ValidacaoException("Invalid beer type!");
            } else {
                return true;
            }
        } catch (IllegalArgumentException ex) {
            throw new ValidacaoException("Invalid beer type!");
        }
    }

}
