package s260510904.mytools;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import boardgame.Board;
import omweso.CCBoardState;
import omweso.CCMove;
import omweso.CCBoardState.*;

public class MyTools7 {

    public static double getSomething(){
        return Math.random();
    }

    public static void bestMove(ArrayList<CCMove> moves, CCBoardState board)
    {
    	
    }
    
    public static int[] my_move(CCBoardState board, ArrayList<CCMove> moves, Map<Integer, Integer> op_moves)
    {
    	TreeMap<Integer, Integer> sorted_op_moves = SortByValue(op_moves);
    	int [] my_move = null;
    	
    	//Defense
    	if (op_moves == null) {}
    		// do something
    	
    	//Capture
    	for (Integer s : sorted_op_moves.keySet())
    	{
    		System.out.println(sorted_op_moves.get(s));
    	}
    	
		return my_move;
    }
    
    private static TreeMap<Integer, Integer> SortByValue (Map<Integer, Integer> map)
    {
    	ValueComparator vc = new ValueComparator(map);
    	TreeMap<Integer, Integer> sortedMap = new TreeMap<Integer, Integer>(vc);
    	sortedMap.putAll(map);
    	
    	return sortedMap;
    }
     
    public static Map<Integer, Integer> op_move(int[] pits)
    {
    	Map<Integer, Integer> op_index = new HashMap<Integer, Integer>();
    	op_index = null;
    	
    	int[] op_move = new int[8];
		
    	
    	for (int i = 0; i < op_move.length; i++)
    		op_move[i] = 0;
    	
    	//Check if the first or second row is empty
    	int j = 0;
    	for (int i = 0; i < 8; i++)
    	{
    		if (pits[i] == 0)
    			j++;
    		else
    			break;
    	}
    	
    	if (j == 8)
    		return op_index; //null op_move results in a defensive move
    	
    	j = 0;
    	for (int i = 8; i < 16; i++)
    	{
    		if (pits[i] == 0)
    			j++;
    		else
    			break;
    	}
    	
    	if (j == 8)
    		return op_index; //null op_move results in a defensive move
    	
    	//Find the pits which are capturable
    	int i = 0;
    	j = 15;
    	while (i < 8 && j > 7)
    	{
    		if (pits[i] != 0 && pits[j] != 0)
    		{
    			op_move[i] = pits[i] + pits[j]; 
    		}
    		
    		i++; j--;
    	}
    
    	for (i = 0; i < op_move.length; i++)
    	{
    		op_index.put(i, op_move[i]);
    	}
    	
    	return op_index;
    }
    
    public static void printPits (int[] pits, boolean original) 
    {
    	
    	if (original == true) {
    		for (int i = 0; i < 16; i++)
    		{
    			if	(i == 0)
    				System.out.print("Original pits: [" + pits[0] + ", ");
    			if	(i > 0 && i < 15)
    				System.out.print(pits[i] + ", ");
    			if (i == 15)
    				System.out.print(pits[15] + "]\n");
    		}
    	} else {
    		for (int i = 0; i < 16; i++)
    		{
    			if	(i == 0)
    				System.out.print("Simulated pits: [" + pits[0] + ", ");
    			if	(i > 0 && i < 15)
    				System.out.print(pits[i] + ", ");
    			if (i == 15)
    				System.out.print(pits[15] + "]\n");
    		}
    	}
    }
}

class ValueComparator implements Comparator<Integer> {
	
	Map<Integer, Integer> map;
	
	public ValueComparator (Map<Integer, Integer> base)
	{
		this.map = base;
	}

	@Override
	public int compare(Integer o1, Integer o2) {
		if (map.get(o1) >= map.get(o2))
			return -1;
		else
			return 1;
	}
}
