/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import controle.ControladorNegociacao;
import java.sql.SQLException;
import java.util.Date;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.annotation.WebListener;
import org.quartz.Job;
import org.quartz.JobExecutionContext;

/**
 *
 * @author joaosantos
 */

public class AgenteVerificadorNegociosPendentes implements Job  {

    
 public void execute(JobExecutionContext context) {

     try {
         ControladorNegociacao.verificarNegociosPendentesPorRespostasInativas();
     } catch (SQLException ex) {
         Logger.getLogger(AgenteVerificadorNegociosPendentes.class.getName()).log(Level.SEVERE, null, ex);
     }

        

    }
    
   

}
