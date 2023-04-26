/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sp.bw.user;

/**
 *
 * @author Huynh Van Phuot
 */
public class ProductError {
    private String productIDError;
    private String nameError;
    private String error;

    public ProductError() {
        this.productIDError = "";
        this.nameError = "";
        this.error = "";
    }

    public ProductError(String productIDError, String nameError, String error) {
        this.productIDError = productIDError;
        this.nameError = nameError;
        this.error = error;
    }

    public String getProductIDError() {
        return productIDError;
    }

    public void setProductIDError(String productIDError) {
        this.productIDError = productIDError;
    }

    public String getNameError() {
        return nameError;
    }

    public void setNameError(String nameError) {
        this.nameError = nameError;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
    
    
}
