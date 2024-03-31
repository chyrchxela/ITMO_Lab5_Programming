package Classes;

import java.util.Date;
import ReadWriteToXML.*;
public class Organization {
    private int id; //3начение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private java.util.Date creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Integer annualTurnover; //Поле не может быть null, Значение поля должно быть больше 0
    private OrganizationType type; //Поле может быть null
    private Address officialAddress; //Поле не может быть null

    Organization(int id, Coordinates coordinates, java.util.Date creationDate, Integer annualTurnover, OrganizationType type, Address officialAddress){
        this.id = genUniqueId();
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.type = type;
        this.officialAddress = officialAddress;
        this.annualTurnover = annualTurnover;
    }
    private int genUniqueId(){return (int) (System.currentTimeMillis() + idCounter++);}
    private static int idCounter = 1;
    @Override
    public int hashCode(){return id;}

    public int getId(){return id;}
    public Coordinates getCoordinates(){return coordinates;}
    public java.util.Date getCreationDate(){return creationDate;}
    public Integer getAnnualTurnover(){return annualTurnover;}
    public OrganizationType getType(){return type;}
    public Address getOfficialAddress(){return officialAddress;}

    public void setCoordinates(Coordinates coordinates){this.coordinates = coordinates;}
    public void setOrganizationType(OrganizationType type){this.type = type;}
    public void setAnnualTurnover(Integer annualTurnover){this.annualTurnover = annualTurnover;}
    public void setOfficialAddress(Address officialAddress){this.officialAddress = officialAddress;}
}