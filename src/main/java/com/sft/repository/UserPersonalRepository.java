/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.sft.repository;

import com.jmoordb.core.annotation.repository.Find;
import com.jmoordb.core.annotation.repository.Lookup;
import com.jmoordb.core.annotation.repository.Repository;
import com.jmoordb.core.model.Search;
import com.jmoordb.core.repository.CrudRepository;
import com.sft.model.User;
import com.sft.model.UserPersonal;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author avbravo
 */
@Repository(entity = UserPersonal.class,collection = "user")
public interface UserPersonalRepository extends CrudRepository<UserPersonal, Long> {
    @Find
    public Optional<UserPersonal> findByUsername(String username);
    
    @Lookup
public List<UserPersonal> lookup(Search search);
}
