package guru.microservices.mssc_beer_service.service;

import guru.microservices.mssc_beer_service.web.model.BeerDTO;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.UUID;

public interface BeerService {
    BeerDTO getBeerById(UUID beerId) throws ChangeSetPersister.NotFoundException;

    BeerDTO updateBeer(UUID beerId, BeerDTO beerDTO) throws ChangeSetPersister.NotFoundException;

    void deleteBeer(UUID beerId);

    BeerDTO saveNewBeer(BeerDTO beerDTO);
}
