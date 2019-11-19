package app;

import java.util.Random;

class Easy extends Player{
    Easy(Game game,Mark mark,String type ){
        super(game, mark, type);
    }
    public int getEasyTurn(){
        Random rnd=new Random();
        Field[] emptyFields=game.getCloneMap().getFields(Mark.Empty);
        int rndNumber=rnd.nextInt(emptyFields.length);
        return emptyFields[rndNumber].index;
    }
    @Override
    public int turn(){
        title("Making move level \"easy\"");
        return getEasyTurn();
    }
    private void title(String title){
        System.out.println(title);
    }
}