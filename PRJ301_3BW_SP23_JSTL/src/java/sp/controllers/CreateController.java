/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sp.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import sp.bw.user.UserDAO;
import sp.bw.user.UserDTO;
import sp.bw.user.UserError;

/**
 *
 * @author Huynh Van Phuot
 */
public class CreateController extends HttpServlet {

    private static final String ERROR = "createUser.jsp";
    private static final String SUCCESS = "login.html";
    static final Logger LOGGER = Logger.getLogger(LoginController.class);

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        UserError userError = new UserError();
        try {
            String userID = request.getParameter("userID");
            String fullName = request.getParameter("fullName");
            String roleID = request.getParameter("roleID");
            String password = request.getParameter("password");
            String confirm = request.getParameter("confirm");
            boolean checkValidation = true;
            if (userID.length() < 2 || userID.length() > 6) {
                userError.setUserIDError("UserID must contain 2 to 6 characters!");
                checkValidation = false;
            }
            if (fullName.length() < 5 || fullName.length() > 20) {
                userError.setFullNameError("Full name must contain 5 to 20 characters!");
                checkValidation = false;
            }
            if (!password.equals(confirm)) {
                userError.setConfirmError("Confirm password is not correct");
                checkValidation = false;
            }
            if (checkValidation) {
                UserDAO dao = new UserDAO();
                UserDTO newUser = new UserDTO(userID, fullName, roleID, password);
                boolean checkDuplicate = dao.checkDuplicate(userID);
//                if (checkDuplicate) {
//                    userError.setUserIDError("Duplicate userID!");
//                    request.setAttribute("USER_ERROR", userError);
//                } else {
//                    boolean checkInsert = dao.insert(newUser);
                boolean checkInsert = dao.insertV2(newUser);
                if (checkInsert) {
                    LOGGER.info("Create a new user successfully");
                    url = SUCCESS;
                } else {
                    LOGGER.info("Create a new user failed");
                    url = ERROR;
                    userError.setError("Undefined error!");
                    request.setAttribute("USER_ERROR", userError);
                }
 //                }
            } else {
                LOGGER.info("Create a new user failed");
                url = ERROR;
                request.setAttribute("USER_ERROR", userError);
            }
        } catch (Exception e) {
            log("Error at CreateController: " + e.toString());
            if (e.toString().contains("duplicate")) {
                userError.setUserIDError("Trung ID!");
                request.setAttribute("USER_ERROR", userError);
            }
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
