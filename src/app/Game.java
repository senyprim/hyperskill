package app;


class Game{
    public static final String[] STATES ={"Game not finished","X wins","O wins","Draw"}; 
    public int getState(){
        Mark win=this.map.getWinner();
        if (win.equals(Mark.X)) return 1;
        else if (win.equals(Mark.O)) return 2;
        else if (this.map.getCountMark(Mark.Empty)==0) return 3;
        else return 0;
    }
    
    private Map map;
    public Map getCloneMap(){return map.getClone();}
    private int turnNumber;
    public int getTurnNumber(){return turnNumber;}
    
    private Player[] players;
    public  Player getCurentPlayer(int turnNumber){return players[getTurnNumber()%2];}
    public  Player getPlayer(int index){return players[index];}
    public  Player getOpponent(Player player){
        return player==players[0]?players[1]:players[0];
    }

    private Player createPlayer(String type,Mark mark){
        Player player;
        if (type.equals("user")) player=new User(this, mark, type);
        else if (type.equals("easy"))  player=new Easy(this, mark, type);
        else if (type.equals("medium"))  player=new Medium(this, mark, type);
        else if (type.equals("hard"))  player=new Hard(this, mark, type);
        else throw new IllegalArgumentException("Bad parameters!");
        return player;
    }

    public Game(String initMap,String player1,String player2){
        
        this.map=new Map(initMap);
        turnNumber=map.getCountMark(Mark.X)+map.getCountMark(Mark.O);
        
        players=new Player[2];
        players[0]=createPlayer(player1, Mark.X);
        players[1]=createPlayer(player2, Mark.O);
    }
    public static Game createGame(String init){
        String[] token=(init).split(" ");
        //if exit
        if (token[0].equals("exit")) return null;
        //if not start
        else if (token.length!=3 ||!token[0].equals("start"))
            throw new IllegalArgumentException("Bad parameters!");
        else {
            return new Game(null,token[1],token[2]);
        }
    }
     
    public void turn(){
        Player player=getCurentPlayer(getTurnNumber());
        int turn=player.turn();
        map.setField(new Field(turn,player.mark));
        turnNumber++;
    }
    
}