package com.douineau.test;

import java.io.File;
import java.io.IOException;

import com.douineau.utils.FileReader;

public class TestQuestion {
	
	public static void main(String[] args) throws IOException {
		
		FileReader reader = new FileReader();
		File jsonFile = reader.getFile("datas/myFile.json");
	}
	


//	public static int[] getXXX(int[] integers) {
//
//		int i, j, min, tmp;
//
//		for (i = 0 ; i < integers.length - 1 ; i++) {
//			min = i;
//			for (j = i + 1; j < integers.length; j++) {
//				if (integers[j] < integers[min]) {
//					min = j ;
//				}
//			}
//
//			if (min != integers[i]) {
//				tmp = integers[min];
//				integers[min] = integers[i];
//				integers[i] = tmp;
//
//			}
//		}
//		return integers;
//	}
//	
//	public static int getXXX(int n, int m) {
//		int accumulator = 1;
//
//		for (int i = 0; i < m; i++) {
//			accumulator *= n;
//		}
//		return accumulator;
//	}
//	
//	public static double getXXX(int nombre) {
//		double X0 = 1;
//		double Xn = (X0 + nombre / X0) / 2;
//
//		double Xn_Plus_1 = 0;
//		for (int i = 0; i < 10; i++) {
//			Xn_Plus_1 = (Xn + nombre / Xn) / 2;
//			Xn = Xn_Plus_1;
//		}
//		return Xn_Plus_1;
//	}
//	
//	public static int methodeRecursive(int n, int m) {
//		if (m == 0)
//			return 1;
//
//		return n * methodeRecursive(n, m - 1);
//	}
//
//	public static boolean isXXX(int n) {
//		boolean isXXX = true;
//		if (n % 2 == 0) {
//			isXXX = false;
//		} 
//		return isXXX;
//	}
	
//	public static boolean isXXX(int n) {
//		boolean isXXX = true;
//		if (n < 0) {
//			isXXX = false;
//		} else if (n != 0 && n != 1) {
//			for (int i = 2; i <= n / 2; i++) {
//				if (n != i && n % i == 0) {
//					isXXX = false;
//					break;
//				}
//			}
//		}
//		return isXXX;
//	}
	
//	public static int methodeRecursive(int n)  {
//	    if(n == 0)
//	        return 0;
//	    else if(n == 1)
//	      return 1;
//	    else
//	      return methodeRecursive(n - 1) + methodeRecursive(n - 2);
//	}

//	public static int methodeRecursive(int nombre) {
//		int x = 0;
//		if (nombre == 1) {
//			x = 1;
//			return x;
//		} else {
//			// on appelle encore la fonction qui s'appellera encore elle meme si nombre est != de 1
//			x = nombre * methodeRecursive(nombre - 1); 
//			System.out.println(x);
//			return x;
//		}
//	}



}
