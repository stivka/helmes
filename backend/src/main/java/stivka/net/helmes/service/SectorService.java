package stivka.net.helmes.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import stivka.net.helmes.exception.ResourceNotFoundException;
import stivka.net.helmes.model.Sector;
import stivka.net.helmes.repository.SectorRepository;

@Service
public class SectorService {

    private SectorRepository sectorRepository;

    public SectorService(SectorRepository sectorRepository) {
        this.sectorRepository = sectorRepository;
    }

    public List<Sector> getAllSectors() {
        return sectorRepository.findAll();
    }

    public Sector getSector(int htmlId) {
        Optional<Sector> optionalSector = sectorRepository.findByHtmlId(htmlId);
        if (optionalSector.isPresent()) {
            return optionalSector.get();
        } else {
            throw new ResourceNotFoundException("Sector not found with htmlId " + htmlId);
        }
    }

    public Sector saveSector(Sector sector) {
        return sectorRepository.save(sector);
    }
}
