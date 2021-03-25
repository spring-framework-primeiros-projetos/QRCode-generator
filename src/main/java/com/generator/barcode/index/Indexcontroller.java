/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.generator.barcode.index;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import javax.annotation.security.PermitAll;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Apresenta tela inicial.
 * @author Alvaro
 */
@Api(description = "Apresenta tela inicial.")
@Controller
public class Indexcontroller {
    
    /**
     * Apresenta tela inicial.
     * @return 
     */
    @ApiOperation(value = "Endpoint para Apresentar tela inicial.")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Tela inicial."),
    })
    @GetMapping("")
    @PermitAll
    public ModelAndView Index(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("Index");
        return mv;
    }
}
