# Activité Pratique N°3 : Spring MVC Spring Data JPA Thymeleaf
Ce TP constitue une extension du TP précédent, à savoir : [Activité Pratique N°2 : ORM, Jpa, Hibernate Spring Data](https://github.com/osmel1/tpSpring1)

## Les utilisations de l'application : 
- ### Authentification des utilisateurs
  - L'authentification des utilisateurs est gérée par la classe SecurityConfiguration, qui sert de configuration de sécurité dans l'application. Cette classe étend WebSecurityConfigurerAdapter afin de personnaliser la configuration de la sécurité HTTP. Son rôle principal est de définir et de paramétrer les aspects de sécurité de l'application.

  - Dans cette configuration, on fait appel au service UserDetailsManager pour charger les détails des utilisateurs depuis la base de données. Pour utiliser UserDetailsManager, il est nécessaire de redéfinir la méthode loadUserByUsername.

  - Deux classes doivent être définies (si elles ne le sont pas déjà) pour représenter l'utilisateur et le rôle, ainsi que leurs repositories respectifs. Ces classes et repositories sont essentiels pour la gestion des utilisateurs et des rôles au sein de l'application.

  - Dans le cas spécifique de cette application, qui se concentre uniquement sur l'authentification des utilisateurs, seule la méthode loadUserByUsername est utilisée. Cependant, si l'application nécessite également un processus d'inscription, les autres méthodes définies dans l'interface SecurityService peuvent être utilisées.
- ## Application Web JEE basée sur Spring MVC, Thylemeaf et Spring Data JPA
Dans ce tp on a creer une application web qui donne l'utilisateur la possibilites de : 
  
  - Pour gérer les mises en page réutilisables dans les applications web pour garder le meme navbar et changer le contenu on a utliser une template 'layout' en utilisant `**ThymleafLayout**`
  ```<body>
<nav class="navbar navbar-expand-sm bg-light navbar-light">
    <div class="container-fluid">
        <ul class="navbar-nav">
            <li class="nav-item">
                <a class="nav-link active" href="#">HomePage</a>
            </li>
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown">Patients</a>
                <ul class="dropdown-menu">
                    <li><a class="dropdown-item" sec:authorize="hasAuthority('ADMIN')" th:href="@{/admin/formPatient}">Nouveau</a></li>
                    <li><a class="dropdown-item" sec:authorize="hasAuthority('USER')||hasAuthority('ADMIN')" th:href="@{/user/index}">Chercher</a></li>
                </ul>
            </li>
        </ul>
        <ul class="navbar-nav" >
            <li class="nav-item dropdown" sec:authorize="isAuthenticated()">
                <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown">
                    <span sec:authentication="name"></span>
                </a>
                <ul class="dropdown-menu" >
                    <li><a class="dropdown-item" th:href="@{/logout}">Log Out</a></li>
                </ul>
            </li>
            <li sec:authorize="!isAuthenticated()">
                <a class="nav-link" th:href="@{/login}" >Login</a>
            </li>
        </ul>
    </div>
</nav>
<section layout:fragment="content">

</section>

</body>
```
    - Dependencies :
  
  ![image](https://github.com/osmel1/tp_springbot_3/assets/110778429/f4579f3d-30ff-4b67-905d-d107a7735b6c)

  -  Afficher les patients `templates\patient.html`
  -  Faire la pagination `templates\patient.html`
  -  Chercher les patients `templates\patient.html`
  -  Supprimer un patient `templates\patient.html`
  ![image](https://github.com/osmel1/tp_springbot_3/assets/110778429/2fafeb33-e4ae-4c23-88aa-5a5939bbcd2f)
<br><br>
  -  Modifier un patien `templates\editPatient.html`
  ![image](https://github.com/osmel1/tp_springbot_3/assets/110778429/c712e775-2c6b-4376-a4fd-b44aec1330d6)
<br><br>
  -  Ajouter Un patient `templates\formPatient.html`
    ![image](https://github.com/osmel1/tp_springbot_3/assets/110778429/edf60356-cb8b-4146-8ddd-c412536f1520)
<br><br>
  - Pour valider les entities (exemples : avoir au moins 5 caracteres , la forme de date ...) on a utiliser  la dependance suivant
![image](https://github.com/osmel1/tp_springbot_3/assets/110778429/4deba0a6-cd24-40a2-a1f7-00c7746e768f)
