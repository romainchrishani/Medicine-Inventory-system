package com.mycompanyy.drug;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class drugController {


    @Autowired private DrugService service;
    @GetMapping("/drugs")
    public String showUserList(Model model)
    {
        List<Drug> listDrugs = service.listAll();
        model.addAttribute("listDrugs",listDrugs);
        return "drugs";

    }


    @GetMapping("/drugs/search")
    public String searchDrug(Model model, @RequestParam String drugName) {
        List<Drug> drugs = service.search(drugName);
        model.addAttribute("listDrugs",drugs);
        return "drugs";
    }

    @GetMapping("/drugs/new")
    public String showNewForm(Model model)
    {

        model.addAttribute("drug",new Drug());
        model.addAttribute("pageTitle","Add New Drug");
        return "drug_form";
    }

    @PostMapping("/drugs/save")
    public String saveUser(Drug drug , RedirectAttributes ra)
    {
        service.save(drug);
        return "redirect:/drugs";

    }


     public void save(Drug drug)
     {
         service.save(drug);
     }

        @GetMapping("/drugs/edit/{id}")
        public String showEditForm(@PathVariable("id") Integer id,Model model, RedirectAttributes ra)
        {
            try{
                Drug drug=  service.get(id);
                model.addAttribute("drug",drug);
                model.addAttribute("pageTitle","Edit Drug(ID: " + id+")");
                return "drug_form";
            }catch (DrugNotFoundException e)
            {
                ra.addFlashAttribute("message",e.getMessage());
                return "redirect:/drugs";
            }
    }

    @GetMapping("/drugs/delete/{id}")
    public String deleteDrug(@PathVariable("id") Integer id,Model model, RedirectAttributes ra)
    {
        try{
            service.delete(id);
            ra.addFlashAttribute("message","the drug id" + id+"has been deleted");

        }catch (DrugNotFoundException e)
        {
            ra.addFlashAttribute("message",e.getMessage());

        }
        return "redirect:/drugs";
    }


}
