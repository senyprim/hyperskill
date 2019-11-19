package app;

abstract class Player{
    public final Mark mark;
    protected String type;
    public String getType(){return type;};
    public void setType(String type){this.type=type; };
    protected Game game;
    
    Player(Game game,Mark mark,String type ){
        this.game=game;
        this.mark=mark;
        this.type=type;
    }
    abstract public int turn();
    
}

