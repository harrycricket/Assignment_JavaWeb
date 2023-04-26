/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sp.bw.user;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import javax.naming.NamingException;
import sp.bw.utils.DBUtils;

/**
 *
 * @author Huynh Van Phuot
 */
public class OrderDAO {

    private static final String INSERT_ORDERS = "INSERT INTO tblOrders(orderID, userID, orderDate) VALUES (?, ?, ?)";
    private static final String INSERT_ORDER_DETAILS = "INSERT INTO tblOrderDetails(detailID, orderID, productID, quantity) VALUES (?, ?, ?, ?)";
    private static final String UPDATE_ORDER_DETAILS = "UPDATE tblProducts \n"
            + "SET quantity = (SELECT quantity - (SELECT quantity FROM tblOrderDetails WHERE productID =? and detailID = ?) FROM tblProducts WHERE productID = ?)\n"
            + "WHERE productID = ?";

    public static boolean insertOrder(String userID, Map<String, Product> cartList) throws SQLException, ClassNotFoundException, NamingException {

        boolean result = false;
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        String orderID = System.currentTimeMillis() + "1";
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                con.setAutoCommit(false);
                stm = con.prepareStatement(INSERT_ORDERS);
                Date orderDate = new Date(System.currentTimeMillis());
                stm.setString(1, orderID);
                stm.setString(2, userID);
                stm.setDate(3, orderDate);

                int rowAffected1 = stm.executeUpdate();
                if (rowAffected1 > 0) {
                    if (cartList != null && cartList.size() > 0) {
                        for (String dto : cartList.keySet()) {
                            String detailID = System.currentTimeMillis() + "10";
                            Product pro = cartList.get(dto);
                            stm = con.prepareStatement(INSERT_ORDER_DETAILS);
                            stm.setString(1, detailID);
                            stm.setString(2, orderID);
                            stm.setString(3, pro.getProductID());
                            stm.setInt(4, pro.getQuantity());
                            int rowAffected2 = stm.executeUpdate();
                            if (rowAffected2 > 0) {
                                stm = con.prepareStatement(UPDATE_ORDER_DETAILS);
                                stm.setString(1, pro.getProductID());
                                stm.setString(2, detailID);
                                stm.setString(3, pro.getProductID());
                                stm.setString(4, pro.getProductID());
                                int rowAffected3 = stm.executeUpdate();
                                if (rowAffected3 > 0) {
                                    result = true;
                                }
                            }
                        }
                    }
                }
                con.commit();
                con.setAutoCommit(true);
            }
        } finally {

            if (stm != null) {
                stm.close();
            }
            if (rs != null) {
                rs.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return result;
    }
}
