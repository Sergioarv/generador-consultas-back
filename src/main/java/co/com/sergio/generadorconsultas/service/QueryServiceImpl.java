package co.com.sergio.generadorconsultas.service;

import co.com.sergio.generadorconsultas.entity.Query;
import co.com.sergio.generadorconsultas.repository.QueryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Author: Ing Sergio Abelardo Rodríguez Vásquez
 * Date: 09/11/2023
 * Email: ingsergiorodriguezv@gmail.com
 **/

@Service
public class QueryServiceImpl implements QueryService {

    @Autowired
    private QueryRepository queryRepository;

    /**
     * Método encargado dede listar todas querys de la base de datos
     * @return una List con las querys de la base de datos
     */
    @Override
    @Transactional(readOnly = true)
    public List<Query> getAllQuerys() {
        return queryRepository.findAll();
    }

    /**
     * Método ncargado de buscar las querys por nombre de la query o nombre del usuario
     * @param name, nombre con la que se encuentra guardada la query
     * @param userresgister, usuario que realiza el comentario en la query
     * @param pageRequest, parametro para generar una paginación de la información buscada
     * @return Un Page o una paginación de los resultados
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Query> filterQuerys(String name, String userresgister, PageRequest pageRequest) {
        return null;
    }

    /**
     * Método encargado de guardar una query
     * @param query, entidad query que será guardada
     * @return la query guardada en la base de datos
     */
    @Override
    @Transactional
    public Query saveQuery(Query query) {
        return null;
    }

    /**
     * Método encargado de actualizar una query
     * @param query, entidad query a actualizar con los datos actualizados
     * @return La query actualizada en la base de datos
     */
    @Override
    @Transactional
    public Query updateQuery(Query query) {
        return null;
    }

    /**
     * Método encargado de eliminar una query
     * @param query, entidad a eliminar
     * @return booleano para verificar la eliminación de la query
     */
    @Override
    @Transactional
    public boolean deleteQuery(Query query) {
        return false;
    }


}
