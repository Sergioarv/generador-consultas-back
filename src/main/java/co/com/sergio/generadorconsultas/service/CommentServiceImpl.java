package co.com.sergio.generadorconsultas.service;

import co.com.sergio.generadorconsultas.entity.Comment;
import co.com.sergio.generadorconsultas.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Author: Ing Sergio Abelardo Rodríguez Vásquez
 * Date: 14/11/2023
 * Email: ingsergiorodriguezv@gmail.com
 **/

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    CommentRepository commentRepository;

    /**
     * Método encargado dede listar todas comments de la base de datos
     * @return una List con los comments de la base de datos
     */
    @Override
    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }

    /**
     * Método encargado de guardar un comment
     * @param comment, entidad comment que será guardada
     * @return la comment guardada en la base de datos
     */
    @Override
    public Comment saveComments(Comment comment) {
        return null;
    }

    /**
     * Método encargado de actualizar una comment
     * @param comment, entidad comment a actualizar con los datos actualizados
     * @return La comment actualizada en la base de datos
     */
    @Override
    public Comment updateComment(Comment comment) {
        return null;
    }

    /**
     * Método encargado de eliminar una comment
     * @param comment, entidad a eliminar
     * @return booleano para verificar la eliminación de el comment
     */
    @Override
    public boolean deleteComment(Comment comment) {
        return false;
    }
}
