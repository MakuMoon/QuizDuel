package resultat_gui;

import java.io.*;
import java.util.ArrayList;

public class SaveResult {

    public static void main(String[] args) {
        SaveResult saveResult = new SaveResult();
    }

    public SaveResult() {
        createFile();


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


    public void saveQuestionRound(ArrayList<Integer> roundResult) {

        try (BufferedReader br = new BufferedReader(new FileReader("src/resultat_gui/result.txt"))) {


        } catch (Exception e) {
            e.printStackTrace();
        }


    }


}
