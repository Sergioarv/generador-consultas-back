package co.com.sergio.generadorconsultas.service;

import co.com.sergio.generadorconsultas.entity.Query;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Author: Ing Sergio Abelardo Rodríguez Vásquez
 * Date: 09/11/2023
 * Email: ingsergiorodriguezv@gmail.com
 **/

public interface QueryService {

    /**
     * Método encargado dede listar todas querys de la base de datos
     *
     * @return una List con las querys de la base de datos
     */
    List<Query> getAllQuerys();

    /**
     * Método ncargado de buscar las querys por nombre de la query o nombre del usuario
     *
     * @param name,          nombre con la que se encuentra guardada la query
     * @param userresgister, usuario que realiza el comentario en la query
     * @param pageable,      parametro para generar una paginación de la información buscada
     * @return Un Page o una paginación de los resultados
     */
    Page<Query> filterQuerys(String name, String userresgister, Pageable pageable);

    /**
     * Método encargado de guardar una query
     *
     * @param query, entidad query que será guardada
     * @return la query guardada en la base de datos
     */
    Query saveQuery(Query query);

    /**
     * Método encargado de actualizar una query
     *
     * @param query, entidad query a actualizar con los datos actualizados
     * @return La query actualizada en la base de datos
     */
    Query updateQuery(Query query);

    /**
     * Método encargado de eliminar una query
     *
     * @param query, entidad a eliminar
     * @return booleano para verificar la eliminación de la query
     */
    boolean deleteQuery(Query query);
}
