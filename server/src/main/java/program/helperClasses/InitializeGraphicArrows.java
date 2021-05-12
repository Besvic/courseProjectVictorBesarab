package program.helperClasses;

public class InitializeGraphicArrows {
    Double xDouble;
    Double yDouble;
    String xString;
    String yString;
    Integer xInteger;
    Integer yInteger;

    public InitializeGraphicArrows(Double xDouble, String xString, String yString, Integer xInteger, Integer yInteger) {
        this.xDouble = xDouble;
        this.xString = xString;
        this.yString = yString;
        this.xInteger = xInteger;
        this.yInteger = yInteger;
    }

    public InitializeGraphicArrows() {}

    public InitializeGraphicArrows(String xString, String yString) {
        this.xString = xString;
        this.yString = yString;
    }

    public InitializeGraphicArrows(Double xDouble, String yString) {
        this.xDouble = xDouble;
        this.yString = yString;
    }

    public Double getxDouble() {
        return xDouble;
    }

    public void setxDouble(Double rDouble) {
        this.xDouble = rDouble;
    }

    public Double getyDouble() {
        return yDouble;
    }

    public void setyDouble(Double yDouble) {
        this.yDouble = yDouble;
    }

    public String getxString() {
        return xString;
    }

    public void setxString(String xString) {
        this.xString = xString;
    }

    public String getyString() {
        return yString;
    }

    public void setyString(String yString) {
        this.yString = yString;
    }

    public Integer getxInteger() {
        return xInteger;
    }

    public void setxInteger(Integer xInteger) {
        this.xInteger = xInteger;
    }

    public Integer getyInteger() {
        return yInteger;
    }

    public void setyInteger(Integer yInteger) {
        this.yInteger = yInteger;
    }
}
