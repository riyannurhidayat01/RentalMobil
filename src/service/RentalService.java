/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
// RentalService.java
package service;

import model.Rental;
import java.util.ArrayList;
import java.util.List;

public class RentalService {
    private List<Rental> daftarRental = new ArrayList<>();

    public void tambahRental(Rental rental) {
        daftarRental.add(rental);
    }

    public List<Rental> getDaftarRental() {
        return daftarRental;
    }
}
