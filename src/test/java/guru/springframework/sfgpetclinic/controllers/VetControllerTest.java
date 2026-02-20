package guru.springframework.sfgpetclinic.controllers;

import guru.springframework.sfgpetclinic.ControllerTests;
import guru.springframework.sfgpetclinic.fauxspring.Model;
import guru.springframework.sfgpetclinic.fauxspring.ModelMapImpl;
import guru.springframework.sfgpetclinic.model.Vet;
import guru.springframework.sfgpetclinic.services.SpecialtyService;
import guru.springframework.sfgpetclinic.services.VetService;
import guru.springframework.sfgpetclinic.services.map.SpecialityMapService;
import guru.springframework.sfgpetclinic.services.map.VetMapService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class VetControllerTest implements ControllerTests {

    VetService vetService;
    SpecialtyService specialtyService;
    VetController vetController;

    @BeforeEach
    void setUp() {
        specialtyService = new SpecialityMapService();
        vetService = new VetMapService(specialtyService);
        vetController = new VetController(vetService);
    }

    @Test
    void listVets() {
        vetService.save(new Vet(1L, "aaa", "bbb", new HashSet<>()));
        vetService.save(new Vet(2L, "ccc", "ddd", new HashSet<>()));

        Model model = new ModelMapImpl();

        String view = vetController.listVets(model);

        Set modelAttribute = (Set)((ModelMapImpl)model).getMap().get("vets");

        assertEquals("vets/index", view);
        assertThat("vets/index").isEqualTo(view);
        assertThat(modelAttribute.size()).isEqualTo(2);
        assertThat(vetService.findAll().size()).isEqualTo(2);
    }
}