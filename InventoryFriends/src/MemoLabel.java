import javax.swing.*;

public class MemoLabel extends JLabel {
    String date;
    String contents;

    public MemoLabel(){
        super();
    }
    public MemoLabel(String str){
        this.contents = str;
        setText(str);
    }
    public MemoLabel(String str, String date){
        this.contents = str;
        this.date = date;
        super.setText(this.date + " || " + this.contents);
    }

    @Override
    public void setText(String str){
        if (this.date != null){
            this.contents = str;
            super.setText(this.date + " || " + this.contents);
        } else {
            this.contents = str;
            super.setText(str);
        }

    }

    public String getContents(){
        return this.contents;
    }

    public String getDate(){
        return this.date;
    }

    public static void main(String[] args) {
        JFrame f = new JFrame();
        JPanel p = new JPanel();
        MemoLabel ml = new MemoLabel("123");

        p.add(ml);
        f.add(p);
        f.setSize(100, 210);
        f.setVisible(true);

    }
}
