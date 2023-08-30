package stivka.net.helmes.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import stivka.net.helmes.model.Sector;

@SpringBootTest
public class HtmlParserTest {

    private static final String HTML_PATH = "index original.html";

    private static HtmlParser htmlParser;

    private static List<Sector> sectors;

    @BeforeAll
    static void setUp(ApplicationContext applicationContext) {
        htmlParser = (HtmlParser) applicationContext.getBean(HtmlParser.class);
        sectors = htmlParser.parseHtml(HTML_PATH);
    }

    @Test
    public void testFileNotFound() {
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            htmlParser.parseHtml("nonexistent.html");
        });
        assertEquals("Resource file not found: nonexistent.html", exception.getMessage());
    }

    @Test
    public void testFileReadingAndParsing() {
        List<Sector> sectors = htmlParser.parseHtml(HTML_PATH);
        assertNotNull(sectors);
        assertFalse(sectors.isEmpty());
    }

    @Test
    void parseSectorWithZeroDepth() {
        int expectedDepthLevel = 0;
        int expectedHtmlId = 1;
        String expectedName = "Manufacturing";
        Sector expectedParent = null;

        Optional<Sector> sectorOpt = sectors.stream()
                .filter(s -> expectedName.equals(s.getName()))
                .findFirst();

        assertTrue(sectorOpt.isPresent());

        Sector sector = sectorOpt.get();
        assertEquals(expectedDepthLevel, sector.getDepthLevel());
        assertEquals(expectedHtmlId, sector.getHtmlId());
        assertEquals(expectedName, sector.getName());
        assertEquals(expectedParent, sector.getParent());
    }

    @Test
    void parseSectorWithMaxDepth() {
        int expectedDepthLevel = 3;
        int expectedHtmlId = 53;
        String expectedName = "Plastics welding and processing";
        String expectedParentName = "Plastic processing technology";

        Optional<Sector> sectorOpt = sectors.stream()
                .filter(s -> expectedName.equals(s.getName()))
                .findFirst();

        assertTrue(sectorOpt.isPresent());

        Sector sector = sectorOpt.get();
        assertEquals(expectedDepthLevel, sector.getDepthLevel());
        assertEquals(expectedHtmlId, sector.getHtmlId());
        assertEquals(expectedName, sector.getName());
        assertEquals(expectedParentName, sector.getParent().getName());
    }
}
