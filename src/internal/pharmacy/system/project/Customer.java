
package internal.pharmacy.system.project;

import java.util.LinkedList;
import java.util.List;

public class Customer {
    
    private int customerID;
    private String  name , phone , address ;
    private List <String> order = new LinkedList<>();

    
//------------------------------------Constructor-------------------------------
    public  Customer(){
        this.customerID=0;
        this.name="";
        this.address="";
        this.phone="";
        this.order = new LinkedList<>();
    }

    public Customer(int customerID, String name, String phone, String address,List<String>order) {
        setCustomerID(customerID);
        setName(name);
        setPhone(phone);
        setAddress(address);
        setOrder(order);

    }
     
//---------------------------------- Setter ------------------------------------

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setOrder(List<String> order) {
        this.order = (order != null) ? order : new LinkedList<>();
    }
//--------------------------------- getter -------------------------------------

    public int getCustomerID() {
        return customerID;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public List<String> getOrder() {
        return order;
    } 
}
