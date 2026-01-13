package gui;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import java.awt.CardLayout;

public class MainFrame extends JFrame {
    private static MainFrame instance;
    private CardLayout cardLayout;
    private JPanel mainPanel;

    // Referencje do paneli
    private LoginPanel loginPanel;
    private RegisterPanel registerPanel;
    private DashboardPanel dashboardPanel;
    private AccountPanel accountPanel;
    private BrowsePanel browsePanel;
    private ReservationsPanel reservationsPanel;
    private BookingPanel bookingPanel;

    // Prywatny konstruktor (Singleton)
    private MainFrame() {
        initFrame();
        initPanels();
    }

    // Singleton pattern - tylko jedna instancja głównego okna
    public static MainFrame getInstance() {
        if (instance == null) {
            instance = new MainFrame();
        }
        return instance;
    }

    private void initFrame() {
        setTitle("System Studencki - Projekt");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1100, 800);
        setLocationRelativeTo(null);

        // setIconImage(new ImageIcon("icon.png").getImage());
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        add(mainPanel);

        // Menu (będzie widoczne tylko po zalogowaniu)
        setJMenuBar(createMenuBar());
    }

    private void initPanels() {
        // Tworzymy panele
        loginPanel = new LoginPanel();
        registerPanel = new RegisterPanel();
        dashboardPanel = new DashboardPanel();
        accountPanel = new AccountPanel();
        browsePanel = new BrowsePanel();
        bookingPanel = new BookingPanel();
        reservationsPanel = new ReservationsPanel();

        // Dodajemy panele do CardLayout z nazwami
        mainPanel.add(loginPanel, "LOGIN");
        mainPanel.add(registerPanel, "REGISTER");
        mainPanel.add(dashboardPanel, "DASHBOARD");
        mainPanel.add(accountPanel, "ACCOUNT");
        mainPanel.add(browsePanel, "BROWSE");
        mainPanel.add(reservationsPanel, "RESERVATIONS");
        mainPanel.add(bookingPanel, "BOOKING");
    }

    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        JButton dashboardButton = new JButton("Dashboard");
        dashboardButton.addActionListener(e -> showDashboard());

        JButton accountButton = new JButton("Moje konto");
        accountButton.addActionListener(e -> showAccount());

        JButton reservationsButton = new JButton("Moje rezerwacje");
        reservationsButton.addActionListener(e -> showReservastions());

        JButton browseButton = new JButton("Przeglądaj sale konferencyjne");
        browseButton.addActionListener(e -> showBrowse());


        JButton bookingButton = new JButton("booking");
        bookingButton.addActionListener(e -> showBooking());


        JButton logoutButton = new JButton("Wyloguj się");
        logoutButton.addActionListener(e -> showLogin());

        JButton exitButton = new JButton("Zamknij");
        exitButton.addActionListener(e -> System.exit(0));


        menuBar.add(dashboardButton);
        menuBar.add(accountButton);
        menuBar.add(reservationsButton);
        menuBar.add(browseButton);

        menuBar.add(new JSeparator());

        menuBar.add(bookingButton);

        menuBar.add(new JSeparator());

        menuBar.add(logoutButton);
        menuBar.add(exitButton);

        return menuBar;
    }

    // Metody do przełączania widoków
    public void showLogin() {
        cardLayout.show(mainPanel, "LOGIN");
        getJMenuBar().setVisible(false);
        setTitle("System Sal - Logowanie");
    }

    public void showRegister() {
        cardLayout.show(mainPanel, "REGISTER");
        getJMenuBar().setVisible(false);
        setTitle("System Sal - Rejestracja");
    }

    public void showDashboard() {
        cardLayout.show(mainPanel, "DASHBOARD");
        getJMenuBar().setVisible(true);
        setTitle("System Sal - Dashboard");
    }

    public void showAccount() {
        cardLayout.show(mainPanel, "ACCOUNT");
        getJMenuBar().setVisible(true);
        setTitle("System Sal - Moje konto");
    }

    public void showBrowse() {
        cardLayout.show(mainPanel, "BROWSE");
        getJMenuBar().setVisible(true);
        setTitle("System Sal - przegldądaj");
    }

    public void showBooking() {
        cardLayout.show(mainPanel, "BOOKING");
        getJMenuBar().setVisible(true);
        setTitle("System Sal - rezerwacja");
    }

    public void showReservastions() {
        cardLayout.show(mainPanel, "RESERVATIONS");
        getJMenuBar().setVisible(true);
        setTitle("System Sal - historia rezerwacji");
    }

    // Gettery do paneli (jeśli inne klasy potrzebują dostępu)
    public LoginPanel getLoginPanel() {
        return loginPanel;
    }

    public DashboardPanel getDashboardPanel() {
        return dashboardPanel;
    }
}