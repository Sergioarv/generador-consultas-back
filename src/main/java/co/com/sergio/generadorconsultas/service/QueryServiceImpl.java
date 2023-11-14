package co.com.sergio.generadorconsultas.service;

import co.com.sergio.generadorconsultas.entity.Query;
import co.com.sergio.generadorconsultas.repository.QueryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    QueryRepository queryRepository;

    /**
     * Método encargado dede listar todas querys de la base de datos
     *
     * @return una List con las querys de la base de datos
     */
    @Override
    @Transactional(readOnly = true)
    public List<Query> getAllQuerys() {
        return queryRepository.findAll();
    }

    /**
     * Método encargado de buscar las querys por nombre de la query o nombre del usuario
     *
     * @param name,          nombre con la que se encuentra guardada la query
     * @param userresgister, usuario que realiza el comentario en la query
     * @param pageable,      parametro para generar una paginación de la información buscada
     * @return Un Page o una paginación de los resultados
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Query> filterQuerys(String name, String userresgister, Pageable pageable) {

        Page<Query> resultList;

        if (name != null && userresgister != null) {
            resultList = queryRepository.filterNameAndUser(name, userresgister, pageable);
        } else if(name != null){
            resultList = queryRepository.filterName(name, pageable);
        } else if (userresgister != null) {
            resultList = queryRepository.filterUser(userresgister, pageable);
        }else{
            resultList = queryRepository.findAll(pageable);
        }

        return resultList;
    }

    /**
     * Método encargado de guardar una query
     *
     * @param query, entidad query que será guardada
     * @return la query guardada en la base de datos
     */
    @Override
    @Transactional
    public Query saveQuery(Query query) {

        if(queryRepository.findByName(query.getName()).isPresent()){
            throw new RuntimeException("El nombre de la query ya existe");
        }

        return queryRepository.save(query);
    }

    /**
     * Método encargado de actualizar una query
     *
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
     *
     * @param query, entidad a eliminar
     * @return booleano para verificar la eliminación de la query
     */
    @Override
    @Transactional
    public boolean deleteQuery(Query query) {
        return false;
    }


}
