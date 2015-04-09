package computergraphics.datastructures;

/**
 * Helper class to handle coordinates.
 */
public class Coordinates {

	private int x = 0;
	private int y = 0;
	private int z = 0;

	/**
	 * Constructor.
	 * 
	 * @param x
	 *            the x axis
	 * @param y
	 *            the y axis
	 * @param z
	 *            the z axis
	 */
	public Coordinates(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	@Override
	public boolean equals(Object obj) {
		if ((obj instanceof Coordinates)) {
			if ((((Coordinates) obj).getX() == x)
					&& (((Coordinates) obj).getY() == y)
					&& (((Coordinates) obj).getZ() == z)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public int hashCode() {
		int hash = 1;
		hash = hash * 17 + x;
		hash = hash * 31 + y;
		hash = hash * 51 + z;
		return hash;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getZ() {
		return z;
	}

	@Override
	public String toString() {
		String s = String.valueOf(x) + " + " + String.valueOf(y) + " + "
				+ String.valueOf(z);
		return s;
	}
}
