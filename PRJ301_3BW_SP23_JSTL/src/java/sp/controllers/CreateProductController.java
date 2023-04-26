/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sp.controllers;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import sp.bw.user.Product;
import sp.bw.user.ProductDAO;
import sp.bw.user.ProductError;

/**
 *
 * @author Huynh Van Phuot
 */
public class CreateProductController extends HttpServlet {

    private static final String ERROR = "createProduct.jsp";
    private static final String SUCCESS = "product.jsp";
    static final Logger LOGGER = Logger.getLogger(LoginController.class);

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        ProductError productError = new ProductError();
        try {
            ProductDAO dao = new ProductDAO();
            String productID = request.getParameter("productID");
            String name = request.getParameter("name");
            double price = Double.parseDouble(request.getParameter("price"));
            int quantity = Integer.parseInt(request.getParameter("quantity"));

            boolean checkValidation = true;
            if (productID.length() < 2 || productID.length() > 6) {
                productError.setProductIDError("ProductID must contain 2 to 6 characters!");
                checkValidation = false;
            }
            if (name.length() < 5 || name.length() > 20) {
                productError.setNameError("Product name must contain 5 to 20 characters!");
                checkValidation = false;
            }
            if (checkValidation) {
                Product pro = new Product(productID, name, price, quantity);
                boolean checkDuplicateID = dao.checkDuplicateID(productID);
                boolean checkExistedName = dao.checkExistedName(name);
                if (checkDuplicateID) {
                    productError.setProductIDError("Duplicate productID!");
                    request.setAttribute("PRODUCT_ERROR", productError);
                } else if (checkExistedName) {
                    productError.setNameError("This product name is exsited!");
                    request.setAttribute("PRODUCT_ERROR", productError);
                } else {
                    boolean checkInsert = dao.insertProduct(pro);
                    if (checkInsert) {
                        LOGGER.info("Create a new product successfully");
                        url = SUCCESS;
                        request.setAttribute("SUCCESS_MSG", "You have successfully created a new product!");
                    } else {
                        productError.setError("Undefined Errror!");
                        request.setAttribute("PRODUCT_ERROR", productError);
                    }
                }
                List<Product> listProduct = dao.getListProduct();
                if (listProduct.size() > 0) {
                    request.setAttribute("LIST_PRODUCT", listProduct);
                }
            } else {
                LOGGER.info("Create a new product failed");
                url = ERROR;
                request.setAttribute("PRODUCT_ERROR", productError);
            }
        } catch (Exception e) {
            log("Error at CreateProductController: " + e.toString());
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
