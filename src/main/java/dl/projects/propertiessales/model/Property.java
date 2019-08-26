package dl.projects.propertiessales.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document
public class Property {

    @Id
    private String keyId;

    private String  type;
    private String  city;
    private String  address;
    private String addressNum;
    private String desc;
    private LocalDate acquisitionDate;
    private LocalDate adDate;
    private Double numOfRooms;
    private Double sqrMeter;

    public Property(String keyId, String type, String city, String address, String addressNum, String desc, LocalDate acquisitionDate, LocalDate adDate, Double numOfRooms, Double sqrMeter, Double price, Integer floor,String buildingFloors, boolean isMediation) {
        this.keyId = keyId;
        this.type = type;
        this.city = city;
        this.address = address;
        this.addressNum = addressNum;
        this.desc = desc;
        this.acquisitionDate = acquisitionDate;
        this.adDate = adDate;
        this.numOfRooms = numOfRooms;
        this.sqrMeter = sqrMeter;
        this.price = price;
        this.floor = floor;
        this.buildingFloors =buildingFloors;
        this.isMediation = isMediation;
    }

    private Double price;
    private Integer floor;
    private String buildingFloors;
    private boolean isMediation;



    public String getKeyId() {
        return keyId;
    }

    public void setKeyId(String keyId) {
        this.keyId = keyId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddressNum() {
        return addressNum;
    }

    public void setAddressNum(String addressNum) {
        this.addressNum = addressNum;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public LocalDate getAcquisitionDate() {
        return acquisitionDate;
    }

    public void setAcquisitionDate(LocalDate acquisitionDate) {
        this.acquisitionDate = acquisitionDate;
    }

    public LocalDate getAdDate() {
        return adDate;
    }

    public void setAdDate(LocalDate adDate) {
        this.adDate = adDate;
    }

    public Double getNumOfRooms() {
        return numOfRooms;
    }

    public void setNumOfRooms(Double numOfRooms) {
        this.numOfRooms = numOfRooms;
    }

    public Double getSqrMeter() {
        return sqrMeter;
    }

    public void setSqrMeter(Double sqrMeter) {
        this.sqrMeter = sqrMeter;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getFloor() {
        return floor;
    }

    public void setFloor(Integer floor) {
        this.floor = floor;
    }

    public boolean isMediation() {
        return isMediation;
    }

    public void setMediation(boolean mediation) {
        isMediation = mediation;
    }
private String filteredStreetName;
    private String filteredCityName;

    public String getFilteredStreetName() {
        return filteredStreetName;
    }

    public void setFilteredStreetName(String filteredStreetName) {
        this.filteredStreetName = filteredStreetName;
    }

    public String getFilteredCityName() {
        return filteredCityName;
    }

    public void setFilteredCityName(String filteredCityName) {
        this.filteredCityName = filteredCityName;
    }
}
