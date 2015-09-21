package com.hockeyhurd.api.math;

/**
 * Helper math class for creating rectangles.
 * 
 * @author hockeyhurd
 * @version May 21, 2015
 */
public class Rect<N> {

	public Vector2<N> min, max;
	private int color = 0x0;
	
	/**
	 * @param min min/top-left corner.
	 * @param max max/bottom-right corner.
	 */
	public Rect(Vector2<N> min, Vector2<N> max) {
		this(min, max, 0x0);
	}
	
	/**
	 * @param min min/top-left corner.
	 * @param max max/bottom-right corner.
	 * @param color color of rectangle.
	 */
	public Rect(Vector2<N> min, Vector2<N> max, final int color) {
		this.min = min;
		this.max = max;
		this.color = color;
	}
	
	/**
	 * @param x min/top-left x-position.
	 * @param y min/top-left y-position.
	 * @param width max/bottom-right x-position.
	 * @param height max/bottom-right y-position.
	 */
	public Rect(N x, N y, N width, N height) {
		this(x, y, width, height, 0x0);
	}
	
	/**
	 * @param x min/top-left x-position.
	 * @param y min/top-left y-position.
	 * @param width max/bottom-right x-position.
	 * @param height max/bottom-right y-position.
	 * @param color color of rectangle.
	 */
	public Rect(N x, N y, N width, N height, final int color) {
		this.min = new Vector2<N>(x, y);
		this.max = this.min.copy().add(new Vector2<N>(width, height));
		this.color = color;
	}
	
	/**
	 * @return color of rectangle.
	 */
	public int getColor() {
		return color;
	}
	
	/**
	 * Sets color of rectangle.
	 * 
	 * @param color color to set.
	 */
	public void setColor(final int color) {
		if (color >= 0x0 && color <= 0xff) this.color = color;
		else if (color < 0x0) this.color = 0x0;
		else if (color > 0xff) this.color = 0xff;
	}
	
	/**
	 * Checks if vector point is in the bounds of this rectangle.
	 * 
	 * @param vec vector point to check.
	 * @return true if bounds, else rreturns false.
	 */
	public boolean isInBounds(Vector2<Double> vec) {
		Vector2<Double> min = this.min.getVector2d();
		Vector2<Double> max = this.max.getVector2d();
		
		return vec.x >= min.x && vec.x <= max.x && vec.y >= min.y && vec.y <= max.y;
	}
	
	/**
	 * Gets the center vector point in rectangle.
	 * 
	 * @return center vector point.
	 */
	public Vector2<Double> getCenter() {
		Vector2<Double> min = this.min.getVector2d();
		Vector2<Double> max = this.max.getVector2d();
		
		return new Vector2<Double>((max.x - min.x) / 2d + min.x, (max.y - min.y) / 2d + min.y);
	}

	/**
	 * Difference between this and 'getCenter()' function, this accounts for
	 * the same block.
	 * <br><tab>min: (0, 0), max: (3, 3)</tab>
	 * <br><tab>return vector2d: ((max.x - min.x) / 2d + min.x, (max.y - min.y) / 2d + min.y)</tab>
	 *
	 * @return center vector point.
	 */
	public Vector2<Double> getNormalizedCenter() {
		Vector2<Double> min = this.min.getVector2d();
		Vector2<Double> max = this.max.getVector2d();

		return new Vector2<Double>((1d + max.x - min.x) / 2d + min.x, (1d + max.y - min.y) / 2d + min.y);
	}
	
	/**
	 * @return area of rectangle.
	 */
	public double getArea() {
		Vector2<Double> min = this.min.getVector2d();
		Vector2<Double> max = this.max.getVector2d();
		
		Vector2<Double> ret = new Vector2<Double>(max.x - min.x, max.y - min.y);
		
		return Math.abs(ret.x * ret.y);
	}

	/**
	 * Difference between this and 'getArea()' function, this accounts for
	 * the same block.
	 * <br><tab>min: (0, 0), max: (3, 3)</tab>
	 * <br><tab>return vector2d: (1 + max.x - min.x), (1 + max.y - min.y)</tab>
	 *
	 * @return area of rectangle.
	 */
	public double getNormalizedArea() {
		Vector2<Double> min = Vector2.absoluteDouble(this.min.getVector2d());
		Vector2<Double> max = Vector2.absoluteDouble(this.max.getVector2d());

		Vector2<Double> ret = new Vector2<Double>(1d + max.x - min.x, 1d + max.y - min.y);

		return Math.abs(ret.x * ret.y);
	}
	
	/**
	 * Translates corners by said vector.
	 * 
	 * @param vec vector quantity to translate.
	 */
	public void translate(Vector2<N> vec) {
		this.min.add(vec);
		this.max.add(vec);
	}
	
	/**
	 * Rotates corners about given vector point with said angle in degrees.
	 * 
	 * @param ref reference vector point to rotate about.
	 * @param angle angle in degrees to rotate by.
	 */
	public void rotate(Vector2<Double> ref, double angle) {
		this.min.rotate(ref, angle);
		this.max.rotate(ref, angle);
	}
	
	/**
	 * Rotates corners about center vector point of this rectangle with said angle in degrees.
	 * 
	 * @param angle angle in degrees to rotate by.
	 */
	public void rotate(double angle) {
		Vector2<Double> center = getCenter();
		
		this.min.rotate(center, angle);
		this.max.rotate(center, angle);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Rect)) return false;

		Rect<?> rect = (Rect<?>) o;

		if (color != rect.color) return false;
		if (!min.equals(rect.min)) return false;
		return max.equals(rect.max);

	}

	@Override
	public int hashCode() {
		int result = min.hashCode();
		result = 31 * result + max.hashCode();
		result = 31 * result + color;
		return result;
	}

	@Override
	public String toString() {
		StringBuilder ret = new StringBuilder();

		ret.append("Min: ");
		ret.append(min.x);
		ret.append(", ");
		ret.append(min.y);
		ret.append(", Max: ");
		ret.append(max.x);
		ret.append(", ");
		ret.append(max.y);
		ret.append(", Color: ");
		ret.append(color);

		return ret.toString();
	}

}
