package de.kuno.lazyjam.camera;

import de.kuno.lazyjam.tools.cdi.annotations.Service;

@Service
public class Cam {

	public Integer x;
	public Integer y;
	
	public Cam() {
		x = 0;
		y = 0;
	}

	public Integer getX() {
		return x;
	}

	public void setX(Integer x) {
		this.x = x;
	}

	public Integer getY() {
		return y;
	}

	public void setY(Integer y) {
		this.y = y;
	}

}
