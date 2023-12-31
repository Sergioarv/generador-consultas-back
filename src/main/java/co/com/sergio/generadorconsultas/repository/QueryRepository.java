package co.com.sergio.generadorconsultas.repository;

import co.com.sergio.generadorconsultas.entity.Query;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Author: Ing Sergio Abelardo Rodríguez Vásquez
 * Date: 09/11/2023
 * Email: ingsergiorodriguezv@gmail.com
 **/

@Repository
public interface QueryRepository extends JpaRepository<Query, Integer> {

    /**
     * Query encargada de realizar la seleccion de querys
     * por el nombre de query y usuario que crea la query
     *
     * @param name,     nombre del query
     * @param createby,     usuario que crea la query
     * @param pageable, parametro para generar una paginación de la información buscada
     * @return Page o Paginacion de querys resultantes
     */
    @org.springframework.data.jpa.repository.Query(
            value = "SELECT q.* FROM Query q WHERE LOWER(q.createby) LIKE LOWER(CONCAT('%', :createby, '%')) AND LOWER(q.name) LIKE LOWER(CONCAT('%', :name, '%'))",
            nativeQuery = true
    )
    Page<Query> filterNameAndUser(String name, String createby, Pageable pageable);

    /**
     * Query encargada de realizar la seleccion de querys
     * por el nombre de query
     *
     * @param name,     nombre del query
     * @param pageable, parametro para generar una paginación de la información buscada
     * @return Page o Paginacion de querys resultantes
     */
    @org.springframework.data.jpa.repository.Query(
            value = "select * from public.query where lower(name) like lower(concat('%',:name,'%'))",
            nativeQuery = true
    )
    Page<Query> filterName(String name, Pageable pageable);

    /**
     * Query encargada de realizar la seleccion de querys
     * por el usuario que la crea
     *
     * @param user,     usuario que crea la query
     * @param pageable, parametro para generar una paginación de la información buscada
     * @return Page o Paginacion de querys resultantes
     */
    @org.springframework.data.jpa.repository.Query(
            value = "select * from public.query as q where lower(q.createby) like lower(concat('%',:user,'%'))",
            nativeQuery = true
    )
    Page<Query> filterUser(String user, Pageable pageable);

    /**
     * Query encargada de realizar la busqueda por nombre de la query
     * @param name, nombre de la query que se busca
     * @return Un optional de Query buscada por el parametro nombre
     */
    Optional<Query> findByName(String name);
}
