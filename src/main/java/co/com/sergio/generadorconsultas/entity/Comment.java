package co.com.sergio.generadorconsultas.entity;

import jakarta.persistence.*;

import java.io.Serializable;

/**
 * Author: Ing Sergio Abelardo Rodríguez Vásquez
 * Date: 09/11/2023
 * Email: ingsergiorodriguezv@gmail.com
 **/

@Entity
@Table(name = "comment")
public class Comment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idcomment", nullable = false)
    private int idcomment;

    @Column(nullable = false)
    private String user;

    @Column(nullable = false)
    private String commentary;

    @ManyToOne
    @JoinColumn(name = "idquery")
    private Query query;

    /***********************/
    /** Getter and Setter **/
    /***********************/

    public int getIdcomment() {
        return idcomment;
    }

    public void setIdcomment(int idcomment) {
        this.idcomment = idcomment;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getCommentary() {
        return commentary;
    }

    public void setCommentary(String commentary) {
        this.commentary = commentary;
    }

    public void setQuery(Query query) {
        this.query = query;
    }
}
