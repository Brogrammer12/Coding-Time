package Main;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.awt.Color;
import java.awt.Graphics2D;

import javax.imageio.ImageIO;
public class TextReader {
    The_Hub hb;
    public String boi;
    char[] boi2;
    char[] boi3;
    int stringEnd;
    public int xCoord;
    public int yCoord;
    public int boxWidth;
    int boxHeight;
    public int boxHeight2;
    BufferedImage a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v, w, x, y, z, period, exclamation, question, one, two, three, four, five, six, seven, eight, nine, colon, slash, zero, plus, minus;
    
    public TextReader(The_Hub hb)  {
        this.hb=hb;
        TextLoader();
    }
    public void TextLoader() {
       try {
        a=ImageIO.read(getClass().getResourceAsStream("/Resources/Letters/A.png"));
        b=ImageIO.read(getClass().getResourceAsStream("/Resources/Letters/B.png"));
        c=ImageIO.read(getClass().getResourceAsStream("/Resources/Letters/C.png"));
        d=ImageIO.read(getClass().getResourceAsStream("/Resources/Letters/D.png"));
        e=ImageIO.read(getClass().getResourceAsStream("/Resources/Letters/E.png"));
        f=ImageIO.read(getClass().getResourceAsStream("/Resources/Letters/F.png"));
        g=ImageIO.read(getClass().getResourceAsStream("/Resources/Letters/G.png"));
        h=ImageIO.read(getClass().getResourceAsStream("/Resources/Letters/H.png"));
        i=ImageIO.read(getClass().getResourceAsStream("/Resources/Letters/I.png"));
        j=ImageIO.read(getClass().getResourceAsStream("/Resources/Letters/J.png"));
        k=ImageIO.read(getClass().getResourceAsStream("/Resources/Letters/K.png"));
        l=ImageIO.read(getClass().getResourceAsStream("/Resources/Letters/L.png"));
        m=ImageIO.read(getClass().getResourceAsStream("/Resources/Letters/M.png"));
        n=ImageIO.read(getClass().getResourceAsStream("/Resources/Letters/N.png"));
        o=ImageIO.read(getClass().getResourceAsStream("/Resources/Letters/O.png"));
        p=ImageIO.read(getClass().getResourceAsStream("/Resources/Letters/P.png"));
        q=ImageIO.read(getClass().getResourceAsStream("/Resources/Letters/Q.png"));
        r=ImageIO.read(getClass().getResourceAsStream("/Resources/Letters/R.png"));
        s=ImageIO.read(getClass().getResourceAsStream("/Resources/Letters/S.png"));
        t=ImageIO.read(getClass().getResourceAsStream("/Resources/Letters/T.png"));
        u=ImageIO.read(getClass().getResourceAsStream("/Resources/Letters/U.png"));
        v=ImageIO.read(getClass().getResourceAsStream("/Resources/Letters/V.png"));
        w=ImageIO.read(getClass().getResourceAsStream("/Resources/Letters/W.png"));
        x=ImageIO.read(getClass().getResourceAsStream("/Resources/Letters/X.png"));
        y=ImageIO.read(getClass().getResourceAsStream("/Resources/Letters/Y.png"));
        z=ImageIO.read(getClass().getResourceAsStream("/Resources/Letters/Z.png"));
        period=ImageIO.read(getClass().getResourceAsStream("/Resources/Letters/Period.png"));
        exclamation=ImageIO.read(getClass().getResourceAsStream("/Resources/Letters/Exclamation Mark.png"));
        question=ImageIO.read(getClass().getResourceAsStream("/Resources/Letters/Question Mark.png"));
        zero=ImageIO.read(getClass().getResourceAsStream("/Resources/Letters/zero.png"));
        one=ImageIO.read(getClass().getResourceAsStream("/Resources/Letters/one.png"));
        two=ImageIO.read(getClass().getResourceAsStream("/Resources/Letters/two.png"));
        three=ImageIO.read(getClass().getResourceAsStream("/Resources/Letters/three.png"));
        four=ImageIO.read(getClass().getResourceAsStream("/Resources/Letters/four.png"));
        five=ImageIO.read(getClass().getResourceAsStream("/Resources/Letters/five.png"));
        six=ImageIO.read(getClass().getResourceAsStream("/Resources/Letters/six.png"));
        seven=ImageIO.read(getClass().getResourceAsStream("/Resources/Letters/seven.png"));
        eight=ImageIO.read(getClass().getResourceAsStream("/Resources/Letters/eight.png"));
        nine=ImageIO.read(getClass().getResourceAsStream("/Resources/Letters/nine.png"));
        colon=ImageIO.read(getClass().getResourceAsStream("/Resources/Letters/colon.png"));
        slash=ImageIO.read(getClass().getResourceAsStream("/Resources/Letters/Slash.png"));
        plus=ImageIO.read(getClass().getResourceAsStream("/Resources/Letters/Plus.png"));
        minus=ImageIO.read(getClass().getResourceAsStream("/Resources/Letters/Minus.png"));
    } catch (IOException e) {
        e.printStackTrace();
    }
        }
        public void draw(Graphics2D g2, String boi, int xCoord, int yCoord, int sizeX, int sizeY) {
            this.boi=boi;
            this.xCoord=xCoord;
            this.yCoord=yCoord;
        if(boi.length()>=16) {
            boxWidth=20*sizeX;
        }
        else if(boi.length()<16) {
            boxWidth=boi.length()*sizeX;
        }
        boxHeight=(boi.length()/16)+1;
        boxHeight2=boxHeight*sizeY;
        boi2=boi.toCharArray();
        stringEnd=boi.length()-1;
        g2.setColor(Color.BLACK);
            g2.fillRect(xCoord, yCoord, boxWidth, boxHeight2);

            int col=0;
            int row=0;
            BufferedImage image=null;
            for(int index=0; index<=stringEnd; index++) {
                switch (boi2[index]) {
                    case 'A':
                    image=a;
                    break;
                    case 'B':
                    image=b;
                    break;
                    case 'C':
                    image=c;
                    break;
                    case 'D':
                    image=d;
                    break;
                    case 'E':
                    image=e;
                    break;
                    case 'F':
                    image=f;
                    break;
                    case 'G':
                    image=g;
                    break;
                    case 'H':
                    image=h;
                    break;
                    case 'I':
                    image=i;
                    break;
                    case 'J':
                    image=j;
                    break;
                    case 'K':
                    image=k;
                    break;
                    case 'L':
                    image=l;
                    break;
                    case 'M':
                    image=m;
                    break;
                    case 'N':
                    image=n;
                    break;
                    case 'O':
                    image=o;
                    break;
                    case 'P':
                    image=p;
                    break;
                    case 'Q':
                    image=q;
                    break;
                    case 'R':
                    image=r;
                    break;
                    case 'S':
                    image=s;
                    break;
                    case 'T':
                    image=t;
                    break;
                    case 'U':
                    image=u;
                    break;
                    case 'V':
                    image=v;
                    break;
                    case 'W':
                    image=w;
                    break;
                    case 'X':
                    image=x;
                    break;
                    case 'Y':
                    image=y;
                    break;
                    case 'Z':
                    image=z;
                    break;
                    case '.':
                    image=period;
                    break;
                    case '?':
                    image=question;
                    break;
                    case '!':
                    image=exclamation;
                    break;
                    case ' ':
                    image=null;
                    break;
                    case '1':
                    image=one;
                    break;
                    case '2':
                    image=two;
                    break;
                    case '3':
                    image=three;
                    break;
                    case '4':
                    image=four;
                    break;
                    case '5':
                    image=five;
                    break;
                    case '6':
                    image=six;
                    break;
                    case '7':
                    image=seven;
                    break;
                    case '8':
                    image=eight;
                    break;
                    case '9':
                    image=nine;
                    break;
                    case ':':
                    image=colon;
                    break;
                    case '/':
                    image=slash;
                    break;
                    case '0':
                    image=zero;
                    break;
                    case '+':
                    image=plus;
                    break;
                    case '-':
                    image=minus;
                }
               
                col=index%hb.maxScreenHoriz;
                row=index/hb.maxScreenHoriz;
                int X;
                int Y;
                if(sizeX==hb.resTileSize/3 && sizeY==hb.resTileSize/3) {
                    X=xCoord+col*hb.resTileSize/3;
                    Y=yCoord+row*hb.resTileSize/3;
                }
                else {
                    X=xCoord+col*hb.resTileSize/2;
                    Y=yCoord+row*hb.resTileSize/2;
                }
            g2.drawImage(image, X, Y, sizeX, sizeY, null); 
            }
    
        }
    
    public static void main(String[] args) {
       
         
        
        
    }
}


