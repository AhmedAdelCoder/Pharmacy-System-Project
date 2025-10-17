/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package internal.pharmacy.system.project;

import java.time.LocalDate;

public class Drug extends Product{
    private String dosage;
    
// Constrcutor
    public Drug( ){super();}
    public Drug (int productID, int stockQuantity,double price,String name,LocalDate expiryDate,boolean isPrescriptionRequired,String dosage){
        super(productID, stockQuantity, price, name, expiryDate, isPrescriptionRequired);
        setDosage(dosage);
    }
    
//Setter
    public void setDosage(String dosage) {
        this.dosage = dosage;
    }
    
// Getter
    public String getDosage() {
        return dosage;
    }
    
//display Drug
    @Override
    public void display(){
        super.display();
        System.out.println("Dosage: " + dosage);
        System.out.println("========================");
    } 
}
