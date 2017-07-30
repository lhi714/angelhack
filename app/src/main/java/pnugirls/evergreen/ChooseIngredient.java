package pnugirls.evergreen;

import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by user on 2017-07-30.
 */

public class ChooseIngredient {
    private enum Symptom {ALG,AN,FT,CTP,DRR,BRTH ,FOOT,SK,EYE,HANG,COLD,SMOK,FE, MALE}
    private enum Indigrent{apple,canola,celery,kale,broccoli,
        tomato,carrot,pineapple,pomegranate ,banana,
        japaneseplum,cucumber,houttuyniacordata,waterparsely,lemon,
        aloe,raspberry,persimmon,plum,parsely,
        crowndaisy,chive,tangerine,cabbage,cornusfruit}

    private int[][] chart=new int[14][25];

    ChooseIngredient(){
        Setchart();
    }

    public void Setchart(){
        chart[0][0]=1; chart[0][1]=1;
        chart[1][2]=1; chart[1][3]=1; chart[1][4]=1;
        chart[2][5]=1; chart[2][6]=1; chart[2][0]=1;chart[2][7]=1; chart[2][8]=1;
        chart[3][3]=1; chart[3][8]=1; chart[3][4]=1;
        chart[4][8]=1; chart[4][9]=1; chart[4][6]=1;chart[4][10]=1;
        chart[6][8]=1; chart[6][12]=1; chart[6][11]=1;chart[6][13]=1;
        chart[5][8]=1; chart[5][2]=1; chart[5][11]=1;chart[5][0]=1;
        chart[7][4]=1; chart[7][14]=1; chart[7][15]=1;chart[7][16]=1;chart[7][8]=1;
        chart[8][6]=1; chart[8][16]=1; chart[8][2]=1;chart[8][19]=1;
        chart[9][17]=1; chart[9][18]=1; chart[9][0]=1;chart[9][19]=1;
        chart[10][20]=1; chart[10][21]=1; chart[10][6]=1;chart[10][2]=1;
        chart[11][19]=1; chart[11][22]=1; chart[11][23]=1;
        chart[12][8]=1; chart[12][4]=1; chart[12][2]=1;
        chart[13][16]=1; chart[13][24]=1;
    }

    public ArrayList<Integer> GetIngrediChoose(int[] sym){
        ArrayList<Integer> result= new ArrayList<Integer>();
        int[] temp= new int[25];
        for(int i=0; i<25; i++) temp[i]=0;
        for(int i=0; i<sym.length; i++){
            for(int j=0; j<25; j++){
                if(chart[i][j]==1 && sym[i]==1) temp[j]++;
                else continue;
            }
        }
        int max=-1;
        for(int i=0; i<25; i++){ if(max<temp[i]) max= temp[i];};
        for(int i=0; i<25; i++){ if(max<=temp[i]) max= temp[i];};
        return result;
    }

    class Descending implements Comparator<Integer> {
        @Override
        public int compare(Integer o1, Integer o2) {
            return o2.compareTo(o1);
        }
    }

}
