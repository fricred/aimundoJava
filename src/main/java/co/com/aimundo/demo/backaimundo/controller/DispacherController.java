/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.aimundo.demo.backaimundo.controller;

import co.com.aimundo.demo.backaimundo.model.EstadoLlamada;
import co.com.aimundo.demo.backaimundo.model.LLamada;
import co.com.aimundo.demo.backaimundo.service.EmpleadoService;
import javax.annotation.PostConstruct;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author jearm
 */
@Controller
public class DispacherController {

    @Autowired
    private EmpleadoService empleadoService;

    @PostConstruct
    public void init() {
        empleadoService.inicializarEmpleados(7, 2, 1);
        System.out.println(empleadoService.consultarEmpleados());
    }

    @GetMapping("/dispacher/llamar")
    @ResponseBody
    public String dispatchCall() {
        String generatedString = RandomStringUtils.randomAlphabetic(10);
        LLamada lLamada = new LLamada(generatedString, EstadoLlamada.COLA);
        empleadoService.atenderLlamada(lLamada);
        return "Atendiendo su llamada " + generatedString;
    }
}
