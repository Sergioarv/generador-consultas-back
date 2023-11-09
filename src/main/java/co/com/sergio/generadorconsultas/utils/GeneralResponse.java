package co.com.sergio.generadorconsultas.utils;

import java.io.Serializable;

/**
 *
 * Author: Ing Sergio Abelardo Rodríguez Vásquez
 * Date: 9/11/23
 * Email: ingsergiorodriguezv@gmail.com
 */

 public class GeneralResponse<T> implements Serializable {

    /**
     * Esta clase es creada con la finalidad de
     * manejar los response que genere la aplicación
     * de forma general y buscando una estructura estandar
     * donde se podra visualizar los datos del response
     * mensajes de error y feedback y un verificación booleana
     */

    private T data;

    private boolean success;

    private String message;

    public GeneralResponse() {
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
