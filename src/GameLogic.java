import resultat_gui.ResultatGUI;

import java.io.IOException;
import java.util.ArrayList;

public class GameLogic {

    public GameLogic() {


        ArrayList<Integer> trueTable = new ArrayList<>();
        ArrayList<Integer> opponentsTrueTable = new ArrayList<>();

        for (int i = 0; i < 18; i++) {
            trueTable.add(-1);
            opponentsTrueTable.add(-1);
        }


        ResultatGUI resultatGUI = new ResultatGUI(0, 0, true, trueTable, opponentsTrueTable);


        
        
        

    }

    public static void main(String[] args) {
        new GameLogic();
    }

}
