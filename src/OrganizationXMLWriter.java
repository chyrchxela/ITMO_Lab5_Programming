package ReadWriteToXML;
import Classes.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class OrganizationXMLWriter {
    public static void writeToFile(ArrayList<Organization> organizations, String filename) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
            writer.write("<organizations>\n");

            for (Organization org : organizations) {
                writer.write("    <organization id=\"" + org.getId() + "\">\n");
                writer.write("        <coordinates>\n");
                writer.write("            <x>" + org.getCoordinates().getX() + "</x>\n");
                writer.write("            <y>" + org.getCoordinates().getY() + "</y>\n");
                writer.write("        </coordinates>\n");
                writer.write("        <creationDate>" + org.getCreationDate().getTime() + "</creationDate>\n");
                writer.write("        <annualTurnover>" + org.getAnnualTurnover() + "</annualTurnover>\n");
                writer.write("        <type>" + (org.getType() != null ? org.getType().name() : "") + "</type>\n");
                writer.write("        <officialAddress>\n");
                writer.write("            <street>" + org.getOfficialAddress().getStreet() + "</street>\n");
                writer.write("        </officialAddress>\n");
                writer.write("    </organization>\n");
            }

            writer.write("</organizations>");
            writer.close();
            System.out.println("Данные успешно записаны в файл: " + filename);
        } catch (IOException e) {
            System.out.println("Ошибка при записи данных в файл: " + e.getMessage());
        }
    }
}
