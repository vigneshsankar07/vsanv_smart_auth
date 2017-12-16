package com.share.image.tollsmart;

/**
 * Created by test on 5/12/17.
 */

public class Vehicle {

    int total_number_of_axles = 2;
    int number_of_axles_without_trailer = 2;
    int trailer_number_of_axles;
    int number_of_trailers;
    float weight;
    float height;
    float width;
    float length;
    boolean has_trailer;
    boolean trailer_is_car;
    boolean has_dual_tires;
    boolean has_low_axles;
    boolean is_commercial;
    boolean is_school_bus;
    boolean is_motorcycle;
    boolean is_moped;
    boolean trailer_has_dual_tires;
    boolean is_auto;
    boolean is_truck;
    boolean is_RV;
    boolean is_bus;
    boolean is_limo;
    boolean is_franchise_bus;
    boolean is_tractor;
    boolean is_tractor_trailer;
    boolean is_tandem_trailer;
    boolean is_mini_bus;
    boolean is_motor_home;
    boolean is_motorhome;
    boolean is_wrecker;
    boolean is_franchise;
    boolean is_special_load;
    boolean is_clean_air;
    boolean is_emergency_vehicle;

    String vehicle_type = "auto";//auto,limo,truck,bus,motorcycle,RV,school_bus, wrecker

    int number_of_occupants = 1;

    boolean combustible;
    boolean hazardousGoods;
    boolean hazardousToWaters;

    public Vehicle() {
        super();
        this.number_of_axles_without_trailer = 2;
        this.total_number_of_axles = 2;
        this.vehicle_type = "auto";
        this.is_auto = true;
    }

    public int getNumber_of_occupants() {
        return number_of_occupants;
    }
    public void setNumber_of_occupants(int number_of_occupants) {
        this.number_of_occupants = number_of_occupants;
    }
    public boolean isIs_wrecker() {
        return is_wrecker;
    }
    public void setIs_wrecker(boolean is_wrecker) {
        this.is_wrecker = is_wrecker;
    }
    public boolean isIs_moped() {
        return is_moped;
    }
    public void setIs_moped(boolean is_moped) {
        this.is_moped = is_moped;
        this.is_motorcycle = is_moped;

    }
    public float getLength() {
        return length;
    }
    public void setLength(float length) {
        this.length = length;
    }
    public boolean isIs_special_load() {
        return is_special_load;
    }
    public void setIs_special_load(boolean is_special_load) {
        this.is_special_load = is_special_load;
    }
    public float getWidth() {
        return width;
    }
    public void setWidth(float width) {
        this.width = width;
    }
    public boolean isIs_franchise() {
        return is_franchise;
    }
    public void setIs_franchise(boolean is_franchise) {
        this.is_franchise = is_franchise;
    }
    public boolean isIs_motor_home() {
        return is_motor_home;
    }
    public void setIs_motor_home(boolean is_motor_home) {
        this.is_motor_home = is_motor_home;
    }

    public float getHeight() {
        return height;
    }
    public void setHeight(float height) {
        this.height = height;
    }
    public boolean isIs_mini_bus() {
        return is_mini_bus;
    }
    public void setIs_mini_bus(boolean is_mini_bus) {
        this.is_mini_bus = is_mini_bus;
        if (is_mini_bus){
            this.is_bus = true;
        }
    }
    public boolean isIs_tandem_trailer() {
        return is_tandem_trailer;
    }
    public void setIs_tandem_trailer(boolean is_tandem_trailer) {
        this.is_tandem_trailer = is_tandem_trailer;
    }
    public boolean isIs_tractor() {
        return is_tractor;
    }
    public void setIs_tractor(boolean is_tractor) {
        this.is_tractor = is_tractor;
    }
    public boolean isIs_tractor_trailer() {
        return is_tractor_trailer;
    }
    public void setIs_tractor_trailer(boolean is_tractor_trailer) {
        this.is_tractor_trailer = is_tractor_trailer;
    }
    public int getNumber_of_axles_without_trailer() {
        return number_of_axles_without_trailer;
    }
    public void setNumber_of_axles_without_trailer(
            int number_of_axles_without_trailer) {
        this.number_of_axles_without_trailer = number_of_axles_without_trailer;
    }
    public boolean isIs_motorcycle() {
        return is_motorcycle;
    }
    public void setIs_motorcycle(boolean is_motorcycle) {
        this.is_motorcycle = is_motorcycle;
    }
    public boolean isIs_franchise_bus() {
        return is_franchise_bus;
    }
    public void setIs_franchise_bus(boolean is_franchise_bus) {
        this.is_franchise_bus = is_franchise_bus;
        if (is_franchise_bus){
            this.is_commercial = true;
            this.is_bus = true;
        }
    }
    public boolean isIs_school_bus() {
        return is_school_bus;
    }
    public void setIs_school_bus(boolean is_school_bus) {
        this.is_school_bus = is_school_bus;
        if (is_school_bus){
            this.is_bus = true;
        }
    }

    public int getTotal_number_of_axles() {

        return total_number_of_axles;

    }
    public void setTotal_number_of_axles(int total_number_of_axles) {
        this.total_number_of_axles = total_number_of_axles;
    }
    public int getTrailer_number_of_axles() {
        return trailer_number_of_axles;
    }

