package stivka.net.helmes.initializer;

import java.util.List;

import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import stivka.net.helmes.model.Sector;
import stivka.net.helmes.parser.HtmlParser;
import stivka.net.helmes.service.SectorService;

@Component
public class DataInitializer {

    private final HtmlParser htmlParser;
    private final SectorService sectorService;

    public DataInitializer(HtmlParser htmlParser, SectorService sectorService) {
        this.htmlParser = htmlParser;
        this.sectorService = sectorService;
    }

    @PostConstruct
    public void init() throws Exception {
        List<Sector> sectors = htmlParser.parseHtml("index original.html");
        sectors.forEach(sectorService::saveSector);
    }
}