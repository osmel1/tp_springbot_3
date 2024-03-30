package com.example.tpspring1;

import com.example.tpspring1.models.Medecin;
import com.example.tpspring1.models.Patient;
import com.example.tpspring1.repository.MedecinRepository;
import com.example.tpspring1.repository.PatientRepository;
import com.example.tpspring1.service.PatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

@SpringBootApplication
public class TpSpring1Application  {
    @Autowired
    private PatService patService;
    public static void main(String[] args) {
        SpringApplication.run(TpSpring1Application.class, args);
    }

    CommandLineRunner start(PatService patService , MedecinRepository  medecinRepository){
        return args -> {

            // ajouter un patient
            Patient pat1 = new Patient();
            pat1.setNom("Ahmed");
            pat1.setMalade(false);
            pat1.setDateNaissance(new Date());
            patService.ajouterPatient(pat1);
            //ajouter trois patient on utilisant les streams
            Stream.of("Samir","Sami","Mina").forEach(
                    name->{
                        Patient pat = new Patient();
                        pat.setNom(name);
                        pat.setMalade(false);
                        pat.setDateNaissance(new Date());
                        patService.ajouterPatient(pat);
                    }
            );
            //ajouter des medecins
            Stream.of("Alae","Fatiha","Omar").forEach(
                    name->{
                        Medecin med = new Medecin();
                        med.setNom(name);
                        med.setEmail(name+"@hotmail.com");
                        med.setSpecialite(Math.random() > 0.5 ? "Dentiste" : "Cardiologue");
                        medecinRepository.save(med);
                    }
            );
            //chercher des patients par nom
            List<Patient> patients = patService.rechercherPatients("Samir");
            //chercher un medecin par nom
            //Medecin medecin = medecinRepository.findByNom("Alae");


        };
    }


    @Bean
    CommandLineRunner commandLineRunner(JdbcUserDetailsManager jdbcUserDetailsManager){

        return args -> {
            //To add users to the database
            if (!jdbcUserDetailsManager.userExists("user1")){
                String encodedPassword = passwordEncoder().encode("osmel");
                System.out.println(encodedPassword);
                jdbcUserDetailsManager.createUser(User.withUsername("user1").password(encodedPassword).roles("USER").build());
            }
            if (!jdbcUserDetailsManager.userExists("user2")){
                String encodedPassword = passwordEncoder().encode("osmel");
                System.out.println(encodedPassword);
                jdbcUserDetailsManager.createUser(User.withUsername("user2").password(encodedPassword).roles("USER").build());
            }
            if (!jdbcUserDetailsManager.userExists("admin")){
                String encodedPassword = passwordEncoder().encode("osmel");
                System.out.println(encodedPassword);
                jdbcUserDetailsManager.createUser(User.withUsername("admin").password(encodedPassword).roles("USER","ADMIN").build());
            }
        };

    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
