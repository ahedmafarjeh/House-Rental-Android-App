package com.example.renthouses;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.util.Log;
import android.widget.SimpleCursorAdapter;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class DataBaseHelper extends SQLiteOpenHelper {
    public DataBaseHelper(Context context){
        super(context, "RentHousesProject", null, 2);

    }
    @Override
    public void onCreate(SQLiteDatabase db) {
       // sqLiteDatabase.execSQL("CREATE TABLE LOGIN(ID INTEGER PRIMARY KEY AUTOINCREMENT,EMAIL TEXT, PASSWORD TEXT)");
        db.execSQL("CREATE TABLE TENANT_USER(EMAIL TEXT PRIMARY KEY ,F_NAME TEXT, L_NAME TEXT," +
                "GENDER TEXT,PASSWORD TEXT,NATIONALITY TEXT,SALARY INTEGER,OCCUPATION TEXT" +
                ",FAMILY_SIZE INTEGER ,COUNTRY TEXT,CITY TEXT,PHONE_NUM TEXT)");
       db.execSQL("CREATE TABLE RENTING_AGENCY_USER(EMAIL TEXT PRIMARY KEY ,AGENCY_NAME TEXT," +
                "PASSWORD TEXT,COUNTRY TEXT,CITY TEXT,PHONE_NUM TEXT)");
        db.execSQL("CREATE TABLE HOUSE_Info(_id INTEGER PRIMARY KEY AUTOINCREMENT,owner_email TEXT,city TEXT,postal_adress TEXT,surface_area INTEGER,IMAGE BLOB" +
                ",construction_year INTEGER,bedroom_number INTEGER,rental_price INTEGER,status TEXT,availability_date TEXT" +
                ",description TEXT,garden BOOLEAN,balcony BOOLEAN,FOREIGN KEY (owner_email)\n" +
                "       REFERENCES RENTING_AGENCY_USER (EMAIL))");
        db.execSQL("CREATE TABLE Notification(ag_email Text ,TEN_EMAIL TEXT,_id INTEGER ,FOREIGN KEY (ag_email)REFERENCES RENTING_AGENCY_USE (EMAIL),FOREIGN KEY (_id)REFERENCES HOUSE_Info (_id)," +
                "FOREIGN KEY (TEN_EMAIL)REFERENCES TENANT_USER (EMAIL))");
        db.execSQL("CREATE TABLE TENANT_HOUSE(_id INTEGER,TEN_EMAIL TEXT,city TEXT,postalAdd TEXT,agncy_email TEXT ,FOREIGN KEY (_id)REFERENCES HOUSE_Info (_id)," +
                "FOREIGN KEY (TEN_EMAIL)REFERENCES TENANT_USER (EMAIL))");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public boolean insert_RentingAgency_User(String email,String agency_name,String password,String country,String city ,String phone_num) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("EMAIL",email);
        contentValues.put("AGENCY_NAME", agency_name);
        contentValues.put("PASSWORD", password);
        contentValues.put("COUNTRY",country);
        contentValues.put("CITY",city);
        contentValues.put("PHONE_NUM",phone_num);

        long result=sqLiteDatabase.insert("RENTING_AGENCY_USER", null, contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    public boolean insert_Tenant_User(String email,String f_name,String l_name,String gender,String password,
                                      String nationality,int salary,String ocuupation,int family_size,String country
                                       ,String city ,String phone_num) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("EMAIL",email);
        contentValues.put("F_NAME", f_name);
        contentValues.put("L_NAME", l_name);
        contentValues.put("GENDER", gender);
        contentValues.put("PASSWORD", password);
        contentValues.put("NATIONALITY", nationality);
        contentValues.put("SALARY", salary);
        contentValues.put("OCCUPATION",ocuupation);
        contentValues.put("FAMILY_SIZE",family_size);
        contentValues.put("COUNTRY",country);
        contentValues.put("CITY",city);
        contentValues.put("PHONE_NUM",phone_num);

        long result=sqLiteDatabase.insert("TENANT_USER", null, contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }
    public Cursor getAllValues(String table_Name) {
        //ArrayList userlist = new ArrayList();
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
          return sqLiteDatabase.rawQuery("SELECT * FROM "+table_Name, null);
    }
    public Cursor search (String email,String table_Name){
        SQLiteDatabase sqLiteDatabase= getReadableDatabase();
        Cursor values;
        if (table_Name.equals("HOUSE_Info"))
            values= sqLiteDatabase.rawQuery("SELECT * from "+table_Name+" WHERE owner_email= '" + email+"'",null);
        else if (table_Name.equals("Notification"))
            values= sqLiteDatabase.rawQuery("SELECT * from "+table_Name+" WHERE ag_email= '" + email+"'",null);
        else if (table_Name.equals("TENANT_HOUSE"))
            values= sqLiteDatabase.rawQuery("SELECT * from "+table_Name+" WHERE TEN_EMAIL= '" + email+"'",null);
        else
            values= sqLiteDatabase.rawQuery("SELECT * from "+table_Name+" WHERE EMAIL= '" + email+"'",null);

        values.moveToFirst();
        if (values.getCount() == 0)
            return null;
        else {

            return values;
        }
    }

    public Cursor findCityPostal(int id){
        SQLiteDatabase sqLiteDatabase= getReadableDatabase();
        Cursor values;
        values= sqLiteDatabase.rawQuery("SELECT * from HOUSE_Info WHERE _id= '" + id+"'",null);
        values.moveToFirst();
        if (values.getCount() == 0)
            return null;
        else {

            return values;
        }
    }
    public boolean searchById(int id, String table_name){
        SQLiteDatabase sqLiteDatabase= getReadableDatabase();
        Cursor values;
            values= sqLiteDatabase.rawQuery("SELECT * from "+table_name+" WHERE _id= '" + id+"'",null);
        values.moveToFirst();
        if (values.getCount() == 0)
            return false;
        else {

            return true;
        }
    }
    public Cursor requestAnswer (String tenant_email){
        SQLiteDatabase sqLiteDatabase= getReadableDatabase();
        Cursor values;
        values= sqLiteDatabase.rawQuery("SELECT * from HOUSE_Info" +
                " INNER JOIN TENANT_HOUSE on TENANT_HOUSE._id = HOUSE_Info._id" +
                " WHERE TENANT_HOUSE.TEN_EMAIL= '" + tenant_email+"'",null);

        if (values.getCount() == 0)
            return null;
        else {
            return values;
        }
    }
    public Cursor tenantHistory (String tenant_email){
        SQLiteDatabase sqLiteDatabase= getReadableDatabase();
        Cursor values;
        values= sqLiteDatabase.rawQuery("SELECT TENANT_HOUSE._id,TENANT_HOUSE.city,TENANT_HOUSE.postalAdd,RENTING_AGENCY_USER.AGENCY_NAME from TENANT_HOUSE" +
                " INNER JOIN RENTING_AGENCY_USER on RENTING_AGENCY_USER.EMAIL = TENANT_HOUSE.agncy_email" +
                " WHERE TENANT_HOUSE.TEN_EMAIL= '" + tenant_email+"'",null);

        if (values.getCount() == 0)
            return null;
        else {
            return values;
        }
    }
    public Cursor agencyHistory (String agncy_email){
        SQLiteDatabase sqLiteDatabase= getReadableDatabase();
        Cursor values;
        values= sqLiteDatabase.rawQuery("SELECT TENANT_HOUSE._id,TENANT_HOUSE.city,TENANT_HOUSE.postalAdd,TENANT_USER.F_NAME,TENANT_USER.L_NAME from TENANT_HOUSE" +
                " INNER JOIN TENANT_USER on TENANT_USER.EMAIL = TENANT_HOUSE.TEN_EMAIL" +
                " WHERE TENANT_HOUSE.agncy_email= '" + agncy_email+"'",null);

        if (values.getCount() == 0)
            return null;
        else {
            return values;
        }
    }
    public void update_Tenant (String email,String table_Name,String f_name,String l_name,String gender,
                               String nationality,int salary,String ocuupation, int family_size,String country
            ,String city ,String phone_num ){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("F_NAME", f_name);
        contentValues.put("L_NAME", l_name);
        contentValues.put("GENDER", gender);
        contentValues.put("NATIONALITY", nationality);
        contentValues.put("SALARY", salary);
        contentValues.put("OCCUPATION",ocuupation);
        contentValues.put("FAMILY_SIZE",family_size);
        contentValues.put("COUNTRY",country);
        contentValues.put("CITY",city);
        contentValues.put("PHONE_NUM",phone_num);
        sqLiteDatabase.update(table_Name, contentValues, "EMAIL=?", new String[]{email});

    }
    public void update_RentalAgency (String email,String name,String contry,String city,String phone_num ){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("EMAIL", email);
        contentValues.put("AGENCY_NAME", name);
        contentValues.put("COUNTRY", contry);
        contentValues.put("CITY", city);
        contentValues.put("PHONE_NUM", phone_num);
        sqLiteDatabase.update("RENTING_AGENCY_USER", contentValues, "EMAIL=?", new String[]{email});

    }
    public void update_Properity (int id,String email,String city,String address,int area,int construct_year,
                               int bedroom_num ,int price,String status,String available_date,String description,
                                  boolean garden,boolean balcony){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("owner_email", email);
        contentValues.put("city", city);
        contentValues.put("postal_adress", address);
        contentValues.put("surface_area", area);
        contentValues.put("construction_year", construct_year);
        contentValues.put("bedroom_number",bedroom_num);
        contentValues.put("rental_price",price);
        contentValues.put("status",status);
        contentValues.put("availability_date",available_date);
        contentValues.put("description",description);
        contentValues.put("garden",garden);
        contentValues.put("balcony",balcony);
        sqLiteDatabase.update("HOUSE_Info", contentValues, "_id=?", new String[]{""+id});

    }
    public boolean remove_Properity(int id){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
       long success= sqLiteDatabase.delete("HOUSE_Info",  "_id=?", new String[]{""+id});
       if (success == -1)
           return false;
       else
           return true;
    }
    public void deleteNotification(int id){
        SQLiteDatabase sqLiteDatabase =this.getWritableDatabase();
        sqLiteDatabase.delete("Notification","_id=?",new String[]{""+id});
    }
    public void storeHouse(String owner_email,String city,String postal_adress,int surface_area, Bitmap img,
                           int construction_year,int bedroom_number,int rental_price,String status
                           ,String availability_date,String description,boolean garden,boolean balcony){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        byte[] imageByte;
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        img.compress(Bitmap.CompressFormat.JPEG,0,stream);
        imageByte=stream.toByteArray();

        ContentValues contentValues = new  ContentValues();
        contentValues.put("owner_email", owner_email);
        contentValues.put("city", city);
        contentValues.put("postal_adress", postal_adress);
        contentValues.put("surface_area", surface_area);
        contentValues.put("construction_year",construction_year );
        contentValues.put("bedroom_number", bedroom_number);
        contentValues.put("IMAGE", imageByte);
        contentValues.put("rental_price",rental_price );
        contentValues.put("status", status);
        contentValues.put("availability_date",availability_date );
        contentValues.put("description",description );
        contentValues.put("garden",garden );
        contentValues.put("balcony", balcony);
        sqLiteDatabase.insert( "House_Info", null, contentValues );

    }
    public Cursor home_Search (String city, int minArea,int maxArea,int minBed,int maxBed,int price, String status,boolean is_garden,boolean is_balcony){
        SQLiteDatabase sqLiteDatabase= getReadableDatabase();
            Cursor values = sqLiteDatabase.rawQuery("SELECT * from HOUSE_Info WHERE city='"+city+"'"+" AND surface_area between "+minArea+" and "+maxArea
                +" AND bedroom_number between "+minBed+" and "+maxBed+" AND rental_price>="+price+" AND status='"+status+"'"
                +" AND garden="+is_garden+" AND balcony="+is_balcony+
                " AND garden="+is_garden+" AND balcony="+is_balcony,null);

        values.moveToFirst();
        if (values.getCount() == 0)
            return null;
        else {

            return values;
        }
    }
    public boolean insert_Notification(String agncy_email ,String tenant_email, int home_id) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("ag_email",agncy_email);
        contentValues.put("TEN_EMAIL",tenant_email);
        contentValues.put("_id", home_id);
        long result=sqLiteDatabase.insert("Notification", null, contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }
    public boolean insert_Tenant_Home(String tenant_email, int home_id,String city, String agency_email,String postad_add) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("TEN_EMAIL",tenant_email);
        contentValues.put("_id", home_id);
        contentValues.put("city", city);
        contentValues.put("postalAdd", postad_add);
        contentValues.put("agncy_email", agency_email);
        long result=sqLiteDatabase.insert("TENANT_HOUSE", null, contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }
    public boolean notificationIdSearch(int id,String ten_email){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor values;
        values= sqLiteDatabase.rawQuery("SELECT * from Notification WHERE _id= '" + id+"'"+" and TEN_EMAIL= '" +
                 ten_email+"'",null);
        if (values.getCount() == 0)
            return false;
        else
            return true;
    }
    public Cursor getLast5Post(){
        SQLiteDatabase sqLiteDatabase =getReadableDatabase();
        Cursor values;
        values= sqLiteDatabase.rawQuery("SELECT * from HOUSE_Info ORDER BY _id DESC LIMIT 5",null);
        if (values.getCount() == 0)
            return null;
        else
            return values;
    }


}
