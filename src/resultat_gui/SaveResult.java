package resultat_gui;

import java.io.*;
import java.util.ArrayList;

public class SaveResult {

    public static void main(String[] args) {
        new SaveResult();
    }

    public SaveResult() {




    }

    public ArrayList<Integer> readResult(boolean isPlayer1) {
        try (BufferedReader br = new BufferedReader(new FileReader("src/resultat_gui/result.txt"))) {

            ArrayList<Integer> trueTable = new ArrayList<>();
            ArrayList<Integer> opponentsTrueTable = new ArrayList<>();

            String line;
            while ((line = br.readLine()) != null) {
                if (line.equals("-x-")) {
                    break;
                }
                String[] values = line.split(",");
                for (String value : values) {
                    trueTable.add(Integer.parseInt(value));
                }
            }

            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                for (String value : values) {
                    opponentsTrueTable.add(Integer.parseInt(value));
                }
            }


            if (isPlayer1) {
                return trueTable;
            } else {
                return opponentsTrueTable;
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public void createFile() {

        try (PrintWriter printWriter = new PrintWriter(new BufferedWriter(new FileWriter("src/resultat_gui/result.txt")))) {

            for (int i = 0; i < 6; i++) {
                printWriter.write("-1,-1,-1");
                printWriter.write("\n");
            }
            printWriter.write("-x-");
            printWriter.write("\n");

            for (int i = 0; i < 6; i++) {
                printWriter.write("-1,-1,-1");
                printWriter.write("\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void saveQuestionRound(ArrayList<Integer> roundResult, boolean isPlayer1) {


            ArrayList<Integer> trueTable;
            ArrayList<Integer> opponentsTrueTable;

            trueTable = readResult(true);
            opponentsTrueTable = readResult(false);

            int countNegativeOne = 0;

            if (isPlayer1) {
                for (int i = 0; i < trueTable.size(); i++) {

                    if (trueTable.get(i) == -1){
                        trueTable.set(i, roundResult.get(countNegativeOne));
                        countNegativeOne++;

                        if (countNegativeOne == 3) {
                            break;
                        }
                    }

                }

                writeToFile(trueTable, opponentsTrueTable);

            }
            
            //TODO: Fix this
            else{
                for (int i = 0; i < opponentsTrueTable.size(); i++) {

                    if (opponentsTrueTable.get(i) == -1){
                        opponentsTrueTable.set(i, roundResult.get(countNegativeOne));
                        countNegativeOne++;

                        if (countNegativeOne == 3) {
                            break;
                        }
                    }

                }

                writeToFile(trueTable, opponentsTrueTable);
            }




    }


    private void writeToFile(ArrayList<Integer> trueTable, ArrayList<Integer> opponentsTrueTable) {
        try (PrintWriter printWriter = new PrintWriter(new BufferedWriter(new FileWriter("src/resultat_gui/result.txt"))) ) {

            for (int i = 0; i < trueTable.size(); i++) {
                printWriter.write(trueTable.get(i) + ",");
            }
            printWriter.write("\n");

            printWriter.write("-x-");
            printWriter.write("\n");

            for (int i = 0; i < opponentsTrueTable.size(); i++) {
                printWriter.write(opponentsTrueTable.get(i) + ",");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
