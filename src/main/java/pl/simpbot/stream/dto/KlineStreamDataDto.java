package pl.simpbot.stream.dto;

public class KlineStreamDataDto {
    public long t;
    public long T;
    public String s;
    public String i;
    public long f;
    public long L;
    public String o;
    public String c;
    public String h;
    public String l;
    public String v;
    public long n;
    public boolean x;
    public String q;
    public String V;
    public String Q;
    public String B;

    @Override
    public String toString() {
        return "KlineStreamDataDto{" +
                "t=" + t +
                ", T=" + T +
                ", s='" + s + '\'' +
                ", i='" + i + '\'' +
                ", f=" + f +
                ", L=" + L +
                ", o='" + o + '\'' +
                ", c='" + c + '\'' +
                ", h='" + h + '\'' +
                ", l='" + l + '\'' +
                ", v='" + v + '\'' +
                ", n=" + n +
                ", x=" + x +
                ", q='" + q + '\'' +
                ", V='" + V + '\'' +
                ", Q='" + Q + '\'' +
                ", B='" + B + '\'' +
                '}';
    }
}
