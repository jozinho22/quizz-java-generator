package com.douineau.test;

public class TestQuestion {

	public static int methodeRecursive(int n, int m) {
		if (m == 0)
			return 1;
		
		return n * methodeRecursive(n, m - 1);
	}
	
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
	
	public static void main(String[] args) {
System.out.println(methodeRecursive(2, 4));
		methodeRecursive(2, 4);
	}

}
