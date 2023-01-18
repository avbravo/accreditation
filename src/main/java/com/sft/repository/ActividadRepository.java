/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.sft.repository;

import com.jmoordb.core.annotation.repository.Repository;
import com.jmoordb.core.repository.CrudRepository;
import com.sft.model.Actividad;

/**
 *
 * @author avbravo
 */
@Repository(database = "{mongodb.database1}", entity = Actividad.class)
public interface ActividadRepository extends CrudRepository<Actividad, Long>{
    
}
