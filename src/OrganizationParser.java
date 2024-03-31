package ReadWriteToXML;

import Exceptions.InvalidOrganizationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import Classes.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.xml.sax.InputSource;
import java.io.*;
import java.util.ArrayList;
import java.util.Date;
public class OrganizationParser {
    public static ArrayList<Organization> parse(String filename) throws InvalidOrganizationException {
        ArrayList<Organization> organizations = new ArrayList<>();

        try {
            InputStream is = OrganizationParser.class.getResourceAsStream(filename);
            InputStreamReader isr = new InputStreamReader(is, "UTF-8");

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            InputSource inputSource = new InputSource(isr);
            Document document = builder.parse(inputSource);

            NodeList organizationNodes = document.getElementsByTagName("organization");

            for (int i = 0; i < organizationNodes.getLength(); i++) {
                Element organizationElement = (Element) organizationNodes.item(i);
                Organization organization = parseOrganizationElement(organizationElement);
                organizations.add(organization);
            }

            isr.close();
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return organizations;
    }

    private static Organization parseOrganizationElement(Element organizationElement) throws InvalidOrganizationException {
        int id = Integer.parseInt(organizationElement.getAttribute("id"));
        Coordinates coordinates = parseCoordinatesElement((Element)organizationElement.getElementsByTagName("coordinates").item(0));
        Date creationDate = new Date(Long.parseLong(organizationElement.getElementsByTagName("creationDate").item(0).getTextContent()));
        Integer annualTurnover = Integer.parseInt(organizationElement.getElementsByTagName("annualTurnover").item(0).getTextContent());
        OrganizationType type = parseOrganizationType(organizationElement.getElementsByTagName("type").item(0).getTextContent());
        Address officialAddress = parseAddressElement((Element) organizationElement.getElementsByTagName("officialAddress").item(0));

        if (coordinates == null || annualTurnover == null || officialAddress == null) {
            throw new InvalidOrganizationException("Invalid organization data");
        }

        return new Organization(id, coordinates, creationDate, annualTurnover, type, officialAddress);
    }

    private static Coordinates parseCoordinatesElement(Element coordinatesElement) {
        Long x = Long.parseLong(coordinatesElement.getElementsByTagName("x").item(0).getTextContent());
        Long y = Long.parseLong(coordinatesElement.getElementsByTagName("y").item(0).getTextContent());
        return new Coordinates(x, y);
    }

    private static Address parseAddressElement(Element addressElement) {
        String street = addressElement.getElementsByTagName("street").item(0).getTextContent();
        return new Address(street);
    }

    private static OrganizationType parseOrganizationType(String typeString) {
        if (typeString == null || typeString.isEmpty()) {
            return null;
        }
        return OrganizationType.valueOf(typeString.toUpperCase());
    }
}
