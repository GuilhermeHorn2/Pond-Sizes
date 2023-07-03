#include <stdio.h>
#include <iostream>
#include <vector>

bool has_path(std::vector<std::vector<int>>& m,std::vector<std::vector<int>> &summed,int i,int j);

void sum_path_set(std::vector<std::vector<int>>& m,std::vector<std::vector<int>>& summed,int i,int j,int &sum);

int sum_path(std::vector<std::vector<int>>& m,std::vector<std::vector<int>>& summed,int i,int j);

std::vector<int> pond_sizes(std::vector<std::vector<int>>& m,std::vector<std::vector<int>>& summed);

std::vector<int> pond_sizes(std::vector<std::vector<int>>& m);

int main()
{
	 /* ?\n;||(or) e &&(and)*/


	std::vector<std::vector<int>> m = {{0,2,1,0},{0,1,0,1},{1,1,0,1},{0,0,0,1},{0,1,1,1}};


	std::vector<int> sizes = pond_sizes(m);

	for(int i = 0;i < sizes.size();i++){
		std::cout << sizes[i] << " ";
	}


	return 0;
}

std::vector<int> pond_sizes(std::vector<std::vector<int>>& m){

	std::vector<std::vector<int>> summed;

	for(int i = 0;i < m.size();i++){
		std::vector<int> row;
		summed.push_back(row);
		for(int j = 0;j < m[i].size();j++){
			summed[i].push_back(0);
		}
	}
	return pond_sizes(m,summed);

}

std::vector<int> pond_sizes(std::vector<std::vector<int>>& m,std::vector<std::vector<int>>& summed){

	std::vector<int> sizes;

	for(int i = 0;i < m.size();i++){
		for(int j = 0;j < m[i].size();j++){
			if(m[i][j] == 0 && summed[i][j] == 0){
				sizes.push_back(sum_path(m,summed,i,j));
			}
		}
	}
	return sizes;

}

bool has_path(std::vector<std::vector<int>>& m,std::vector<std::vector<int>> &summed,int i,int j){

	int high_i = i+1;
	if(i+1 >= m.size()){
		high_i = i;
	}
	int low_j = j-1;
	if(j-1 < 0){
		low_j = j;
	}
	int high_j = j+1;
	if(j+1 >= m[i].size()){
		high_j = j;
	}
	//

	//max of 6 iterations
	for(int k = i;k <= high_i;k++){
		for(int l = low_j;l <= high_j;l++){
			if(l == j && k == i){
				continue;
			}
			if(m[k][l] == 0 && summed[k][l] == 0){
				return true;
			}
		}
	}
	return false;
}

void sum_path_set(std::vector<std::vector<int>>& m,std::vector<std::vector<int>>& summed,int i,int j,int &sum){

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
		if(j+1 >= m[i].size()){
			high_j = j;
		}
		//

		int all_sum = 0;
		for(int k = i;k <= high_i;k++){
			for(int l = low_j;l <= high_j;l++){
				if(l == j && k == i){
					if(summed[k][l] == 0) {
						summed[k][l] = 1;
						sum++;
					}
					continue;
				}
				if(m[k][l] == 0 && summed[k][l] == 0){
					summed[k][l] = 1;
					sum++;
					sum_path_set(m,summed,k,l,sum);
				}
			}
		}

}

int sum_path(std::vector<std::vector<int>>& m,std::vector<std::vector<int>>& summed,int i,int j){

		int sum = 0;
		sum_path_set(m,summed,i,j,sum);

		return sum;

}
