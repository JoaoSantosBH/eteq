/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import controle.ControladorNegociacao;
import controle.ControladorPatrimonio;
import java.sql.SQLException;
import java.util.Date;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;

/**
 *
 * @author joaosantos
 */

public class AgenteVerificadorPatrimoniosVencidos implements Job{

    public void execute(JobExecutionContext context) {

        try {
            ControladorPatrimonio.verificarSePatrimonioEstaExpirado();
        } catch (SQLException ex) {
            Logger.getLogger(AgenteVerificadorPatrimoniosVencidos.class.getName()).log(Level.SEVERE, null, ex);
        }
        

    }
}
