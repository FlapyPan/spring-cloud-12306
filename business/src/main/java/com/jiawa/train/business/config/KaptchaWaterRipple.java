package com.jiawa.train.business.config;

import com.google.code.kaptcha.GimpyEngine;
import com.google.code.kaptcha.NoiseProducer;
import com.google.code.kaptcha.util.Configurable;
import com.jhlabs.image.RippleFilter;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.util.Random;

public class KaptchaWaterRipple extends Configurable implements GimpyEngine {
	public KaptchaWaterRipple(){}

	@Override
	public BufferedImage getDistortedImage(BufferedImage baseImage) {
		NoiseProducer noiseProducer = this.getConfig().getNoiseImpl();
		BufferedImage distortedImage = new BufferedImage(baseImage.getWidth(), baseImage.getHeight(), 2);
		Graphics2D graph = (Graphics2D)distortedImage.getGraphics();
		Random rand = new Random();
		RippleFilter rippleFilter = new RippleFilter();
		rippleFilter.setXAmplitude(7.6F);
		rippleFilter.setYAmplitude(rand.nextFloat() + 1.0F);
		rippleFilter.setEdgeAction(1);
		BufferedImage effectImage = rippleFilter.filter(baseImage, (BufferedImage)null);
		graph.drawImage(effectImage, 0, 0, (Color)null, (ImageObserver)null);
		graph.dispose();
		noiseProducer.makeNoise(distortedImage, 0.1F, 0.1F, 0.25F, 0.25F);
		noiseProducer.makeNoise(distortedImage, 0.1F, 0.25F, 0.5F, 0.9F);
		return distortedImage;
	}
}
