package net.atos.beerapi.service;

import net.atos.beerapi.dto.BeerDTO;

import java.util.List;

public interface IBeerService {

    List<BeerDTO> getAllBeers();

    BeerDTO getBeerById(Long id);

    BeerDTO saveBeer(BeerDTO beerDTO);

    void deleteBeer(Long id);
}
