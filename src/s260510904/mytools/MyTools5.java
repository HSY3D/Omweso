package s260510904.mytools;
 
import java.util.ArrayList;
 
import omweso.CCBoardState;
import omweso.CCMove;
 
public class MyTools5 {
       
        public static final int OP_GT_16 = 0;
        public static final int WIN = 1000;
        public static final int OP_LT_16 = 100;
        public static final int CAPTURE = 50;
        public static final int LOSS = -10;
        public static final int SEEDS_LT_16 = -100;
        public static final int NONE = 1;
 
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

    	public static int calculateHeuristic(int[] myseeds_initial, int[] myseeds_final, CCBoardState bs, boolean play){
    		int pits[][] = bs.getBoard();
    		return getTotalSeeds(pits[0]) - getTotalSeeds(pits[1]);
    	}
    
    public static int[] minimax(int level, boolean play, CCBoardState bs, int[] myseeds_initial, int[] opseeds_initial){
       
        int best_node = -1;
        int curr_score = 0;
        int best_score = (play) ? Integer.MIN_VALUE : Integer.MAX_VALUE;
       
        ArrayList<CCMove> remaining_moves = bs.getLegalMoves();
        if(level <= 0 || remaining_moves.isEmpty()){
                best_score = calculateHeuristic(myseeds_initial, opseeds_initial, bs, play);
        }
        else{
                if(play){
                        ArrayList<CCMove> moves = bs.getLegalMoves();
 
                        for(int i = 0; i < moves.size(); i++){
                                CCMove m = moves.get(i);
                                int[][] state = bs.getBoard();
                                CCBoardState clone = (CCBoardState) bs.clone();
                                if (clone.move(m)) {
                                	if(bs.haveWon()){
                                		best_score = Integer.MIN_VALUE;
                                		best_node = i;
                                		break;
                                	}
//                                	int next = (play == 0) ? 0 : 1;
                                	curr_score = minimax(level - 1, !play, clone, state[0], state[1])[0];

                                	if(curr_score > best_score){
                                		best_score = curr_score;
                                		best_node = i;
                                	}
                                }
                        }
                }
                else{
                        ArrayList<CCMove> moves = bs.getLegalMoves();
 
                        for(int i = 0; i < moves.size(); i++){
                                CCMove m = moves.get(i);
                                int[][] state = bs.getBoard();
                                CCBoardState clone = (CCBoardState) bs.clone();
                                if (clone.move(m)) {
                                	if(bs.haveLost()){
                                		best_score = Integer.MAX_VALUE;
                                		best_node = i;
                                		break;
                                	}
//                                	int next = (play == 0) ? 0 : 1;
                                	curr_score = minimax(level - 1, !play, clone, state[1], state[0])[0];

                                	if(curr_score < best_score){
                                		best_score = curr_score;
                                		best_node = i;
                                	}
                                }
                        }
                }
        }
               
        return new int[] {best_score, best_node};
    }
   
    public static void printMoveList(ArrayList<CCMove> m){
       
        System.out.print("List of legal moves: " );
        for(CCMove e : m){
                System.out.print(e.getPit() + " ");
        }
        System.out.println();
    }
}