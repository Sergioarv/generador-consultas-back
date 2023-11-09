package co.com.sergio.generadorconsultas.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;

/**
 * Author: Ing Sergio Abelardo Rodríguez Vásquez
 * Date: 9/11/23
 * Email: ingsergiorodriguezv@gmail.com
 */

@Entity
@Table(name = "query")
public class Query implements Serializable {
    @Id
    @Column(name = "idquery", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idquery;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String querysave;

    @OneToMany(mappedBy = "comment")
    private List<Comment> comments;

    /***********************/
    /** Getter and Setter **/
    /***********************/

    public int getIdquery() {
        return idquery;
    }

    public void setIdquery(int idquery) {
        this.idquery = idquery;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQuerysave() {
        return querysave;
    }

    public void setQuerysave(String querysave) {
        this.querysave = querysave;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
