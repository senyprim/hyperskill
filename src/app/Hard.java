package app;

class FieldScore{
    Field field;
    int score;
    FieldScore(Field field,int score){
        this.field=field;
        this.score=score;
    }
}
class Hard extends Player{
    
    Hard(Game game,Mark mark,String type ){
        super(game, mark, type);
    }

    private FieldScore minMax(Map map,boolean curentPlayer,int deep){
        Mark mark=curentPlayer?this.mark:this.game.getOpponent(this).mark;
        if (map.getWinner().equals(this.mark)) return new FieldScore(null, 1);
        if (map.getWinner().equals(this.game.getOpponent(this).mark)) return new FieldScore(null, -1);
        if (map.getEmptyFields()<=0||deep==0) return new FieldScore(null, 0);

        Field[] emptyFields=map.getFields(Mark.Empty);
        FieldScore minimax=new FieldScore(null, curentPlayer?Integer.MIN_VALUE:Integer.MAX_VALUE);
        for(Field item:emptyFields){
            Field newField=new Field(item.index,mark);
            Map newmap=map.getClone();
            newmap.setField(newField);
            FieldScore score=minMax(newmap,!curentPlayer,deep-1);
            score.field=newField;
            if (curentPlayer)
            {
                if (minimax.score<score.score){
                    minimax=score;
                }
            }
            else {
                if(minimax.score>score.score){
                    minimax=score;
                }
            }
           // System.out.println(String.format("глубина %d координаты %d очки %d \n %s",deep,score.field.index,score.score,newmap.getPrintString()));
        }
        
        return minimax;
    }
    public int getHardTurn(){
        return minMax(this.game.getCloneMap(), true, 100).field.index;
    }
    public int turn(){
        title("Making move level \"hard\"");
        return getHardTurn();
    }
    private void title(String title){
        System.out.println(title);
    }
}