/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sp.controllers;

import java.io.IOException;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import sp.bw.shopping.Cart;
import sp.bw.user.OrderDAO;
import sp.bw.user.Product;
import sp.bw.user.UserDTO;

/**
 *
 * @author Huynh Van Phuot
 */
public class CheckOutController extends HttpServlet {

    private static final String ERROR = "error.html";
    private static final String SUCCESS = "checkOut.jsp";
    static final Logger LOGGER = Logger.getLogger(LoginController.class);
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            HttpSession session = request.getSession();
            Cart cart = (Cart) session.getAttribute("CART");
            if (cart != null) {
                Map<String, Product> cartList = (Map<String, Product>) cart.getCart();
                UserDTO loginUser = (UserDTO) session.getAttribute("LOGIN_USER");
                if (cartList != null) {
                    boolean result = OrderDAO.insertOrder(loginUser.getUserID(), cartList);
                    if (result) {
                        session.setAttribute("CART", null);
                        request.setAttribute("NOTIFICATION", "Congratulations. Your order has been successfully placed!");
                        LOGGER.info("Check out successfully");
                        url = SUCCESS;
                    }
                }
            }
        } catch (Exception e) {
            log("Error at CheckOutController: " + e.toString());
            request.setAttribute("NOTIFICATION", "These products are out of stock");
            LOGGER.info("Check out failed");
            url = SUCCESS;
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
