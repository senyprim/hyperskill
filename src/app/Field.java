package app;

class Field{
    public final int index;
    public final Mark mark;
    public Field(int index,Mark mark){
        this.index=index;
        this.mark=mark;
    }
    public boolean isEmpty(){return mark.equals(Mark.Empty);}
    
}
