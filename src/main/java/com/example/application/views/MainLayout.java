package com.example.application.views;

import com.example.application.security.SecurityService;
import com.example.application.views.list.ListView;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.HighlightConditions;
import com.vaadin.flow.router.RouterLink;


public class MainLayout extends AppLayout {
    private final SecurityService securityService;
    public MainLayout(SecurityService securityService) {
        this.securityService = securityService;
        createHeader();
        createDrawer();
    }
    private void createHeader(){
        Button logout = new Button("Log out", e -> securityService.logout());

        // Add a FlexLayout wrapper for the element you want to right align
        FlexLayout logoutButtonWrapper = new FlexLayout(logout);
        // Set the wrapper flex alignment to end (right)
        logoutButtonWrapper.setJustifyContentMode(FlexComponent.JustifyContentMode.END);

        HorizontalLayout header = new HorizontalLayout(new DrawerToggle(), logoutButtonWrapper);
        header.expand(logoutButtonWrapper);
//        header.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
//        header.addClassNames("py-0","px-m");
        addToNavbar(header);
    }
    private void createDrawer(){
        RouterLink listView = new RouterLink("List", ListView.class);
        listView.setHighlightCondition(HighlightConditions.sameLocation());
        addToDrawer(new VerticalLayout(listView));
    }

}
