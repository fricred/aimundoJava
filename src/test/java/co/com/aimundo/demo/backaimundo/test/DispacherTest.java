/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.aimundo.demo.backaimundo.test;

import co.com.aimundo.demo.backaimundo.Application;
import static org.assertj.core.api.Assertions.assertThat;

import co.com.aimundo.demo.backaimundo.controller.DispacherController;
import co.com.aimundo.demo.backaimundo.service.EmpleadosServiceImpl;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

/**
 * Clase para probar el controlador dispacher que atiende las llamadas. Este
 * test se hace sobre la capa web sin lanzar el servidor de srping boot
 *
 * @author jearm
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class DispacherTest {

    @Autowired
    private DispacherController controller;

    /**
     * Probar que el controladro se injecte correctamente
     *
     * @throws Exception
     */
    @Test
    public void contexLoads() throws Exception {
        assertThat(controller).isNotNull();
    }

    @Autowired
    private MockMvc mockMvc;

    /**
     * Test de 10 solicitudes / llamadas Se configuro una capacidad inicial de
     * 10 empleados 7 operadores 2 supervisores 1 Director
     *
     * @see DispacherController#init()
     *
     * Cuando no existe ningun empleado empleado libre, cada segundo se repite
     * la busqueda hasta que se encuentre uno libre para asignarle la llamada
     * @see EmpleadosServiceImpl#buscarEmpleadoDisponible()
     *
     * Cuando entran mas de 10 llamadas concurrentes, las sigueintes 490
     * concurrencias irian a una cola esperando su turno a ser atendidas debido
     * a la configuraci{on inicial que se le hizo al Async Executor de
     * SrpingBoot
     * @see Application#asyncExecutor()
     * @throws Exception
     */
    @Test
    public void call10times() throws Exception {
        for (int i = 0; i < 10; i++) {
            this.mockMvc.perform(get("/dispacher/llamar")).andDo(print()).andExpect(status().isOk())
                    .andExpect(content().string(containsString("Atendiendo su llamada")));
        }

    }

}
