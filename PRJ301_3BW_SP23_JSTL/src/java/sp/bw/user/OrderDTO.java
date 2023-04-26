/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sp.bw.user;

import java.sql.Date;

/**
 *
 * @author Huynh Van Phuot
 */
public class OrderDTO {
    private String orderID;
    private String userID;
    private Date orderDate;
    private double total;

    public OrderDTO() {
    this.orderID = "";
    this.userID = "";
    this.orderDate = new Date(System.currentTimeMillis());
    this.total = 0;
}

    public OrderDTO(String orderID, String userID, Date orderDate, double total) {
        this.orderID = orderID;
        this.userID = userID;
        this.orderDate = orderDate;
        this.total = total;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
    
    
}
