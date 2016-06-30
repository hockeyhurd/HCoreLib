package com.hockeyhurd.hcorelib.api.math;

/**
 * @author hockeyhurd
 * @version 6/29/2016.
 */
@SuppressWarnings("unchecked")
public class Cube<N> {

	public Vector3<N> min, max;

	/**
	 * Protected constructor provided for quickly initializing
	 * internal variables commonly used in the following functions.
	 */
	protected Cube() {
		min = Vector3.zero.copy();
		max = Vector3.zero.copy();
	}

	/**
	 * <bold>NOTE:</bold> Although not necessary, the min position should be the
	 * 		bottom-left most vector and inversely the max position should be the
	 * 	    upper-right most vector.  Through this constructor, we ensure this
	 * 	    guideline is followed accordingly.
	 *
	 * @param min Vec3 starting position (bottom-left oriented).
	 * @param max Vec3 starting position (top-right oriented).
	 */
	public Cube(Vector3<N> min, Vector3<N> max) {
		final double x0 = ((Number) min.x).doubleValue();
		final double y0 = ((Number) min.y).doubleValue();
		final double z0 = ((Number) min.z).doubleValue();

		final double x1 = ((Number) max.x).doubleValue();
		final double y1 = ((Number) max.y).doubleValue();
		final double z1 = ((Number) max.z).doubleValue();

		this.min = new Vector3<N>(x0 <= x1 ? (N) (Number) x0 : (N) (Number) x1, y0 <= y1 ? (N) (Number) y0 : (N) (Number) y1, 
				z0 <= z1 ? (N) (Number) z0 : (N) (Number) z1);

		this.max = new Vector3<N>(x0 >= x1 ? (N) (Number) x0 : (N) (Number) x1, y0 >= y1 ? (N) (Number) y0 : (N) (Number) y1,
				z0 >= z1 ? (N) (Number) z0 : (N) (Number) z1);
	}

	/**
	 * @param pos Vec3 starting position (bottom-left oriented).
	 * @param width Magnitude width.
	 * @param height Magnitude height.
	 * @param depth Magnitude depth.
	 */
	public Cube(Vector3<N> pos, N width, N height, N depth) {
		this.min = pos;

		max = pos.copy();
		max.add(new Vector3<N>(width, height, depth));
	}

	/**
	 * Gets a copy of the cube.
	 *
	 * @return Copied cube.
	 */
	public Cube<N> copy() {
		final Cube<N> copy = new Cube<N>();

		copy.min = min.copy();
		copy.max = max.copy();

		return copy;
	}

	/**
	 * Checks if vector is contained in the cube.
	 *
	 * @param vec Vector3d to reference.
	 * @return boolean result.
	 */
	public boolean isInBounds(Vector3<Double> vec) {
		final Vector3<Double> min = this.min.getVector3d();
		final Vector3<Double> max = this.max.getVector3d();

		return vec.x >= min.x && vec.x <= max.x && vec.y >= min.y && vec.y <= max.y && vec.z >= min.z && vec.z <= max.z;
	}

	/**
	 * Gets the mathematical center position for the cube.
	 *
	 * @return Vector3d center position.
	 */
	public Vector3<Double> getCenter() {
		final Vector3<Double> min = this.min.getVector3d();
		final Vector3<Double> max = this.max.getVector3d();

		return new Vector3<Double>((max.x - min.x) / 2.0d, (max.y - min.y) / 2.0d, (max.z - min.z) / 2.0d);
	}

	/**
	 * Gets the normalized center vector3i i.e. by Minecraft's
	 * block position.
	 *
	 * @return Vector3d although realistically should be a Vector3i.
	 */
	public Vector3<Double> getNormalizedCenter() {
		final Vector3<Double> min = this.min.getVector3d();
		final Vector3<Double> max = this.max.getVector3d();

		return new Vector3<Double>((1.0d + max.x - min.x) / 2.0d, (1.0d + max.y - min.y) / 2.0d, (1.0d + max.z - min.z) / 2.0d);
	}

	/**
	 * Gets the mathimatical volume of the cube.
	 *
	 * @return double volume.
	 */
	public double getVolume() {
		final Vector3<Double> min = this.min.getVector3d();
		final Vector3<Double> max = this.max.getVector3d();

		final Vector3<Double> ret = max.subtract(min);

		return Mathd.abs(ret.x * ret.y * ret.z);
	}

	/**
	 * Gets the normalized volume i.e. Minecraft's applicable volume
	 * by cubic blocks.
	 *
	 * @return double volume although realistically should be an integer.
	 */
	public double getNormalizedVolume() {
		final Vector3<Double> min = this.min.getVector3d();
		final Vector3<Double> max = this.max.getVector3d();

		final double difX = max.x >= min.x ? max.x - min.x : min.x - max.x;
		final double difY = max.y >= min.y ? max.y - min.y : min.y - max.y;
		final double difZ = max.z >= min.z ? max.z - min.z : min.z - max.z;
		
		final Vector3<Double> ret = new Vector3<Double>(1.0d + difX, 1.0d + difY, 1.0d + difZ);

		return Mathd.abs(ret.x * ret.y * ret.z);
	}

	/**
	 * Translates cube by said vector.
	 *
	 * @param vec vector quantity to translate.
	 * @return this instance.
	 */
	public Cube<N> translate(Vector3<N> vec) {
		min.add(vec);
		max.add(vec);

		return this;
	}

	/**
	 * Translates cube by said vector.
	 *
	 * @param magnitude quantity to translate.
	 * @return this instance.
	 */
	public Cube<N> translate(N magnitude) {
		final Vector3<N> translationVec = new Vector3<N>(magnitude, magnitude, magnitude);

		min.add(translationVec);
		max.add(translationVec);

		return this;
	}

	/**
	 * Rotates cube by axis and angle about a reference vector3i.
	 *
	 * @param refVec Vector3i reference vec.
	 * @param axis EnumAxis to rotate on.
	 * @param angle double to rotate by (degrees).
	 * @return
	 */
	public Cube<N> rotate(Vector3<Double> refVec, EnumAxis axis, double angle) {
		if (axis == EnumAxis.X) {
			refVec.x = 0.0d;

			min = (Vector3<N>) Vector3.rotateAroundXAxis(min.getVector3d().subtract(refVec), angle);
			max = (Vector3<N>) Vector3.rotateAroundXAxis(max.getVector3d().subtract(refVec), angle);
		}

		else if (axis == EnumAxis.Y) {
			refVec.y = 0.0d;

			min = (Vector3<N>) Vector3.rotateAroundYAxis(min.getVector3d().subtract(refVec), angle);
			max = (Vector3<N>) Vector3.rotateAroundYAxis(max.getVector3d().subtract(refVec), angle);
		}

		else {
			refVec.z = 0.0d;

			min = (Vector3<N>) Vector3.rotateAroundZAxis(min.getVector3d().subtract(refVec), angle);
			max = (Vector3<N>) Vector3.rotateAroundZAxis(max.getVector3d().subtract(refVec), angle);
		}

		return this;
	}

	/**
	 * Rotates cube by axis and angle.
	 *
	 * @param axis EnumAxis to rotate on.
	 * @param angle double to rotate by (degrees).
	 * @return cube instance.
	 */
	public Cube<N> rotate(EnumAxis axis, double angle) {
		return rotate(getCenter(), axis, angle);
	}

}
