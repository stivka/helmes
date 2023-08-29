package stivka.net.helmes.parser;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import stivka.net.helmes.model.Sector;

@Component
public class HtmlParser {

    public List<Sector> parseHtml(String resourcePath) {
        List<Sector> sectors = new ArrayList<>();

        InputStream is = getClass().getClassLoader().getResourceAsStream(resourcePath);
        if (is == null) {
            throw new RuntimeException("Resource file not found: " + resourcePath);
        }

        try {
            Document doc = Jsoup.parse(is, "UTF-8", "");
            Elements options = doc.select("option");

            for (Element option : options) {
                int id = Integer.parseInt(option.attr("value"));
                String name = option.text();

                String innerHtml = option.html();
                int depthLevel = countIndentation(innerHtml);

                Sector parent = determineParent(depthLevel, sectors);

                Sector sector = new Sector();
                sector.setHtmlId(id);
                sector.setName(name);
                sector.setDepthLevel(depthLevel);
                sector.setParent(parent);

                sectors.add(sector);
            }
        } catch (IOException e) {
            throw new RuntimeException("Error parsing HTML from: " + resourcePath, e);
        }

        return sectors;
    }

    private Sector determineParent(int currentDepthLevel, List<Sector> sectors) {
        if (currentDepthLevel <= 0 || sectors.isEmpty()) {
            return null;
        }

        for (int i = sectors.size() - 1; i >= 0; i--) {
            if (sectors.get(i).getDepthLevel() == currentDepthLevel - 1) {
                return sectors.get(i);
            }
        }

        return null;
    }

    private int countIndentation(String name) {
        int count = 0;
        int index = 0;
        String target = "&nbsp;";

        while ((index = name.indexOf(target, index)) != -1) {
            count++;
            index += target.length(); // Move to the end of the found target to continue searching
        }
        
        return count / 4;
    }
}
