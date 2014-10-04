package de.kuno.lazyjam.helper.map;


public class MapObjectEntity {

	private int gid;
	private int oid;
	private String name, type;
	private int width, height;
	private int x, y;

	public MapObjectEntity(int gid, int oid, String name, String type, int x, int y, int width, int height) {
		this.gid = gid;
		this.oid = oid;
		this.name = name;
		this.type = type;
		this.width = width;
		this.height = height;
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public String getProperty(String name) {
		// TODO:
		// TiledMap map = GameState.getInstance().getMap();

		// return map.getObjectProperty(gid, oid, name, "no prop");
		return null;

	}

	public int getGid() {
		return gid;
	}

	public void setGid(int gid) {
		this.gid = gid;
	}

	public int getOid() {
		return oid;
	}

	public void setOid(int oid) {
		this.oid = oid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

}
