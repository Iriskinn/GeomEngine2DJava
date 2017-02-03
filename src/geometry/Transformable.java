package geometry;


public interface Transformable {
	public Transformable applyTransformation(Transformation trafo) throws InfinitePointException;
}
