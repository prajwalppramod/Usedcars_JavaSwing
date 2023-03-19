import javax.swing.*;
import java.awt.*;
import java.sql.*;  
import java.awt.event.*;
//https://web.mit.edu/6.005/www/sp14/psets/ps4/java-6-tutorial/components.html
class Microproject
{
    public static void main(String[] args) 
    {   
        try
        {   //********loading page*******//
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/usedcars", "carguy", "carguy");
            Statement st = conn.createStatement();
            JFrame open = new JFrame("Loading...");
            JLabel usedcars = new JLabel("Used Cars Finder");
            open.add(usedcars);
            usedcars.setBounds(250,180,300,80);
            usedcars.setFont(new Font("Helvetica", Font.BOLD, 30));
            open.setBounds(175,0,360,360);
            open.setSize(750,500);
            open.setLayout(null);
            open.setVisible(true);
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            open.setVisible(false);
            //********home page*******//
            JFrame home = new JFrame("Used Cars Finder");
            home.setBounds(175,0,360,360);
            home.setSize(750,500);
            JLabel logo = new JLabel("Used Cars Finder");
            JLabel bio = new JLabel("Finally, it's Easy. Find your perfect match from the comfort of home and shop millions of new and used cars based on your budget.");
            home.add(bio);
            logo.setFont(new Font("Helvetica", Font.BOLD, 18));
            home.add(logo);
            logo.setBounds(275,-20,200,100);
            bio.setBounds(120,7,500,100);
            home.setLayout(null);
            home.setVisible(true);
            String[] petStrings = { "Karnataka" , "Kerala", "Delhi", "Tamilnadu", "Telangana", "Maharashtra" };
            JComboBox<String> petList = new JComboBox<String>(petStrings);
            home.add(petList);
            petList.setBounds(220,100,300,50);
            JButton state= new JButton("Find cars");
            home.add(state);
            state.setBounds(220,200,300,50);
            ///******************list page**************************** */
            JFrame list = new JFrame("Used Cars");
            list.setLayout(null);
            list.setSize(750,500);
            //list.setBounds(175,0,750,500);
            JLabel srch = new JLabel("Check out these cars...");
            srch.setFont(new Font("Helvetica", Font.BOLD, 20));
            list.add(srch);
            srch.setBounds(50,50,300,20);
            //ResultSet rs = st.executeQuery("SELECT * FROM usedcars where city = '" + petStrings[petList.getSelectedIndex()]+"'");
            // JLabel jl = new JLabel("YEss");
            // jl.setBounds(100, 100, 100, 100);
            // list.add(jl);
            // int i=0;
            // while(rs.next()) {
            // JLabel l = new JLabel(rs.getInt("carid") + ": " + rs.getString("carname") + " (" + rs.getString("cartype") + ") for Rs." + rs.getInt("carprice") + ". Place: " + rs.getString("city"));
            // l.setBounds(50, 100+(50*i), 700, 50);
            // list.add(l);
            // System.out.println(rs.getString("carname"));
            // i++;
            //}
            state.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    try{
                    home.setVisible(false);
                    ResultSet rs = st.executeQuery("SELECT * FROM usedcars where city = '" + petList.getSelectedItem() +"';");
                    int i=0;
                    while(rs.next()) {
                        JLabel l = new JLabel(rs.getInt("carid") + ": " + rs.getString("carname") + " (" + rs.getString("cartype") + ") for Rs." + rs.getInt("carprice") + ". Place: " + rs.getString("city"));
                        l.setBounds(50, 100+(50*i), 700, 50);
                        list.add(l);
                        System.out.println(rs.getString("carname"));
                        i++;
                    }
                    list.setVisible(true);
                }
                catch(Exception err) {
                    System.out.println(err);
                }
            }
            });
            JButton back=new JButton("Back");
            list.add(back);
            back.setBounds(325,380,100,50);
            back.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                    list.setVisible(false);
                    list.getContentPane().removeAll();
                    list.add(back);
                    list.add(srch);
                    
                    home.setVisible(true);
                }
            });
        
        }
        catch(Exception e)
        {
            System.out.println(""+e.toString());
        }
    }

}