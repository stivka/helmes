package stivka.net.helmes.service;

import java.util.List;

import org.springframework.stereotype.Service;

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

    public Sector saveSector(Sector sector) {
        return sectorRepository.save(sector);
    }
}
