package net.atos.beerapi.validation.types;

import net.atos.beerapi.dto.BeerDTO;
import net.atos.beerapi.exception.ValidacaoException;
import net.atos.beerapi.repository.BeerRepository;
import net.atos.beerapi.validation.BeerValidation;
import org.springframework.stereotype.Component;

@Component
public class UniqueBeerName implements BeerValidation {

    private final BeerRepository beerRepository;

    public UniqueBeerName(BeerRepository beerRepository) {
        this.beerRepository = beerRepository;
    }

    @Override
    public boolean validate(BeerDTO beerDTO) {
        if (beerRepository.findByName(beerDTO.getName())) {
            throw new ValidacaoException("Beer with the same name already exists!");
        }
        return false;
    }
}
