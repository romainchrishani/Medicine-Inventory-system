package com.mycompanyy.drug;


import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface DrugRepository extends CrudRepository<Drug,Integer>{
    public Long countById(Integer id);

    List<Drug> findByGenericnameIsStartingWith(String genericname);

}



