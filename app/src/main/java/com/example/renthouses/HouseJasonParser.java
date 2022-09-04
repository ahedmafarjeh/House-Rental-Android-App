package com.example.renthouses;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;
public class HouseJasonParser {
    public static List<House> getObjectFromJason(String jason) {
        List<House> houses ;
        try {
            JSONArray jsonArray = new JSONArray(jason);
            houses = new ArrayList<>();
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = new JSONObject();
                jsonObject = (JSONObject) jsonArray.get(i);
                /*Student student = new Student();
                student.setID(jsonObject.getInt("id"));
                student.setName(jsonObject.getString("name"));
                student.setAge(jsonObject.getDouble("age"));
                students.add(student);*/
                House house = new House();
                house.setOwner_email(jsonObject.getString("owner_email"));
                house.setCity(jsonObject.getString("city"));
                house.setPostal_address(jsonObject.getString("postal_adress"));
                house.setSurface_area(jsonObject.getInt("surface_area"));
                house.setConstruction_year(jsonObject.getInt("construction_year"));
                house.setBedroom_number(jsonObject.getInt("bedroom_number"));
                house.setRental_price(jsonObject.getInt("rental_price"));
                house.setStatus(jsonObject.getString("status"));
                house.setAvailability_date(jsonObject.getString("availability_date"));
                house.setDescription(jsonObject.getString("description"));
                house.setGarden(jsonObject.getBoolean("garden"));
                house.setBalcony(jsonObject.getBoolean("balcony"));
                house.setImg(jsonObject.getString("img"));
                houses.add(house);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return houses;
    }
}
