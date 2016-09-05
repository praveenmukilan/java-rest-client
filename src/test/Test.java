package test;

public class Test {
	
	public static void main(String args[]){
		int[] a={2,0,4,7,6,1,3,5,4};
		printArray(evenOddPartition(a));
	}
	
	static int[] evenOddPartition(int [] b){
		int i=0,j=b.length-1;
		while(i<j){			
			if(b[i]%2==0)
				i++;
			else if(b[j]%2==1)
				j--;
			//elm at i is odd & elm at j is even. can swap
			else{
				b[i]=b[i]+b[j];
				b[j]=b[i]-b[j];
				b[i]=b[i]-b[j];
				i++;
				j--;
			}
			
		}
		return b;
		
	}
	
	static void printArray(int [] arr){		
		for(int y : arr)
			System.out.print(y+",");
			
	}

}
