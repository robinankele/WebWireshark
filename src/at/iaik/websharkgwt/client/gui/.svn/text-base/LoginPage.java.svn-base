package at.iaik.websharkgwt.client.gui;

import at.iaik.websharkgwt.client.services.LoginService;
import at.iaik.websharkgwt.client.services.FileUploadService;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;


/**
 * @author 		Ralph Ankele
 * @description This class represents the LoginPage
 *
 */
public class LoginPage extends Composite implements HasText {

	@UiField
	Button loginButton;

	private static LoginPageUiBinder uiBinder = GWT
			.create(LoginPageUiBinder.class);

	interface LoginPageUiBinder extends UiBinder<Widget, LoginPage> {
	}

	/**
	 * Constructor 
	 */
	public LoginPage() {
		initWidget(uiBinder.createAndBindUi(this));
		loginButton.setText("Login");
	}
	
	/**
	 * When the login button is clicked blabla happens
	 */
	@UiHandler("loginButton")
	void onClick(ClickEvent e) {
		
		if(loginButton.getText().equals("Login"))
		{
			login();
		}
		else if(loginButton.getText().equals("Logout"))
			logout();

	}

	/**
	 * Logs a user in
	 */
	public void login() {
		//do anything
		LoginService service = new LoginService("loginServlet");
		service.sendLoginRequest();
		
		loginButton.setText("Logout");
		
		RootPanel.get().add(new PacketDisplayTable());
		//Window.alert("Hello!");
		
	/*	Command cmdOpen = new Command(){
		      public void execute() {

		        }
		};
		
		Command cmdSave = new Command(){
		      public void execute() {

		        }
		};
		
		Command cmdSaveAs = new Command(){
		      public void execute() {

		        }
		};
		
		Command cmdQuit = new Command(){
		      public void execute() {

		        }
		};
		
		Command cmdFindPacket = new Command(){
		      public void execute() {

		        }
		};
		
		Command cmdMarkPacket = new Command(){
		      public void execute() {

		        }
		};
		
		Command cmdIgnorPacket = new Command(){
		      public void execute() {

		        }
		};
		
		Command cmdPreferences = new Command(){
		      public void execute() {

		        }
		};
		
		Command cmdZoomIn = new Command(){
		      public void execute() {

		        }
		};
		
		Command cmdZoomOut = new Command(){
		      public void execute() {

		        }
		};
		
		Command cmdColoring = new Command(){
		      public void execute() {

		        }
		};
		
		Command cmdReload = new Command(){
		      public void execute() {

		        }
		};
		
		Command cmdBack = new Command(){
		      public void execute() {

		        }
		};
		
		Command cmdForward = new Command(){
		      public void execute() {

		        }
		};
		
		Command cmdGoToPacket = new Command(){
		      public void execute() {

		        }
		};
		
		Command cmdPreviousPacket = new Command(){
		      public void execute() {

		        }
		};
		
		Command cmdNextPacket = new Command(){
		      public void execute() {

		        }
		};
		
		Command cmdFirstPacket = new Command(){
		      public void execute() {

		        }
		};
		
		Command cmdLastPacket = new Command(){
		      public void execute() {

		        }
		};
		
		Command cmdStart = new Command(){
		      public void execute() {

		        }
		};
		
		Command cmdStop = new Command(){
		      public void execute() {

		        }
		};
		
		Command cmdReStart = new Command(){
		      public void execute() {

		        }
		};
		
		Command cmdCaptureFilter = new Command(){
		      public void execute() {

		        }
		};
		
		Command cmdSummary = new Command(){
		      public void execute() {

		        }
		};
		
		Command cmdConversations = new Command(){
		      public void execute() {

		        }
		};
		
		Command cmdIOGraphs = new Command(){
		      public void execute() {

		        }
		};
		
		Command cmdReadMe = new Command(){
		      public void execute() {

		        }
		};
		
		Command cmdAbout = new Command(){
		      public void execute() {

		        }
		};

	    // Make some sub-menus that we will cascade from the top menu.
	    MenuBar fileMenu = new MenuBar(true);
	    fileMenu.addItem("Open", cmdOpen);
	    fileMenu.addItem("Save", cmdSave);
	    fileMenu.addItem("Save As", cmdSaveAs);
	    fileMenu.addItem("Quit", cmdQuit);

	    MenuBar editMenu = new MenuBar(true);
	    editMenu.addItem("Find Packet", cmdFindPacket);
	    editMenu.addItem("Mark Packet", cmdMarkPacket);
	    editMenu.addItem("Ignor Packet", cmdIgnorPacket);
	    editMenu.addItem("Preferences", cmdPreferences);
	    
	    MenuBar viewMenu = new MenuBar(true);
	    viewMenu.addItem("Zoom In", cmdZoomIn);
	    viewMenu.addItem("Zoom Out", cmdZoomOut);
	    viewMenu.addItem("Coloring", cmdColoring);
	    viewMenu.addItem("Reload", cmdReload);
	    
	    MenuBar goMenu = new MenuBar(true);
	    goMenu.addItem("Back", cmdBack);
	    goMenu.addItem("Forward", cmdForward);
	    goMenu.addItem("Go To Packet", cmdGoToPacket);
	    goMenu.addItem("Previous Packet", cmdPreviousPacket);
	    goMenu.addItem("Next Packet", cmdNextPacket);
	    goMenu.addItem("First Packet", cmdFirstPacket);
	    goMenu.addItem("Last Packet", cmdLastPacket);
	    
	    MenuBar captureMenu = new MenuBar(true);
	    captureMenu.addItem("Start", cmdStart);
	    captureMenu.addItem("Stop", cmdStop);
	    captureMenu.addItem("Restart", cmdReStart);
	    captureMenu.addItem("Capture Filtes", cmdCaptureFilter);
	    
	    MenuBar statisticMenu = new MenuBar(true);
	    statisticMenu.addItem("Summary", cmdSummary);
	    statisticMenu.addItem("Conversations", cmdConversations);
	    statisticMenu.addItem("IO-Graphs", cmdIOGraphs);

	    MenuBar helpMenu = new MenuBar(true);
	    helpMenu.addItem("Read Me", cmdReadMe);
	    helpMenu.addItem("About", cmdAbout);

	    // Make a new menu bar, adding a few cascading menus to it.
	    MenuBar menu = new MenuBar();
	    menu.addItem("File", fileMenu);
	    menu.addItem("Edit", editMenu);
	    menu.addItem("View", viewMenu);
	    menu.addItem("Go", goMenu);
	    menu.addItem("Capture", captureMenu);
	    menu.addItem("Statistic", statisticMenu);
	    menu.addItem("Help", helpMenu);

	    // Add it to the root panel.
	    RootPanel.get().add(menu);
	    RootPanel.get().add(new PacketsViewer());
	    //progressbutton();
	     
	     */
		
	}
	
