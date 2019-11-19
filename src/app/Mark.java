package app;
class Mark 
{
    public static final char[] MARKS={' ','X','O'};
    public final char mark;
    public Mark(char mark){
        if (new String(MARKS).indexOf(mark)<0) throw new IllegalArgumentException("Неизвестный маркер клетки");
        this.mark=mark;
    }
    public static final Mark Empty=new Mark(' ');
    public static final Mark X=new Mark('X');
    public static final Mark O=new Mark('O');
    public boolean equals(Mark mark){
        if (mark==null) return false;
        return this.mark==mark.mark;
    }
    @Override
    public boolean equals(Object obj){
        if (obj==this) return true;
        if (obj==null || obj.getClass()!=this.getClass() ) return false;
        return this.equals((Mark)obj);
    }
    @Override
    public int hashCode(){
        return Character.hashCode(this.mark);
    }
}