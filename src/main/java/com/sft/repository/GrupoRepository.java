/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sft.repository;

import com.jmoordb.core.annotation.repository.Find;
import com.jmoordb.core.annotation.repository.Lookup;
import com.jmoordb.core.annotation.repository.Repository;
import com.jmoordb.core.model.Search;
import com.jmoordb.core.repository.CrudRepository;
import com.sft.model.Grupo;
import java.util.List;

/**
 *
 * @author avbravo
 */
@Repository(database = "{mongodb.database1}",entity = Grupo.class)
public interface GrupoRepository extends CrudRepository<Grupo,Long> {
      @Find
    public List<Grupo> findByGrupo(String grupo);
    
    @Lookup
public List<Grupo> lookup(Search search);
}
