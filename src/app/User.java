package app;
import java.awt.Point;
import java.util.Scanner;

class User extends Player{

    User(Game game,Mark mark,String type ){
        super(game, mark, type);
    }
    @Override
    public int turn(){
        title("Enter the coordinates: ");
        Scanner scanner=new Scanner(System.in);
        while(true){
        try{
            String[] args=scanner.nextLine().split(" ");
            if (args.length!=2) throw new IllegalArgumentException("You should enter numbers!");
            int col = Integer.parseInt(args[0]);
            int row = Integer.parseInt(args[1]);
            if (col<1||col>3||row<1||row>3) throw new IllegalArgumentException("Coordinates should be from 1 to 3!");
            return Map.getNumber(new Point(col,row));
        }
        catch(NumberFormatException e){
            throw new NumberFormatException("You should enter numbers!");
        }
    }
    }
    private void title(String title){
        System.out.print(title);
    }
}