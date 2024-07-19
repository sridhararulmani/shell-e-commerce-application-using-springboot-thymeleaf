package com.project.shell.component;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.springframework.stereotype.Component;

@Component
public class ProfileImageGenerator {

	public byte[] generateProfileImage(String userName) throws IOException {
		int width = 100;
		int height = 100;
		
		BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

		Graphics2D graphics2d = bufferedImage.createGraphics();
		graphics2d.setColor(Color.LIGHT_GRAY);
		graphics2d.fillRect(0, 0, width, height);
		graphics2d.setColor(Color.WHITE);
		graphics2d.setFont(new Font("Arial", Font.BOLD, 50));

		FontMetrics fontMetrics = graphics2d.getFontMetrics();
		String userNameFirstLetter = userName.substring(0, 1).toUpperCase();
		int x = (width - fontMetrics.stringWidth(userNameFirstLetter)) / 2;
		int y = ((height - fontMetrics.getHeight()) / 2) + fontMetrics.getAscent();

		graphics2d.drawString(userNameFirstLetter, x, y);
		graphics2d.dispose();

		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		ImageIO.write(bufferedImage, "png", byteArrayOutputStream);

		return byteArrayOutputStream.toByteArray();
	}
}
