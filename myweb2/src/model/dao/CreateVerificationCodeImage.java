package model.dao;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

public class CreateVerificationCodeImage {
	private static final int WIDTH = 100;
	private static final int HEIGHT = 30;
	private static final int LENGTH = 4;
	public static final int LINECOUNT =20;
	
	private static final String str = "23456789"
			+ "ABCDEFGHJKLMNPQRSTUVWXYZ" + "abcdefghijklmnpqrstuvwxyz";
	private static Random random = new Random();
	
	public String createCode() {
		String code = "";
		for(int i = 0;i < LENGTH;i ++) {
			char c = str.charAt(random.nextInt(str.length()));
			
			code = code + c;
		}
		return code;
	}
	
	public Color getColor() {
		return new Color(random.nextInt(255),random.nextInt(255),
				random.nextInt(255));
	}
	
	public Font getFont() {
		return new Font("Fixedsys",Font.CENTER_BASELINE,22);
	}
	
	public void drawChar(Graphics g,String code){
		g.setFont(getFont());
		for(int i = 0;i < LENGTH;i++){
			char c = code.charAt(i);
			g.setColor(getColor());
			g.drawString(c + "", 20 * i + 10, 20);
		}
	}
	
	public void drawLine(Graphics g){
		int x = random.nextInt(WIDTH);
		int y = random.nextInt(HEIGHT);
		int x1 = random.nextInt(13);
		int y1 = random.nextInt(15);
		g.setColor(getColor());
		g.drawLine(x, y, x + x1, y + y1);
	}
	
	public BufferedImage CreateImage(String code) {
		BufferedImage image = new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_3BYTE_BGR);
		Graphics g = image.getGraphics();
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		drawChar(g,code);
		for(int i = 0;i < LINECOUNT;i++) {
			drawLine(g);
		}
		g.dispose();
		return image;
	}
}
