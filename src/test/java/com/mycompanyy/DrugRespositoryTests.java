package com.mycompanyy;
import com.mycompanyy.drug.Drug;
import com.mycompanyy.drug.DrugRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.Optional;
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class DrugRespositoryTests {

    @Autowired
    private DrugRepository repo;

    @Test
    public void testAddNew()
    {
        Drug drug = new Drug();
        drug.setGenericname("");
        drug.setBrand("dasuni");
        drug.setQuantity(2);
        drug.setPrice(2);

        Drug savedDrug = repo.save(drug);


    }

    @Test
    public void testListAll() {
        Iterable<Drug> drugs = repo.findAll();

        for (Drug drug : drugs) {
            System.out.println(drug);

        }
    }
    @Test
    public void testUpdate()
    {
        Integer drugId=1;
        Optional<Drug> optionalUser = repo.findById(drugId);
        Drug drug =optionalUser.get();
        drug.setBrand("nice");
        repo.save(drug);

        Drug updatedDrug =repo.findById(drugId).get();

    }

}
