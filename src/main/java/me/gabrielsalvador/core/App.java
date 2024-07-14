package me.gabrielsalvador.core;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import com.google.gson.Gson;
import controlP5.*;
import controlP5.layout.LayoutBuilder;
import me.friwi.jcefmaven.CefAppBuilder;
import me.friwi.jcefmaven.CefInitializationException;
import me.friwi.jcefmaven.MavenCefAppHandlerAdapter;
import me.friwi.jcefmaven.UnsupportedPlatformException;
import me.friwi.jcefmaven.impl.progress.ConsoleProgressHandler;
import me.gabrielsalvador.Config;
import me.gabrielsalvador.kinescript.ast.KFunction;
import me.gabrielsalvador.kinescript.ast.KList;
import me.gabrielsalvador.pobject.PObjectPreset;
import me.gabrielsalvador.pobject.components.RoutingComponent;
import me.gabrielsalvador.pobject.components.body.BodyComponent;
import me.gabrielsalvador.ui.*;
import me.gabrielsalvador.pobject.PObject;
import me.gabrielsalvador.timing.Clock;
import org.apache.logging.log4j.core.util.FileWatcher;
import org.cef.CefApp;
import org.cef.CefClient;
import org.cef.browser.CefBrowser;
import org.cef.browser.CefFrame;
import org.cef.browser.CefMessageRouter;
import org.cef.callback.CefQueryCallback;
import org.cef.handler.*;
import org.cef.misc.BoolRef;
import org.cef.network.CefRequest;
import org.jbox2d.common.Vec2;
import processing.core.PApplet;
import processing.core.PFont;

import javax.swing.*;
import java.nio.file.*;

import static java.nio.file.StandardWatchEventKinds.*;

import java.nio.file.attribute.*;


public class App extends JFrame {

    private JDialog devToolsDialog;
    private CefBrowser devTools;
    private static final long serialVersionUID = -5570653778104813836L;
    private final JTextField address_;
    private final CefApp cefApp_;
    private final CefClient client_;
    private CefBrowser browser_;
    private final Component browerUI_;
    private boolean browserFocus_ = true;

    private static App _instance;

