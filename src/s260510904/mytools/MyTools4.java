package s260510904.mytools;

import boardgame.Board;
import boardgame.BoardState;
import boardgame.Move;
import boardgame.Player;

import omweso.CCBoardState;
import omweso.CCBoard;
import omweso.CCMove;

import java.util.ArrayList;
import java.util.Random;

public class MyTools4 {

	public static double getSomething(){
		return Math.random();
	}

	private static int getTotalSeeds(int pits[]){
		int num_of_seeds = 0;
		for(int i = 0;i<pits.length;i++){
			num_of_seeds += pits[i];
		}

		return num_of_seeds;
	}

	private static int value_of_board(CCBoardState board){
		int pits[][] = board.getBoard();
		return getTotalSeeds(pits[0]) - getTotalSeeds(pits[1]);
	}

	public static void printLegalMoves(ArrayList<CCMove> moves){
		for(CCMove move: moves){
			System.out.print(move.getPit() + " ");
		}

		System.out.println();
	}

	public static CCMove returnBestMove(CCBoardState board){

		System.out.println("\nStarts HERE");
		int moves_left = 100;
		ArrayList<CCMove> legal_moves = board.getLegalMoves();
		//System.out.println(legal_moves.toString());
		CCMove best_move = legal_moves.get(0);
		int best_score = 0;
		int current_score = best_score;
		CCBoardState tmp_board;

		for(CCMove m : legal_moves){
			tmp_board = (CCBoardState)board.clone();
			//			if (!board.isLegal(m))
			//				return best_move;
			board.move(m);
			current_score = getBestMove(tmp_board, moves_left);

			if(current_score > best_score){
				best_score = current_score;
				best_move = m;
			}
		}

		return best_move;
	}

	private static int getBestMove(CCBoardState board, int moves_left){

		if(moves_left == 0){
			ArrayList<CCMove> moves = board.getLegalMoves();
			if(moves.size() == 0){
				System.out.println(board.getBoard().toString());
				return -1;
			}else{
				int score = 0;
				for(CCMove move : moves){
					if(score < value_of_board(board)){
						score = value_of_board(board);
					}
				}
				return score;
			}
		}

		ArrayList<CCMove> legal_moves = board.getLegalMoves();

		if(legal_moves.size() == 0){
			return -1;
		}

		printLegalMoves(legal_moves);
		int best_score = 0;
		int current_score = best_score;
		CCBoardState tmp_board;

		for(CCMove m : legal_moves){
			tmp_board = (CCBoardState)board.clone();
			//			if (!board.isLegal(m))
			//				return best_score;
			board.move(m);
			current_score = getBestMove(tmp_board,moves_left-1);

			if(current_score > best_score){
				best_score = current_score;
			}
		}

		return best_score;
	}
}






