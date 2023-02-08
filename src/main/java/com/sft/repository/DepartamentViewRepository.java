/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.sft.repository;

import com.jmoordb.core.annotation.repository.Count;
import com.jmoordb.core.annotation.repository.Find;
import com.jmoordb.core.annotation.repository.Lookup;
import com.jmoordb.core.annotation.repository.Repository;
import com.jmoordb.core.model.Search;
import com.jmoordb.core.repository.CrudRepository;
import com.sft.model.DepartamentView;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author avbravo
 */
@Repository(entity = DepartamentView.class,collection = "departament")
public interface DepartamentViewRepository extends CrudRepository<DepartamentView, Long> {
    @Find
    public Optional<DepartamentView> findByDepartament(String departament);
    
    @Lookup
public List<DepartamentView> lookup(Search search);

  @Count()
    public Long count(Search... search);
}