	/**
	 * Log a user off
	 */
	public void logout() {
		loginButton.setText("Login");
		RootPanel.get().clear();
		RootPanel.get().add(this);
		
		//Window.alert("( o ) ( o )  HONEY TITS!!!");
	}
	
	/**
	 * Sets the text of the Button
	 */
	public void setText(String text) {
		loginButton.setText(text);
	}

	/**
	 * Returns the text of the Button
	 */
	public String getText() {
		return loginButton.getText();
	}
	
	
	
	/**
	 * When the login button is clicked blabla happens
	 */
/*	@UiHandler("testButton")
	void onClick1(ClickEvent e) {
		  
		MessageBox box = new MessageBox();
		box.updateProgress(20, "blabla");*/
        /*MessageBox.show(

                setTitle("Please wait...");  
                setMsg("Initializing...");  
                setWidth(240);  
                setProgress(true);  
                setClosable(false);  
                setCallback(new MessageBox.PromptCallback() {  
                    public void execute(String btnID, String text) {  
   
                    }  
                });  
                setAnimEl(testButton);  
        );  */

        //create bogus progress  
     /*   for (int i = 1; i < 12; i++) {  
            final int j = i;  
            Timer timer = new Timer() {  
                public void run() {  
                    if (j == 11) {  
                        MessageBox.hide();  
                    } else {  
                        MessageBox.updateProgress(j * 10, "Loading item "  
                                                    + j + " of 10... ");  
                    }  
                }  
            };  
            timer.schedule(i * 1000);  
        }  */ 
	//} 
}



