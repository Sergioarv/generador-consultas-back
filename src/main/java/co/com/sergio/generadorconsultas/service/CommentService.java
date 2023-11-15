package co.com.sergio.generadorconsultas.service;

import co.com.sergio.generadorconsultas.entity.Comment;
import java.util.List;

/**
 * Author: Ing Sergio Abelardo Rodríguez Vásquez
 * Date: 14/11/2023
 * Email: ingsergiorodriguezv@gmail.com
 **/

public interface CommentService {

    /**
     * Método encargado dede listar todas comments de la base de datos
     * @return una List con los comments de la base de datos
     */
    List<Comment> getAllComments();

    /**
     * Método encargado de guardar un comment
     * @param comment, entidad comment que será guardada
     * @return la comment guardada en la base de datos
     */
    Comment saveComments(Comment comment);

    /**
     * Método encargado de actualizar una comment
     * @param comment, entidad comment a actualizar con los datos actualizados
     * @return La comment actualizada en la base de datos
     */
    Comment updateComment(Comment comment);

    /**
     * Método encargado de eliminar una comment
     * @param comment, entidad a eliminar
     * @return booleano para verificar la eliminación de el comment
     */
    boolean deleteComment(Comment comment);
}
