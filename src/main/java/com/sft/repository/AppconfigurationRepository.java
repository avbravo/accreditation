/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.sft.repository;

import com.jmoordb.core.annotation.repository.Lookup;
import com.jmoordb.core.annotation.repository.Query;
import com.jmoordb.core.annotation.repository.Repository;
import com.jmoordb.core.model.Search;
import com.jmoordb.core.repository.CrudRepository;
import com.sft.model.Appconfiguration;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author avbravo
 */
@Repository(entity = Appconfiguration.class)
public interface AppconfigurationRepository  extends CrudRepository<Appconfiguration, Long>{
//    @Query(where = "applicative.idapplicative .eq. @idapplicative")
//    public Optional<Appconfiguration> queryByApplicative(Long idapplicative);
//    
//    @Query(where = "idoceano .eq. @id")
//public Optional<Appconfiguration> queryById(Long id);

@Lookup
public List<Appconfiguration> lookup(Search search);
}
