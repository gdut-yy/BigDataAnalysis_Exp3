package com.devyy.util;

public class MatrixUtil {
	// 两个矩阵乘法运算
	public static double[][] multiMatrix(double[][] a, double[][] b) {
		if (a[0].length != b.length) {
			System.out.println("矩阵无法相乘，请确定行列维度后再重试！");
			return null;
		} else {
			double[][] result = new double[a.length][b[0].length];
			int temp;
			double sum = 0.0;
			for (int row = 0; row < a.length; row++) {
				for (int col = 0; col < b[0].length; col++) {
					sum = 0.0;
					for (temp = 0; temp < a[0].length; temp++) {
						sum += a[row][temp] * b[temp][col];
					}
					result[row][col] = sum;
				}
			}
			return result;
		}
	}

	// 矩阵乘以一个实数
	public static double[][] RealMulMatrix(double[][] a, double number) {
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < a[0].length; j++) {
				a[i][j] = number * a[i][j];
			}
		}
		return a;
	}

	// 矩阵加法运算
	public static double[][] addMatrix(double[][] a, double[][] b) {
		if (a.length != b.length || a[0].length != b[0].length) {
			System.out.println("矩阵无法相加，请确定行列维度后再重试！");
			return null;
		} else {
			double result[][] = new double[a.length][a[0].length];
			int i, j;
			for (i = 0; i < a.length; i++) {
				for (j = 0; j < a[0].length; j++) {
					result[i][j] = a[i][j] + b[i][j];
				}
			}
			return result;
		}
	}
}
