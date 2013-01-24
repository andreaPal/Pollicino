package com.example.pollicino;

public class Clean {
	private static PointDAO dao;

	public static void clean() {
		dao = new PointDAO_DB_impl();
		dao.open();
		dao.clear();
	}
}
