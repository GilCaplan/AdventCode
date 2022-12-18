public class day9 {
    public static int part1 = 2, part2=10;
    public static void main(String[] args) throws FileNotFoundException {
        List<String[]> input = getinput("day9input");
        solve(input, new Grid(part2));
        solve(input, new Grid(part1));
    }
    public static void solve(@NotNull List<String[]> input, Grid board) {
        for(String[] command : input)
            board = moveTails(command, board);
        System.out.println(board.getPoint().size());
    }
    public static Grid moveTails(String @NotNull [] command, Grid board) {
        var hor = command[0].equals("R")?1:command[0].equals("L")?-1:0;
        var ver = command[0].equals("U")?1:command[0].equals("D")?-1:0;
        for(var i=0; i < Integer.valueOf(command[1]);i++) {
            board.getKnots()[0].setLoc(hor,ver);
            board = moveHD(board, hor,ver,0);
        }
        return board;
    }
    public static @NotNull Grid moveHD(@NotNull Grid board, int hor, int ver, int knot) {
        Point head= new Point(board.getKnots()[knot].getLoc().getX(),board.getKnots()[knot].getLoc().getY());
        Point tail = new Point(board.getKnots()[knot+1].getLoc().getX(),board.getKnots()[knot+1].getLoc().getY());
        var disX = Math.abs(head.getX()-tail.getX());
        var disY = Math.abs(head.getY()-tail.getY());
        if(disX+disY > 2 ) {//move diaganolly
            tail.setX(tail.getX() + (head.getX()>tail.getX()?1:-1));
            tail.setY(tail.getY() + (head.getY()>tail.getY()?1:-1));
        }
        else if(disX == 2 || disY == 2){
            tail.setX(tail.getX()+(head.getX()>tail.getX()?1:((head.getX()<tail.getX())?-1:0)));
            tail.setY(tail.getY()+(head.getY()>tail.getY()?1:((head.getY()<tail.getY())?-1:0)));
        }
        board.getKnots()[knot+1].setnewLoc(tail);
        if(knot+1 < board.getKnots().length-1)
            return moveHD(board, hor,ver,++knot);
        board.addPoint(new Point(tail.getX(), tail.getY()));
        return board;
    }
    public static @NotNull List<String[]> getinput(String filename) throws FileNotFoundException {
        var reader = new Scanner(new File(filename+".txt"));
        var list = new ArrayList<String[]>();
        while (reader.hasNextLine())
            list.add(reader.nextLine().split(" "));
        return list;
    }
}

import java.util.HashSet;
public class Grid {
    private HashSet<Point> Point;
    private Knot[] knots;
    public Grid(int n) {
        this.Point = new HashSet<Point>();
        this.Point.add(new Point(0,0));
        this.knots = new Knot[n];
        for(int i=0; i < n; i++) {
            this.knots[i]= new Knot(i);
        }
    }
    public Knot[] getKnots() {
        return knots;
    }

    public void addPoint(Point p) {
        this.Point.add(p);
    }
    public HashSet<Point> getPoint() {
        return Point;
    }

    @Override
    public String toString() {
        String str="";
        for(int i=0; i<this.getKnots().length ;i++){
            str += " knot "+i+" is: "+this.getKnots()[i].knotlocStr()+", ";
        }
        return str;
    }
}

public class Point {
    private int x;
    private int y;
    private int hash;
    public Point(int x, int y) {
        this.x=x;
        this.y=y;
    }
    public int getX() {
        return x;
    }
    public void setX(int x) {
        this.x = x;
    }
    public int getY() {
        return y;
    }
    public void setY(int y) {
        this.y = y;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Point that = (Point) o;
        return x == that.x && y == that.y;
    }
    @Override
    public int hashCode() {
        return this.hash;
    }
}

import org.jetbrains.annotations.NotNull;

public class Knot {
    private Point loc;
    private int num;
    public Knot(int n) {
        this.loc = new Point(0,0);
        this.num = n;
    }
    public Point getLoc() {
        return loc;
    }
    public void setLoc(int addX, int addY) {
        this.loc = new Point(this.loc.getX()+addX,this.getLoc().getY()+addY);
    }
    public void setnewLoc(@NotNull Point p) {
        this.loc = new Point(p.getX(), p.getY());
    }
    public String knotlocStr(){
        var str="";
        str= this.getLoc().getX()+","+this.getLoc().getY();
        return str;
    }
}
