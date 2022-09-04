package com.example.renthouses;
public class House {
   private String owner_email;
   private String city;
   private String postal_address;
   private int surface_area;
   private int construction_year;
   private int bedroom_number;
   private int rental_price;
   private String status;
   private String availability_date;
   private String description;
   private boolean garden;
   private boolean balcony;
   private String img;

    public String getOwner_email() {
        return owner_email;
    }

    public void setOwner_email(String owner_email) {
        this.owner_email = owner_email;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostal_address() {
        return postal_address;
    }

    public void setPostal_address(String postal_address) {
        this.postal_address = postal_address;
    }

    public int getSurface_area() {
        return surface_area;
    }

    public void setSurface_area(int surface_area) {
        this.surface_area = surface_area;
    }

    public int getConstruction_year() {
        return construction_year;
    }

    public void setConstruction_year(int construction_year) {
        this.construction_year = construction_year;
    }

    public int getBedroom_number() {
        return bedroom_number;
    }

    public void setBedroom_number(int bedroom_number) {
        this.bedroom_number = bedroom_number;
    }

    public int getRental_price() {
        return rental_price;
    }

    public void setRental_price(int rental_price) {
        this.rental_price = rental_price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAvailability_date() {
        return availability_date;
    }

    public void setAvailability_date(String availability_date) {
        this.availability_date = availability_date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImg() {
        return img;
    }

    public boolean isGarden() {
        return garden;
    }

    public void setGarden(boolean garden) {
        this.garden = garden;
    }

    public boolean isBalcony() {
        return balcony;
    }

    public void setBalcony(boolean balcony) {
        this.balcony = balcony;
    }

    @Override
    public String toString() {
        return "House{" +
                "owner_email='" + owner_email + '\'' +
                ", city='" + city + '\'' +
                ", postal_address='" + postal_address + '\'' +
                ", surface_area=" + surface_area +
                ", construction_year=" + construction_year +
                ", bedroom_number=" + bedroom_number +
                ", rental_price=" + rental_price +
                ", status='" + status + '\'' +
                ", availability_date='" + availability_date + '\'' +
                ", description='" + description + '\'' +
                ", garden=" + garden +
                ", balcony=" + balcony +
                ", img='" + img + '\'' +
                '}';
    }

    public void setImg(String img) {
        this.img = img;
    }
}
