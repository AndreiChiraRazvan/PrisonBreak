
package com.example.penitenciarv1.Interfaces;

import com.example.penitenciarv1.HelloApplication;
import com.example.penitenciarv1.Listeners.DynamicScalingAppDailySchedule;
import com.example.penitenciarv1.Listeners.DynamicScalingAppWardenSchedulesPrisoner;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class WardenDashboard extends Application {

    private VBox contentArea; // Reference to the main content area for dynamic updates

    @Override
    public void start(Stage primaryStage) {
        // Root Layout
        BorderPane rootLayout = new BorderPane();
        rootLayout.setStyle("-fx-background-color: #f3f4f6;");

        // Header Section
        HBox headerPane = createHeader();
        rootLayout.setTop(headerPane);

        // Sidebar Navigation
        VBox sidebar = createSidebar(primaryStage);
        rootLayout.setLeft(sidebar);

        // Main Content Area
        contentArea = createContentArea();
        rootLayout.setCenter(contentArea);

        // Footer Section
        StackPane footerPane = createFooter();
        rootLayout.setBottom(footerPane);

        // Scene and Stage Setup
        Scene scene = new Scene(rootLayout, 1100, 700);
        primaryStage.setTitle("Warden Dashboard");
        primaryStage.setScene(scene);
        primaryStage.setResizable(true);
        primaryStage.show();
    }

    private HBox createHeader() {
        HBox headerPane = new HBox();
        headerPane.setAlignment(Pos.CENTER);
        headerPane.setPadding(new Insets(20));
        headerPane.setStyle("-fx-background-color: linear-gradient(to right, #6c5ce7, #0984e3);");

        VBox headerContent = new VBox(5);
        headerContent.setAlignment(Pos.CENTER);

        Text headerTitle = new Text("Warden Dashboard");
        headerTitle.setFont(Font.font("Arial", 36));
        headerTitle.setStyle("-fx-font-weight: bold; -fx-fill: #ffffff;");

        Text headerSubtitle = new Text("Streamline and manage prison operations with ease");
        headerSubtitle.setFont(Font.font("Arial", 16));
        headerSubtitle.setStyle("-fx-fill: #ffffff;");

        headerContent.getChildren().addAll(headerTitle, headerSubtitle);
        headerPane.getChildren().add(headerContent);

        return headerPane;
    }

    private VBox createSidebar(Stage primaryStage) {
        VBox sidebar = new VBox(20);
        sidebar.setPadding(new Insets(15));
        sidebar.setAlignment(Pos.TOP_CENTER);
        sidebar.setStyle("-fx-background-color: #2d3436; -fx-border-radius: 12; -fx-background-radius: 12;");

        // Sidebar Buttons
        Button inmatesButton = createSidebarButton("Manage Inmates");
        Button guardsButton = createSidebarButton("Manage Guards");
        Button cellsButton = createSidebarButton("Manage Cells");
        Button schedulesButton = createSidebarButton("Schedules");
        Button reportsButton = createSidebarButton("Reports");
        Button logoutButton = createSidebarButton("Logout");

        // Button Actions
        inmatesButton.setOnAction(e -> updateContentArea("Manage Inmates", "Here you can manage inmate records and details."));
        guardsButton.setOnAction(e -> updateContentArea("Manage Guards", "Here you can assign duties and manage guards."));
        cellsButton.setOnAction(e -> updateContentArea("Manage Cells", "View and manage cell allocations and maintenance."));
        schedulesButton.setOnAction(e -> {
            contentArea.getChildren().clear(); // Golește zona centrală

            // Creează instanța din DynamicScalingAppWardenSchedulesPrisoner
            DynamicScalingAppWardenSchedulesPrisoner dynamicApp = new DynamicScalingAppWardenSchedulesPrisoner();

            // Obține întreaga interfață folosind metoda getFullInterface()
            AnchorPane fullInterface = dynamicApp.getContent();

            // Adaugă interfața în contentArea
            contentArea.getChildren().add(fullInterface);
        });



        reportsButton.setOnAction(e -> updateContentArea("Reports", "Generate and view operational reports."));
        logoutButton.setOnAction(e -> {
            primaryStage.close(); // Close the current window
            HelloApplication loginInterface = new HelloApplication();
            Stage loginStage = new Stage();
            try {
                loginInterface.start(loginStage);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        reportsButton.setOnAction(e -> updateContentArea("Reports", "Generate and view operational reports."));
        logoutButton.setOnAction(e -> {
            primaryStage.close(); // Close the current window
            HelloApplication loginInterface = new HelloApplication();
            Stage loginStage = new Stage();
            try {
                loginInterface.start(loginStage);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        sidebar.getChildren().addAll(inmatesButton, guardsButton, cellsButton, schedulesButton, reportsButton, logoutButton);
        return sidebar;
    }

    private Button createSidebarButton(String text) {
        Button button = new Button(text);
        button.setStyle(
                "-fx-background-color: #6c5ce7; " +
                        "-fx-text-fill: white; " +
                        "-fx-font-size: 16; " +
                        "-fx-padding: 10 20; " +
                        "-fx-border-radius: 20; " +
                        "-fx-background-radius: 20;" +
                        "-fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.3), 10, 0, 2, 2);" +
                        "-fx-transition: all 0.3s ease;"
        );

        button.setOnMouseEntered(e -> button.setStyle(
                "-fx-background-color: #00cec9; " +
                        "-fx-text-fill: white; " +
                        "-fx-font-size: 16; " +
                        "-fx-padding: 10 20; " +
                        "-fx-border-radius: 20; " +
                        "-fx-background-radius: 20;" +
                        "-fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.4), 12, 0, 3, 3);" +
                        "-fx-transition: all 0.3s ease;"
        ));

        button.setOnMouseExited(e -> button.setStyle(
                "-fx-background-color: #6c5ce7; " +
                        "-fx-text-fill: white; " +
                        "-fx-font-size: 16; " +
                        "-fx-padding: 10 20; " +
                        "-fx-border-radius: 20; " +
                        "-fx-background-radius: 20;" +
                        "-fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.3), 10, 0, 2, 2);" +
                        "-fx-transition: all 0.3s ease;"
        ));
        return button;
    }

    private VBox createContentArea() {
        VBox contentArea = new VBox(20);
        contentArea.setPadding(new Insets(20));
        contentArea.setAlignment(Pos.TOP_CENTER);
        contentArea.setStyle("-fx-background-color: #ffffff; -fx-border-radius: 12; -fx-background-radius: 12; -fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.1), 10, 0, 2, 2);");

        Text welcomeText = new Text("Welcome to the Warden Dashboard!");
        welcomeText.setFont(Font.font("Arial", 24));
        welcomeText.setStyle("-fx-fill: #2d3436;");

        // Placeholder for cards or details
        Text placeholder = new Text("Select an option from the sidebar to view details.");
        placeholder.setFont(Font.font("Arial", 18));
        placeholder.setStyle("-fx-fill: #636e72;");

        contentArea.getChildren().addAll(welcomeText, placeholder);
        return contentArea;
    }

    private void updateContentArea(String title, String description) {
        contentArea.getChildren().clear();

        Text sectionTitle = new Text(title);
        sectionTitle.setFont(Font.font("Arial", 28));
        sectionTitle.setStyle("-fx-fill: #2d3436; -fx-font-weight: bold;");

        Text sectionDescription = new Text(description);
        sectionDescription.setFont(Font.font("Arial", 18));
        sectionDescription.setStyle("-fx-fill: #636e72;");

        contentArea.getChildren().addAll(sectionTitle, sectionDescription);
    }

    private StackPane createFooter() {
        StackPane footerPane = new StackPane();
        footerPane.setPadding(new Insets(10));
        footerPane.setStyle("-fx-background-color: #2d3436;");

        Text footer = new Text("© 2025 Penitenciar Management System | All rights reserved.");
        footer.setFont(Font.font("Arial", 14));
        footer.setStyle("-fx-fill: #ffffff;");

        footerPane.getChildren().add(footer);
        return footerPane;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
