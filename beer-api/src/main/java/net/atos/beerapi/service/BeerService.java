package net.atos.beerapi.service;

import net.atos.beerapi.dto.BeerDTO;
import net.atos.beerapi.helpers.FieldFormatter;
import net.atos.beerapi.model.Beer;
import net.atos.beerapi.repository.BeerRepository;
import net.atos.beerapi.validation.BeerValidation;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BeerService implements IBeerService {

    private final BeerRepository beerRepository;
    private final List<BeerValidation> beerValidations;
    private final FieldFormatter fieldFormatter;

    public BeerService(BeerRepository beerRepository, List<BeerValidation> beerValidations, FieldFormatter fieldFormatter) {
        this.beerRepository = beerRepository;
        this.beerValidations = beerValidations;
        this.fieldFormatter = fieldFormatter;
    }

    @Override
    public List<BeerDTO> getAllBeers() {
        List<Beer> beers = beerRepository.findAll();
        List<BeerDTO> beerDTOs = new ArrayList<>();
        for (Beer beer : beers) {
            beerDTOs.add(new BeerDTO(beer));
        }
        return beerDTOs;
    }

    @Override
    public BeerDTO getBeerById(Long id) {
        Optional<Beer> optionalBeer = beerRepository.findById(id);
        if (optionalBeer.isPresent()) {
            return new BeerDTO(optionalBeer.get());
        } else {
            return null;
        }
    }

    @Override
    public BeerDTO saveBeer(BeerDTO beerDTO) {
        for (BeerValidation validation : beerValidations) {
            if (!validation.validate(beerDTO)) {
                return null;
            }
        }

        Beer savedBeer = beerRepository.save(new Beer(beerDTO));
        return new BeerDTO(savedBeer);
    }

    @Override
    public void deleteBeer(Long id) {
        beerRepository.deleteById(id);
    }
}
