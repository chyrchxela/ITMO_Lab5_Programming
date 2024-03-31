package Classes;
import ReadWriteToXML.*;

public class Coordinates
{
    private Long x;
    private Long y; //Поле не может быть null

    public Coordinates(Long x, Long y) {
    }

    public Object getX() {
        return x;
    }

    public Object getY() {
        return y;
    }
}