package s260510904.mytools;

import java.util.ArrayList;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

import omweso.CCBoardState;
import omweso.CCMove;

public class MyTools2 {

	public static int my_move(CCBoardState board, ArrayList<CCMove> moves, int[] my_pits, TreeMap<Integer, Integer> op_moves)
	{
		int index = 0;
		int value = 0;
		
		int
		
		return index;
	}
	
	public static SortedSet op_move (int[] pits)
	{
		
		TreeMap<Integer, Integer> op_moves = new TreeMap<Integer, Integer>();
		SortedSet op_index = new TreeSet();
		
		int i = 0, j = 15;
		while (i < 8 && j > 7)
		{
//			if (pits[i] != 0 && pits[j] != 0)
//			{
				int value = pits[i] + pits[j];
//				op_moves.put(i, value);
				op_index.add(value);
//			}
			
			i++; j--;
		}
		
		return op_index;
	}
	
}