    public App(String startURL, boolean useOSR, boolean isTransparent, String[] args) throws UnsupportedPlatformException, CefInitializationException, IOException, InterruptedException {
        super();
        _instance = this;


        ArrayList<PObject> pObjects = AppState.getInstance().getPObjects();
        //add 100 random objects
        for (int i = 0; i < 100; i++) {
            PObjectPreset.DropletPreset dropletPreset = new PObjectPreset.DropletPreset(new Vec2((float) Math.random() * 100, (float) Math.random() * 100));
            PObject[] droplets = dropletPreset.create();
            for (PObject droplet : droplets) {
                AppState.getInstance().addPObject(droplet);
            }
        }


        CanvasController canvasController = new CanvasController();
        AppController.getInstance().addDroplet();
//        Clock.getInstance().play();


        // (0) Initialize CEF using the maven loader
        CefAppBuilder builder = new CefAppBuilder();
        // windowless_rendering_enabled must be set to false if not wanted.
//            builder.getCefSettings().windowless_rendering_enabled = useOSR;
        // USE builder.setAppHandler INSTEAD OF CefApp.addAppHandler!
        // Fixes compatibility issues with MacOSX
        builder.setAppHandler(new MavenCefAppHandlerAdapter() {
            @Override
            public void stateHasChanged(org.cef.CefApp.CefAppState state) {
                // Shutdown the app if the native CEF part is terminated
                if (state == CefApp.CefAppState.TERMINATED) System.exit(0);
            }
        });

        if (args.length > 0) {
            builder.addJcefArgs(args);
        }

        // (1) The entry point to JCEF is always the class CefApp. There is only one
        //     instance per application and therefore you have to call the method
        //     "getInstance()" instead of a CTOR.
        //
        //     CefApp is responsible for the global CEF context. It loads all
        //     required native libraries, initializes CEF accordingly, starts a
        //     background task to handle CEF's message loop and takes care of
        //     shutting down CEF after disposing it.
        //
        //     WHEN WORKING WITH MAVEN: Use the builder.build() method to
        //     build the CefApp on first run and fetch the instance on all consecutive
        //     runs. This method is thread-safe and will always return a valid app
        //     instance.
        cefApp_ = builder.build();

        // (2) JCEF can handle one to many browser instances simultaneous. These
        //     browser instances are logically grouped together by an instance of
        //     the class CefClient. In your application you can create one to many
        //     instances of CefClient with one to many CefBrowser instances per
        //     client. To get an instance of CefClient you have to use the method
        //     "createClient()" of your CefApp instance. Calling an CTOR of
        //     CefClient is not supported.
        //
        //     CefClient is a connector to all possible events which come from the
        //     CefBrowser instances. Those events could be simple things like the
        //     change of the browser title or more complex ones like context menu
        //     events. By assigning handlers to CefClient you can control the
        //     behavior of the browser. See tests.detailed.MainFrame for an example
        //     of how to use these handlers.
        client_ = cefApp_.createClient();

        // (3) Create a simple message router to receive messages from CEF.
        CefMessageRouter msgRouter = CefMessageRouter.create(new CefMessageRouter.CefMessageRouterConfig("myQuery", "cancelMyQuery"));
        client_.addMessageRouter(msgRouter);
        msgRouter.addHandler(new CefMessageRouterHandlerAdapter() {
            @Override
            public boolean onQuery(CefBrowser browser, CefFrame frame, long query_id, String request, boolean persistent, CefQueryCallback callback) {
                // Check if the request is what we expect
                if (request.startsWith("callJava:")) {
                    // Extract the message sent from JavaScript
                    String message = request.substring("callJava:".length());
                    // Handle the message, e.g., by calling a method in your application
                    ArrayList<PObject> objects = callJava(message);

                    StringBuilder jsonResponse = new StringBuilder();

                    jsonResponse.append("[");
                    for (PObject object : objects) {
                        for (int i = 0; i < 1; i++) {
                            line(jsonResponse, object);
                        }

                    }
                    jsonResponse.deleteCharAt(jsonResponse.length() - 1);
                    jsonResponse.append("]");

                    // Send a response back to JavaScript
                    callback.success(jsonResponse.toString());
                    return true; // Indicate that the query has been handled
                }
                return false; // Indicate that the query has not been handled
            }
        }, true);

        msgRouter.addHandler(new CefMessageRouterHandlerAdapter() {
            @Override
            public boolean onQuery(CefBrowser browser, CefFrame frame, long query_id, String request, boolean persistent, CefQueryCallback callback) {
                // Check if the request is what we expect
                if (request.startsWith("runKinScript:")) {
                    // Extract the message sent from JavaScript
                    String script = request.substring("runKinScript:".length());
                    // Handle the message, e.g., by calling a method in your application
                    runKinScript(script);
                    // Send a response back to JavaScript
                    callback.success("Script executed");
                    return true; // Indicate that the query has been handled
                }
                return false; // Indicate that the query has not been handled
            }
        }, true);

        // (4) One CefBrowser instance is responsible to control what you'll see on
        //     the UI component of the instance. It can be displayed off-screen
        //     rendered or windowed rendered. To get an instance of CefBrowser you
        //     have to call the method "createBrowser()" of your CefClient
        //     instances.
        //
        //     CefBrowser has methods like "goBack()", "goForward()", "loadURL()",
        //     and many more which are used to control the behavior of the displayed
        //     content. The UI is held within a UI-Compontent which can be accessed
        //     by calling the method "getUIComponent()" on the instance of CefBrowser.
        //     The UI component is inherited from a java.awt.Component and therefore
        //     it can be embedded into any AWT UI.

        Path path = Paths.get("/Users/gabrielsalvador/Code/kines-thesia/src/main/frontend/.webpack/renderer/main_window/index.html");


//        browser_ = client_.createBrowser("file://" + path, isTransparent, useOSR);
//        http://localhost/customContent
        browser_ = client_.createBrowser("index.html", isTransparent, useOSR);
        browerUI_ = browser_.getUIComponent();

        FileWatcherService fileWatcherService = new FileWatcherService(Paths.get("src/main"), browser_);
        new Thread(fileWatcherService).start();


        // (5) For this minimal browser, we need only a text field to enter an URL
        //     we want to navigate to and a CefBrowser window to display the content
        //     of the URL. To respond to the input of the user, we're registering an
        //     anonymous ActionListener. This listener is performed each time the
        //     user presses the "ENTER" key within the address field.
        //     If this happens, the entered value is passed to the CefBrowser
        //     instance to be loaded as URL.
        address_ = new JTextField(startURL, 100);
        address_.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                browser_.loadURL(address_.getText());
            }
        });

        // Update the address field when the browser URL changes.
        client_.addDisplayHandler(new CefDisplayHandlerAdapter() {
            @Override
            public void onAddressChange(CefBrowser browser, CefFrame frame, String url) {
                address_.setText(url);
            }
        });

        // Clear focus from the browser when the address field gains focus.
        address_.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (!browserFocus_) return;
                browserFocus_ = false;
                KeyboardFocusManager.getCurrentKeyboardFocusManager().clearGlobalFocusOwner();
                address_.requestFocus();
            }
        });

        // Clear focus from the address field when the browser gains focus.
        client_.addFocusHandler(new CefFocusHandlerAdapter() {
            @Override
            public void onGotFocus(CefBrowser browser) {
                if (browserFocus_) return;
                browserFocus_ = true;
                KeyboardFocusManager.getCurrentKeyboardFocusManager().clearGlobalFocusOwner();
                browser.setFocus(true);
            }

            @Override
            public void onTakeFocus(CefBrowser browser, boolean next) {
                browserFocus_ = false;
            }
        });

        // (6) All UI components are assigned to the default content pane of this
        //     JFrame and afterwards the frame is made visible to the user.
        getContentPane().add(address_, BorderLayout.NORTH);
        getContentPane().add(browerUI_, BorderLayout.CENTER);
        pack();
        setSize(800, 600);
        setVisible(true);

        // (7) To take care of shutting down CEF accordingly, it's important to call
        //     the method "dispose()" of the CefApp instance if the Java
        //     application will be closed. Otherwise you'll get asserts from CEF.
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                CefApp.getInstance().dispose();
                dispose();
            }
        });

        CefBrowser myDevTools = browser_.getDevTools();
        if (devToolsDialog == null) {
            devToolsDialog = new JDialog();
            devToolsDialog.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
            devToolsDialog.setSize(800, 600);
            devToolsDialog.add(browser_.getDevTools().getUIComponent());
        } else {
            devToolsDialog.removeAll();
            devToolsDialog.add(browser_.getDevTools().getUIComponent());
            devToolsDialog.validate();
            devToolsDialog.getContentPane().repaint();
        }
        devToolsDialog.setVisible(true);


        client_.addRequestHandler(new CefRequestHandlerAdapter() {
            @Override
            public CefResourceRequestHandler getResourceRequestHandler(CefBrowser browser, CefFrame frame, CefRequest request, boolean isNavigation, boolean isDownload, String requestInitiator, BoolRef disableDefaultHandling) {
//               if (request.getURL().endsWith("ontent/")) {
                // Prepare the content to serve

                CustomResourceHandler customResourceHandler = new CustomResourceHandler();
                return customResourceHandler;
//               }
//               return null;
            }
        });

    }


    public void setup() {


        loadAppState();


        Map<String, Object> scope = KFunction.getScope();
        ArrayList<?> objects = AppState.getInstance().getPObjects();
        scope.put("all", new KList(objects));


        System.out.println("App initialized");
        System.out.println("=========================");
        System.out.println("Press SPACE to play/pause");
        System.out.println("Alt + / to toggle console");

    }


    public ArrayList<PObject> callJava(String message) {
//        System.out.println("Message from JavaScript: " + message);

        ArrayList<PObject> pObjects = AppState.getInstance().getPObjects();

        return pObjects;
    }


    public void runKinScript(String script) {

    }

    private void loadAppState() {

        try (FileInputStream fileIn = new FileInputStream("appState.ser");
             ObjectInputStream in = new ObjectInputStream(fileIn)) {
            AppState loadedState = (AppState) in.readObject();
            AppState myState = AppState.getInstance();

            for (PObject pObject : loadedState.getPObjects()) {
                myState.addPObject(pObject);
            }
            myState.loadSequencerState(loadedState.getSequencerState());
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }

    }


    public static void main(String[] args) throws UnsupportedPlatformException, CefInitializationException, IOException, InterruptedException {
        App app = new App("https://www.google.com", false, false, args);

    }


    public void line(StringBuilder jsonResponse, PObject object) {

        int hash = object.hashCode();
        Vec2 position = object.getBodyComponent().getPosition();


        jsonResponse.append("{");
        jsonResponse.append("\"hash\":").append(hash).append(",");
        jsonResponse.append("\"x\":").append(position.x).append(",");
        jsonResponse.append("\"y\":").append(position.y);
        jsonResponse.append("},");



    }


    class FileWatcherService implements Runnable {
        private final Path pathToWatch;
        private final CefBrowser browser;

        public FileWatcherService(Path pathToWatch, CefBrowser browser) {
            this.pathToWatch = pathToWatch;
            this.browser = browser;
        }

        @Override
        public void run() {
            try (WatchService watchService = FileSystems.getDefault().newWatchService()) {
                pathToWatch.register(watchService, ENTRY_MODIFY);
                while (true) {
                    WatchKey key;
                    try {
                        key = watchService.take();
                    } catch (InterruptedException x) {
                        return;
                    }

                    for (WatchEvent<?> event : key.pollEvents()) {
                        WatchEvent.Kind<?> kind = event.kind();

                        if (kind == OVERFLOW) {
                            continue;
                        }

                        // Context for directory entry event is the file name of entry
                        WatchEvent<Path> ev = (WatchEvent<Path>) event;
                        Path filename = ev.context();

                        if (filename.toString().equals("index.html")) {

                            if (browser == null) {
                                System.out.println("Browser is null");

                            } else {
                                // File has been modified, reload it
                                String path = pathToWatch.toAbsolutePath().toString() + "/index.html";

                                browser.loadURL("file://" + path);

                                System.out.println("Reloaded index.html");
                            }


                        }
                    }

                    boolean valid = key.reset();
                    if (!valid) {
                        break;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
