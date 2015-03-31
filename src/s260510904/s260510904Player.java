package s260510904;

import boardgame.Board;
import boardgame.BoardState;
import boardgame.Move;
import boardgame.Player;
import omweso.CCBoardState;
import omweso.CCBoard;
import omweso.CCMove;

import java.util.ArrayList;
import java.util.Random;

import s260510904.mytools.MyTools7;
import s260510904.mytools.MyTools4;
import s260510904.mytools.MyTools5;
import s260510904.mytools.MyTools;

/** A random Omweso player. */
public class s260510904Player extends Player {
    Random rand = new Random();

    /** You must provide a default public constructor like this,
     * which does nothing but call the base-class constructor with
     * your student number. */
    public s260510904Player() { super("XXXXXXXXX"); }
    public s260510904Player(String s) { super(s); }

    /** Leave this method unchanged. */
    public Board createBoard() { return new CCBoard(); }

    /** Use this method to take actions when the game is over. */
    public void gameOver( String msg, BoardState bs) {
        CCBoardState board_state = (CCBoardState) bs;

        if(board_state.haveWon()){
            System.out.println("I won!");
        }else if(board_state.haveLost()){
            System.out.println("I lost!");
        }else if(board_state.tieGame()){
            System.out.println("Draw!");
        }else{
            System.out.println("Undecided!");
        }
    }

    /** Implement a very stupid way of picking moves. */
    public Move chooseMove(BoardState bs)
    {
        // Cast the arguments to the objects we want to work with.
        CCBoardState board_state = (CCBoardState) bs;

        // Get the contents of the pits so we can use it to make decisions.
        int[][] pits = board_state.getBoard();

        // Our pits in first row of array, opponent pits in second row.
        int[] my_pits = pits[0];
        int[] op_pits = pits[1];

        // Use my tool to do precisely nothing
        MyTools7.getSomething();

        if(!board_state.isInitialized()){
            // Code for setting up our initial position. Also, if your agent needs to be
            // set-up in any way (e.g. loading data from files) you should do it here.

            //CCBoardState.SIZE is the width of the board, in pits.
            int[] initial_pits = new int[2 * CCBoardState.SIZE];

            // Make sure your initialization move includes the proper number of seeds.
            int num_seeds = CCBoardState.NUM_INITIAL_SEEDS;

            if(board_state.playFirst()){
                // If we're going to play first in this game, choose one random way of setting up.
                // Throw each seed in a random pit that we control.
                for(int i = 0; i < num_seeds; i++){
                    int pit = rand.nextInt(2 * CCBoardState.SIZE);
                    initial_pits[pit]++;
                }
            }else{
                // If we're going to play second this game, choose another random way of setting up.
                // Throw each seed in a random pit that we control, but in the row closest to us.
                for(int i = 0; i < num_seeds; i++){
                    int pit = rand.nextInt(CCBoardState.SIZE);
                    initial_pits[pit]++;
                }
            }

            return new CCMove(initial_pits);
        }else{
                        
                // Find the legal moves and initialize the potential board states and needed moves variables.      
               	CCBoardState board = (CCBoardState)board_state.clone();
        		ArrayList<CCMove> moves = board_state.getLegalMoves();

        		int depth = 7;
        		int move = MyTools.MiniMax(board, true, depth)[1];
                return moves.get(move);
        }
    }
}
