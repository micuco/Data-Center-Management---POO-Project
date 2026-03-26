package org.example;

public class Location {
    private String country;
    private String city;
    private String address;
    private Double latitude;
    private Double longitude;

    public Location(String country, String city, String address, Double latitude, Double longitude) throws LocationException {
        if (country.isEmpty()) {
            throw new LocationException();
        }

        this.country = country;
        this.city = city;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public String getAddress() {
        return address;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

}
