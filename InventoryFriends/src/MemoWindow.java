import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.text.SimpleDateFormat;

public class MemoWindow extends JFrame{
    JPanel jpMemoWinDate, jpMemoWin, jpMemoWinBtn;
    static JLabel jlDate;
    JButton btnSave, btnCancle, btnDelete;
    JTextArea jtaMemoWin;
    JScrollPane jspMemoWin;
    static Integer selectIndex;
    static MemoTab memoTab;
    static String seleteDate;


    public MemoWindow(){
        setSize(300, 400);

        jpMemoWin = new JPanel(new BorderLayout());
        jpMemoWinBtn = new JPanel();
        jpMemoWinDate = new JPanel();
        jtaMemoWin = new JTextArea();
        jspMemoWin = new JScrollPane(jtaMemoWin);
        jtaMemoWin.setLineWrap(true);           //자동 줄바꿈
        jlDate = new JLabel();
        jpMemoWinBtn.setLayout(new BoxLayout(jpMemoWinBtn, BoxLayout.LINE_AXIS));
        jpMemoWinDate.setBackground(Color.WHITE);
        jpMemoWinBtn.setBackground(Color.WHITE);

        jspMemoWin.setBorder(BorderFactory.createEmptyBorder());
        jtaMemoWin.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        btnDelete = new JButton("삭제");
        btnDelete.setEnabled(false);
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selectIndex != null){
                    int input = JOptionPane.showConfirmDialog(null, "이 메모를 삭제하시겠습니까?",
                            "삭제 확인", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

                    if(input == JOptionPane.OK_OPTION){
                        deleteMemo(memoTab);
                        setVisible(false);
                        selectIndex = null;
                    }
                }
            }
        });
        btnSave = new JButton("저장");
        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selectIndex != null){
                    changeMemo(memoTab, jtaMemoWin.getText(), jlDate.getText());
                    setVisible(false);
                    dispose();
                } else {
                    saveMemo(memoTab, jtaMemoWin.getText(), jlDate.getText());
                    setVisible(false);
                    dispose();
                }
                selectIndex = null;
//                System.out.println(memoCont);
            }
        });
        btnCancle = new JButton("취소");
        btnCancle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                dispose();
            }
        });

        jpMemoWinDate.add(jlDate);
        jpMemoWinBtn.add(btnDelete);
        jpMemoWinBtn.add(Box.createHorizontalGlue());
        jpMemoWinBtn.add(btnSave);
        jpMemoWinBtn.add(btnCancle);
        jpMemoWin.add(jpMemoWinDate, BorderLayout.NORTH);
        jpMemoWin.add(jspMemoWin, BorderLayout.CENTER);
        jpMemoWin.add(jpMemoWinBtn, BorderLayout.SOUTH);

        add(jpMemoWin);
        setResizable(false);
        setLocationRelativeTo(null);            //창을 화면 중앙으로
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                setVisible(false);
                dispose();
            }
        });
    }

    public void saveMemo(MemoTab mt, String memoCont, String date){
        mt.addMemo(memoCont, date);
        mt.revalidate();
        mt.repaint();
    }
    public void changeMemo(MemoTab mt, String memoCont, String date){
        mt.changeMemo(selectIndex, memoCont, date);
        mt.revalidate();
        mt.repaint();
    }
    public void deleteMemo(MemoTab mt){
        mt.deleteMemo(selectIndex);
    }
    public void setMemoTab(MemoTab mt){
        memoTab = mt;
    }
    public void setIndex(int idx){
        selectIndex = idx;
    }
    public void setDate(LocalDate date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        seleteDate = date.format(formatter);
        jlDate.setText(seleteDate);
//        jlDate.repaint();
    }
    public void setDate(String date){
        jlDate.setText(date);
//        jlDate.repaint();
    }
    public void setDate(Object date){
        SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");
        seleteDate = df.format(date);
        jlDate.setText(seleteDate);
    }
    public static void main(String[] args) {
        MemoWindow f =new MemoWindow();
        f.setDate(LocalDate.now());
        f.setVisible(true);
    }
}
