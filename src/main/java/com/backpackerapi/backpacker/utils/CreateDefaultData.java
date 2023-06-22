package com.backpackerapi.backpacker.utils;

import com.backpackerapi.backpacker.dtos.tourist_place.TouristPlaceRequest;
import com.backpackerapi.backpacker.models.Category;
import com.backpackerapi.backpacker.repositories.CategoryRepository;
import com.backpackerapi.backpacker.security.entity.Role;
import com.backpackerapi.backpacker.security.enums.ERole;
import com.backpackerapi.backpacker.security.service.RoleService;
import com.backpackerapi.backpacker.services.tourist_place.TouristPlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Esta clase es temporal solo para crear los roles
 * Sólo funciona con bases de datos en memoria porque escrito en disco tarda demasiado
 */
@Component
public class CreateDefaultData implements CommandLineRunner {

    @Autowired
    private RoleService roleService;

    @Autowired
    private TouristPlaceService touristPlaceService;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public void run(String... args) throws Exception {

        boolean createTouristPlace = true;
        boolean createRoles = false;
// crear roles
        if(createRoles){
            Role adminRole = new Role(ERole.ROLE_ADMIN);
            Role userRole = new Role(ERole.ROLE_USER);
            if(!roleService.existsByName(ERole.ROLE_ADMIN))
                roleService.save(adminRole);
            if(!roleService.existsByName(ERole.ROLE_USER))
                roleService.save(userRole);
        }

//        Crear categorías
        if(!categoryRepository.existsByName("Cultural"))
            categoryRepository.save(new Category(
                    "Cultural",
                    "motivado por conocer rasgos y elementos distintivos, " +
                            "espirituales y materiales intelectuales y afectivos que caracterizan " +
                            "a una sociedad o un grupo social."
            ));
        if(!categoryRepository.existsByName("Religioso"))
            categoryRepository.save(new Category(
                    "Religioso",
                    "son desplazamientos por motivos de fervor y devoción religiosa, ya sea con la " +
                            "finalidad de cumplir un voto o una promesa, pedir algún tipo de beneficio o " +
                            "agradecer alguno ya recibido. Este tipo de turismo hace visitas repetidas al mismo " +
                            "sitio, lo que podría generar fidelidad del huésped."
            ));
        if(!categoryRepository.existsByName("Gastronómico"))
            categoryRepository.save(new Category(
                    "Gastronómico",
                    "turistas que se desplazan para tener experiencias culinarias y probar determinados " +
                            "tipos de comidas y bebidas. En ocasiones coinciden con festividades cívicas o culturales " +
                            "locales. Viajan para conocer los ingredientes de los platillos o incluso para aprender a " +
                            "prepararlos."
            ));
        if(!categoryRepository.existsByName("Idiomático"))
            categoryRepository.save(new Category(
                    "Idiomático",
                    "la motivación principal es desplazarse para aprender un idioma."
            ));
        if(!categoryRepository.existsByName("Salud o wellness"))
            categoryRepository.save(new Category(
                    "Salud o wellness",
                    "acudir a algún lugar para recibir tratamiento de enfermedades o simple relajación en spas, " +
                            "saunas u otros centros de medicina tradicional o alternativa."
            ));
        if(!categoryRepository.existsByName("Deportivo"))
            categoryRepository.save(new Category(
                    "Deportivo",
                    "realizan viajes relacionados con la práctica de algún deporte como maratones o torneos " +
                            "o para acudir a eventos deportivos como la Fórmula 1 o peleas de box."
            ));
        if(!categoryRepository.existsByName("Parque temático"))
            categoryRepository.save(new Category(
                    "Parque temático",
                    "son los turistas que viajan con la finalidad de disfrutar de los parques temáticos como " +
                            "parques de diversiones, parque de los dinosaurios, AquanLand, etc"
            ));
        if(!categoryRepository.existsByName("Negocio"))
            categoryRepository.save(new Category(
                    "Negocio",
                    "el motivo de viaje está vinculado con la realización de actividades laborales y " +
                            "profesionales. Puede ser realizado de manera grupal o individual."
            ));
        if(!categoryRepository.existsByName("Ecoturismo"))
            categoryRepository.save(new Category(
                    "Ecoturismo",
                    "los viajes que tienen como fin realizar actividades recreativas de conocimiento con la naturaleza."
            ));
        if(!categoryRepository.existsByName("Aventura"))
            categoryRepository.save(new Category(
                    "Aventura",
                    "realizar actividades deportivas relacionadas con desafíos impuestos por la naturaleza."
            ));
        if(!categoryRepository.existsByName("Rural"))
            categoryRepository.save(new Category(
                    "Rural",
                    "el turista participa en actividades que lo involucran a vivir experiencias con la comunidad " +
                            "anfitriona, valorando y respetando mutuamente su identidad cultural, contribuyendo así a una " +
                            "cultura de paz."
            ));
        if(!categoryRepository.existsByName("Paranormal"))
            categoryRepository.save(new Category(
                    "Paranormal",
                    "Se relaciona con fenómenos sobrenaturales en los que se combinan elementos pintorescos y psíquicos " +
                            "en los que no hay una explicación científica o lógica. Este tipo de turismo se basa en hechos extraños, " +
                            "producto de leyendas o supersticiones locales, que atraen a turistas de todo el mundo."
            ));


        // Crear datos de prueba tourist places
        if(touristPlaceService.count() == 0 && createTouristPlace){
            for (int i = 1; i <= 100; i++){
                touristPlaceService.save(
                        new TouristPlaceRequest(
                                "name " + i,
                                null,
                                true,
                                null,
                        "Este es el resumen " + i,
                        "Estas son las palabras clave " + i,
                        "Esta es la descripción " + i
                )
                );
            }
        }
    }
}
