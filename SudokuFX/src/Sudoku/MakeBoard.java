package Sudoku;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class MakeBoard
{
	// 보드판 만들기
	public static int[][] CreateBoard() {
		// 1~9를 섞어서 규칙대로 보드에 배열.
		List<Integer> seed = new ArrayList<>();
		for (int i = 1; i < 10; i++) {
		    seed.add(i);
		}
		Collections.shuffle(seed);
		
		int n1=seed.get(0) ,n2=seed.get(1), n3=seed.get(2), n4=seed.get(3), n5=seed.get(4), n6=seed.get(5), n7=seed.get(6), n8=seed.get(7), n9=seed.get(8);
		int[][] board = {{n1,n2,n3,n4,n5,n6,n7,n8,n9}
						,{n4,n5,n6,n7,n8,n9,n1,n2,n3}
						,{n7,n8,n9,n1,n2,n3,n4,n5,n6}
						,{n2,n3,n1,n5,n6,n4,n8,n9,n7}
						,{n5,n6,n4,n8,n9,n7,n2,n3,n1}
						,{n8,n9,n7,n2,n3,n1,n5,n6,n4}
						,{n3,n1,n2,n6,n4,n5,n9,n7,n8}
						,{n6,n4,n5,n9,n7,n8,n3,n1,n2}
						,{n9,n7,n8,n3,n1,n2,n6,n4,n5}
						};
		
		// 가로를 3줄 씩 섞어서 붙임.
		// 0,1,2 리스틀를 섞고 순서대로 배열, 3칸씩 반복.
		List<Integer> random = new ArrayList<>();
		for (int i=0; i<3;i++) {
			random.add(i);
		}
		
		for(int i=0; i<7; i+=3) {
			Collections.shuffle(random);
			int[] row_temp = board[i];
			board[i] = board[random.get(0)+i];
			board[random.get(0)+i] = board[random.get(1)+i];
			board[random.get(1)+i] = row_temp;
		}
		// 먼저 세로를 배열로 받아서 copy 배열을 만들고 가로와 같은 방법으로 섞고 다시 보드에 값을 넣어준다.
		// 세로 3줄씩 나누어서 섞기.
		int count = 0;
		int [][]copy = new int[9][9];
		while (count < 9) {
			for(int i=0; i<9; i++) {
				copy[count][i] = board[i][count];
			}
			count++;
		}
		for(int i=0; i<7; i+=3) {
			Collections.shuffle(random);
			int[] col_temp = copy[i];
			copy[i] = copy[random.get(0)+i];
			copy[random.get(0)+i] = copy[random.get(1)+i];
			copy[random.get(1)+i] = col_temp;
		}
		count = 0;
		while (count < 9) {
			for(int i=0; i<9; i++) {
				board[i][count] = copy[count][i];
			}
			count++;
		}
			
		return board;
	}
    
    // 난이도 받기에서 받은 구멍 개수만큼 구멍을 뚫는 함수
    public static int[][] MakeHoles(int[][] board,int holes) {
    	int [][] p_board = board;
    	int i,j,holes_num;
    	holes_num = holes;
    	while (holes_num>0) {
    		i = (int) (Math.random()*9);
    		j = (int) (Math.random()*9);
    		if(p_board[i][j] == 0) {
    			continue;
    		}
    		p_board[i][j] = 0;
    		holes_num--;
    	}
    	return p_board;
    }
    
}