package guru.microservices.mssc_beer_service.service.impl;

import guru.microservices.mssc_beer_service.domain.Beer;
import guru.microservices.mssc_beer_service.repositories.IBeerRepository;
import guru.microservices.mssc_beer_service.service.BeerService;
import guru.microservices.mssc_beer_service.web.mapper.BeerMapper;
import guru.microservices.mssc_beer_service.web.model.BeerDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BeerServiceImpl implements BeerService {

    private final IBeerRepository beerRepository;
    private final BeerMapper beerMapper;

    @Override
    public BeerDTO getBeerById(UUID beerId) throws ChangeSetPersister.NotFoundException {
        return beerMapper.beerToDto(beerRepository.findById(beerId).orElseThrow(ChangeSetPersister.NotFoundException::new));
    }

    @Override
    public BeerDTO updateBeer(UUID beerId, BeerDTO beerDTO) throws ChangeSetPersister.NotFoundException {
        Beer beer = beerRepository.findById(beerId).orElseThrow(ChangeSetPersister.NotFoundException::new);
        beer.setBeerName(beerDTO.getBeerName());
        beer.setBeerStyle(beerDTO.getBeerStyle().name());
        beer.setPrice(beerDTO.getPrice());
        beer.setUpc(beerDTO.getUpc());
        return beerMapper.beerToDto(beerRepository.save(beer));
    }

    @Override
    public void deleteBeer(UUID beerId) {
        beerRepository.deleteById(beerId);
    }

    @Override
    public BeerDTO saveNewBeer(BeerDTO beerDTO) {
        return beerMapper.beerToDto(beerRepository.save(beerMapper.dtoToEntity(beerDTO)));
    }
}
