package com.example.pollicino;

import java.util.List;

public interface PointDAO {
	public void open();
	public void close();
	public Point insertPoint(Point point);
	public Point getLastPoint();
	public void deletePoint(Point point);
	public void clear();
	public List<Point> getAllPoint();
}
