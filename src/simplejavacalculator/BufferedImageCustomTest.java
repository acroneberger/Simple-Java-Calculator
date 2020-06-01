package simplejavacalculator;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import javax.imageio.ImageIO;

import org.junit.jupiter.api.Test;


class BufferedImageCustomTest {

	BufferedImageCustom bimage = new BufferedImageCustom();
	
	boolean compareImages(BufferedImage originalScreenshot, BufferedImage currentScreenshot ) throws IOException {
	    ByteArrayOutputStream baosOriginal = new ByteArrayOutputStream();
	    ByteArrayOutputStream baosCurrent = new ByteArrayOutputStream();
	    ImageIO.write((RenderedImage) originalScreenshot, "png", baosOriginal);
	    baosOriginal.flush();
	    byte[] imageInByteOriginal = baosOriginal.toByteArray();
	    baosOriginal.close();
	    ImageIO.write((RenderedImage) currentScreenshot, "png", baosCurrent);
	    baosCurrent.flush();
	    byte[] imageInByteCurrent = baosCurrent.toByteArray();
	    baosCurrent.close();
	    boolean difference = Arrays.equals(imageInByteOriginal, imageInByteCurrent);
	    return difference;
	}
	
	@Test
	//Tests BufferedImageCustom in True case
	void testBufferedImageCustomTrue() throws IOException {
		
		BufferedImage originalImg = 
	            ImageIO.read(new File("src/resources/icon/icon.png"));
		
		BufferedImage ActualImg = (BufferedImage) bimage.imageReturn();
		
		
		assertTrue(compareImages(originalImg, ActualImg));
		
	}
	
	@Test
	//Tests BufferedImageCustom in False case
	void testBufferedImageCustomFalse() throws IOException {
		
		BufferedImage originalImg = 
	            ImageIO.read(new File("Screenshots/screenshot.png"));
		
		BufferedImage ActualImg = (BufferedImage) bimage.imageReturn();
		
		
		assertFalse(compareImages(originalImg, ActualImg));
		
	}
	

	
}