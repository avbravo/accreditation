/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.sft.repository;

import com.jmoordb.core.annotation.repository.Lookup;
import com.jmoordb.core.annotation.repository.Repository;
import com.jmoordb.core.model.Search;
import com.jmoordb.core.repository.CrudRepository;
import com.sft.model.Headquarter;
import java.util.List;

/**
 *
 * @author avbravo
 */
@Repository(entity = Headquarter.class)
public interface HeadquarterRepository extends CrudRepository<Headquarter, Long> {
       @Lookup
public List<Headquarter> lookup(Search search);
}
