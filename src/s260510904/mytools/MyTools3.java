package s260510904.mytools;

import java.util.ArrayList;

import omweso.CCBoardState;
import omweso.CCMove;

public class MyTools3 {

	public static int MinMax (CCBoardState board)
	{
		return MaxMove(board);
	}
	
	public static int MaxMove (CCBoardState board)
	{
		int best_move = 0;
		
		if (board.gameOver())
			return board.getWinner();
		else {
			ArrayList<CCMove> moves = board.getLegalMoves();
			for (CCMove m : moves)
			{
				if (!board.isLegal(m))
					break;
				board.move(m);
				best_move = m.getPit();
				int move = MinMove(board);
				
				if (board.getBoard()[0][m.getPit()] > board.getBoard()[0][best_move])
					best_move = move;
			}
			
			return best_move;
		}
	}
	
	public static int MinMove(CCBoardState board)
	{
		int best_move = 0;
		
		if (board.gameOver())
			return board.getWinner();
		else {
			ArrayList<CCMove> moves = board.getLegalMoves();
			for (CCMove m : moves)
			{
				if (!board.isLegal(m))
					break;
				board.move(m);
				best_move = m.getPit();
				int move =MaxMove(board);
				
				if (board.getBoard()[0][m.getPit()] > board.getBoard()[0][best_move])
					best_move = move;
			}
			
			return best_move;
		}
	}
	
	
	public static int minax(CCBoardState board, int player, int depth, boolean max, int alpha, int beta)
	{
		if (player == 0) {
			player = 1;
		} else {
			player = 0;
		}
		
		if (board.gameOver() || depth == 2)
		{
			return board.getWinner();
		} 
		
		else if (max) {
			ArrayList<CCMove> moves = board.getLegalMoves();
			for (CCMove m : moves)
			{
				System.out.println(m.toPrettyString());
				board.move(m);
//				alpha = Math.max(alpha, minimax(board, player, depth + 1, !max, alpha, beta));
				if (alpha >= beta)
					break;
			}
			
			return alpha;
		}
		
		else {
			ArrayList<CCMove> moves = board.getLegalMoves();
			for (CCMove m : moves)
			{
				System.out.println(m.toPrettyString());
				board.move(m);
//				beta = Math.min(beta, minimax(board, player, depth + 1, !max, alpha, beta));
				if (alpha >= beta)
					break;
			}
			
			return beta;
		}

	}
	
	public static int alphabeta(int player, CCBoardState board, int alpha, int beta, int depth)
	{
		if (board.gameOver() || depth == 5)
		return board.getTurn();
		
		//How to recursion
		ArrayList<CCMove> moves = board.getLegalMoves();
		
		if (player == 0) {
			for (CCMove m : moves)
			{
				player = 1;
				System.out.println("Move Alpha: " + m.toPrettyString() + " Depth: " + depth);
				if (!board.isLegal(m)) {
					break;
				}
				board.move(m);
				int score = alphabeta(player, board, alpha, beta, depth + 1);
				System.out.println("Score at "+ depth +  ": " + score);
				if (score > alpha)
					alpha = score;
				if (alpha >= beta)
					return alpha;
			}
			System.out.println("Alpha: " + alpha);
			return alpha;
		} else {
			for (CCMove m: moves)
			{
				player = 0;
				System.out.println("Move  Beta: " + m.toPrettyString() + " Depth: " + depth);
				if (!board.isLegal(m)) {
					break;
				}
				board.move(m);
				int score = alphabeta(player, board, alpha, beta, depth + 1);
				System.out.println("Score at "+ depth +  ": " + score);
				if (score < beta)
					beta = score;
				if (alpha >= beta)
					return beta;
			}
			System.out.println("Beta: " + beta);
			return beta;
		}	
	}
	
	public static void next_board (CCBoardState board)
	{
		ArrayList<CCMove> moves = board.getLegalMoves();
		
		for (CCMove m : moves)
		{
			print_pits(board, true);
			board.move(m);
			print_pits(board, false);
		}
	}
	
	public static void print_pits (CCBoardState board, boolean original) 
    {
    	
		// Get the contents of the pits so we can use it to make decisions.
        int[][] all_pits = board.getBoard();

        // Our pits in first row of array, opponent pits in second row.
        int[] pits = all_pits[0];
        int[] op_pits = all_pits[1];
		
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
