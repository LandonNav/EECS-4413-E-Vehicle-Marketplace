package com.name.studentapp;

import java.util.ArrayList; 

import java.util.List; 

  

public class VehicleDAO { 

    private static List<Vehicle> vehicles = new ArrayList<>(); 

  

    public Vehicle create(Vehicle student) { 

    	vehicles.add(student); 

        return student; 

    } 

  

    public List<Vehicle> readAll() { 

        return vehicles; 

    } 

  

    public Vehicle read(int id) { 

        return vehicles.stream().filter(s -> s.getId() == id).findFirst().orElse(null); 

    } 

  

    public Vehicle update(int id, Vehicle student) { 

        // Implement update logic 
    	Vehicle updatedStudent = null;
    	 for (int i = 0; i < vehicles.size(); i++) {
    		 if (vehicles.get(i).getId() == id) {
    			 vehicles.set(i, student);
    			 updatedStudent = vehicles.get(i);
    		 }
    		 
    	 }
        // ... 

        return updatedStudent; 

    } 

  

    public void delete(int id) { 

    	vehicles.removeIf(s -> s.getId() == id); 

    } 

} 