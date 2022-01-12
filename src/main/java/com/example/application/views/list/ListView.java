package com.example.application.views.list;

import com.example.application.data.entity.Apiinfo;
import com.example.application.data.service.ApiService;
import com.example.application.views.MainLayout;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("API Data | Data Tool")
@Route(value = "", layout = MainLayout.class)
public class ListView extends VerticalLayout {
    Grid<Apiinfo> grid=new Grid<>(Apiinfo.class);
    TextField filterText= new TextField();
    ApiForm form;
    ApiService service;

    public ListView(ApiService service) {
       this.service=service;
       addClassName("list-view");
       setSizeFull();
       configureGrid();
       configureForm();
       add(getToolbar(),getContent());
       updateList();
       closeEditor();
    }
    private void closeEditor(){
        form.setApiinfo(null);
        form.setVisible(false);
        removeClassName("editing");
    }

    private void updateList(){
        grid.setItems(service.findAllApiinfos(filterText.getValue()));
    }
    private Component getContent(){
        VerticalLayout content= new VerticalLayout(grid,form);
        content.setFlexGrow(2,grid);
        content.setFlexGrow(1,form);
        content.addClassName("content");
        content.setSizeFull();
        return content;
    }
    private void configureForm(){
        form = new ApiForm(service.findAllApitypes());
        form.addListener(ApiForm.SaveEvent.class,this::saveApiinfo);
        form.addListener(ApiForm.DeleteEvent.class,this::deleteApiinfo);
        form.addListener(ApiForm.CloseEvent.class, e -> closeEditor());
    }
    private void saveApiinfo(ApiForm.SaveEvent event){
        service.saveApiinfo(event.getApiinfo());
        updateList();
        closeEditor();
    }

    private void deleteApiinfo(ApiForm.DeleteEvent event){
        service.deleteApiinfo(event.getApiinfo());
        updateList();
        closeEditor();
    }
    private Component getToolbar(){
        filterText.setPlaceholder("Type API Name...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        filterText.addValueChangeListener(e -> updateList());
        Button addButton=new Button("Add API Info");
        addButton.addClickListener(e->addApiinfo());
        HorizontalLayout toolbar= new HorizontalLayout(filterText,addButton);
        toolbar.addClassName("toolbar");
        return toolbar;
    }

    private void addApiinfo(){
        grid.asSingleSelect().clear();
        editApiinfo(new Apiinfo());
    }
    public void configureGrid(){
        grid.addClassName("configure-grid");
        grid.setSizeFull();
        grid.setColumns("apiName","purpose","request","response");
        grid.addColumn(Apiinfo -> Apiinfo.getApitype().getApitypename()).setHeader("Api_Type");
        grid.getColumns().forEach(col->col.setAutoWidth(true));
        grid.asSingleSelect().addValueChangeListener(e -> editApiinfo(e.getValue()));
    }
    private void editApiinfo(Apiinfo apiinfo){
        if(apiinfo==null){
            closeEditor();
        } else{
            form.setApiinfo(apiinfo);
            form.setVisible(true);
            addClassName("editing");
        }
    }

}
