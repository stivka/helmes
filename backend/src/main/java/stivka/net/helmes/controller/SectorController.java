package stivka.net.helmes.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import stivka.net.helmes.model.Sector;
import stivka.net.helmes.service.SectorService;

@RestController
@RequestMapping("/api/sectors")
public class SectorController {

    private SectorService sectorService;

    public SectorController(SectorService sectorService) {
        this.sectorService = sectorService;
    }

    @GetMapping
    public List<Sector> getAllSectors() {
        return sectorService.getAllSectors();
    }
}
