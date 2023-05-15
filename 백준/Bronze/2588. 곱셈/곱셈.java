import java.util.*;

public class Main {
	
	public static void main(String args[]) {
		
		Scanner sc=new Scanner(System.in);
		int a,b,k1,k2,k3;
		a=sc.nextInt();
		b=sc.nextInt();
		
		k1=b/100;
		k2=(b%100)/10;
		k3=(b%100)%10;
		
		System.out.println(a*k3);
		System.out.println(a*k2);
		System.out.println(a*k1);
		System.out.println(a*b);
		
	}

}