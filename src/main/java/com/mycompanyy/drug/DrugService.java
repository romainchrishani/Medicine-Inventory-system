package com.mycompanyy.drug;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

@Controller


public class DrugService {




    public final DrugRepository repo;

    @Autowired
    public DrugService(DrugRepository repo) {
        this.repo = repo;
    }

    public List<Drug> listAll()
    {

        return (List<Drug>) repo.findAll();

    }


    public void save(Drug drug) {
        repo.save(drug);
    }

    public Drug get(Integer id) throws DrugNotFoundException {
        Optional<Drug> result = repo.findById(id);
        if(result.isPresent()){
            return result.get();

    }
        throw new DrugNotFoundException("Could not find any drug with ID" + id);
    }

    public List<Drug> search(String name) {
        return repo.findByGenericnameIsStartingWith(name);
    }

    public void delete(Integer id) throws DrugNotFoundException {
        Long count = repo.countById(id);
        if(count == null || count == 0){
            throw new DrugNotFoundException("Could not find any drug with "+id);
        }

        repo.deleteById(id);

    }
    
}
