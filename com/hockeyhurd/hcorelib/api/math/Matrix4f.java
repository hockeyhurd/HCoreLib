package com.hockeyhurd.hcorelib.api.math;

/**
 * Simple wrapper esque class for using 4 float based vector3's.
 *
 * @author hockeyhurd
 * @version 8/7/2015.
 */
public final class Matrix4f {

	private Vector3<Float> vecU1V1, vecU1V0, vecU0V0, vecU0V1;

	public static final Matrix4f identity = new Matrix4f();

	/**
	 * Generic matrix, with four 'zeroed' Vector3f's.
	 */
	public Matrix4f() {
		vecU1V1 = Vector3.zero.getVector3f();
		vecU1V0 = Vector3.zero.getVector3f();
		vecU0V0 = Vector3.zero.getVector3f();
		vecU0V1 = Vector3.zero.getVector3f();
	}

	/**
	 * @param vecU1V1 u1v1
	 * @param vecU1V0 u1v0
	 * @param vecU0V0 u0v0
	 * @param vecU0V1 u0v1
	 */
	public Matrix4f(Vector3<Float> vecU1V1, Vector3<Float> vecU1V0, Vector3<Float> vecU0V0, Vector3<Float> vecU0V1) {
		if (vecU1V1 == null || vecU1V0 == null || vecU0V0 == null || vecU0V1 == null) throw new NullPointerException("Parameters in Matrix4f mustn't be null!");

		this.vecU1V1 = vecU1V1;
		this.vecU1V0 = vecU1V0;
		this.vecU0V0 = vecU0V0;
		this.vecU0V1 = vecU0V1;
	}

	/**
	 * @param vecU1V1 u1v1
	 * @param vecU1V0 u1v0
	 * @param vecU0V0 u0v0
	 * @param vecU0V1 u0v1
	 * @return newly created matrix4f object.
	 */
	public static Matrix4f createMatrix(Vector3<Float> vecU1V1, Vector3<Float> vecU1V0, Vector3<Float> vecU0V0, Vector3<Float> vecU0V1) {
		return new Matrix4f(vecU1V1, vecU1V0, vecU0V0, vecU0V1);
	}

	/**
	 * Gets the matrix projection from established vectors.
	 *
	 * @return vector3 array.
	 */
	public Vector3<?>[] getProjection() {
		return new Vector3<?>[] { vecU1V1, vecU1V0, vecU0V0, vecU0V1 };
	}

}
