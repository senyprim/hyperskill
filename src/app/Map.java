package app;

import java.awt.Point;
class Map{
    private static final int SIZE=9;
    public int getEmptyFields(){return getCountMark(Mark.Empty);}
    private static final int[][] winCoordinate={
        new int[]{0,1,2},new int[]{3,4,5},new int[]{6,7,8},//Горизонтальные
        new int[]{0,3,6},new int[]{1,4,7},new int[]{2,5,8},//Вертикальные
        new int[]{0,4,8},new int[]{2,4,6}//Диагонали
    };
    
    Field[]map;
    public Field getField(int num){ return map[num]; }
    public void setField(Field field){  
        if (field==null) throw new NullPointerException("Поле пустое");
        if (field.mark.equals(map[field.index])) return;
        int index=field.index;
        if (index<0||index>8) throw new IllegalArgumentException("Индекс должен быть от 1 до 9");
        if (!map[index].isEmpty()) throw new IllegalArgumentException("This cell is occupied! Choose another one!");
        map[field.index]=field;
    }
    public int getCountMark(Mark mark){
        int counter=0;
        for(int i=0;i<SIZE;i++){
            if (getField(i).mark.equals(mark)) counter++;
        }
        return counter;
    }
    public Map(String init){
        map=new Field[SIZE];
        if (init==null) init="         ";
        if (init.length()!=SIZE) throw new IllegalArgumentException("Инициация ошибочна");
        int counter=0;
        for(int row=3;row>0;row--){
            for(int col=1;col<=3;col++){
                int number=getNumber(new Point(col,row));
                map[number]=new Field(number,new Mark(init.charAt(counter++)));
            }
        }
    }
    public Map(){this("         ");}
    public Map(Field[] map){this.map=map;}
    public Map(Map map){this.map=map.map.clone();}
    public Mark getWinner(){
        for (int[] line : winCoordinate) {
            Mark mark=map[line[0]].mark;
            if (mark.equals(map[line[1]].mark)&&mark.equals(map[line[2]].mark)) return mark;
        }
        return Mark.Empty;
    }
    public boolean isWin(){return !getWinner().equals(Mark.Empty);}
    public static Point getNumber(int number){return new Point(number%3+1,number/3+1);}
    public static int getNumber(Point point){return (point.y-1)*3+point.x-1;}

    public Field[] getFields(Mark mark){
        int countMarkField=getCountMark(mark);
        Field[] result=new Field[countMarkField];
        int counter=0;
        for(int i=0;i<SIZE;i++){
            if (map[i].mark.equals(mark)) {
                result[counter]=new Field(i,mark);
                counter++;
            }
        }
        return result;
    }

    private String getPrintLineString(int row){
        String result="";
        for(int col=1;col<=3;col++){
            result+=getField(getNumber(new Point(col,row))).mark.mark+" ";
        }
        return result;
    }
    public String getPrintString(){
        return 
        "--------"+"\n"+
        "| "+getPrintLineString(3)+"|"+"\n"+
        "| "+getPrintLineString(2)+"|"+"\n"+
        "| "+getPrintLineString(1)+"|"+"\n"+
        "--------";
    }
    public Map getClone(){ return new Map(this.map.clone());}
}
