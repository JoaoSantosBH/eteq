package upload;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.sql.SQLException;
import persistencia.ParametrosDAO;
 
/*
 * @author mkyong
 *
 */
public class RedefinirTamanhoImagem {
 
	private static  int IMG_WIDTH = 500;
	private static  int IMG_HEIGHT = 400;
        
        
 

    public static BufferedImage resizeImageWithHint(BufferedImage originalImage, int type, String finalidade) throws SQLException{
        
        if(finalidade.equals("patrimonio")){
            String idLargura = "3";
            String idAltura = "4";
            IMG_WIDTH = ParametrosDAO.pegarValorParametro(idLargura);
            IMG_HEIGHT = ParametrosDAO.pegarValorParametro(idAltura);
        } else if(finalidade.equals("fotoPerfil")){
            String idLargura = "5";
            String idAltura = "6";
            IMG_WIDTH = ParametrosDAO.pegarValorParametro(idLargura);
            IMG_HEIGHT = ParametrosDAO.pegarValorParametro(idAltura);
        }
        
        
	BufferedImage resizedImage = new BufferedImage(IMG_WIDTH, IMG_HEIGHT, type);
	Graphics2D g = resizedImage.createGraphics();
	g.drawImage(originalImage, 0, 0, IMG_WIDTH, IMG_HEIGHT, null);
	g.dispose();	
	g.setComposite(AlphaComposite.Src);
 
	g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
	RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	g.setRenderingHint(RenderingHints.KEY_RENDERING,
	RenderingHints.VALUE_RENDER_QUALITY);
	g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
	RenderingHints.VALUE_ANTIALIAS_ON);
 
	return resizedImage;
    }	
}