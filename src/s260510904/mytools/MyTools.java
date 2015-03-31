package s260510904.mytools;

import java.util.ArrayList;

import javax.swing.text.html.HTMLDocument.Iterator;

import omweso.CCBoardState;
import omweso.CCMove;


public class MyTools {
	
	
	/*
	 * The MiniMax algorithm returns an int array where the first value is
	 * the value of the board (best_score) and the second value is the index
	 * for the move.
	 */
	public static int[] MiniMax (CCBoardState board, boolean max, int depth)
	{
		
		// Initialize Variables
		int best_index = -1, score = 0;
		int best_score = (max) ? Integer.MIN_VALUE : Integer.MAX_VALUE;

		ArrayList<CCMove> moves = board.getLegalMoves();

		// Recursion base case
		if (depth <= 0 || moves.isEmpty())
			best_score = Value(board);

		else {
			// If the player != opponent
			if (max) 
			{
				ArrayList<CCMove> legal_moves = board.getLegalMoves();

				// Cycle through the legal moves
				for (CCMove m : legal_moves)
				{
					int i = legal_moves.indexOf(m);
					CCBoardState board_clone = (CCBoardState) board.clone();
					
					// Simulate the board move on a clone
					if (board_clone.move(m))
					{
						// If the game finishes save the value
						if (board.haveWon())
						{
							best_score = Integer.MIN_VALUE;
							best_index = i;
							break;
						}

						// Run the recursion
						score = MiniMax(board_clone, !max, depth-1)[0];

						//Select the best score and index
						if (score > best_score)
						{
							best_score = score;
							best_index = i;
						}
					}
				}
			} 

			else 
			{
				// Same logic applied to opponent
				ArrayList<CCMove> legal_moves = board.getLegalMoves();

				for (CCMove m : legal_moves)
				{
					int i = legal_moves.indexOf(m);
					CCBoardState board_clone = (CCBoardState) board.clone();

					if (board_clone.move(m))
					{
						if (board.haveLost())
						{
							best_score = Integer.MAX_VALUE;
							best_index = i;
							break;
						}

						score = MiniMax(board_clone, !max, depth-1)[0];

						if (score < best_score)
						{
							best_score = score;
							best_index = i;
						}
					}
				}
			}
		}

		return new int[] {best_score, best_index};
	}



	/*******************************************************
	 ******************* HELPER FUNCTIONS ******************
	 *******************************************************/


	/*
	 * Return the value of the board
	 * @param CCBoardState board
	 */
	private static int Value (CCBoardState board)
	{
		int pits[][] = board.getBoard();

		return getTotalSeeds(pits[0]) - getTotalSeeds(pits[1]);
	}

	/*
	 * Returns the total number of seeds for a given player
	 */
	private static int getTotalSeeds (int pits[])
	{
		int num_seeds = 0;

		for(int i = 0; i < pits.length; i++)
		{
			num_seeds += pits[i];
		}

		return num_seeds;
	}
}
