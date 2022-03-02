import javax.swing.AbstractAction;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.table.JTableHeader;
import Table.TableModel;
import Task.Result;
import Task.Task;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
import Task.Status;
import java.awt.Color;

public class Main extends JFrame {

    private static final long serialVersionUID = 1L;

    private static JButton _button;
    private static JTableHeader _header;
    private static TableModel _model;
    private static Table _table;
    private static ExecutorService _executorService;

    public static void main(String[] args) {
        new Main();
    }

    private Main() {
        _model = new TableModel();
        _table = new Table(_model);
        _header = _table.getTableHeader();
        Action action = new Action();
        _button = new JButton(action);
        _executorService = Executors.newFixedThreadPool(10);
        SwingUtilities.invokeLater(() -> start());
    }

    private void start() {
        Container contentPane = getContentPane();
        BoxLayout layout = new BoxLayout(contentPane, BoxLayout.Y_AXIS);
        contentPane.setLayout(layout);
        contentPane.add(_header);
        contentPane.add(_table);
        contentPane.add(_button);
        pack();
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Thread thread = new Thread(() -> run());
        thread.start();
    }

    private void run() {
        try {
            while (true) {
                Thread.sleep(200);
                _model.fireTableDataChanged();
            }
        } catch (Throwable e) {
        }
    }

    private class Action extends AbstractAction {
        private static final long serialVersionUID = 2964797485807886991L;

        public Action() {
            putValue(NAME, "Add task");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            Task task = new Task();
            Future<Result> future = _executorService.submit(task);
            _model.addTask(task, future);
        }
    }

    private class CellRender extends JLabel implements TableCellRenderer {

        private static final long serialVersionUID = 1L;

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
                int row, int column) {
            if( value != null ){
                JLabel label = new JLabel();
                label.setOpaque(true);
                Status status = (Status)value;
                label.setText(status.getName());
                label.setBackground(backgroundColor(status));
                label.setForeground(foregroundColor(status));
                add(label);
                return label;
            }
            return this;
        }   

        public Color foregroundColor(Status status){
            
            if (status != null){
                Color c = null;
                
                switch (status){
                    case RUNNING:
                    c = Color.BLACK;
                    case PENDING:
                    c = Color.BLACK;
                    case ACCOMPLISHED:
                    c = Color.BLACK;
                    case FAILED:
                    c = Color.BLACK;
                }
                return c;
            }else {
                return null;
            }
        } 

        public Color backgroundColor(Status status){
            if (status != null){
                Color c = null;
                switch (status){
                    case RUNNING:
                    c = Color.ORANGE;
                    break;
                    case PENDING:
                    c = Color.yellow;
                    break;
                    case ACCOMPLISHED:
                    c = Color.GREEN;
                    break;
                    case FAILED:
                    c = Color.RED;
                    break;
                }
                return c;
            }else{
                return null;
            }
            
        } 
    }

    public class Table extends JTable {

        private static final long serialVersionUID = 1L;
        private TableCellRenderer _render;
        
        public Table(TableModel model){
            super(model);
            _render = new CellRender();
        }
    
        @Override
        public TableCellRenderer getCellRenderer(int row , int column){
            if(column  == TableModel.cStatusIndex){
                return _render;
            }
            return super.getCellRenderer(row, column);
        }
        
    }
}


