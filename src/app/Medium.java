package app;

class Medium extends Player {
    Medium(Game game,Mark mark,String type ){
        super(game, mark, type);
    }
    private int getMediumTurn(){
        Field[] emptyFields=game.getCloneMap().getFields(Mark.Empty);
        for (Field field : emptyFields) {
            Map map=game.getCloneMap();
            map.setField(new Field(field.index,mark));
            if (map.isWin()) return field.index;
        }
        return -1;
    }
    @Override
    public int turn(){
        title("Making move level \"medium\"");
        int win=getMediumTurn();
        if (win!=-1) return win;
        win=new Medium(this.game, this.mark==Mark.X?Mark.O:Mark.X, this.type).getMediumTurn();
        if (win!=-1) return win;
        return new Easy(this.game, this.mark, "easy").getEasyTurn();
    }
    private void title(String title){
        System.out.println(title);
    }
}