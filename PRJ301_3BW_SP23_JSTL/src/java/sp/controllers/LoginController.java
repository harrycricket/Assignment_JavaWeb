/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sp.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import sp.bw.user.UserDAO;
import sp.bw.user.UserDTO;

/**
 *
 * @author Huynh Van Phuot
 */
public class LoginController extends HttpServlet {

    private static final String USER_PAGE = "user.jsp";
    private static final String US = "US";
    private static final String ADMIN_PAGE = "admin.jsp";
    private static final String AD = "AD";
    private static final String LOGIN_PAGE = "login.jsp";
    static final Logger LOGGER = Logger.getLogger(LoginController.class);

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = LOGIN_PAGE;
        try {
            String userID = request.getParameter("userID");
            String password = request.getParameter("password");
//            String remember = request.getParameter("rememberMe");
//            boolean rememberMe = "y".equalsIgnoreCase(remember);
            UserDAO dao = new UserDAO();
            UserDTO loginUser = dao.checkLogin(userID, password);
            // Xác thực ở đây
            if (loginUser == null) {
                request.setAttribute("ERROR", "Incorrect UserID or Password");
                url = LOGIN_PAGE;
            } else { // phân quyền ở đây
//                request.setAttribute("rememberMe", rememberMe);
//                if (rememberMe) {
//                    //store cookie for one day
//                    final int TIME = 24 * 60 * 60;
//                    String token = System.currentTimeMillis() + "";
//                    Cookie userCookie = new Cookie("token", token );
//                    //update token in database
//                    UserDAO.updateToken(userID, token);
//                    userCookie.setMaxAge(TIME);
//                    response.addCookie(userCookie);
//                }
                String roleID = loginUser.getRoleID();
                HttpSession session = request.getSession();
                session.setAttribute("LOGIN_USER", loginUser);
                if (US.equals(roleID)) {
                    url = USER_PAGE;
                    LOGGER.info("UserID: " + request.getParameter("userID"));
                    LOGGER.info("Login user page successfully");
                } else if (AD.equals(roleID)) {
                    url = ADMIN_PAGE;
                    LOGGER.info("UserID: " + request.getParameter("userID"));
                    LOGGER.info("Login admin page successfully");
                } else {
                    request.setAttribute("ERROR", "Your role is not support yet!");
                    LOGGER.info("Login Error");
                }
            }

        } catch (Exception e) {
            log("Error a LoginController: " + e.toString());
            LOGGER.error(e);
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
