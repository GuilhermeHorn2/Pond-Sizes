package misc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class main {

	public static void main(String[] args) {
		
	/* ?\n;||(or) e &&(and)*/
		
	List<Integer> c1 = new ArrayList<>(Arrays.asList(0,2,1,0));
	List<Integer> c2 = new ArrayList<>(Arrays.asList(0,1,0,1));
	List<Integer> c3 = new ArrayList<>(Arrays.asList(1,1,0,1));
	List<Integer> c4 = new ArrayList<>(Arrays.asList(0,0,0,1));
	List<Integer> c5 = new ArrayList<>(Arrays.asList(0,1,1,1));
	List<List<Integer>> m = new ArrayList<>(Arrays.asList(c1,c2,c3,c4,c5));
	
	System.out.println(pond_sizes(m));
	
		
}

private static List<Integer> pond_sizes(List<List<Integer>> m){
	
	List<List<Integer>> summed = new ArrayList<>();
	
	for(int i = 0;i < m.size();i++){
		summed.add(new ArrayList<>());
		for(int j = 0;j < m.get(i).size();j++){
			summed.get(i).add(0);
		}
	}
	
	return pond_sizes(m,summed);
	
}
	
private static List<Integer> pond_sizes(List<List<Integer>> m,List<List<Integer>> summed){
	
	List<Integer> sizes = new ArrayList<>();
	
	for(int i = 0;i < m.size();i++) {
		for(int j = 0;j < m.get(i).size();j++){
			if(summed.get(i).get(j) == 0 && m.get(i).get(j) == 0){
				if(sizes.size() > 0) {
					sizes.add(sum_path(m,summed,i,j)-sizes.get(sizes.size()-1));
				}
				else {
					sizes.add(sum_path(m,summed,i,j));
				}
			}
			
		}
		
	}
	
	return sizes;
}
	
private static boolean has_path(List<List<Integer>> m,List<List<Integer>> summed,int i,int j){


		//setting the boundaries
		int high_i = i+1;
		if(i+1 >= m.size()){
			high_i = i;
		}
		int low_j = j-1;
		if(j-1 < 0){
			low_j = j;
		}
		int high_j = j+1;
		if(j+1 >= m.get(i).size()){
			high_j = j;
		}
		//

		//max of 6 iterations
		for(int k = i;k <= high_i;k++){
			for(int l = low_j;l <= high_j;l++){
				if(l == j && k == i){
					continue;
				}
				if(m.get(k).get(l) == 0 && summed.get(k).get(l) == 0){
					return true;
				}
			}
		}
		return false;
}

private static void sum_path_set(List<List<Integer>> m,List<List<Integer>> summed,int i,int j){

	//summed[i][j] == 0 means that this position was not summed by any path

	if(!has_path(m,summed,i,j)){
		return;
	}

	//setting the boundaries
	int high_i = i+1;
	if(i+1 >= m.size()){
		high_i = i;
	}
	int low_j = j-1;
	if(j-1 < 0){
		low_j = j;
	}
	int high_j = j+1;
	if(j+1 >= m.get(i).size()){
		high_j = j;
	}
	//

	int all_sum = 0;
	for(int k = i;k <= high_i;k++){
		for(int l = low_j;l <= high_j;l++){
			if(l == j && k == i){
				if(summed.get(k).get(l) == 0) {
					summed.get(k).remove(l);
					summed.get(k).add(l,1);
					//sum++;
				}
				continue;
			}
			if(m.get(k).get(l) == 0 && summed.get(k).get(l) == 0){
				summed.get(k).remove(l);
				summed.get(k).add(l,1);
				//sum++;
				sum_path_set(m,summed,k,l);
			}
		}
	}
	//return all_sum;


}

private static int sum_path(List<List<Integer>> m,List<List<Integer>> summed,int i,int j){

	/*List<List<Integer>> summed = new  ArrayList<>();
	for(int k = 0;k < m.size();k++){
		summed.add(new ArrayList<>());
		for(int l = 0;l < m.get(k).size();l++){
			summed.get(k).add(0);
		}
	}*/
	sum_path_set(m,summed,i,j);
	int sum = 0;
	for(int k = 0;k < summed.size();k++) {
		for(int l = 0;l < summed.get(k).size();l++) {
			if(summed.get(k).get(l) == 1) {
				sum++;
			}
		}
	}
	return sum;

}

}