    public void setTrailer_number_of_axles(int trailer_number_of_axles) {

        this.trailer_number_of_axles = trailer_number_of_axles;
        if (trailer_number_of_axles > 0){
            this.has_trailer = true;
        }else{
            this.has_trailer = false;
        }

    }
    public float getWeight() {
        return weight;
    }
    public void setWeight(float weight) {
        this.weight = weight;
    }
    public boolean isHas_trailer() {
        return has_trailer;
    }
    public void setHas_trailer(boolean has_trailer) {
        this.has_trailer = has_trailer;
    }
    public boolean isHas_dual_tires() {
        return has_dual_tires;
    }
    public void setHas_dual_tires(boolean has_dual_tires) {
        this.has_dual_tires = has_dual_tires;
    }
    public boolean isHas_low_axles() {
        return has_low_axles;
    }
    public void setHas_low_axles(boolean has_low_axles) {
        this.has_low_axles = has_low_axles;
    }
    public boolean isIs_commercial() {
        return is_commercial;
    }
    public void setIs_commercial(boolean is_commercial) {
        this.is_commercial = is_commercial;
    }
    public String getVehicle_type() {
        return vehicle_type;
    }
    public void setVehicle_type(String vehicle_type) {
        this.vehicle_type = vehicle_type;
        this.is_auto = false;
        if (vehicle_type.equals("truck")){
            this.is_truck = true;
        }else if (vehicle_type.equals("auto")){
            this.is_auto = true;
        }else if (vehicle_type.equals("limo")){
            this.is_limo = true;
            this.is_commercial = true;
        }else if (vehicle_type.equals("bus")){
            this.is_bus = true;
        }else if (vehicle_type.equals("school_bus")){
            this.is_school_bus = true;
            this.is_bus = true;
        }else if (vehicle_type.equals("mini_bus")){
            this.is_mini_bus = true;
            this.is_bus = true;
        }else if (vehicle_type.equals("motorcycle")){
            this.is_motorcycle = true;
        }else if (vehicle_type.equals("moped")){
            this.is_motorcycle = true;
            this.is_moped = true;
        }else if (vehicle_type.equals("RV") || vehicle_type.equals("motor_home") || vehicle_type.equals("motorhome")){
            this.is_motor_home = true;
            this.is_RV = true;
            this.is_motorhome = true;
        }else if (vehicle_type.equals("franchise_bus")){
            this.is_franchise_bus = true;
            this.is_bus = true;
            this.is_franchise = true;
        }else if (vehicle_type.equals("tractor")){
            this.is_tractor = true;
            this.is_truck = true;
        }else if (vehicle_type.equals("tractor_trailer")){
            this.is_tractor_trailer = true;
            this.is_truck = true;
        }else if (vehicle_type.equals("tandem_trailer")){
            this.is_tandem_trailer = true;
            this.is_truck = true;
        }else if (vehicle_type.equals("wrecker")){
            this.is_wrecker = true;
            this.is_truck = true;
        }
        if (this.getTrailer_number_of_axles() > 0 && this.getNumber_of_trailers() == 0){
            this.number_of_trailers = 1;
        }

        if (this.total_number_of_axles == 0){
            this.total_number_of_axles = 2;
            this.trailer_number_of_axles = 0;
            this.number_of_axles_without_trailer = 2;
        }
    }

    public boolean isTrailer_has_dual_tires() {
        return trailer_has_dual_tires;
    }
    public void setTrailer_has_dual_tires(boolean trailer_has_dual_tires) {
        this.trailer_has_dual_tires = trailer_has_dual_tires;
    }
    public boolean isIs_auto() {
        return is_auto;
    }
    public void setIs_auto(boolean is_auto) {
        this.is_auto = is_auto;
    }
    public boolean isIs_truck() {
        return is_truck;
    }
    public void setIs_truck(boolean is_truck) {
        if (is_truck){
            this.is_commercial = true;
        }
        this.is_truck = is_truck;
    }
    public boolean isIs_RV() {
        return is_RV;
    }
    public void setIs_RV(boolean is_RV) {
        this.is_RV = is_RV;
    }
    public boolean isIs_bus() {

        return is_bus;
    }
    public void setIs_bus(boolean is_bus) {
        this.is_bus = is_bus;
    }
    public boolean isIs_limo() {
        return is_limo;
    }
    public void setIs_limo(boolean is_limo) {
        if (is_limo){
            this.is_commercial = true;
        }
        this.is_limo = is_limo;
    }
    public int getNumber_of_trailers() {
        return number_of_trailers;
    }
    public void setNumber_of_trailers(int number_of_trailers) {
        this.number_of_trailers = number_of_trailers;
    }

    public boolean isIs_clean_air() {
        return is_clean_air;
    }

    public void setIs_clean_air(boolean is_clean_air) {
        this.is_clean_air = is_clean_air;
    }

    public boolean isIs_motorhome() {
        return is_motorhome;
    }

    public void setIs_motorhome(boolean is_motorhome) {
        this.is_motorhome = is_motorhome;
        this.is_motor_home = is_motorhome;
    }

    public boolean isTrailer_is_car() {
        return trailer_is_car;
    }

    public void setTrailer_is_car(boolean trailer_is_car) {
        this.trailer_is_car = trailer_is_car;
        if (trailer_is_car)
            this.has_trailer = true;
    }

    public boolean isIs_emergency_vehicle() {
        return is_emergency_vehicle;
    }

    public void setIs_emergency_vehicle(boolean is_emergency_vehicle) {
        this.is_emergency_vehicle = is_emergency_vehicle;
    }

    public boolean isCombustible() {
        return combustible;
    }

    public void setCombustible(boolean combustible) {
        this.combustible = combustible;
    }

    public boolean isHazardousGoods() {
        return hazardousGoods;
    }

    public void setHazardousGoods(boolean hazardousGoods) {
        this.hazardousGoods = hazardousGoods;
    }

    public boolean isHazardousToWaters() {
        return hazardousToWaters;
    }

    public void setHazardousToWaters(boolean hazardousToWaters) {
        this.hazardousToWaters = hazardousToWaters;
    }

}

