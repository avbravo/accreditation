/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.sft.repository;

import com.jmoordb.core.annotation.repository.Ping;
import com.jmoordb.core.annotation.repository.Repository;
import com.jmoordb.core.repository.CrudRepository;
import com.sft.model.Applicative;

/**
 *
 * @author avbravo
 */
@Repository(entity = Applicative.class)
public interface ApplicativeRepository extends CrudRepository<Applicative, Long> {
       @Ping
    public Boolean ping();
}
