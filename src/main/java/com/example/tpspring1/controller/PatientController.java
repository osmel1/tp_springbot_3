package com.example.tpspring1.controller;

import com.example.tpspring1.models.Patient;
import com.example.tpspring1.repository.PatientRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@AllArgsConstructor
public class PatientController {
    private PatientRepository patientRepository;
    @GetMapping("/user/index")
    public String index(Model model ,
                        @RequestParam (name="page",defaultValue = "0") int page,
                        @RequestParam (name="size",defaultValue = "5") int size,
                        @RequestParam (name="keyword",defaultValue = "") String keyword
    ){
        //to get the list of patients with pagination and the keyword
        Page<Patient> patients = patientRepository.findByNomContains(keyword, PageRequest.of(page, size));
        model.addAttribute("patients", patients);
        model.addAttribute("pages", new int[patients.getTotalPages()]);
        model.addAttribute("currentPage", page);
        model.addAttribute("keyword", keyword);
        return "patients";
    }

    //on ajoute le keyword et currentPage pour garder les donnes apres la redirection
    @GetMapping("/admin/delete")
    public String delete(Long id , String keyword , int page) {
        System.out.println("you deleted the patient with id = "+id);
        patientRepository.deleteById(id);
        return "redirect:/user/index?page="+page+"&keyword="+keyword;
    }
    @GetMapping("/admin/edit")
    public String edit(Model model, Long id) {
       Patient patient = patientRepository.findById(id).orElse(null);
       if (patient == null) throw new RuntimeException("Patient not found");
       model.addAttribute("patient", patient);
       return "editPatient";
    }
    @GetMapping("/admin/formPatient")
    public String formPatient(Model model){
        model.addAttribute("patient", new Patient());
        return "formPatient";
    }
    @GetMapping("/")
    public String firstPage(){
        return "redirect:/home";
    }
    @GetMapping("/home")
    public String home(){
        return "home";
    }
    @GetMapping("/tata/test")
    public String test(){
        return "test";
    }
    @PostMapping("/admin/save")
    public String save(Model model, @Valid Patient patient, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) return "formPatient";
        patientRepository.save(patient);
        return "redirect:/user/index";
    }
}
