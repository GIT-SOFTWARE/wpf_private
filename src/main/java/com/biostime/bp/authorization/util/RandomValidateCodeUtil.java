package com.biostime.bp.authorization.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

import lombok.extern.slf4j.Slf4j;

/** 
 * 描述: TODO
 *
 * @author <a href="mailto:zhangguojian@hh.global">12552</a>
 * @createDate 2019年5月24日 上午11:14:59
 */
@Slf4j
public class RandomValidateCodeUtil {
	private String randString = "23456789ABCDEFGHIJKLMNPQRSTUVWXYZ";
	private int width = 95;
	private int height = 25;
	private int lineSize = 40;
	private int stringNum = 4;

	private Random random = new Random();

	private String randCode = "";
	private Font getFont() {
		return new Font("Fixedsys", Font.CENTER_BASELINE, 18);
	}
	private Color getRandColor(int fc, int bc) {
		if (fc > 255)
			fc = 255;
		if (bc > 255)
			bc = 255;
		int r = fc + random.nextInt(bc - fc - 16);
		int g = fc + random.nextInt(bc - fc - 14);
		int b = fc + random.nextInt(bc - fc - 18);
		return new Color(r, g, b);
	}
	public String getRandCode() {
		return this.randCode;
	}
	
	
	public BufferedImage randImage() {
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);
		Graphics g = image.getGraphics();
		g.fillRect(0, 0, width, height);
		g.setFont(new Font("Times New Roman", Font.ROMAN_BASELINE, 18));
		g.setColor(getRandColor(110, 133));
		for (int i = 0; i <= lineSize; i++) {
			drowLine(g);
		}
		for (int i = 1; i <= stringNum; i++) {
			drowString(g, i);
		}
		log.info("random code:{}", randCode);
		g.dispose();
		return image;
	}
	
	public BufferedImage randImage(String randCode) {
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);
		Graphics g = image.getGraphics();
		g.fillRect(0, 0, width, height);
		g.setFont(new Font("Times New Roman", Font.ROMAN_BASELINE, 18));
		g.setColor(getRandColor(110, 133));
		for (int i = 0; i <= lineSize; i++) {
			drowLine(g);
		}
		for(int i =0;i<randCode.length();i++) {
			drowString(g,randCode.toCharArray(),i);
		}
		log.info("exist random code:{}", randCode);
		g.dispose();
		return image;
	}

	private void drowString(Graphics g, char[] charArray, int i) {
		g.setFont(getFont());
		g.setColor(new Color(random.nextInt(101), random.nextInt(111), random.nextInt(121)));
		String rand = String.valueOf(charArray[i]);
		g.translate(random.nextInt(3), random.nextInt(3));
		g.drawString(rand, 13 * i, 16);
		
	}
	private void drowString(Graphics g, int i) {
		g.setFont(getFont());
		g.setColor(new Color(random.nextInt(101), random.nextInt(111), random.nextInt(121)));
		String rand = String.valueOf(getRandomString(random.nextInt(randString.length())));
		randCode += rand;
		g.translate(random.nextInt(3), random.nextInt(3));
		g.drawString(rand, 13 * i, 16);
	}

	private void drowLine(Graphics g) {
		int x = random.nextInt(width);
		int y = random.nextInt(height);
		int xl = random.nextInt(13);
		int yl = random.nextInt(15);
		g.drawLine(x, y, x + xl, y + yl);
	}

	public String getRandomString(int num) {
		return String.valueOf(randString.charAt(num));
	}
}
