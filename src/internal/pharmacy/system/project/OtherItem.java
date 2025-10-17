/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package internal.pharmacy.system.project;

import java.time.LocalDate;

    class OtherItem extends Product{
        private String category;
// Constructor
        public OtherItem (){super();}
        public OtherItem(int productID, int stockQuantity,double price,String name,LocalDate expiryDate,boolean isPrescriptionRequired,String category){
            super(productID, stockQuantity, price, name, expiryDate, isPrescriptionRequired);
            setCategory(category);
        };
        
//Setter       
    public void setCategory(String category) {
        this.category = category;
    }
    
//getter
    public String getCategory() {
        return category;
    }
    
//display otheritem
    @Override
    public void display(){
        super.display();
        System.out.println("category: " + category);
    }
        
   }
